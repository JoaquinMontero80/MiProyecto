package com.jobeanda.miproyecto.ui.newsApi;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.jobeanda.miproyecto.R;
import com.jobeanda.miproyecto.ui.noticias.Detalles;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.TimeZone;

public class NewsApiAdapter extends RecyclerView.Adapter<NewsApiAdapter.NewsApiViewHolder> {

    private final ArrayList<NewsArticle> articles;
    private final Context context;

    public NewsApiAdapter(ArrayList<NewsArticle> articles, Context context) {
        this.articles = articles;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsApiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_news_api, parent, false);
        return new NewsApiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsApiViewHolder holder, int position) {
        final NewsArticle article = articles.get(position);

        holder.titulo.setText(valorSeguro(article.getTitle(), "Sin título"));
        holder.descripcion.setText(valorSeguro(article.getDescription(), "Sin descripción"));
        holder.fuente.setText(article.getSource() != null ? valorSeguro(article.getSource().getName(), "Fuente desconocida") : "Fuente desconocida");
        holder.fecha.setText(formatearFecha(article.getPublishedAt()));

        if (!TextUtils.isEmpty(article.getUrlToImage())) {
            holder.imagen.setVisibility(View.VISIBLE);
            Picasso.with(context)
                    .load(article.getUrlToImage())
                    .placeholder(R.drawable.noticias)
                    .error(R.drawable.noticias)
                    .into(holder.imagen);
        } else {
            holder.imagen.setVisibility(View.GONE);
        }

        View.OnClickListener abrirDetalleListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(article.getUrl())) {
                    return;
                }
                Intent intent = new Intent(context, Detalles.class);
                intent.putExtra("Enlace", article.getUrl());
                context.startActivity(intent);
            }
        };

        holder.botonVer.setOnClickListener(abrirDetalleListener);
        holder.imagen.setOnClickListener(abrirDetalleListener);

        holder.botonCompartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_SUBJECT, valorSeguro(article.getTitle(), "Noticia"));
                sendIntent.putExtra(Intent.EXTRA_TEXT, valorSeguro(article.getTitle(), "") + "\n" + valorSeguro(article.getUrl(), ""));
                sendIntent.setType("text/plain");
                context.startActivity(Intent.createChooser(sendIntent, "Compartir noticia"));
            }
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    private String valorSeguro(String value, String fallback) {
        return TextUtils.isEmpty(value) ? fallback : value;
    }

    private String formatearFecha(String publishedAt) {
        if (TextUtils.isEmpty(publishedAt)) {
            return "Fecha no disponible";
        }

        try {
            SimpleDateFormat entrada = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
            entrada.setTimeZone(TimeZone.getTimeZone("UTC"));
            SimpleDateFormat salida = new SimpleDateFormat("dd/MM/yyyy HH:mm", new Locale("es", "ES"));
            return salida.format(entrada.parse(publishedAt));
        } catch (Exception e) {
            return publishedAt;
        }
    }

    static class NewsApiViewHolder extends RecyclerView.ViewHolder {
        TextView titulo;
        TextView descripcion;
        TextView fuente;
        TextView fecha;
        ImageView imagen;
        ImageButton botonCompartir;
        AppCompatButton botonVer;

        public NewsApiViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.tvNewsApiTitle);
            descripcion = itemView.findViewById(R.id.tvNewsApiDescription);
            fuente = itemView.findViewById(R.id.tvNewsApiSource);
            fecha = itemView.findViewById(R.id.tvNewsApiDate);
            imagen = itemView.findViewById(R.id.ivNewsApiImage);
            botonCompartir = itemView.findViewById(R.id.btnNewsApiShare);
            botonVer = itemView.findViewById(R.id.btnNewsApiOpen);
        }
    }
}
