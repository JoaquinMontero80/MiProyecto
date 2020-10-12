package com.jobeanda.miproyecto.ui.diarios;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jobeanda.miproyecto.R;

import java.util.ArrayList;


public class ActividadDiarios extends Fragment{

    // Usamos esta matriz dinámica (ArrayList) para guardar los datos de las 32 opciones del listado
    private ArrayList<Opcion> generales, regionales, economia, deportes, tecnologia, celebridades, moda;
    // Lista del tipo RecyclerView
    private RecyclerView recViewGenerales, recViewRegionales, recViewEconomia, recViewDeportes, recViewTecnologia, recViewCelebridades, recViewModa;

    // Recoge los eventos del Recycler, posicion y URI con el enlace
    private Uri uri;
    private int posClick;

    // Botones con las nuevas opciones
    private Button btnInsertar;
    private Button btnBorrar;
    private Button btnMover;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(com.jobeanda.miproyecto.R.layout.fragment_diarios, container, false);

        // Buscamos en el layout la lista RecyclerView
        recViewGenerales = (RecyclerView) root.findViewById(R.id.recyclerviewGenerales);
        recViewRegionales = (RecyclerView) root.findViewById(R.id.recyclerviewRegionales);
        recViewEconomia = (RecyclerView) root.findViewById(R.id.recyclerviewEconomia);
        recViewDeportes= (RecyclerView) root.findViewById(R.id.recyclerviewDeportes);
        recViewTecnologia = (RecyclerView) root.findViewById(R.id.recyclerviewTecnologia);
        recViewCelebridades= (RecyclerView) root.findViewById(R.id.recyclerviewCelebridades);
        recViewModa = (RecyclerView) root.findViewById(R.id.recyclerviewModa);

        // Indicamos que el tamaño del RecyclerView no cambia
        //recViewGenerales.setHasFixedSize(true);
        //recViewRegionales.setHasFixedSize(true);
        //recViewDeportes.setHasFixedSize(true);

        // Se instancia el ArrayList de cada RecyclerView con las opciones
        generales = new ArrayList<Opcion>();
        regionales = new ArrayList<Opcion>();
        economia = new ArrayList<Opcion>();
        deportes = new ArrayList<Opcion>();
        tecnologia = new ArrayList<Opcion>();
        celebridades = new ArrayList<Opcion>();
        moda = new ArrayList<Opcion>();

        // Creamos el Adaptador que se usa para mostrar las opciones del listado
        AdaptadorOpciones adaptador = new AdaptadorOpciones(generales);
        AdaptadorOpciones adaptador2 = new AdaptadorOpciones(regionales);
        AdaptadorOpciones adaptador3 = new AdaptadorOpciones(economia);
        AdaptadorOpciones adaptador4 = new AdaptadorOpciones(deportes);
        AdaptadorOpciones adaptador5 = new AdaptadorOpciones(tecnologia);
        AdaptadorOpciones adaptador6 = new AdaptadorOpciones(celebridades);
        AdaptadorOpciones adaptador7 = new AdaptadorOpciones(moda);

        // Asignamos el adaptador al RecyclerView para que sepa cómo dibujar el listado de opciones
        recViewGenerales.setAdapter(adaptador);
        recViewRegionales.setAdapter(adaptador2);
        recViewEconomia.setAdapter(adaptador3);
        recViewDeportes.setAdapter(adaptador4);
        recViewTecnologia.setAdapter(adaptador5);
        recViewCelebridades.setAdapter(adaptador6);
        recViewModa.setAdapter(adaptador7);

