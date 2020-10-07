package com.jobeanda.miproyecto.ui.noticias;




import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.jobeanda.miproyecto.R;


public class ActividadNoticias extends Fragment
{
    private RecyclerView recyclerViewNoticias;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_diarios, container, false);

        //root.setContentView(R.layout.activity_main_noticias);
        recyclerViewNoticias = (RecyclerView) root.findViewById(R.id.recyclerNoticias);
        // Instancia clase LectoRss ( constructor recibe Context ), le paso el RecyclerView
        LectorRss lectorRss = new LectorRss(ActividadNoticias.this, recyclerViewNoticias);
        // Metodo Execute de la clase AsyncTask
        lectorRss.execute();

        return root;
    }


}