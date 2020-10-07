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
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class LectorRss extends AsyncTask<Void, Void, Void>
{
    // Creo ArrayList donde voy a añadir los atributos de clase Noticia
    ArrayList<Noticia> noticias = new ArrayList<>();

    Context context;
    // Referencia al RecyclerView
    RecyclerView recyclerview;

    // Direccion del feed
    String direccion = "https://www.20minutos.es/rss/";
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
        // Instancia el adaptador y le pasa los 2 parametros de su constructor ( ArrayList y Context )
        AdapterNoticias adaptadorNoticias = new AdapterNoticias(noticias, context);
        // Crea el estilo de RecyclerView
        recyclerview.setLayoutManager(new LinearLayoutManager(context));
        recyclerview.setAdapter(adaptadorNoticias);
        super.onPostExecute(aVoid);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        procesarXML(obtenerDatos());
        return null;
    }

    private void procesarXML(Document data)
    {
        if(data!=null)
        {
            // Introducimos en un Nodo toda la documentación del RSS
            Element root = data.getDocumentElement();
            Node channel = root.getChildNodes().item(1);
            // Almaceno en variable todos los item del rss
            NodeList items = channel.getChildNodes();

            // Recorro los item
            for(int i=0; i<items.getLength(); i++)
            {
                Node hijoActual = items.item(i);
                // Si el componente por el que navegamos tiene la etiqueta item
                if(hijoActual.getNodeName().equalsIgnoreCase("item"))
                {
                    //Instancia objeto Noticia
                    Noticia noticia = new Noticia();
                    // Almacenamos lo que contiene la noticia de 1 item
                    NodeList itemsChild = hijoActual.getChildNodes();
                    // Recorremos el item de la noticia
                    for(int j=0; j<itemsChild.getLength(); j++)
                    {
                        Node actual = itemsChild.item(j);

                        // Si es igual a titulo
                        if(actual.getNodeName().equalsIgnoreCase("title"))
                        {
                            noticia.setTitulo(actual.getTextContent());
                        } // link
                        else if(actual.getNodeName().equalsIgnoreCase("link"))
                        {
                            noticia.setEnlace(actual.getTextContent());
                        } // Descripcion ( content:encoded ) se llama
             /*           else if(actual.getNodeName().equalsIgnoreCase("content:encoded"))
                        {
                            noticia.setDescripcion(actual.getTextContent());
                        } // URL a imagen*/
                        else if(actual.getNodeName().equalsIgnoreCase("media:content"))
                        {
                            //noticia.setUrl_imagen(actual.getTextContent());
                            // El enlace de la imagen, dentro del item, tiene varios atributos
                            // Voy a necesitar el tercero(posicion 2) que es la URL.  ( https://www.20minutos.es/rss/ )
                            String miUrl = actual.getAttributes().item(1).getTextContent();
                            noticia.setUrl_imagen(miUrl);
                        } // Fecha publicacion
                        else if(actual.getNodeName().equalsIgnoreCase("pubDate"))
                        {
                            noticia.setFecha(actual.getTextContent());
                        }

                    } // Fin de for interno(j)

                    // Añado los elementos al ArrayList
                    noticias.add(noticia);
                    // Log.d("title", noticia.getTitulo());
                    // Log.d("link", noticia.getEnlace());
                    // Log.d("content:encoded", noticia.getDescripcion());
                    // Log.d("media:content", noticia.getUrl_imagen());
                    // Log.d("pubDate", noticia.getFecha());
                }
            } // Fin de for principal(i)

        } // Fin de if principal

    } // Fin de metodo procesarXML


    // Metodo obtener los datos del FEED
    public Document obtenerDatos()
    {
        try
        {
            // Inicializo URL con la direccion del FEED
            url = new URL(direccion);
            // Conexion
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            // Asigno el tipo de conexion que va a ser, ( get --> obtener datos )
            connection.setRequestMethod("GET");
            // Procesa la peticion
            InputStream inputStream = connection.getInputStream();
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document xmlDoc = builder.parse(inputStream);
            return xmlDoc;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }

    } // Fin de metodo obtenerDatos

}
