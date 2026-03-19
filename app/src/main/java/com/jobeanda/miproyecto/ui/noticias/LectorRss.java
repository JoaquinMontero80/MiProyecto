package com.jobeanda.miproyecto.ui.noticias;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class LectorRss extends AsyncTask<Void, Void, Void> {
    // Creo ArrayList donde voy a añadir los atributos de clase Noticia
    ArrayList<Noticia> noticias = new ArrayList<>();

    Context context;
    // Referencia al RecyclerView
    RecyclerView recyclerview;

    // Direccion del feed
    //String direccion = "https://www.20minutos.es/rss/";
    String[] direccion = {"https://www.20minutos.es/rss/", "https://feeds.elpais.com/mrss-s/pages/ep/site/elpais.com/portada",
            "https://www.eldiario.es/rss", "https://www.cope.es/api/es/news/rss.xml", "https://euroweeklynews.com/feed/" };
    // Trabajar con la red
    URL url;
    ProgressDialog progressDialog;

    // Al constructor le añado el recyclerView como segundo parametro
    public LectorRss(Context context, RecyclerView recyclerview) {
        this.context = context;
        this.recyclerview = recyclerview;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Cargando");
    }


    @Override
    protected void onPreExecute() {
        // Miestras carga, muestralo
        progressDialog.show();
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        // Cierra el cargado
        progressDialog.dismiss();

        // Ordenar noticias por fecha: más nueva primero
        Collections.sort(noticias, new Comparator<Noticia>() {
            @Override
            public int compare(Noticia n1, Noticia n2) {
                if (n1.getFechaDate() == null && n2.getFechaDate() == null) return 0;
                if (n1.getFechaDate() == null) return 1;
                if (n2.getFechaDate() == null) return -1;
                return n2.getFechaDate().compareTo(n1.getFechaDate());
            }
        });

        // Instancia el adaptador y le pasa los 2 parametros de su constructor ( ArrayList y Context )
        AdapterNoticias adaptadorNoticias = new AdapterNoticias(noticias, context);
        // Crea el estilo de RecyclerView
        recyclerview.setLayoutManager(new LinearLayoutManager(context));
        recyclerview.setAdapter(adaptadorNoticias);
        super.onPostExecute(aVoid);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        List<Document> documentos = obtenerDatos(direccion);

        for (Document doc : documentos) {
            if (doc != null) {
                procesarXML(doc);
            }
        }

        return null;
    }

    private void procesarXML(Document data) {
        if (data == null) return;

        // Busca directamente todos los items del feed
        NodeList items = data.getElementsByTagName("item");

        for (int i = 0; i < items.getLength(); i++) {
            Node itemNode = items.item(i);

            if (itemNode.getNodeType() != Node.ELEMENT_NODE) continue;

            Element itemElement = (Element) itemNode;
            Noticia noticia = new Noticia();

            // Título
            NodeList titleList = itemElement.getElementsByTagName("title");
            if (titleList.getLength() > 0) {
                noticia.setTitulo(titleList.item(0).getTextContent());
            }

            // Enlace
            NodeList linkList = itemElement.getElementsByTagName("link");
            if (linkList.getLength() > 0) {
                noticia.setEnlace(linkList.item(0).getTextContent());
            }

            // Fecha
            NodeList pubDateList = itemElement.getElementsByTagName("pubDate");
            if (pubDateList.getLength() > 0) {

                String fechaStr = pubDateList.item(0).getTextContent();

                // Mantienes esto (para mostrar)
                noticia.setFecha(fechaStr);

                // Añades esto (para ordenar)
                try {
                    SimpleDateFormat formato =
                            new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);

                    Date fechaDate = formato.parse(fechaStr);
                    noticia.setFechaDate(fechaDate);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // Imagen: 20minutos -> enclosure
            NodeList enclosureList = itemElement.getElementsByTagName("enclosure");
            if (enclosureList.getLength() > 0) {
                Element enclosure = (Element) enclosureList.item(0);
                String urlImagen = enclosure.getAttribute("url");
                if (urlImagen != null && !urlImagen.isEmpty()) {
                    noticia.setUrl_imagen(urlImagen);
                }
            }

            // Imagen: El País -> media:content
            if (noticia.getUrl_imagen() == null || noticia.getUrl_imagen().isEmpty()) {
                NodeList mediaList = itemElement.getElementsByTagName("media:content");
                if (mediaList.getLength() > 0) {
                    Element media = (Element) mediaList.item(0);
                    String urlImagen = media.getAttribute("url");
                    if (urlImagen != null && !urlImagen.isEmpty()) {
                        noticia.setUrl_imagen(urlImagen);
                    }
                }
            }

            noticias.add(noticia);
        }
    }


    // Metodo obtener los datos del FEED
    public List<Document> obtenerDatos(String[] direcciones) {
        List<Document> documentos = new ArrayList<>();

        for (String direccion : direcciones) {
            HttpURLConnection connection = null;
            InputStream inputStream = null;

            try {
                URL url = new URL(direccion);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(10000);
                connection.setReadTimeout(10000);

                inputStream = connection.getInputStream();

                DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
                builderFactory.setNamespaceAware(true);

                DocumentBuilder builder = builderFactory.newDocumentBuilder();
                Document xmlDoc = builder.parse(inputStream);
                xmlDoc.getDocumentElement().normalize();

                documentos.add(xmlDoc);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (inputStream != null) inputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (connection != null) {
                    connection.disconnect();
                }
            }
        }

        return documentos;
    }

}
