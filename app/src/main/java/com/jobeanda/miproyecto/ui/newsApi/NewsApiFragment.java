package com.jobeanda.miproyecto.ui.newsApi;

<<<<<<< HEAD
import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
=======
import android.os.Bundle;
import android.text.TextUtils;
>>>>>>> ff8deb57e68caa0f86afd272c6e8bd7a5ba5cb22
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
<<<<<<< HEAD
import android.widget.LinearLayout;
=======
>>>>>>> ff8deb57e68caa0f86afd272c6e8bd7a5ba5cb22
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

<<<<<<< HEAD
import com.google.android.material.chip.ChipGroup;
import com.jobeanda.miproyecto.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
=======
import com.jobeanda.miproyecto.R;

import java.util.ArrayList;
>>>>>>> ff8deb57e68caa0f86afd272c6e8bd7a5ba5cb22

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsApiFragment extends Fragment {

<<<<<<< HEAD
    private static final String PREFS_NAME = "newsapi_filters";
    private static final String KEY_MODO = "modo";
    private static final String KEY_QUERY = "query";
    private static final String KEY_CATEGORIA = "categoria";
    private static final String KEY_IDIOMA = "idioma";
    private static final String KEY_PAIS = "pais";
    private static final String KEY_SORTBY = "sortby";
    private static final String KEY_SEARCHIN = "searchin";
    private static final String KEY_SOURCES = "sources";
    private static final String KEY_DOMAINS = "domains";
    private static final String KEY_EXCLUDE_DOMAINS = "exclude_domains";
    private static final String KEY_FROM = "from";
    private static final String KEY_TO = "to";
    private static final String KEY_PAGE_SIZE = "page_size";

=======
>>>>>>> ff8deb57e68caa0f86afd272c6e8bd7a5ba5cb22
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
<<<<<<< HEAD
    private TextView tvValidation;
    private Button btnAplicar;
    private Button btnLimpiar;
    private LinearLayout layoutTopHeadlines;
    private LinearLayout layoutEverything;

    private ChipGroup chipGroupModo;
    private ChipGroup chipGroupCategoria;
    private ChipGroup chipGroupPais;
    private ChipGroup chipGroupIdioma;
    private ChipGroup chipGroupSortBy;

    private NewsApiAdapter adapter;
    private final ArrayList<NewsArticle> articles = new ArrayList<>();
    private final SimpleDateFormat apiDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
=======
    private Button btnAplicar;
    private Button btnLimpiar;

    private NewsApiAdapter adapter;
    private final ArrayList<NewsArticle> articles = new ArrayList<>();
>>>>>>> ff8deb57e68caa0f86afd272c6e8bd7a5ba5cb22

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
<<<<<<< HEAD
        setupDatePickers();
        setupActions();
        restoreFilters();
        actualizarVisibilidadSegunModo();
        validarFiltros(false);
=======
        setupActions();
>>>>>>> ff8deb57e68caa0f86afd272c6e8bd7a5ba5cb22
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
<<<<<<< HEAD
        tvValidation = view.findViewById(R.id.tvValidationMessage);
        btnAplicar = view.findViewById(R.id.btnAplicarFiltros);
        btnLimpiar = view.findViewById(R.id.btnLimpiarFiltros);
        layoutTopHeadlines = view.findViewById(R.id.layoutTopHeadlines);
        layoutEverything = view.findViewById(R.id.layoutEverything);
        chipGroupModo = view.findViewById(R.id.chipGroupModo);
        chipGroupCategoria = view.findViewById(R.id.chipGroupCategoria);
        chipGroupPais = view.findViewById(R.id.chipGroupPais);
        chipGroupIdioma = view.findViewById(R.id.chipGroupIdioma);
        chipGroupSortBy = view.findViewById(R.id.chipGroupSortBy);
=======
        btnAplicar = view.findViewById(R.id.btnAplicarFiltros);
        btnLimpiar = view.findViewById(R.id.btnLimpiarFiltros);
>>>>>>> ff8deb57e68caa0f86afd272c6e8bd7a5ba5cb22
    }

    private void setupSpinners() {
        setSpinnerItems(spinnerModo, new String[]{"top-headlines", "everything"});
        setSpinnerItems(spinnerCategoria, new String[]{"", "business", "entertainment", "general", "health", "science", "sports", "technology"});
        setSpinnerItems(spinnerIdioma, new String[]{"", "ar", "de", "en", "es", "fr", "he", "it", "nl", "no", "pt", "ru", "sv", "ud", "zh"});
<<<<<<< HEAD
        setSpinnerItems(spinnerPais, new String[]{"", "ae", "ar", "at", "au", "be", "bg", "br", "ca", "ch", "cn", "co", "cu", "cz", "de", "eg", "es", "fr", "gb", "gr", "hk", "hu", "id", "ie", "il", "in", "it", "jp", "kr", "lt", "lv", "ma", "mx", "my", "ng", "nl", "no", "nz", "ph", "pl", "pt", "ro", "rs", "ru", "sa", "se", "sg", "si", "sk", "th", "tr", "tw", "ua", "us", "ve", "za"});
        setSpinnerItems(spinnerSortBy, new String[]{"", "publishedAt", "relevancy", "popularity"});
        setSpinnerItems(spinnerSearchIn, new String[]{"", "title", "description", "content", "title,description", "title,content", "description,content", "title,description,content"});

        spinnerModo.setSelection(findPosition(spinnerModo, "top-headlines"));
        spinnerPais.setSelection(findPosition(spinnerPais, "es"));
        spinnerCategoria.setSelection(findPosition(spinnerCategoria, "general"));
        spinnerIdioma.setSelection(findPosition(spinnerIdioma, "es"));
        spinnerSortBy.setSelection(findPosition(spinnerSortBy, "publishedAt"));
        spinnerSearchIn.setSelection(0);
=======
        setSpinnerItems(spinnerPais, new String[]{"", "ae", "ar", "at", "au", "be", "bg", "br", "ca", "ch", "cn", "co", "cu", "cz", "de", "eg", "fr", "gb", "gr", "hk", "hu", "id", "ie", "il", "in", "it", "jp", "kr", "lt", "lv", "ma", "mx", "my", "ng", "nl", "no", "nz", "ph", "pl", "pt", "ro", "rs", "ru", "sa", "se", "sg", "si", "sk", "th", "tr", "tw", "ua", "us", "ve", "za"});
        setSpinnerItems(spinnerSortBy, new String[]{"", "publishedAt", "relevancy", "popularity"});
        setSpinnerItems(spinnerSearchIn, new String[]{"", "title", "description", "content", "title,description", "title,content", "description,content", "title,description,content"});

        spinnerModo.setSelection(0);
        spinnerPais.setSelection(findPosition(spinnerPais, "us"));
        spinnerCategoria.setSelection(findPosition(spinnerCategoria, "general"));
        spinnerIdioma.setSelection(findPosition(spinnerIdioma, "es"));
        spinnerSortBy.setSelection(findPosition(spinnerSortBy, "publishedAt"));
>>>>>>> ff8deb57e68caa0f86afd272c6e8bd7a5ba5cb22
        etPageSize.setText("20");
    }

    private void setupRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new NewsApiAdapter(articles, requireContext());
        recyclerView.setAdapter(adapter);
    }

