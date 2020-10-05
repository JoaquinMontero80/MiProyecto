package com.jobeanda.miproyecto.ui.diarios;

// Clase que se usa para definir las opciones del listado del ListActivity
public class Opcion
{
	// Cada opción tiene un título, un subtítulo y un icono
	private int icono;

	// Constructor
	public Opcion(int icono)
	{
		this.setIcono(icono);
	}

	// Definimos los getters y setters de la clase
	public int getIcono() {
		return icono;
	}
	public void setIcono(int icono) {
		this.icono = icono;
	}

}
