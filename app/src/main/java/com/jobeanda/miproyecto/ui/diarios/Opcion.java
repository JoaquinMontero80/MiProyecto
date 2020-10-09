package com.jobeanda.miproyecto.ui.diarios;

// Clase que se usa para definir las opciones del listado del ListActivity
public class Opcion
{
	// Cada opción tiene un título, un subtítulo y un icono
	private int icono;
	private String url_imagen;

	public Opcion(int icono, String url_imagen) {
		this.icono = icono;
		this.url_imagen = url_imagen;
	}

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

	public String getUrl_imagen() {
		return url_imagen;
	}

	public void setUrl_imagen(String url_imagen) {
		this.url_imagen = url_imagen;
	}
}