<<<<<<< HEAD
    private void setupDatePickers() {
        etFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker(etFrom);
            }
        });

        etTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker(etTo);
            }
        });
    }

    private void showDatePicker(final EditText target) {
        Calendar calendar = Calendar.getInstance();
        String currentValue = target.getText().toString().trim();

        if (!TextUtils.isEmpty(currentValue)) {
            try {
                calendar.setTime(apiDateFormat.parse(currentValue));
            } catch (Exception ignored) {
            }
        }

        DatePickerDialog dialog = new DatePickerDialog(
                requireContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar selected = Calendar.getInstance();
                        selected.set(Calendar.YEAR, year);
                        selected.set(Calendar.MONTH, month);
                        selected.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        target.setText(apiDateFormat.format(selected.getTime()));
                        validarFiltros(false);
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        dialog.show();
    }

=======
>>>>>>> ff8deb57e68caa0f86afd272c6e8bd7a5ba5cb22
    private void setupActions() {
        btnAplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
<<<<<<< HEAD
                if (!validarFiltros(true)) {
                    return;
                }
                guardarFiltros();
=======
>>>>>>> ff8deb57e68caa0f86afd272c6e8bd7a5ba5cb22
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
<<<<<<< HEAD
                sincronizarChipsConSpinnerModo();
                actualizarVisibilidadSegunModo();
                validarFiltros(false);
=======
                actualizarVisibilidadSegunModo();
>>>>>>> ff8deb57e68caa0f86afd272c6e8bd7a5ba5cb22
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {
            }
        });
