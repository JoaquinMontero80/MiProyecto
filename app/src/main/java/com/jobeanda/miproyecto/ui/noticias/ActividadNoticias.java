package com.jobeanda.miproyecto.ui.noticias;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.jobeanda.miproyecto.R;


public class ActividadNoticias extends Fragment
{
    private RecyclerView recyclerViewNoticias;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.activity_main_noticias, container, false);

        //root.setContentView(R.layout.activity_main_noticias);
        recyclerViewNoticias = (RecyclerView) root.findViewById(R.id.recyclerNoticias);

        // Instancia clase LectoRss ( constructor recibe Context ), le paso el RecyclerView
        LectorRss lectorRss = new LectorRss(root.getContext(), recyclerViewNoticias);
        // Metodo execute de la clase AsyncTask
        lectorRss.execute();

        return root;

    }


}