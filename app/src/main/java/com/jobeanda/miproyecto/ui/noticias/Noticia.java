package com.jobeanda.miproyecto.ui.noticias;

import java.util.Date;

public class Noticia
{
    String titulo;
    String enlace;
    //String descripcion;
    String url_imagen;
    String fecha;
    private Date fechaDate;

    public Date getFechaDate() {
        return fechaDate;
    }

    public void setFechaDate(Date fechaDate) {
        this.fechaDate = fechaDate;
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }
/*
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
*/
    public String getUrl_imagen() {
        return url_imagen;
    }

    public void setUrl_imagen(String url_imagen) {
        this.url_imagen = url_imagen;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