<<<<<<< HEAD

        spinnerCategoria.setOnItemSelectedListener(simpleItemSelectedListener(new Runnable() {
            @Override
            public void run() {
                sincronizarChipCategoria();
                validarFiltros(false);
            }
        }));

        spinnerPais.setOnItemSelectedListener(simpleItemSelectedListener(new Runnable() {
            @Override
            public void run() {
                sincronizarChipPais();
                validarFiltros(false);
            }
        }));

        spinnerIdioma.setOnItemSelectedListener(simpleItemSelectedListener(new Runnable() {
            @Override
            public void run() {
                sincronizarChipIdioma();
                validarFiltros(false);
            }
        }));

        spinnerSortBy.setOnItemSelectedListener(simpleItemSelectedListener(new Runnable() {
            @Override
            public void run() {
                sincronizarChipSortBy();
                validarFiltros(false);
            }
        }));

        chipGroupModo.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                if (checkedId == R.id.chipModoTop) {
                    spinnerModo.setSelection(findPosition(spinnerModo, "top-headlines"));
                } else if (checkedId == R.id.chipModoEverything) {
                    spinnerModo.setSelection(findPosition(spinnerModo, "everything"));
                }
            }
        });

        chipGroupCategoria.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                if (checkedId == R.id.chipCategoriaGeneral) {
                    spinnerCategoria.setSelection(findPosition(spinnerCategoria, "general"));
                } else if (checkedId == R.id.chipCategoriaBusiness) {
                    spinnerCategoria.setSelection(findPosition(spinnerCategoria, "business"));
                } else if (checkedId == R.id.chipCategoriaSports) {
                    spinnerCategoria.setSelection(findPosition(spinnerCategoria, "sports"));
                } else if (checkedId == R.id.chipCategoriaTechnology) {
                    spinnerCategoria.setSelection(findPosition(spinnerCategoria, "technology"));
                } else if (checkedId == R.id.chipCategoriaHealth) {
                    spinnerCategoria.setSelection(findPosition(spinnerCategoria, "health"));
                }
            }
        });

        chipGroupPais.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                if (checkedId == R.id.chipPaisEs) {
                    spinnerPais.setSelection(findPosition(spinnerPais, "es"));
                } else if (checkedId == R.id.chipPaisUs) {
                    spinnerPais.setSelection(findPosition(spinnerPais, "us"));
                } else if (checkedId == R.id.chipPaisGb) {
                    spinnerPais.setSelection(findPosition(spinnerPais, "gb"));
                } else if (checkedId == R.id.chipPaisMx) {
                    spinnerPais.setSelection(findPosition(spinnerPais, "mx"));
                }
            }
        });

        chipGroupIdioma.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                if (checkedId == R.id.chipIdiomaEs) {
                    spinnerIdioma.setSelection(findPosition(spinnerIdioma, "es"));
                } else if (checkedId == R.id.chipIdiomaEn) {
                    spinnerIdioma.setSelection(findPosition(spinnerIdioma, "en"));
                } else if (checkedId == R.id.chipIdiomaFr) {
                    spinnerIdioma.setSelection(findPosition(spinnerIdioma, "fr"));
                }
            }
        });

        chipGroupSortBy.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                if (checkedId == R.id.chipSortPublishedAt) {
                    spinnerSortBy.setSelection(findPosition(spinnerSortBy, "publishedAt"));
                } else if (checkedId == R.id.chipSortRelevancy) {
                    spinnerSortBy.setSelection(findPosition(spinnerSortBy, "relevancy"));
                } else if (checkedId == R.id.chipSortPopularity) {
                    spinnerSortBy.setSelection(findPosition(spinnerSortBy, "popularity"));
                }
            }
        });

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validarFiltros(false);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };

        etQuery.addTextChangedListener(watcher);
        etSources.addTextChangedListener(watcher);
        etDomains.addTextChangedListener(watcher);
        etExcludeDomains.addTextChangedListener(watcher);
        etFrom.addTextChangedListener(watcher);
        etTo.addTextChangedListener(watcher);
        etPageSize.addTextChangedListener(watcher);
    }

    private android.widget.AdapterView.OnItemSelectedListener simpleItemSelectedListener(final Runnable runnable) {
        return new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, View view, int position, long id) {
                runnable.run();
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {
            }
        };