        // Muy importante indicar el tipo de Layout. ¡Obligatorio!!!
        // PDF --> la clase RecyclerView no determina por sí
        //misma la forma en la que se van a mostrar los elementos de datos, sino que delega esta funcionalidad
        //a otra clase denominada LayoutManager, que debemos crear y asociar al RecyclerView para
        //su correcto funcionamiento. Es muy importante hacerlo ya que si no, la aplicación mostrará un error,
        //se cerrará y no funcionará bien.
        recViewGenerales.setLayoutManager(new LinearLayoutManager(root.getContext(), LinearLayoutManager.HORIZONTAL, false));
        recViewRegionales.setLayoutManager(new LinearLayoutManager(root.getContext(), LinearLayoutManager.HORIZONTAL, false));
        recViewEconomia.setLayoutManager(new LinearLayoutManager(root.getContext(), LinearLayoutManager.HORIZONTAL, false));
        recViewDeportes.setLayoutManager(new LinearLayoutManager(root.getContext(), LinearLayoutManager.HORIZONTAL, false));
        recViewTecnologia.setLayoutManager(new LinearLayoutManager(root.getContext(), LinearLayoutManager.HORIZONTAL, false));
        recViewCelebridades.setLayoutManager(new LinearLayoutManager(root.getContext(), LinearLayoutManager.HORIZONTAL, false));
        recViewModa.setLayoutManager(new LinearLayoutManager(root.getContext(), LinearLayoutManager.HORIZONTAL, false));

        // Animador de la lista
        recViewGenerales.setItemAnimator(new DefaultItemAnimator());
        recViewRegionales.setItemAnimator(new DefaultItemAnimator());
        recViewEconomia.setItemAnimator(new DefaultItemAnimator());
        recViewDeportes.setItemAnimator(new DefaultItemAnimator());
        recViewTecnologia.setItemAnimator(new DefaultItemAnimator());
        recViewCelebridades.setItemAnimator(new DefaultItemAnimator());
        recViewModa.setItemAnimator(new DefaultItemAnimator());


        añadirGenerales();
        añadirRegionales();
        añadirEconomia();
        añadirDeportes();
        añadirTecnologia();
        añadirCelebridades();
        añadirModa();

        // Definimos el evento onClick del adaptador diarios generales
        adaptador.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                posClick  = recViewGenerales.getChildAdapterPosition(v);

