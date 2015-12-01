package com.dam.adpspsalesianostriana.clienteaemet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.dam.adpspsalesianostriana.clienteaemet.Adaptador.BuscadorAdapter;
import com.dam.adpspsalesianostriana.clienteaemet.Adaptador.TiempoAdapter;
import com.dam.adpspsalesianostriana.clienteaemet.Pojo_AEMET.Dia;
import com.dam.adpspsalesianostriana.clienteaemet.Pojo_AEMET.Tiempo;
import com.dam.adpspsalesianostriana.clienteaemet.Pojo_Busqueda.Municipio;
import com.dam.adpspsalesianostriana.clienteaemet.Pojo_Busqueda.Municipios;

import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    TextView txt_ciudad;
    TextView txt_fecha_elaboracion;
    AutoCompleteTextView autoCompleteCiudad;


    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    public static String FORMATO_FECHA = "yyyy-MM-dd'T'HH:mm:ss";

    /**
     * EMPLEADA PARA BUSCAR MUNICIPIOS
     **/
    final static String URL_BUSCADOR = "http://www.salesianos-triana.com/dam/xml/municipios/?ciudad=";

    /**
     * URL QUE OBTIENE EL XML DE LA LOCALIDAD A TRAVÉS DEL CÓDIGO CORRESPONDIENTE DE LA PÁGINA DE AEMET
     **/
    public String URL_AEMET(String codigo) {
        return "http://www.aemet.es/xml/municipios/localidad_" + codigo + ".xml";
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Elementos rescatados de la UI
        txt_ciudad = (TextView) findViewById(R.id.textViewCiudad);
        txt_fecha_elaboracion = (TextView) findViewById(R.id.textViewFecElab);
        autoCompleteCiudad = (AutoCompleteTextView) findViewById(R.id.autoCompleteCiudad);
        autoCompleteCiudad.setVisibility(View.INVISIBLE);

        //Inicializo las caractersísticas del recyclerView
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Inicializo las preferencias.
        prefs = getSharedPreferences("preferencias_aemet", MODE_PRIVATE);

        String url_ciudad = "";
        if (prefs.getString("ciudad_defecto", null) != null) {
            url_ciudad = URL_AEMET(prefs.getString("ciudad_defecto", null));
        } else {
            url_ciudad = this.URL_AEMET("41067");
        }

        //Aquí se obtienen los datos de la población a través de la URL formada para
        //mostrarlos en el Recycler
        VolleyApplication.requestQueue.add(new SimpleXMLRequest<Tiempo>(url_ciudad, Tiempo.class, null, new Response.Listener<Tiempo>() {
            @Override
            public void onResponse(Tiempo response) {

                Log.i("TIEMPO", response.toString());

                //1. Guardo los elementos obtenidos del XML.
                String nombre_ciudad = response.getNombre();
                String nombre_provincia = response.getNombre_provincia();
                String fec_elab = response.getElaborado();
                List<Dia> lista_dias = response.getPredicciones().getLista_dias();

                //2. Actualizo los elementos de la interfaz de usuario con los elementos que he guardado arriba.
                //(Es para tener la imformación mas organizada)

                //Si la el nombre de la ciudad coincide con el de la provincia, quiere
                //decir que es una capital, dado esto, indico en el textview correspondiente que es Capital.
                if (nombre_ciudad.equalsIgnoreCase(nombre_provincia)) {
                    txt_ciudad.setText(response.getNombre() + " (Capital)");
                } else {
                    txt_ciudad.setText(response.getNombre() + " (" + response.getNombre_provincia() + ")");
                }
                txt_fecha_elaboracion.setText("Fecha de elaboración: " + Utils.formatearFechaString(FORMATO_FECHA, fec_elab));

                adapter = new TiempoAdapter(lista_dias);
                recyclerView.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }
        ));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        autoCompleteCiudad.setVisibility(View.VISIBLE);

        //Se obtiene el icono buscar del menú y se le asocia un searchview
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint("Buscar ciudad...");


        // /Dará sugerencias mientras esté escribiendo...
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                autoCompleteCiudad.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        autoCompleteCiudad.showDropDown();
                    }
                }, 0);

                autoCompleteCiudad.setText(newText);

                if (autoCompleteCiudad.getText().length() > 3) {

                    String url_municipios = URL_BUSCADOR + autoCompleteCiudad.getText().toString().replace(" ", "%20");

                    //Aquí se obtienen los resultados que se obtienen de la búsqueda de algún municipio.
                    //Los resultados se mostrarán en una lista y al pulsar sobre alguno, se abrirá de nuevo este
                    //activiy con los datos de ese municipio cargado.

                    VolleyApplication.requestQueue.add(new SimpleXMLRequest<Municipios>(url_municipios, Municipios.class, null, new Response.Listener<Municipios>() {
                        @Override
                        public void onResponse(final Municipios response) {

                            Log.i("MUNICIPIOS", response.toString());

                            autoCompleteCiudad.setAdapter(new BuscadorAdapter(MainActivity.this, response.getMunicipio(), R.layout.list_item_buscador));
                            autoCompleteCiudad.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                    //Estas preferencias servirán para almacenar en una variable
                                    //"ciudad_defecto" el código de la última ciudad seleccionada y abrir esa ciudad
                                    //cada vez que se vuelva a abrir la aplicación.
                                    prefs = getSharedPreferences("preferencias_aemet", MODE_PRIVATE);
                                    editor = prefs.edit();

                                    //En esta variable se forma el código que se le debe pasar a la url
                                    //de AEMET. Esta será la que guardemos en preferencias.
                                    String codigo_ciudad = response
                                            .getMunicipio()
                                            .get(position).getCpro()
                                            .concat(response.getMunicipio().get(position).getCmun());

                                    editor.putString("ciudad_defecto", codigo_ciudad);
                                    editor.commit();

                                    //Se lanza el activity de nuevo.
                                    MainActivity.this.finish();
                                    Intent i = new Intent(MainActivity.this, MainActivity.class);
                                    startActivity(i);
                                }
                            });
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //Aquí se controlan los errores que puede lanzar la URL mientras buscamos alguna ciudad.
                            //Si entra en el fallo, se mostará en la lista de resultados un aviso de que no se ha encontrado ningún resultado
                            int statusError = error.networkResponse.statusCode;
                            if (statusError == HttpsURLConnection.HTTP_NOT_FOUND || statusError == HttpsURLConnection.HTTP_FORBIDDEN) {
                                List<Municipio> list = new ArrayList<Municipio>();
                                list.add(new Municipio("", "", "No se han encontrado resultados"));
                                autoCompleteCiudad.setAdapter(new BuscadorAdapter(MainActivity.this, list, R.layout.error_item));
                                Log.i("ERROR CONTROLADO", "ERROR CONTROLADO");
                            }
                        }
                    }
                    ));
                }
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
