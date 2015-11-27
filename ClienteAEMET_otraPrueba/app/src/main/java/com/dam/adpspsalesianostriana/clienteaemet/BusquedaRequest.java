package com.dam.adpspsalesianostriana.clienteaemet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.dam.adpspsalesianostriana.clienteaemet.Adaptador.BuscadorAdapter;
import com.dam.adpspsalesianostriana.clienteaemet.Pojo_Busqueda.Municipios;

/**
 * Created by Jesús Pallares on 27/11/2015.
 */
public class BusquedaRequest{

    Context context;
    AutoCompleteTextView autoCompleteTextView;

    public BusquedaRequest(final Context context, final AutoCompleteTextView autoCompleteTextView) {

        this.context = context;
        this.autoCompleteTextView = autoCompleteTextView;

        String url_municipios = MainActivity.URL_BUSCADOR + autoCompleteTextView.getText().toString();
        VolleyApplication.requestQueue.add(new SimpleXMLRequest<Municipios>(url_municipios, Municipios.class, null, new Response.Listener<Municipios>() {
            @Override
            public void onResponse(final Municipios response) {

                Log.i("MUNICIPIOS", response.toString());
                autoCompleteTextView.setAdapter(new BuscadorAdapter(context, response.getMunicipio()));
                autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        String codigo_ciudad = response.getMunicipio().get(position).getCpro().concat(response.getMunicipio().get(position).getCmun());
                        ((Activity) context).finish();
                        Intent i = new Intent(context, MainActivity.class);
                        i.putExtra("codigo", codigo_ciudad);
                        context.startActivity(i);
                    }

                });

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        ));

    }
}
