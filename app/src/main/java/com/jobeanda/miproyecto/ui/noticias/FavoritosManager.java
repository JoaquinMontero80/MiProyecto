package com.jobeanda.miproyecto.ui.noticias;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Map;

public class FavoritosManager {

    private static final String PREFS_NAME = "favoritos";
    private final SharedPreferences prefs;
    private final Gson gson;

    public FavoritosManager(Context context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    private String getClave(Noticia noticia) {
        if (noticia == null || noticia.getEnlace() == null) return null;
        return noticia.getEnlace().trim();
    }

    public void guardarFavorito(Noticia noticia) {
        String clave = getClave(noticia);
        if (clave == null || clave.isEmpty()) return;

        String noticiaJson = gson.toJson(noticia);
        prefs.edit().putString(clave, noticiaJson).apply();
    }

    public void eliminarFavorito(Noticia noticia) {
        String clave = getClave(noticia);
        if (clave == null || clave.isEmpty()) return;

        prefs.edit().remove(clave).apply();
    }

    public boolean esFavorito(Noticia noticia) {
        String clave = getClave(noticia);
        if (clave == null || clave.isEmpty()) return false;

        return prefs.contains(clave);
    }

    public ArrayList<Noticia> obtenerFavoritos() {
        ArrayList<Noticia> favoritos = new ArrayList<>();
        Map<String, ?> todos = prefs.getAll();

        for (Map.Entry<String, ?> entry : todos.entrySet()) {
            Object value = entry.getValue();
            if (value != null) {
                Noticia noticia = gson.fromJson(value.toString(), Noticia.class);
                if (noticia != null) {
                    favoritos.add(noticia);
                }
            }
        }

        return favoritos;
    }
}