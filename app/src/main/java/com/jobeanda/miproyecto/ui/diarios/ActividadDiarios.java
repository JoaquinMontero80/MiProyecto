package com.jobeanda.miproyecto.ui.diarios;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jobeanda.miproyecto.R;

import java.util.ArrayList;

public class ActividadDiarios extends AppCompatActivity {

    // Usamos esta matriz dinámica (ArrayList) para guardar los datos de las 32 opciones del listado
    private ArrayList<Opcion> generales, regionales, deportes;
    // Lista del tipo RecyclerView
    private RecyclerView recViewGenerales, recViewRegionales, recViewDeportes;
    // Botones con las nuevas opciones
    private Button btnInsertar;
    private Button btnBorrar;
    private Button btnMover;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_diarios);

        // Buscamos en el layout la lista RecyclerView
        recViewGenerales = (RecyclerView) findViewById(R.id.recyclerviewGenerales);
        recViewRegionales = (RecyclerView) findViewById(R.id.recyclerviewRegionales);
        recViewDeportes= (RecyclerView) findViewById(R.id.recyclerviewDeportes);

        // Indicamos que el tamaño del RecyclerView no cambia
        recViewGenerales.setHasFixedSize(true);
        recViewRegionales.setHasFixedSize(true);
        recViewDeportes.setHasFixedSize(true);

        // Se instancia el ArrayList de cada RecyclerView con las opciones
        generales = new ArrayList<Opcion>();
        regionales = new ArrayList<Opcion>();
        deportes = new ArrayList<Opcion>();

        añadirGenerales();
        añadirRegionales();
        añadirDeportes();

        // Creamos el Adaptador que se usa para mostrar las opciones del listado
        final AdaptadorOpciones adaptador = new AdaptadorOpciones(generales);
        final AdaptadorOpciones adaptador2 = new AdaptadorOpciones(regionales);
        final AdaptadorOpciones adaptador3 = new AdaptadorOpciones(deportes);

        // Definimos el evento onClick del adaptador diarios generales
        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Usamos el resultado de "getChildAdapterPosition()" para saber
                // la posición de la opción sobre la que el usuario ha hecho clic.
                Toast.makeText(getBaseContext(), "Has hecho clic en '" + generales.get(recViewGenerales.getChildAdapterPosition(v)).getIcono()
                        + "'", Toast.LENGTH_SHORT).show();

            }
        });

        // Definimos el evento onClick del adaptador diarios regionales
        adaptador2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Usamos el resultado de "getChildAdapterPosition()" para saber
                // la posición de la opción sobre la que el usuario ha hecho clic.
                Toast.makeText(getBaseContext(), "Has hecho clic en '" + regionales.get(recViewGenerales.getChildAdapterPosition(v)).getIcono()
                        + "'", Toast.LENGTH_SHORT).show();

            }
        });

        // Definimos el evento onClick del adaptador diarios deportes
        adaptador3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Usamos el resultado de "getChildAdapterPosition()" para saber
                // la posición de la opción sobre la que el usuario ha hecho clic.
                Toast.makeText(getBaseContext(), "Has hecho clic en '" + deportes.get(recViewGenerales.getChildAdapterPosition(v)).getIcono()
                        + "'", Toast.LENGTH_SHORT).show();

            }
        });


        // Asignamos el adaptador al RecyclerView para que sepa cómo dibujar el listado de opciones
        recViewGenerales.setAdapter(adaptador);
        // Asignamos el adaptador al RecyclerView para que sepa cómo dibujar el listado de opciones
        recViewRegionales.setAdapter(adaptador2);
        // Asignamos el adaptador al RecyclerView para que sepa cómo dibujar el listado de opciones
        recViewDeportes.setAdapter(adaptador3);

        // Muy importante indicar el tipo de Layout. ¡Obligatorio!!!
        // PDF --> la clase RecyclerView no determina por sí
        //misma la forma en la que se van a mostrar los elementos de datos, sino que delega esta funcionalidad
        //a otra clase denominada LayoutManager, que debemos crear y asociar al RecyclerView para
        //su correcto funcionamiento. Es muy importante hacerlo ya que si no, la aplicación mostrará un error,
        //se cerrará y no funcionará bien.
        recViewGenerales.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recViewRegionales.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recViewDeportes.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // Animador de la lista
        recViewGenerales.setItemAnimator(new DefaultItemAnimator());
        recViewRegionales.setItemAnimator(new DefaultItemAnimator());
        recViewDeportes.setItemAnimator(new DefaultItemAnimator());
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
*/
    }

    public void añadirGenerales()
    {
        generales.add(new Opcion(R.drawable.portada_el_paiss));
        generales.add(new Opcion(R.drawable.portada_el_mundo));
        generales.add(new Opcion(R.drawable.portada_la_razon));
        generales.add(new Opcion(R.drawable.portada_abc));
        generales.add(new Opcion(R.drawable.portada_20_minutos));
        generales.add(new Opcion(R.drawable.portada_ok_diario));
        generales.add(new Opcion(R.drawable.portada_el_diario));
        generales.add(new Opcion(R.drawable.portada_el_espaniol));
        generales.add(new Opcion(R.drawable.portada_europa_press));
        generales.add(new Opcion(R.drawable.portada_libertad_digital));
    }
    public void añadirRegionales()
    {
        generales.add(new Opcion(R.drawable.portada_el_paiss));
        generales.add(new Opcion(R.drawable.portada_el_mundo));
        generales.add(new Opcion(R.drawable.portada_la_razon));
        generales.add(new Opcion(R.drawable.portada_abc));
        generales.add(new Opcion(R.drawable.portada_20_minutos));
        generales.add(new Opcion(R.drawable.portada_ok_diario));
        generales.add(new Opcion(R.drawable.portada_el_diario));
        generales.add(new Opcion(R.drawable.portada_el_espaniol));
        generales.add(new Opcion(R.drawable.portada_europa_press));
        generales.add(new Opcion(R.drawable.portada_libertad_digital));
    }

    public void añadirDeportes()
    {
        generales.add(new Opcion(R.drawable.portada_el_paiss));
        generales.add(new Opcion(R.drawable.portada_el_mundo));
        generales.add(new Opcion(R.drawable.portada_la_razon));
        generales.add(new Opcion(R.drawable.portada_abc));
        generales.add(new Opcion(R.drawable.portada_20_minutos));
        generales.add(new Opcion(R.drawable.portada_ok_diario));
        generales.add(new Opcion(R.drawable.portada_el_diario));
        generales.add(new Opcion(R.drawable.portada_el_espaniol));
        generales.add(new Opcion(R.drawable.portada_europa_press));
        generales.add(new Opcion(R.drawable.portada_libertad_digital));
    }


}