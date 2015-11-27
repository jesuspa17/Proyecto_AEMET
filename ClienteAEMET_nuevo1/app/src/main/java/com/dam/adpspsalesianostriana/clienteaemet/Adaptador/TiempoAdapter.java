package com.dam.adpspsalesianostriana.clienteaemet.Adaptador;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dam.adpspsalesianostriana.clienteaemet.Pojo_AEMET.Dia;
import com.dam.adpspsalesianostriana.clienteaemet.R;
import com.dam.adpspsalesianostriana.clienteaemet.Utils;

import java.util.List;

/**
 * Clase que servirá como adaptador para la lista donde se mostrará la consulta de los 5 días.
 */

public class TiempoAdapter extends RecyclerView.Adapter<TiempoAdapter.ViewHolder> {

    private List<Dia> lista_datos;
    Context contexto;

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

    public TiempoAdapter(List<Dia> lista_datos) {
        this.lista_datos = lista_datos;
    }

    @Override
    public TiempoAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_list_item, viewGroup, false);

        contexto = v.getContext();


        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(TiempoAdapter.ViewHolder holder, int i) {

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
}
