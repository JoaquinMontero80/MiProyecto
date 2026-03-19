package com.jobeanda.miproyecto.ui.noticias;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.jobeanda.miproyecto.R;

public class ActividadNoticias extends Fragment {

    private RecyclerView recyclerViewNoticias;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_main_noticias, container, false);

        recyclerViewNoticias = root.findViewById(R.id.recyclerNoticias);

        // Esta vista solo muestra noticias RSS de España.
        LectorRss lectorRss = new LectorRss(root.getContext(), recyclerViewNoticias);
        lectorRss.execute();

        return root;
    }
}
