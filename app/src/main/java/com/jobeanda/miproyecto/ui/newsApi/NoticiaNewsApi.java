package com.jobeanda.miproyecto.ui.newsApi;

import java.util.Date;

public class NoticiaNewsApi {
    private String titulo;
    private String descripcion;
    private String enlace;
    private String url_imagen;
    private String fecha;
    private Date fechaDate;
    private String fuente;

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getEnlace() { return enlace; }
    public void setEnlace(String enlace) { this.enlace = enlace; }

    public String getUrl_imagen() { return url_imagen; }
    public void setUrl_imagen(String url_imagen) { this.url_imagen = url_imagen; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public Date getFechaDate() { return fechaDate; }
    public void setFechaDate(Date fechaDate) { this.fechaDate = fechaDate; }

    public String getFuente() { return fuente; }
    public void setFuente(String fuente) { this.fuente = fuente; }
}