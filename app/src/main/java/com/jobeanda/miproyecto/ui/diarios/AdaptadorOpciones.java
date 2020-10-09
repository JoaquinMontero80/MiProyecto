package com.jobeanda.miproyecto.ui.diarios;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.jobeanda.miproyecto.R;

import java.util.List;


// Definimos el Adaptador a partir de la clase RecyclerView.Adapter
// que dibuja la opciones del listado del tipo RecyclerView.
// Además, implementa el evento onClick del mismo
class AdaptadorOpciones extends RecyclerView.Adapter<OpcionViewHolder> implements View.OnClickListener
{
	// Matriz dinámica para guardar los datos
	private List<Opcion> generales;
	// Variable para guardar el puntero al método onClick para llamarlo cuando sea necesario
	private View.OnClickListener listener;

	// Contructor del adaptador usando una matriz dinámica del tipo Opcion
	AdaptadorOpciones(List<Opcion> generales)
	{
		// ¡OJO! NO es necesario invocar al constructor de la clase superior
		// Guardamos estas variables de la aplicación principal para usarlas luego
		this.generales = generales;
	}

	/*
	===============================================================
	Sobrescribimos sus tres métodos obligatorios:

		- onCreateViewHolder(): crea los nuevos objetos ViewHolder asociados con las opciones mos-
		tradas.

		- onBindViewHolder(): actualiza los datos de un ViewHolder ya existente.
		- onItemCount(): indica el número de elementos de la colección de datos.

	=================================================================
	 */


	// Evento que se lanza cuando ya es necesario dibujar una opción del listado
	@Override
	public OpcionViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
	{
		// Inflamos la opción con el layout correspondiente
		View itemView = LayoutInflater.from(viewGroup.getContext())
				.inflate(R.layout.recyclerview_item_diarios, viewGroup, false);
		// Establecemos el evento onClick de la opción
		itemView.setOnClickListener(this);
		// Definimos la nueva opción a partir del elemento anterior
		OpcionViewHolder ovh = new OpcionViewHolder(itemView);
		// Devolvemos ya la opción dentro la clase OpcionViewHolder
		return ovh;

	}


	// Método que se lanza cada vez que Android debe dibujar una opción en una determinada posición
	@Override
	public void onBindViewHolder(OpcionViewHolder viewHolder, int pos)
	{
		// Simplemente optenemos los datos en esa posición
		Opcion item = generales.get(pos);
		// Añadimos (bind=ligamos) al ViewHolder los datos
		viewHolder.bindOpcion(item);
	}

	// Devuelve el nº de elementos de la lista
	@Override
	public int getItemCount() {
		return generales.size();
	}
	
	// Método para establecer el evento onClick de la lista
	public void setOnClickListener(View.OnClickListener listener) {
		this.listener = listener;
	}
	
	// Método que se ejecuta cuando se invoca el evento onClick
	@Override
	public void onClick(View view)
	{
		// ¡Sólo se invoca si está establecido previamente!
		if(listener != null)
			listener.onClick(view);
	}

} // end class AdaptadorOpciones

//==============================================================================================================

// Clase que se usa para almacenar las 2 etiquetas de tipo TextView y un icono de tipo ImageView de una opción
class OpcionViewHolder extends RecyclerView.ViewHolder
{
	private ImageView icono;

	public OpcionViewHolder(View itemView)
	{
		super(itemView);
		icono = (ImageView) itemView.findViewById(R.id.imagenDiario);

	}

	public void bindOpcion(Opcion t)
	{
		icono.setImageResource(t.getIcono());
	}

} // Fin clase OpcionViewHolder