                switch(posClick)
                {
                    case 0:
                        uri = Uri.parse(ConstantesDiarios.URL_ELPAIS);
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 1:
                        uri = Uri.parse(ConstantesDiarios.URL_ELMUNDO);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 2:
                        uri = Uri.parse(ConstantesDiarios.URL_20MINUTOS);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 3:
                        uri = Uri.parse(ConstantesDiarios.URL_OKDIARIO);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 4:
                        uri = Uri.parse(ConstantesDiarios.URL_ABC);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 5:
                        uri = Uri.parse(ConstantesDiarios.URL_ELDIARIO);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 6:
                        uri = Uri.parse(ConstantesDiarios.URL_EUROPAPRESS);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 7:
                        uri = Uri.parse(ConstantesDiarios.URL_LARAZON);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 8:
                        uri = Uri.parse(ConstantesDiarios.URL_ELESPAÑOL);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 9:
                        uri = Uri.parse(ConstantesDiarios.URL_PERIODISTADIGITAL);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 10:
                        uri = Uri.parse(ConstantesDiarios.URL_LAINFORMACION);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 11:
                        uri = Uri.parse(ConstantesDiarios.URL_ELINDEPENDIENTE);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 12:
                        uri = Uri.parse(ConstantesDiarios.URL_ELCONFIDENCIAL);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 13:
                        uri = Uri.parse(ConstantesDiarios.URL_PERIODISTADIGITAL);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 14:
                        uri = Uri.parse(ConstantesDiarios.URL_LIBERTADDIGITAL);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + posClick);
                }
            }
        });



        // Definimos el evento onClick del adaptador diarios regionales
        adaptador2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                posClick  = recViewRegionales.getChildAdapterPosition(v);

                switch(posClick)
                {
                    case 0:
                        //Toast.makeText(getContext(), "Has hecho clic en '" + posClick
                        //        + "'", Toast.LENGTH_SHORT).show();
                        uri = Uri.parse(ConstantesDiarios.URL_LAVANGUARDIA);
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 1:
                        uri = Uri.parse(ConstantesDiarios.URL_LAVOZGALICIA);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 2:
                        uri = Uri.parse(ConstantesDiarios.URL_PUBLICO);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 3:
                        uri = Uri.parse(ConstantesDiarios.URL_ELPERIODICO);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 4:
                        uri = Uri.parse(ConstantesDiarios.URL_ELCORREO);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 5:
                        uri = Uri.parse(ConstantesDiarios.URL_SUR);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 6:
                        uri = Uri.parse(ConstantesDiarios.URL_LASPROVINCIAS);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 7:
                        uri = Uri.parse(ConstantesDiarios.URL_IDEAL);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 8:
                        uri = Uri.parse(ConstantesDiarios.URL_LANUEVAESPAÑA);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 9:
                        uri = Uri.parse(ConstantesDiarios.URL_LAVERDAD);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 10:
                        uri = Uri.parse(ConstantesDiarios.URL_ELDIARIOVASCO);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 11:
                        uri = Uri.parse(ConstantesDiarios.URL_LEVANTE);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 12:
                        uri = Uri.parse(ConstantesDiarios.URL_ELCOMERCIO);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 13:
                        uri = Uri.parse(ConstantesDiarios.URL_ELDIARIOMONTAÑES);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 14:
                        //Toast.makeText(getContext(), "Has hecho clic en '" + posClick
                        //        + "'", Toast.LENGTH_SHORT).show();
                        uri = Uri.parse(ConstantesDiarios.URL_FARODEVIGO);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 15:
                        uri = Uri.parse(ConstantesDiarios.URL_HERALDO);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 16:
                        uri = Uri.parse(ConstantesDiarios.URL_CANARIAS7);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 17:
                        uri = Uri.parse(ConstantesDiarios.URL_HOY);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 18:
                        uri = Uri.parse(ConstantesDiarios.URL_ELNORTEDECASTILLA);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 19:
                        uri = Uri.parse(ConstantesDiarios.URL_ELPLURAL);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 20:
                        uri = Uri.parse(ConstantesDiarios.URL_DIARIODECADIZ);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 21:
                        uri = Uri.parse(ConstantesDiarios.URL_DEIA);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 22:
                        uri = Uri.parse(ConstantesDiarios.URL_DIARIOLEON);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 23:
                        uri = Uri.parse(ConstantesDiarios.URL_PERIODICOARAGON);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 24:
                        uri = Uri.parse(ConstantesDiarios.URL_DIARIONAVARRA);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 25:
                        uri = Uri.parse(ConstantesDiarios.URL_DIARIOMALLORCA);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 26:
                        uri = Uri.parse(ConstantesDiarios.URL_ULTIMAHORA);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 27:
                        uri = Uri.parse(ConstantesDiarios.URL_ELDIA);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 28:
                        //Toast.makeText(getContext(), "Has hecho clic en '" + posClick
                        //        + "'", Toast.LENGTH_SHORT).show();
                        uri = Uri.parse(ConstantesDiarios.URL_LAOPINIONMALAGA);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 29:
                        uri = Uri.parse(ConstantesDiarios.URL_LAOPINIONMURCIA);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 30:
                        uri = Uri.parse(ConstantesDiarios.URL_CORREOGALLEGO);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 31:
                        uri = Uri.parse(ConstantesDiarios.URL_diarioCORDOBA);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 32:
                        uri = Uri.parse(ConstantesDiarios.URL_OPINIONACORUÑA);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 33:
                        uri = Uri.parse(ConstantesDiarios.URL_ELDIARIO);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 34:
                        uri = Uri.parse(ConstantesDiarios.URL_LAGACETASALAMANCA);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 35:
                        uri = Uri.parse(ConstantesDiarios.URL_PERIODICOEXTREMADURA);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 36:
                        uri = Uri.parse(ConstantesDiarios.URL_ANDALUCIAINFORMACION);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 37:
                        uri = Uri.parse(ConstantesDiarios.URL_DIARIOBURGOS);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 38:
                        uri = Uri.parse(ConstantesDiarios.URL_DIARIOTARRAGONA);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 39:
                        uri = Uri.parse(ConstantesDiarios.URL_MEDITERRANEO);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 40:
                        uri = Uri.parse(ConstantesDiarios.URL_MALAGAHOY);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 41:
                        uri = Uri.parse(ConstantesDiarios.URL_DIARIODEALMERIA);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 42:
                        uri = Uri.parse(ConstantesDiarios.URL_ADELANTADO);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 43:
                        uri = Uri.parse(ConstantesDiarios.URL_MENORCAINFO);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 44:
                        uri = Uri.parse(ConstantesDiarios.URL_GRANADAHOY);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 45:
                        uri = Uri.parse(ConstantesDiarios.URL_LANZA);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 46:
                        uri = Uri.parse(ConstantesDiarios.URL_TRIBUNATOLEDO);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 47:
                        uri = Uri.parse(ConstantesDiarios.URL_TRIBUNACIUDADREAL);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 48:
                        uri = Uri.parse(ConstantesDiarios.URL_ABCSEVILLA);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 49:
                        uri = Uri.parse(ConstantesDiarios.URL_CRONICABADAJOZ);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 50:
                        uri = Uri.parse(ConstantesDiarios.URL_LAMAÑANA);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 51:
                        uri = Uri.parse(ConstantesDiarios.URL_DIARIOJEREZ);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + posClick);
                }

            }
        });

        // Definimos el evento onClick del adaptador diarios economia
        adaptador3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                posClick  = recViewRegionales.getChildAdapterPosition(v);

                switch(posClick)
                {
                    case 0:
                        uri = Uri.parse(ConstantesDiarios.URL_ELECONOMISTA);
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 1:
                        uri = Uri.parse(ConstantesDiarios.URL_EXPANSION);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 2:
                        uri = Uri.parse(ConstantesDiarios.URL_CINCODIAS);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 3:
                        uri = Uri.parse(ConstantesDiarios.URL_BANCOMUNDIAL);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 4:
                        uri = Uri.parse(ConstantesDiarios.URL_INVERSORGLOBAL);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 5:
                        uri = Uri.parse(ConstantesDiarios.URL_MERCA2);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 6:
                        uri = Uri.parse(ConstantesDiarios.URL_ELBLOGSALMON);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + posClick);
                }
            }
        });

        // Definimos el evento onClick del adaptador diarios deportes
        adaptador4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                posClick  = recViewDeportes.getChildAdapterPosition(v);

                switch(posClick)
                {
                    case 0:
                        uri = Uri.parse(ConstantesDiarios.URL_MARCA);
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 1:
                        uri = Uri.parse(ConstantesDiarios.URL_AS);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 2:
                        uri = Uri.parse(ConstantesDiarios.URL_SPORT);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 3:
                        uri = Uri.parse(ConstantesDiarios.URL_SUPERDEPORTE);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 4:
                        uri = Uri.parse(ConstantesDiarios.URL_ESTADIODEPORTIVO);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 5:
                        uri = Uri.parse(ConstantesDiarios.URL_MUNDODEPORTIVO);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 6:
                        uri = Uri.parse(ConstantesDiarios.URL_DIARIOGOL);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 7:
                        uri = Uri.parse(ConstantesDiarios.URL_ELDESMARQUE);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 8:
                        uri = Uri.parse(ConstantesDiarios.URL_DEFENSACENTRAL);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 9:
                        uri = Uri.parse(ConstantesDiarios.URL_FCBARCELONANOTICIAS);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 10:
                        uri = Uri.parse(ConstantesDiarios.URL_MUCHODEPORTE);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 11:
                        uri = Uri.parse(ConstantesDiarios.URL_AUPAATLETIC);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 12:
                        uri = Uri.parse(ConstantesDiarios.URL_DEPORTEVALENCIANO);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 13:
                        uri = Uri.parse(ConstantesDiarios.URL_CORDOBADEPORTES);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 14:
                        uri = Uri.parse(ConstantesDiarios.URL_DIARIOLAGRADA);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 15:
                        uri = Uri.parse(ConstantesDiarios.URL_FUTBOLASTURIANO);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 16:
                        uri = Uri.parse(ConstantesDiarios.URL_LACOLINANERVION);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 17:
                        uri = Uri.parse(ConstantesDiarios.URL_PASIONZARAGOZISTA);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + posClick);
                }
            }
        });

        // Definimos el evento onClick del adaptador diarios tecnologia
        adaptador5.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                posClick  = recViewTecnologia.getChildAdapterPosition(v);

                switch(posClick)
                {
                    case 0:
                        uri = Uri.parse(ConstantesDiarios.URL_GENBETA);
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 1:
                        uri = Uri.parse(ConstantesDiarios.URL_XATAKA);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 2:
                        uri = Uri.parse(ConstantesDiarios.URL_ANDROIDLIBRE);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 3:
                        uri = Uri.parse(ConstantesDiarios.URL_TECNOGEEK);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 4:
                        uri = Uri.parse(ConstantesDiarios.URL_APPLEESFERA);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 5:
                        uri = Uri.parse(ConstantesDiarios.URL_PUNTOGEEK);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 6:
                        uri = Uri.parse(ConstantesDiarios.URL_HOBBYCONSOLAS);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + posClick);
                }
            }
        });

        // Definimos el evento onClick del adaptador diarios celebridades
        adaptador6.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                posClick  = recViewCelebridades.getChildAdapterPosition(v);

                switch(posClick)
                {
                    case 0:
                        uri = Uri.parse(ConstantesDiarios.URL_HOLA);
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 1:
                        uri = Uri.parse(ConstantesDiarios.URL_ANTENA3);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 2:
                        uri = Uri.parse(ConstantesDiarios.URL_INTERVIU);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 3:
                        uri = Uri.parse(ConstantesDiarios.URL_OHMYMAG);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 4:
                        uri = Uri.parse(ConstantesDiarios.URL_POPROSA);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 5:
                        uri = Uri.parse(ConstantesDiarios.URL_ELHUFFPOST);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + posClick);
                }
            }
        });


        // Definimos el evento onClick del adaptador diarios moda
        adaptador7.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                posClick  = recViewModa.getChildAdapterPosition(v);

                switch(posClick)
                {
                    case 0:
                        uri = Uri.parse(ConstantesDiarios.URL_VOGUE);
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 1:
                        uri = Uri.parse(ConstantesDiarios.URL_ELLE);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 2:
                        uri = Uri.parse(ConstantesDiarios.URL_FASHIONUNITED);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 3:
                        uri = Uri.parse(ConstantesDiarios.URL_SHARPEICO);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + posClick);
                }
            }
        });


        // Devuelve fragment
        return root;


    }
  /*


        // Ahora definimos los eventos onClick de los botones
        btnInsertar = (Button) findViewById(R.id.BtnInsertar);
        // El botón insertar añade una nueva opción
        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Añadimos una nueva opción en datos en la posición 1 (el listado comienza en el 0)
                datos.add(1, new Opcion("Nueva opción", "Subtítulo nueva opción", R.drawable.star1));
                // Indicamos al adaptador que hemos añadido un nuevo elemento en la posición 1
                // para que redibuje el RecyclerView.
                adaptador.notifyItemInserted(196);
            }
        });

        btnBorrar = (Button) findViewById(R.id.BtnBorrar);
        // El botón borrar quitar el elemento 1 añadido anteriormente
        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Si hay suficientes elementos en la lista...
                if (datos.size() < 2) return;
                // Quitamos el elemento 1
                datos.remove(1);
                // Indicamos al adaptador que hemos quitado el elemento en la posición 1
                // para que redibuje el RecyclerView.
                adaptador.notifyItemRemoved(1);
            }
        });

        btnMover = (Button) findViewById(R.id.BtnMover);
        // El botón mover cambia de posición los elementos 1 y 2
        btnMover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtenemos los datos de la posición 1
                Opcion aux = datos.get(1);
                // En la posición 1 escribimos los datos de la posición 2
                datos.set(1, datos.get(2));
                // En la posición 2 escribimos los datos de la 1
                datos.set(2, aux);
                // Indicamos al adaptador que hemos movido los elementos 1 y 2
                // para que redibuje el RecyclerView.
                adaptador.notifyItemMoved(1, 2);
            }
        });

    }
*/
    public void añadirGenerales()
    {
        generales.add(new Opcion(R.drawable.portada_el_paiss));
        generales.add(new Opcion(R.drawable.portada_el_mundo));
        generales.add(new Opcion(R.drawable.portada_20_minutos));
        generales.add(new Opcion(R.drawable.portada_ok_diario));
        generales.add(new Opcion(R.drawable.portada_abc));
        generales.add(new Opcion(R.drawable.portada_el_diario));
        generales.add(new Opcion(R.drawable.portada_europa_press));
        generales.add(new Opcion(R.drawable.portada_la_razon));
        generales.add(new Opcion(R.drawable.portada_la_informacion));
        generales.add(new Opcion(R.drawable.portada_el_confidencial));
        generales.add(new Opcion(R.drawable.portada_el_espaniol));
        generales.add(new Opcion(R.drawable.portada_periodista_digital));
        generales.add(new Opcion(R.drawable.portada_libertad_digital));
    }

    public void añadirRegionales()
    {
        regionales.add(new Opcion(R.drawable.portada_la_vanguardia));
        regionales.add(new Opcion(R.drawable.portada_voz_galicia));
        regionales.add(new Opcion(R.drawable.portada_publico));
        regionales.add(new Opcion(R.drawable.portada_el_periodico));
        regionales.add(new Opcion(R.drawable.portada_el_correo));
        regionales.add(new Opcion(R.drawable.portada_sur));
        regionales.add(new Opcion(R.drawable.portada_las_provincias));
        regionales.add(new Opcion(R.drawable.portada_ideal));
        regionales.add(new Opcion(R.drawable.portada_nueva_espana));
        regionales.add(new Opcion(R.drawable.portada_la_verdad));
        regionales.add(new Opcion(R.drawable.portada_diario_vasco));
        regionales.add(new Opcion(R.drawable.portada_levante));
        regionales.add(new Opcion(R.drawable.portada_comercio));
        regionales.add(new Opcion(R.drawable.portada_montanes));
        regionales.add(new Opcion(R.drawable.portada_faro_vigo));
        regionales.add(new Opcion(R.drawable.portada_heraldo));
        regionales.add(new Opcion(R.drawable.portada_canarias7));
        regionales.add(new Opcion(R.drawable.portada_hoy));
        regionales.add(new Opcion(R.drawable.portada_norte_castilla));
        regionales.add(new Opcion(R.drawable.portada_plural));
        regionales.add(new Opcion(R.drawable.portada_diario_cadiz));
        regionales.add(new Opcion(R.drawable.portada_deia));
        regionales.add(new Opcion(R.drawable.portada_diario_leon));
        regionales.add(new Opcion(R.drawable.portada_periodico_aragon));
        regionales.add(new Opcion(R.drawable.portada_diario_navarra));
        regionales.add(new Opcion(R.drawable.portada_diario_mallorca));
        regionales.add(new Opcion(R.drawable.portada_ultima_hora));
        regionales.add(new Opcion(R.drawable.portada_el_dia));
        regionales.add(new Opcion(R.drawable.portada_opinion_malaga));
        regionales.add(new Opcion(R.drawable.portada_opinion_murcia));
        regionales.add(new Opcion(R.drawable.portada_correo_gallego));
        regionales.add(new Opcion(R.drawable.portada_diario_cordoba));
        regionales.add(new Opcion(R.drawable.portada_diari_girona));
        regionales.add(new Opcion(R.drawable.portada_opinion_coruna));
        regionales.add(new Opcion(R.drawable.portada_gaceta_salamanca));
        regionales.add(new Opcion(R.drawable.portada_periodico_extremadura));
        regionales.add(new Opcion(R.drawable.portada_andalucia_informacion));
        regionales.add(new Opcion(R.drawable.portada_diario_burgos));
        regionales.add(new Opcion(R.drawable.portada_diario_tarragona));
        regionales.add(new Opcion(R.drawable.portada_mediterraneo));
        regionales.add(new Opcion(R.drawable.portada_malaga_hoy));
        regionales.add(new Opcion(R.drawable.portada_diario_almeria));
        regionales.add(new Opcion(R.drawable.portada_adelantado));
        regionales.add(new Opcion(R.drawable.portada_menorca_info));
        regionales.add(new Opcion(R.drawable.portada_granada_hoy));
        regionales.add(new Opcion(R.drawable.portada_lanza));
        regionales.add(new Opcion(R.drawable.portada_tribuna_toledo));
        regionales.add(new Opcion(R.drawable.portada_tribuna_ciudadreal));
        regionales.add(new Opcion(R.drawable.portada_abc_sevilla));
        regionales.add(new Opcion(R.drawable.portada_cronica_badajoz));
        regionales.add(new Opcion(R.drawable.portada_la_maniana));
        regionales.add(new Opcion(R.drawable.portada_diario_jerez));
    }

    public void añadirEconomia()
    {
        economia.add(new Opcion(R.drawable.portada_el_economista));
        economia.add(new Opcion(R.drawable.portada_la_expansion));
        economia.add(new Opcion(R.drawable.portada_cinco_dias));
        economia.add(new Opcion(R.drawable.portada_banco_mundial));
        economia.add(new Opcion(R.drawable.portada_inversor_global));
        economia.add(new Opcion(R.drawable.portada_merca2));
        economia.add(new Opcion(R.drawable.portada_blog_salmon));
    }

    public void añadirDeportes()
    {
        deportes.add(new Opcion(R.drawable.portada_marca));
        deportes.add(new Opcion(R.drawable.portada_as));
        deportes.add(new Opcion(R.drawable.portada_sport));
        deportes.add(new Opcion(R.drawable.portada_superdeporte));
        deportes.add(new Opcion(R.drawable.portada_estadiodeportivo));
        deportes.add(new Opcion(R.drawable.portada_mundodeportivo));
        deportes.add(new Opcion(R.drawable.portada_diariogol));
        deportes.add(new Opcion(R.drawable.portada_el_desmarque));
        deportes.add(new Opcion(R.drawable.portada_defensa_central));
        deportes.add(new Opcion(R.drawable.portada_barcelonanoticias));
        deportes.add(new Opcion(R.drawable.portada_muchodeporte));
        deportes.add(new Opcion(R.drawable.portada_aupaatletic));
        deportes.add(new Opcion(R.drawable.portada_deportevalenciano));
        deportes.add(new Opcion(R.drawable.portada_cordobadeporte));
        deportes.add(new Opcion(R.drawable.portada_diariolagrada));
        deportes.add(new Opcion(R.drawable.portada_futbolasturiano));
        deportes.add(new Opcion(R.drawable.portada_colinanervion));
        deportes.add(new Opcion(R.drawable.portada_pasionzaragozista));
    }

    public void añadirTecnologia()
    {
        tecnologia.add(new Opcion(R.drawable.portada_genbeta));
        tecnologia.add(new Opcion(R.drawable.portada_xataka));
        tecnologia.add(new Opcion(R.drawable.portada_androidlibre));
        tecnologia.add(new Opcion(R.drawable.portada_tecnogeek));
        tecnologia.add(new Opcion(R.drawable.portada_appleesfera));
        tecnologia.add(new Opcion(R.drawable.portada_puntogeek));
        tecnologia.add(new Opcion(R.drawable.portada_hobbyconsolas));
    }

    public void añadirCelebridades()
    {
        celebridades.add(new Opcion(R.drawable.portada_hola));
        celebridades.add(new Opcion(R.drawable.portada_antena3));
        celebridades.add(new Opcion(R.drawable.portada_interviu));
        celebridades.add(new Opcion(R.drawable.portada_ohmymag));
        celebridades.add(new Opcion(R.drawable.portada_poprosa));
        celebridades.add(new Opcion(R.drawable.portada_el_huffpost));
    }

    public void añadirModa()
    {
        moda.add(new Opcion(R.drawable.portada_vogue));
        moda.add(new Opcion(R.drawable.portada_elle));
        moda.add(new Opcion(R.drawable.portada_fashionunited));
        moda.add(new Opcion(R.drawable.portada_sharpeico));
    }

}