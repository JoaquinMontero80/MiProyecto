package com.jobeanda.miproyecto.ui.noticias;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.jobeanda.miproyecto.R;


public class ActividadNoticias extends Fragment
{
    private RecyclerView recyclerViewNoticias;
    // ViewPager y TabLayout de la parte baja
    private ViewPager view_pager;
    private TabLayout tab_layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.activity_main_noticias, container, false);

        //root.setContentView(R.layout.activity_main_noticias);
        recyclerViewNoticias = (RecyclerView) root.findViewById(R.id.recyclerNoticias);
        view_pager = (ViewPager) root.findViewById(R.id.view_pager);
        tab_layout = (TabLayout) root.findViewById(R.id.tab_layout);

        // Instancia la clase TableLayout, para añadir en la parte baja, el desplegable de opciones
        //TableLayout tablelayout = new TableLayout(root.getContext(), view_pager, tab_layout);

        // Instancia clase LectoRss ( constructor recibe Context ), le paso el RecyclerView
        LectorRss lectorRss = new LectorRss(root.getContext(), recyclerViewNoticias);
        // Metodo execute de la clase AsyncTask
        lectorRss.execute();



        return root;

    }


}