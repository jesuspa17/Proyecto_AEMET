package com.dam.adpspsalesianostriana.clienteaemet;

import android.content.Intent;
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
import com.dam.adpspsalesianostriana.clienteaemet.Pojo_AEMET.Tiempo;
import com.dam.adpspsalesianostriana.clienteaemet.Pojo_Busqueda.Municipios;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    TextView ciudad;
    TextView fecha_elaboracion;

    AutoCompleteTextView autoCompleteCiudad;

    public static String FORMATO_FECHA = "yyyy-MM-dd'T'HH:mm:ss";

    final static String URL_BUSCADOR = "http://www.salesianos-triana.com/dam/xml/municipios/?ciudad=";
    public String URL_AEMET(String codigo) {
        return "http://www.aemet.es/xml/municipios/localidad_" + codigo + ".xml";
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ciudad = (TextView) findViewById(R.id.textViewCiudad);
        fecha_elaboracion = (TextView) findViewById(R.id.textViewFecElab);
        autoCompleteCiudad = (AutoCompleteTextView) findViewById(R.id.autoCompleteCiudad);
        autoCompleteCiudad.setVisibility(View.INVISIBLE);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Bundle extras = getIntent().getExtras();
        String url_ciudad = "";

        if (extras != null) {
            String codigo = extras.getString("codigo");
            if (codigo == null) {
                url_ciudad = this.URL_AEMET("41067");
            } else {
                url_ciudad = this.URL_AEMET(codigo);
            }
        } else {
            url_ciudad = URL_AEMET("41067");
        }

        VolleyApplication.requestQueue.add(new SimpleXMLRequest<Tiempo>(url_ciudad, Tiempo.class, null, new Response.Listener<Tiempo>() {
            @Override
            public void onResponse(Tiempo response) {

                Log.i("TIEMPO", response.toString());

                String nombre_ciudad = response.getNombre();
                String nombre_provincia = response.getNombre_provincia();

                if(nombre_ciudad.equalsIgnoreCase(nombre_provincia)){
                    ciudad.setText(response.getNombre() + " (Capital)");
                }else{
                    ciudad.setText(response.getNombre() + " (" + response.getNombre_provincia() + ")");
                }
                String fec_elab = response.getElaborado();
                fecha_elaboracion.setText("Fecha de elaboración: " + Utils.formatearFechaString(FORMATO_FECHA, fec_elab));

                adapter = new TiempoAdapter(response.getPredicciones().getLista_dias());
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

        //Permite modificar el hint que el EditText muestra por defecto
        searchView.setQueryHint("Buscar ciudad...");

        //Dará sugerencias mientras esté escribiendo
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

                String url_municipios = URL_BUSCADOR + autoCompleteCiudad.getText().toString().replace(" ","%20");

                VolleyApplication.requestQueue.add(new SimpleXMLRequest<Municipios>(url_municipios, Municipios.class, null, new Response.Listener<Municipios>() {
                    @Override
                    public void onResponse(final Municipios response) {

                        Log.i("MUNICIPIOS", response.toString());

                        autoCompleteCiudad.setAdapter(new BuscadorAdapter(MainActivity.this, response.getMunicipio()));
                        autoCompleteCiudad.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                String codigo_ciudad = response.getMunicipio().get(position).getCpro().concat(response.getMunicipio().get(position).getCmun());
                                MainActivity.this.finish();
                                Intent i = new Intent(MainActivity.this, MainActivity.class);
                                i.putExtra("codigo", codigo_ciudad);
                                startActivity(i);
                            }
                        });
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
                ));
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
