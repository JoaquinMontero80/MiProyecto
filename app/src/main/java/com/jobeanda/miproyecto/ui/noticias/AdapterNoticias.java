package com.jobeanda.miproyecto.ui.noticias;

import android.content.Context;
import android.content.Intent;
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
import com.squareup.picasso.Picasso;
import java.util.ArrayList;



public class AdapterNoticias extends RecyclerView.Adapter<AdapterNoticias.MyViewHolder>
{

    // ArrayList donde recojo las noticias
    ArrayList<Noticia> noticias;
    Context contexto;

    // Constructor
    public AdapterNoticias(ArrayList<Noticia> noticias, Context contexto) {
        this.noticias = noticias;
        this.contexto = contexto;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Creo una vista para tener acceso al layout fragment_noticias
        View view = LayoutInflater.from(contexto).inflate(R.layout.fragment_noticias, parent, false);
        // Instancio el ViewHolder
        MyViewHolder holder = new MyViewHolder(view);
        // Devuelve el ViewHolder
        return holder;
    }

    // En este metodo vamos a manipular las noticias actuales, cambiamos el contenido de la noticia desde aqui
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Obtiene la noticia que tiene el constructor
        final Noticia actual = noticias.get(position);
        holder.titulo.setText(actual.getTitulo());

        // Creo un metodo para corregir el etiquetado de la descripcion
        //holder.descripcion.setText(corregirDescripcion(actual.getDescripcion()));
//        holder.descripcion.setText(actual.getDescripcion());

        // Creo un metodo para corregir el etiquetado de la fecha
        holder.fecha.setText(corregirFecha(actual.getFecha()));
        //holder.fecha.setText(actual.getFecha());

        // Para la imagen uso la libreria Picasso Gradle( implementation 'com.squareup.picasso:picasso:(insert latest version)' )
        // https://square.github.io/picasso/
        // Carga el enlace de la imagen
        Picasso.with(contexto).load(actual.getUrl_imagen()).into(holder.imagen);

        // Cuando pincha boton ver noticia, abre la web en segundo plano
        holder.appcompatbutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                // Carga la nueva actividad ( calse Detalles - activity_detalles.xml )
                Intent intent = new Intent(contexto, Detalles.class);
                // Enlace a la web de la noticia
                intent.putExtra("Enlace", actual.getEnlace());
                contexto.startActivity(intent);
            }
        });

        // Desde la imagen va a tener un enlace a la web al hacer click
        // Para ello creo una activity ( basic ) ( Detalles - activity_detalles.xml )
        holder.imagen.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // Carga la nueva actividad ( calse Detalles - activity_detalles.xml )
                Intent intent = new Intent(contexto, Detalles.class);
                // Enlace a la web de la noticia
                intent.putExtra("Enlace", actual.getEnlace());
                contexto.startActivity(intent);
            }
        });

        // Cuando pulsa el boton de compartir, puede compartir la noticia con un Intent explicito
        holder.botonCompartir.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // Es necesario un intent que levante la actividad deseada
                Intent sendIntent = new Intent();
                // La accion es un envio a una aplicacion externa
                sendIntent.setAction(Intent.ACTION_SEND);
                // Enlace de la noticia que vamos a enviar
                sendIntent.putExtra(Intent.EXTRA_TEXT, actual.getEnlace());
                // Se va a enviar un texto plano
                sendIntent.setType("text/plain");

                // manda mensaje si no tiene aplicacion con la que compartir en el sistema
                Intent shareIntent = Intent.createChooser(sendIntent, "no tengo aplicacion para compartir en emulador");
                // Inicia la actividad compartir enlace de la noticia
                contexto.startActivity(shareIntent);

            }
        });


    }

    // Devuelve el numero de noticias que tiene el ArrayList()
    @Override
    public int getItemCount() {
        return noticias.size();
    }

    // Metodo corrige etiquetado de la descripcion
    public String corregirDescripcion(String s)
    {
        // "s" es lo que le esta entrando por parametros
        String descripcionOriginal = s;

        String separador = "/>";
        // Array de String que divide en partes la etiqueta y el texto
        String[] partes = descripcionOriginal.split(separador);

        // Devuelve parte [1]
        String devolver = partes[1];

        // devuelve la parte que me interesa ( posicion 1 del array )
        return devolver;
    }

    // Corrige fecha
    public String corregirFecha(String s)
    {
        // "s" es lo que le esta entrando por parametros
        String descripcionOriginal = s;

        String separador = "0200";
        // Array de String que divide en partes la etiqueta y el texto
        String[] partes = descripcionOriginal.split(separador);

        // Devuelve parte [1]
        String devolver = partes[0];

        // devuelve la parte que me interesa ( posicion 1 del array )
        return devolver;
    }
//  =========================================================================================
    // Clase ViewHolder
    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        // Referencia a las variables del CardView ( mostradas en RecyclerView )
        TextView titulo, descripcion, fecha;
        ImageView imagen, botonCompartir;
        AppCompatButton appcompatbutton;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            // Busca en R cada id ( hay que pasarle el itemView de la vista del Layout que recibe la clase como parametro
            titulo = (TextView) itemView.findViewById(R.id.titulo);
           // descripcion = (TextView) itemView.findViewById(R.id.descripcion);
            fecha = (TextView) itemView.findViewById(R.id.fecha);
            imagen = (ImageView) itemView.findViewById(R.id.imagen);
            appcompatbutton = (AppCompatButton)itemView.findViewById(R.id.appcompatbutton);
            botonCompartir = (ImageButton) itemView.findViewById(R.id.botonCompartir);
        }
    }
}
