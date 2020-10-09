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
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jobeanda.miproyecto.R;
import java.util.ArrayList;


public class ActividadDiarios extends Fragment{

    // Usamos esta matriz dinámica (ArrayList) para guardar los datos de las 32 opciones del listado
    private ArrayList<Opcion> generales, regionales, economia, deportes, tecnologia, celebridades, moda;
    // Lista del tipo RecyclerView
    private RecyclerView recViewGenerales, recViewRegionales, recViewEconomia, recViewDeportes, recViewTecnologia, recViewCelebridades, recViewModa;

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
            Uri uri;

            @Override
            public void onClick(View v)
            {

                posClick  = recViewGenerales.getChildAdapterPosition(v);

                switch(posClick)
                {
                    case 0:
                        //Toast.makeText(getContext(), "Has hecho clic en '" + posClick
                        //        + "'", Toast.LENGTH_SHORT).show();
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
                        uri = Uri.parse(ConstantesDiarios.URL_TELEPRENSA);
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + posClick);
                }
            }
        });



        // Definimos el evento onClick del adaptador diarios regionales
        adaptador2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Usamos el resultado de "getChildAdapterPosition()" para saber
                // la posición de la opción sobre la que el usuario ha hecho clic.
                Toast.makeText(getContext(), "Has hecho clic en '" + regionales.get(recViewGenerales.getChildAdapterPosition(v)).getIcono()
                        + "'", Toast.LENGTH_SHORT).show();

            }
        });

        // Definimos el evento onClick del adaptador diarios deportes
        adaptador3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Usamos el resultado de "getChildAdapterPosition()" para saber
                // la posición de la opción sobre la que el usuario ha hecho clic.
                Toast.makeText(getContext(), "Has hecho clic en '" + deportes.get(recViewGenerales.getChildAdapterPosition(v)).getIcono()
                        + "'", Toast.LENGTH_SHORT).show();

            }
        });


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
           /*
        // GENERALES
    0 URL_ELPAIS = "https://elpais.com/";
    1 URL_ELMUNDO = "https://www.elmundo.es/";
    2 URL_20MINUTOS = "https://www.20minutos.es/";
    3 URL_OKDIARIO = "https://okdiario.com/";
    4 URL_ABC = "https://www.abc.es/";
    5 URL_ELDIARIO = "https://www.eldiario.es/";
    6 URL_EUROPAPRESS = "https://www.europapress.es/";
    7 URL_LARAZON = "https://www.larazon.es/";
    8 URL_ELESPAÑOL = "https://www.elespanol.com/";
    9 URL_PERIODISTADIGITAL = "https://www.periodistadigital.com/";
    10 URL_LAINFORMACION = "https://www.lainformacion.com/";
    11 URL_ELINDEPENDIENTE = "https://www.elindependiente.com/";
    12 URL_ELCONFIDENCIAL = "https://www.elconfidencial.com/";
    13 URL_TELEPRENSA = "https://www.teleprensa.com/";
         */
        generales.add(new Opcion(R.drawable.portada_el_paiss));
        generales.add(new Opcion(R.drawable.portada_el_mundo));
        generales.add(new Opcion(R.drawable.portada_20_minutos));
        generales.add(new Opcion(R.drawable.portada_ok_diario));
        generales.add(new Opcion(R.drawable.portada_abc));
        generales.add(new Opcion(R.drawable.portada_el_diario));
        generales.add(new Opcion(R.drawable.portada_europa_press));
        generales.add(new Opcion(R.drawable.portada_la_razon));
        generales.add(new Opcion(R.drawable.portada_el_espaniol));
        //generales.add(new Opcion(R.drawable.portada_periodista_digital));
        //generales.add(new Opcion(R.drawable.portada_lainfirmacion));
        generales.add(new Opcion(R.drawable.portada_libertad_digital));
    }

    public void añadirRegionales()
    {
        regionales.add(new Opcion(R.drawable.portada_el_paiss));
        regionales.add(new Opcion(R.drawable.portada_el_mundo));
        regionales.add(new Opcion(R.drawable.portada_la_razon));
        regionales.add(new Opcion(R.drawable.portada_abc));
        regionales.add(new Opcion(R.drawable.portada_20_minutos));
        regionales.add(new Opcion(R.drawable.portada_ok_diario));
        regionales.add(new Opcion(R.drawable.portada_el_diario));
        regionales.add(new Opcion(R.drawable.portada_el_espaniol));
        regionales.add(new Opcion(R.drawable.portada_europa_press));
        regionales.add(new Opcion(R.drawable.portada_libertad_digital));
    }

    public void añadirEconomia()
    {
        economia.add(new Opcion(R.drawable.portada_el_paiss));
        economia.add(new Opcion(R.drawable.portada_el_mundo));
        economia.add(new Opcion(R.drawable.portada_la_razon));
        economia.add(new Opcion(R.drawable.portada_abc));
        economia.add(new Opcion(R.drawable.portada_20_minutos));
        economia.add(new Opcion(R.drawable.portada_ok_diario));
        economia.add(new Opcion(R.drawable.portada_el_diario));
        economia.add(new Opcion(R.drawable.portada_el_espaniol));
        economia.add(new Opcion(R.drawable.portada_europa_press));
        economia.add(new Opcion(R.drawable.portada_libertad_digital));
    }

    public void añadirDeportes()
    {
        deportes.add(new Opcion(R.drawable.portada_el_paiss));
        deportes.add(new Opcion(R.drawable.portada_el_mundo));
        deportes.add(new Opcion(R.drawable.portada_la_razon));
        deportes.add(new Opcion(R.drawable.portada_abc));
        deportes.add(new Opcion(R.drawable.portada_20_minutos));
        deportes.add(new Opcion(R.drawable.portada_ok_diario));
        deportes.add(new Opcion(R.drawable.portada_el_diario));
        deportes.add(new Opcion(R.drawable.portada_el_espaniol));
        deportes.add(new Opcion(R.drawable.portada_europa_press));
        deportes.add(new Opcion(R.drawable.portada_libertad_digital));
    }

    public void añadirTecnologia()
    {
        tecnologia.add(new Opcion(R.drawable.portada_el_paiss));
        tecnologia.add(new Opcion(R.drawable.portada_el_mundo));
        tecnologia.add(new Opcion(R.drawable.portada_la_razon));
        tecnologia.add(new Opcion(R.drawable.portada_abc));
        tecnologia.add(new Opcion(R.drawable.portada_20_minutos));
        tecnologia.add(new Opcion(R.drawable.portada_ok_diario));
        tecnologia.add(new Opcion(R.drawable.portada_el_diario));
        tecnologia.add(new Opcion(R.drawable.portada_el_espaniol));
        tecnologia.add(new Opcion(R.drawable.portada_europa_press));
        tecnologia.add(new Opcion(R.drawable.portada_libertad_digital));
    }

    public void añadirCelebridades()
    {
        celebridades.add(new Opcion(R.drawable.portada_el_paiss));
        celebridades.add(new Opcion(R.drawable.portada_el_mundo));
        celebridades.add(new Opcion(R.drawable.portada_la_razon));
        celebridades.add(new Opcion(R.drawable.portada_abc));
        celebridades.add(new Opcion(R.drawable.portada_20_minutos));
        celebridades.add(new Opcion(R.drawable.portada_ok_diario));
        celebridades.add(new Opcion(R.drawable.portada_el_diario));
        celebridades.add(new Opcion(R.drawable.portada_el_espaniol));
        celebridades.add(new Opcion(R.drawable.portada_europa_press));
        celebridades.add(new Opcion(R.drawable.portada_libertad_digital));
    }

    public void añadirModa()
    {
        moda.add(new Opcion(R.drawable.portada_el_paiss));
        moda.add(new Opcion(R.drawable.portada_el_mundo));
        moda.add(new Opcion(R.drawable.portada_la_razon));
        moda.add(new Opcion(R.drawable.portada_abc));
        moda.add(new Opcion(R.drawable.portada_20_minutos));
        moda.add(new Opcion(R.drawable.portada_ok_diario));
        moda.add(new Opcion(R.drawable.portada_el_diario));
        moda.add(new Opcion(R.drawable.portada_el_espaniol));
        moda.add(new Opcion(R.drawable.portada_europa_press));
        moda.add(new Opcion(R.drawable.portada_libertad_digital));
    }

    public void abreWebDiarios2(AdaptadorOpciones a)
    {
        // Definimos el evento onClick del adaptador diarios generales
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(posClick)
                {
                    case 0:
                        Uri uri = Uri.parse(ConstantesDiarios.URL_ELPAIS);
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
                        break;
                    case 9:
                        break;
                    case 10:
                        break;
                    case 11:
                        break;
                    case 12:
                        break;
                    case 13:
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + posClick);
                }
            }
        });
    };

    public void abreWebDiarios3(AdaptadorOpciones a)
    {
        // Definimos el evento onClick del adaptador diarios generales
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(posClick)
                {
                    case 0:
                        Uri uri = Uri.parse(ConstantesDiarios.URL_ELPAIS);
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
                        break;
                    case 9:
                        break;
                    case 10:
                        break;
                    case 11:
                        break;
                    case 12:
                        break;
                    case 13:
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + posClick);
                }

            }
        });
    };


}