=======
>>>>>>> ff8deb57e68caa0f86afd272c6e8bd7a5ba5cb22
    }

    private void actualizarVisibilidadSegunModo() {
        boolean esEverything = "everything".equals(getSelectedValue(spinnerModo));
<<<<<<< HEAD
        layoutTopHeadlines.setVisibility(esEverything ? View.GONE : View.VISIBLE);
        layoutEverything.setVisibility(esEverything ? View.VISIBLE : View.GONE);
=======
>>>>>>> ff8deb57e68caa0f86afd272c6e8bd7a5ba5cb22
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
<<<<<<< HEAD
        spinnerModo.setSelection(findPosition(spinnerModo, "top-headlines"));
        spinnerCategoria.setSelection(findPosition(spinnerCategoria, "general"));
        spinnerIdioma.setSelection(findPosition(spinnerIdioma, "es"));
        spinnerPais.setSelection(findPosition(spinnerPais, "es"));
        spinnerSortBy.setSelection(findPosition(spinnerSortBy, "publishedAt"));
        spinnerSearchIn.setSelection(0);
        clearValidation();
        guardarFiltros();
=======
        spinnerModo.setSelection(0);
        spinnerCategoria.setSelection(findPosition(spinnerCategoria, "general"));
        spinnerIdioma.setSelection(findPosition(spinnerIdioma, "es"));
        spinnerPais.setSelection(findPosition(spinnerPais, "us"));
        spinnerSortBy.setSelection(findPosition(spinnerSortBy, "publishedAt"));
        spinnerSearchIn.setSelection(0);
>>>>>>> ff8deb57e68caa0f86afd272c6e8bd7a5ba5cb22
        actualizarVisibilidadSegunModo();
        cargarNoticias();
    }

