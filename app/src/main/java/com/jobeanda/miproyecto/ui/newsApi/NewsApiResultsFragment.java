package com.jobeanda.miproyecto.ui.newsApi;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jobeanda.miproyecto.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsApiResultsFragment extends Fragment {

    public static final String ARG_MODO = "modo";
    public static final String ARG_QUERY = "query";
    public static final String ARG_CATEGORIA = "categoria";
    public static final String ARG_IDIOMA = "idioma";
    public static final String ARG_PAIS = "pais";
    public static final String ARG_SORTBY = "sortby";
    public static final String ARG_SEARCHIN = "searchin";
    public static final String ARG_SOURCES = "sources";
    public static final String ARG_DOMAINS = "domains";
    public static final String ARG_EXCLUDE_DOMAINS = "exclude_domains";
    public static final String ARG_FROM = "from";
    public static final String ARG_TO = "to";
    public static final String ARG_PAGE_SIZE = "page_size";

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private TextView tvEmpty;
    private TextView tvResumenFiltros;

    private NewsApiAdapter adapter;
    private final ArrayList<NewsArticle> articles = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news_api_results, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = view.findViewById(R.id.progressNewsApiResults);
        recyclerView = view.findViewById(R.id.recyclerNewsApiResults);
        tvEmpty = view.findViewById(R.id.tvNewsApiResultsEmpty);
        tvResumenFiltros = view.findViewById(R.id.tvNewsApiResumenFiltros);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new NewsApiAdapter(articles, requireContext());
        recyclerView.setAdapter(adapter);

        renderResumenFiltros();
        cargarNoticias();
    }

    private void renderResumenFiltros() {
        Bundle args = getArguments();
        if (args == null) {
            tvResumenFiltros.setVisibility(View.GONE);
            return;
        }

        String modo = args.getString(ARG_MODO, "top-headlines");
        ArrayList<String> partes = new ArrayList<>();
        partes.add("Modo: " + modo);

        addParte(partes, "q", args.getString(ARG_QUERY));

        if ("everything".equals(modo)) {
            addParte(partes, "idioma", args.getString(ARG_IDIOMA));
            addParte(partes, "sortBy", args.getString(ARG_SORTBY));
            addParte(partes, "searchIn", args.getString(ARG_SEARCHIN));
            addParte(partes, "from", args.getString(ARG_FROM));
            addParte(partes, "to", args.getString(ARG_TO));
            addParte(partes, "sources", args.getString(ARG_SOURCES));
            addParte(partes, "domains", args.getString(ARG_DOMAINS));
            addParte(partes, "excludeDomains", args.getString(ARG_EXCLUDE_DOMAINS));
        } else {
            addParte(partes, "país", args.getString(ARG_PAIS));
            addParte(partes, "categoría", args.getString(ARG_CATEGORIA));
        }

        addParte(partes, "pageSize", args.getString(ARG_PAGE_SIZE));
        tvResumenFiltros.setText(TextUtils.join("  •  ", partes));
        tvResumenFiltros.setVisibility(View.VISIBLE);
    }

    private void addParte(ArrayList<String> partes, String label, String value) {
        if (!TextUtils.isEmpty(value)) {
            partes.add(label + ": " + value);
        }
    }

    private void cargarNoticias() {
        progressBar.setVisibility(View.VISIBLE);
        tvEmpty.setVisibility(View.GONE);

        Bundle args = getArguments();
        if (args == null) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(requireContext(), "No se recibieron filtros para la búsqueda.", Toast.LENGTH_LONG).show();
            mostrarEstadoVacio();
            return;
        }

        NewsApiService service = RetrofitClient.getClient().create(NewsApiService.class);
        Call<NewsApiResponse> call;

        String modo = emptyToNull(args.getString(ARG_MODO, "top-headlines"));
        Integer pageSize = parsePageSize(args.getString(ARG_PAGE_SIZE, "20"));

        if ("everything".equals(modo)) {
            call = service.getEverything(
                    emptyToNull(args.getString(ARG_QUERY)),
                    emptyToNull(args.getString(ARG_SEARCHIN)),
                    emptyToNull(args.getString(ARG_SOURCES)),
                    emptyToNull(args.getString(ARG_DOMAINS)),
                    emptyToNull(args.getString(ARG_EXCLUDE_DOMAINS)),
                    emptyToNull(args.getString(ARG_FROM)),
                    emptyToNull(args.getString(ARG_TO)),
                    emptyToNull(args.getString(ARG_IDIOMA)),
                    emptyToNull(args.getString(ARG_SORTBY)),
                    pageSize,
                    1,
                    NewsApiConfig.API_KEY
            );
        } else {
            call = service.getTopHeadlines(
                    emptyToNull(args.getString(ARG_PAIS)),
                    emptyToNull(args.getString(ARG_CATEGORIA)),
                    emptyToNull(args.getString(ARG_QUERY)),
                    pageSize,
                    1,
                    NewsApiConfig.API_KEY
            );
        }

        call.enqueue(new Callback<NewsApiResponse>() {
            @Override
            public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {
                progressBar.setVisibility(View.GONE);

                if (!response.isSuccessful() || response.body() == null) {
                    Toast.makeText(requireContext(), "Error al cargar noticias", Toast.LENGTH_SHORT).show();
                    mostrarEstadoVacio();
                    return;
                }

                NewsApiResponse body = response.body();
                if (!"ok".equalsIgnoreCase(body.getStatus())) {
                    Toast.makeText(requireContext(), "Error: " + body.getMessage(), Toast.LENGTH_LONG).show();
                    mostrarEstadoVacio();
                    return;
                }

                articles.clear();
                if (body.getArticles() != null) {
                    for (NewsArticle article : body.getArticles()) {
                        if (!TextUtils.isEmpty(article.getUrl())) {
                            articles.add(article);
                        }
                    }
                }

                adapter.notifyDataSetChanged();

                if (articles.isEmpty()) {
                    mostrarEstadoVacio();
                } else {
                    tvEmpty.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<NewsApiResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(requireContext(), "Fallo de red: " + t.getMessage(), Toast.LENGTH_LONG).show();
                mostrarEstadoVacio();
            }
        });
    }

    private void mostrarEstadoVacio() {
        articles.clear();
        adapter.notifyDataSetChanged();
        tvEmpty.setVisibility(View.VISIBLE);
    }

    private String emptyToNull(String value) {
        return TextUtils.isEmpty(value) ? null : value.trim();
    }

    private Integer parsePageSize(String value) {
        if (TextUtils.isEmpty(value)) {
            return 20;
        }
        try {
            int number = Integer.parseInt(value);
            if (number < 1) return 20;
            if (number > 100) return 100;
            return number;
        } catch (Exception e) {
            return 20;
        }
    }
}
