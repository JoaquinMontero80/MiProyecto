package com.jobeanda.miproyecto.ui.noticias;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jobeanda.miproyecto.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

public class FavoritosFragment extends Fragment {

    private RecyclerView recyclerFavoritos;
    private TextView textoVacio;
    private ArrayList<Noticia> favoritos;
    private AdapterNoticias adapter;

    public FavoritosFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favoritos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerFavoritos = view.findViewById(R.id.recyclerFavoritos);
        textoVacio = view.findViewById(R.id.textoVacio);

        recyclerFavoritos.setLayoutManager(new LinearLayoutManager(requireContext()));

        cargarFavoritos();
    }

    @Override
    public void onResume() {
        super.onResume();
        cargarFavoritos();
    }

    private void cargarFavoritos() {
        FavoritosManager favoritosManager = new FavoritosManager(requireContext());
        favoritos = favoritosManager.obtenerFavoritos();

        convertirFechasSiHaceFalta(favoritos);
        ordenarPorFechaDesc(favoritos);

        adapter = new AdapterNoticias(favoritos, requireContext(), true);
        recyclerFavoritos.setAdapter(adapter);

        if (favoritos.isEmpty()) {
            textoVacio.setVisibility(View.VISIBLE);
            recyclerFavoritos.setVisibility(View.GONE);
        } else {
            textoVacio.setVisibility(View.GONE);
            recyclerFavoritos.setVisibility(View.VISIBLE);
        }
    }

    private void convertirFechasSiHaceFalta(ArrayList<Noticia> lista) {
        SimpleDateFormat formato =
                new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);

        for (Noticia noticia : lista) {
            if (noticia.getFechaDate() == null && noticia.getFecha() != null) {
                try {
                    noticia.setFechaDate(formato.parse(noticia.getFecha()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void ordenarPorFechaDesc(ArrayList<Noticia> lista) {
        Collections.sort(lista, new Comparator<Noticia>() {
            @Override
            public int compare(Noticia n1, Noticia n2) {
                if (n1.getFechaDate() == null && n2.getFechaDate() == null) return 0;
                if (n1.getFechaDate() == null) return 1;
                if (n2.getFechaDate() == null) return -1;
                return n2.getFechaDate().compareTo(n1.getFechaDate());
            }
        });
    }
}