<<<<<<< HEAD
    private boolean validarFiltros(boolean mostrarToast) {
        String modo = getSelectedValue(spinnerModo);
        String query = etQuery.getText().toString().trim();
        String from = etFrom.getText().toString().trim();
        String to = etTo.getText().toString().trim();
        String sources = etSources.getText().toString().trim();
        String pageSizeText = etPageSize.getText().toString().trim();

        if (TextUtils.isEmpty(pageSizeText)) {
            return showValidationError("Debes indicar un tamaño de página entre 1 y 100.", mostrarToast);
        }

        int pageSize;
        try {
            pageSize = Integer.parseInt(pageSizeText);
        } catch (Exception e) {
            return showValidationError("El tamaño de página debe ser numérico.", mostrarToast);
        }

        if (pageSize < 1 || pageSize > 100) {
            return showValidationError("El tamaño de página debe estar entre 1 y 100.", mostrarToast);
        }

        if (!TextUtils.isEmpty(from) && !isValidDate(from)) {
            return showValidationError("La fecha 'desde' debe tener formato yyyy-MM-dd.", mostrarToast);
        }

        if (!TextUtils.isEmpty(to) && !isValidDate(to)) {
            return showValidationError("La fecha 'hasta' debe tener formato yyyy-MM-dd.", mostrarToast);
        }

        if (!TextUtils.isEmpty(from) && !TextUtils.isEmpty(to)) {
            try {
                if (apiDateFormat.parse(from).after(apiDateFormat.parse(to))) {
                    return showValidationError("La fecha 'desde' no puede ser posterior a la fecha 'hasta'.", mostrarToast);
                }
            } catch (Exception e) {
                return showValidationError("No se han podido validar las fechas.", mostrarToast);
            }
        }

        if ("everything".equals(modo)) {
            if (TextUtils.isEmpty(query) && TextUtils.isEmpty(sources)) {
                return showValidationError("En 'everything' conviene indicar una búsqueda o una source para obtener resultados más precisos.", mostrarToast);
            }
            if (!TextUtils.isEmpty(sources) && (!TextUtils.isEmpty(etDomains.getText().toString().trim()) || !TextUtils.isEmpty(etExcludeDomains.getText().toString().trim()))) {
                return showValidationError("NewsAPI no permite combinar 'sources' con 'domains' o 'excludeDomains'.", mostrarToast);
            }
        }

        clearValidation();
        return true;
    }

    private boolean isValidDate(String value) {
        try {
            apiDateFormat.setLenient(false);
            apiDateFormat.parse(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean showValidationError(String message, boolean mostrarToast) {
        tvValidation.setText(message);
        tvValidation.setVisibility(View.VISIBLE);
        if (mostrarToast) {
            Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show();
        }
        return false;
    }

    private void clearValidation() {
        tvValidation.setText("");
        tvValidation.setVisibility(View.GONE);
    }

    private void cargarNoticias() {
        if (!validarFiltros(false)) {
            mostrarEstadoVacio();
            return;
        }

=======
    private void cargarNoticias() {
>>>>>>> ff8deb57e68caa0f86afd272c6e8bd7a5ba5cb22
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

<<<<<<< HEAD
    private void guardarFiltros() {
        SharedPreferences preferences = requireContext().getSharedPreferences(PREFS_NAME, 0);
        preferences.edit()
                .putString(KEY_MODO, getSelectedValue(spinnerModo))
                .putString(KEY_QUERY, etQuery.getText().toString().trim())
                .putString(KEY_CATEGORIA, getSelectedValue(spinnerCategoria))
                .putString(KEY_IDIOMA, getSelectedValue(spinnerIdioma))
                .putString(KEY_PAIS, getSelectedValue(spinnerPais))
                .putString(KEY_SORTBY, getSelectedValue(spinnerSortBy))
                .putString(KEY_SEARCHIN, getSelectedValue(spinnerSearchIn))
                .putString(KEY_SOURCES, etSources.getText().toString().trim())
                .putString(KEY_DOMAINS, etDomains.getText().toString().trim())
                .putString(KEY_EXCLUDE_DOMAINS, etExcludeDomains.getText().toString().trim())
                .putString(KEY_FROM, etFrom.getText().toString().trim())
                .putString(KEY_TO, etTo.getText().toString().trim())
                .putString(KEY_PAGE_SIZE, etPageSize.getText().toString().trim())
                .apply();
    }

    private void restoreFilters() {
        SharedPreferences preferences = requireContext().getSharedPreferences(PREFS_NAME, 0);

        spinnerModo.setSelection(findPosition(spinnerModo, preferences.getString(KEY_MODO, "top-headlines")));
        etQuery.setText(preferences.getString(KEY_QUERY, ""));
        spinnerCategoria.setSelection(findPosition(spinnerCategoria, preferences.getString(KEY_CATEGORIA, "general")));
        spinnerIdioma.setSelection(findPosition(spinnerIdioma, preferences.getString(KEY_IDIOMA, "es")));
        spinnerPais.setSelection(findPosition(spinnerPais, preferences.getString(KEY_PAIS, "es")));
        spinnerSortBy.setSelection(findPosition(spinnerSortBy, preferences.getString(KEY_SORTBY, "publishedAt")));
        spinnerSearchIn.setSelection(findPosition(spinnerSearchIn, preferences.getString(KEY_SEARCHIN, "")));
        etSources.setText(preferences.getString(KEY_SOURCES, ""));
        etDomains.setText(preferences.getString(KEY_DOMAINS, ""));
        etExcludeDomains.setText(preferences.getString(KEY_EXCLUDE_DOMAINS, ""));
        etFrom.setText(preferences.getString(KEY_FROM, ""));
        etTo.setText(preferences.getString(KEY_TO, ""));
        etPageSize.setText(preferences.getString(KEY_PAGE_SIZE, "20"));

        sincronizarChipsConSpinnerModo();
        sincronizarChipCategoria();
        sincronizarChipPais();
        sincronizarChipIdioma();
        sincronizarChipSortBy();
    }

    private void sincronizarChipsConSpinnerModo() {
        String modo = getSelectedValue(spinnerModo);
        if ("everything".equals(modo)) {
            chipGroupModo.check(R.id.chipModoEverything);
        } else {
            chipGroupModo.check(R.id.chipModoTop);
        }
    }

    private void sincronizarChipCategoria() {
        String categoria = getSelectedValue(spinnerCategoria);
        if ("business".equals(categoria)) {
            chipGroupCategoria.check(R.id.chipCategoriaBusiness);
        } else if ("sports".equals(categoria)) {
            chipGroupCategoria.check(R.id.chipCategoriaSports);
        } else if ("technology".equals(categoria)) {
            chipGroupCategoria.check(R.id.chipCategoriaTechnology);
        } else if ("health".equals(categoria)) {
            chipGroupCategoria.check(R.id.chipCategoriaHealth);
        } else {
            chipGroupCategoria.check(R.id.chipCategoriaGeneral);
        }
    }

    private void sincronizarChipPais() {
        String pais = getSelectedValue(spinnerPais);
        if ("us".equals(pais)) {
            chipGroupPais.check(R.id.chipPaisUs);
        } else if ("gb".equals(pais)) {
            chipGroupPais.check(R.id.chipPaisGb);
        } else if ("mx".equals(pais)) {
            chipGroupPais.check(R.id.chipPaisMx);
        } else {
            chipGroupPais.check(R.id.chipPaisEs);
        }
    }

    private void sincronizarChipIdioma() {
        String idioma = getSelectedValue(spinnerIdioma);
        if ("en".equals(idioma)) {
            chipGroupIdioma.check(R.id.chipIdiomaEn);
        } else if ("fr".equals(idioma)) {
            chipGroupIdioma.check(R.id.chipIdiomaFr);
        } else {
            chipGroupIdioma.check(R.id.chipIdiomaEs);
        }
    }

    private void sincronizarChipSortBy() {
        String sortBy = getSelectedValue(spinnerSortBy);
        if ("relevancy".equals(sortBy)) {
            chipGroupSortBy.check(R.id.chipSortRelevancy);
        } else if ("popularity".equals(sortBy)) {
            chipGroupSortBy.check(R.id.chipSortPopularity);
        } else {
            chipGroupSortBy.check(R.id.chipSortPublishedAt);
        }
    }

=======
>>>>>>> ff8deb57e68caa0f86afd272c6e8bd7a5ba5cb22
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
