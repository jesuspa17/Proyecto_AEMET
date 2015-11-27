package com.dam.adpspsalesianostriana.clienteaemet.Adaptador;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.dam.adpspsalesianostriana.clienteaemet.MainActivity;
import com.dam.adpspsalesianostriana.clienteaemet.Pojo_AEMET.Dia;
import com.dam.adpspsalesianostriana.clienteaemet.Pojo_AEMET.Tiempo;
import com.dam.adpspsalesianostriana.clienteaemet.R;
import com.dam.adpspsalesianostriana.clienteaemet.SimpleXMLRequest;
import com.dam.adpspsalesianostriana.clienteaemet.Utils;
import com.dam.adpspsalesianostriana.clienteaemet.VolleyApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que servirá como adaptador para la lista donde se mostrará la consulta de los 5 días.
 */

public class TiempoAdapter_prueba extends RecyclerView.Adapter<TiempoAdapter_prueba.ViewHolder> {

    List<Dia> lista_datos = new ArrayList<Dia>();
    Context contexto;
    TextView textViewciudad;
    TextView textViewfecha;
    String url;


    public TiempoAdapter_prueba(Context context, String url, final TextView textViewciudad, final TextView textViewfecha) {
        this.contexto = context;
        this.textViewciudad = textViewciudad;
        this.textViewfecha = textViewfecha;
        this.url = url;


        VolleyApplication.requestQueue.add(new SimpleXMLRequest<Tiempo>(url, Tiempo.class, null, new Response.Listener<Tiempo>() {
            @Override
            public void onResponse(Tiempo response) {

                Log.i("TIEMPO", response.toString());

                textViewciudad.setText(response.getNombre() + " (" + response.getNombre_provincia() + ")");
                String fec_elab = response.getElaborado();
                textViewfecha.setText("Fecha de elaboración: " + Utils.formatearFechaString(MainActivity.FORMATO_FECHA, fec_elab));

                lista_datos = response.getPredicciones().getLista_dias();
                notifyDataSetChanged();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        })
        );


    }

    @Override
    public TiempoAdapter_prueba.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_list_item, viewGroup, false);

        contexto = v.getContext();


        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(TiempoAdapter_prueba.ViewHolder holder, int i) {

        Dia dia = lista_datos.get(i);

        String d0 = dia.getEstado_cielo().get(0).getDescripcion();

        if(d0.isEmpty()){
            String d1 = dia.getEstado_cielo().get(1).getDescripcion();
            String d2 = dia.getEstado_cielo().get(2).getDescripcion();
            if(d1.isEmpty()){
                holder.estado_cielo.setText(d2);
            }else{
                holder.estado_cielo.setText(d1);
            }
        }else{
            holder.estado_cielo.setText(d0);
        }

        holder.fecha.setText(Utils.formatearFechaDate(dia.getFecha()));

        holder.temp_max.setText(dia.getTemperatura().getTemp_max()+"º");
        holder.temp_min.setText(dia.getTemperatura().getTemp_min()+"º");
        holder.sens_max.setText(dia.getSens_termica().getMaxima()+"º");
        holder.sens_min.setText(dia.getSens_termica().getMinima()+"º");

    }

    @Override
    public int getItemCount()  {
        return lista_datos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView estado_cielo, temp_max, temp_min, sens_max, sens_min, prob_prec, prob_niev,fecha;


        public ViewHolder(View v) {
            super(v);
            estado_cielo = (TextView) v.findViewById(R.id.textViewEstadoCielo);
            temp_max = (TextView) v.findViewById(R.id.textViewTempMax);
            temp_min = (TextView) v.findViewById(R.id.textViewTempMin);
            sens_max = (TextView) v.findViewById(R.id.textViewSensMax);
            sens_min = (TextView) v.findViewById(R.id.textViewSensMin);
            prob_niev = (TextView) v.findViewById(R.id.textViewNev);
            prob_prec = (TextView) v.findViewById(R.id.textViewPrec);
            fecha = (TextView) v.findViewById(R.id.textViewFechaDia);

        }
    }
}
