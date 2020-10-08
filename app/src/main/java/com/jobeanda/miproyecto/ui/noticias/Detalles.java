package com.jobeanda.miproyecto.ui.noticias;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.jobeanda.miproyecto.R;

public class Detalles extends AppCompatActivity
{
    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);
        // Busca en R la id del WebView (activity_detalles.xml)
        webview = (WebView) findViewById(R.id.webViewDetalles);

        // Captura en Bundle lo que obtiene del putExtra() del onClick de AdapterNoticias, al pinchar en imagen carga enlace de web
        Bundle bundle = getIntent().getExtras();
        // Guardo en un String la url de ese Bundle
        String url = bundle.getString("Enlace");

        // Crea el mini navegador
        WebSettings websetting = webview.getSettings();
        websetting.setJavaScriptEnabled(true);
        websetting.setLoadsImagesAutomatically(true);
        // Carga
        webview.loadUrl(url);

    } // end onCreate

} // Fin de clase