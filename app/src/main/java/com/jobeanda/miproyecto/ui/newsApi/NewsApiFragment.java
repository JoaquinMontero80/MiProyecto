package com.jobeanda.miproyecto.ui.newsApi;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
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

public class NewsApiFragment extends Fragment {

    private Spinner spinnerModo;
    private Spinner spinnerCategoria;
    private Spinner spinnerIdioma;
    private Spinner spinnerPais;
    private Spinner spinnerSortBy;
    private Spinner spinnerSearchIn;
    private EditText etQuery;
    private EditText etSources;
    private EditText etDomains;
    private EditText etExcludeDomains;
    private EditText etFrom;
    private EditText etTo;
    private EditText etPageSize;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private TextView tvEmpty;
    private Button btnAplicar;
    private Button btnLimpiar;

    private NewsApiAdapter adapter;
    private final ArrayList<NewsArticle> articles = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news_api, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        setupSpinners();
        setupRecycler();
        setupActions();
        cargarNoticias();
    }

    private void initViews(View view) {
        spinnerModo = view.findViewById(R.id.spinnerModo);
        spinnerCategoria = view.findViewById(R.id.spinnerCategoria);
        spinnerIdioma = view.findViewById(R.id.spinnerIdioma);
        spinnerPais = view.findViewById(R.id.spinnerPais);
        spinnerSortBy = view.findViewById(R.id.spinnerSortBy);
        spinnerSearchIn = view.findViewById(R.id.spinnerSearchIn);
        etQuery = view.findViewById(R.id.etQuery);
        etSources = view.findViewById(R.id.etSources);
        etDomains = view.findViewById(R.id.etDomains);
        etExcludeDomains = view.findViewById(R.id.etExcludeDomains);
        etFrom = view.findViewById(R.id.etFrom);
        etTo = view.findViewById(R.id.etTo);
        etPageSize = view.findViewById(R.id.etPageSize);
        progressBar = view.findViewById(R.id.progressNewsApi);
        recyclerView = view.findViewById(R.id.recyclerNewsApi);
        tvEmpty = view.findViewById(R.id.tvNewsApiEmpty);
        btnAplicar = view.findViewById(R.id.btnAplicarFiltros);
        btnLimpiar = view.findViewById(R.id.btnLimpiarFiltros);
    }

    private void setupSpinners() {
        setSpinnerItems(spinnerModo, new String[]{"top-headlines", "everything"});
        setSpinnerItems(spinnerCategoria, new String[]{"", "business", "entertainment", "general", "health", "science", "sports", "technology"});
        setSpinnerItems(spinnerIdioma, new String[]{"", "ar", "de", "en", "es", "fr", "he", "it", "nl", "no", "pt", "ru", "sv", "ud", "zh"});
        setSpinnerItems(spinnerPais, new String[]{"", "ae", "ar", "at", "au", "be", "bg", "br", "ca", "ch", "cn", "co", "cu", "cz", "de", "eg", "fr", "gb", "gr", "hk", "hu", "id", "ie", "il", "in", "it", "jp", "kr", "lt", "lv", "ma", "mx", "my", "ng", "nl", "no", "nz", "ph", "pl", "pt", "ro", "rs", "ru", "sa", "se", "sg", "si", "sk", "th", "tr", "tw", "ua", "us", "ve", "za"});
        setSpinnerItems(spinnerSortBy, new String[]{"", "publishedAt", "relevancy", "popularity"});
        setSpinnerItems(spinnerSearchIn, new String[]{"", "title", "description", "content", "title,description", "title,content", "description,content", "title,description,content"});

        spinnerModo.setSelection(0);
        spinnerPais.setSelection(findPosition(spinnerPais, "us"));
        spinnerCategoria.setSelection(findPosition(spinnerCategoria, "general"));
        spinnerIdioma.setSelection(findPosition(spinnerIdioma, "es"));
        spinnerSortBy.setSelection(findPosition(spinnerSortBy, "publishedAt"));
        etPageSize.setText("20");
    }

    private void setupRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new NewsApiAdapter(articles, requireContext());
        recyclerView.setAdapter(adapter);
    }

    private void setupActions() {
        btnAplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarNoticias();
            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiarFiltros();
            }
        });

        spinnerModo.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, View view, int position, long id) {
                actualizarVisibilidadSegunModo();
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {
            }
        });
    }

    private void actualizarVisibilidadSegunModo() {
        boolean esEverything = "everything".equals(getSelectedValue(spinnerModo));
        spinnerCategoria.setEnabled(!esEverything);
        spinnerPais.setEnabled(!esEverything);
        spinnerIdioma.setEnabled(esEverything);
        spinnerSearchIn.setEnabled(esEverything);
        etSources.setEnabled(esEverything);
        etDomains.setEnabled(esEverything);
        etExcludeDomains.setEnabled(esEverything);
        etFrom.setEnabled(esEverything);
        etTo.setEnabled(esEverything);
    }

    private void limpiarFiltros() {
        etQuery.setText("");
        etSources.setText("");
        etDomains.setText("");
        etExcludeDomains.setText("");
        etFrom.setText("");
        etTo.setText("");
        etPageSize.setText("20");
        spinnerModo.setSelection(0);
        spinnerCategoria.setSelection(findPosition(spinnerCategoria, "general"));
        spinnerIdioma.setSelection(findPosition(spinnerIdioma, "es"));
        spinnerPais.setSelection(findPosition(spinnerPais, "us"));
        spinnerSortBy.setSelection(findPosition(spinnerSortBy, "publishedAt"));
        spinnerSearchIn.setSelection(0);
        actualizarVisibilidadSegunModo();
        cargarNoticias();
    }

    private void cargarNoticias() {
        progressBar.setVisibility(View.VISIBLE);
        tvEmpty.setVisibility(View.GONE);

        NewsApiService service = RetrofitClient.getClient().create(NewsApiService.class);
        Call<NewsApiResponse> call;

        String modo = getSelectedValue(spinnerModo);
        Integer pageSize = parsePageSize(etPageSize.getText().toString().trim());

        if ("everything".equals(modo)) {
            call = service.getEverything(
                    emptyToNull(etQuery.getText().toString()),
                    emptyToNull(getSelectedValue(spinnerSearchIn)),
                    emptyToNull(etSources.getText().toString()),
                    emptyToNull(etDomains.getText().toString()),
                    emptyToNull(etExcludeDomains.getText().toString()),
                    emptyToNull(etFrom.getText().toString()),
                    emptyToNull(etTo.getText().toString()),
                    emptyToNull(getSelectedValue(spinnerIdioma)),
                    emptyToNull(getSelectedValue(spinnerSortBy)),
                    pageSize,
                    1,
                    NewsApiConfig.API_KEY
            );
        } else {
            call = service.getTopHeadlines(
                    emptyToNull(getSelectedValue(spinnerPais)),
                    emptyToNull(getSelectedValue(spinnerCategoria)),
                    emptyToNull(etQuery.getText().toString()),
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

    private void setSpinnerItems(Spinner spinner, String[] items) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private String getSelectedValue(Spinner spinner) {
        Object value = spinner.getSelectedItem();
        return value == null ? "" : value.toString().trim();
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

    private int findPosition(Spinner spinner, String value) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (value.equals(spinner.getItemAtPosition(i).toString())) {
                return i;
            }
        }
        return 0;
    }
}
