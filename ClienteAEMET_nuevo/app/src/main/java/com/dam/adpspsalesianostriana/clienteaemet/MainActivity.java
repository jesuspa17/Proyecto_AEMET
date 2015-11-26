package com.dam.adpspsalesianostriana.clienteaemet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.dam.adpspsalesianostriana.clienteaemet.Adaptador.TiempoAdapter;
import com.dam.adpspsalesianostriana.clienteaemet.Pojo_AEMET.Tiempo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    TextView ciudad;
    TextView fecha_elaboracion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ciudad = (TextView) findViewById(R.id.textViewCiudad);
        fecha_elaboracion = (TextView) findViewById(R.id.textViewFecElab);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        String url = "http://www.aemet.es/xml/municipios/localidad_41067.xml";

        VolleyApplication.requestQueue.add(new SimpleXMLRequest<Tiempo>(url,Tiempo.class, null, new Response.Listener<Tiempo>() {
            @Override
            public void onResponse(Tiempo response) {

                Log.i("TIEMPO", response.toString());

                ciudad.setText(response.getNombre() + " ("+response.getNombre_provincia()+")");

                //Meterlo en un método si es posible.
                String fec_elab = response.getElaborado();
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                SimpleDateFormat f1 = new SimpleDateFormat("dd/MM/yyyy");
                String fecha_format = "";

                try {
                    Date date = f.parse(fec_elab);
                    Log.i("DATE", date.toString());
                    fecha_format = f1.format(date);
                    Log.i("DATE", fecha_format);

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                fecha_elaboracion.setText("Fecha de elaboración: "+fecha_format);

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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
