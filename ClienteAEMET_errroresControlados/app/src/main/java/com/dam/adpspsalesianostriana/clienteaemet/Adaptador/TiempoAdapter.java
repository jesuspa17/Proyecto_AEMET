package com.dam.adpspsalesianostriana.clienteaemet.Adaptador;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dam.adpspsalesianostriana.clienteaemet.Pojo_AEMET.Dia;
import com.dam.adpspsalesianostriana.clienteaemet.R;
import com.dam.adpspsalesianostriana.clienteaemet.Utils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Clase que servirá como adaptador para la lista donde se mostrará la consulta de los 5 días.
 */

public class TiempoAdapter extends RecyclerView.Adapter<TiempoAdapter.ViewHolder> {

    private List<Dia> lista_datos;
    Context contexto;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView estado_cielo, temp_max, temp_min, sens_max, sens_min, prob_prec, prob_niev, fecha;
        ImageView cielo;


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
            cielo = (ImageView) v.findViewById(R.id.imageViewEstado);

        }
    }

    public TiempoAdapter(List<Dia> lista_datos) {

        this.lista_datos = lista_datos;
    }

    @Override
    public TiempoAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_item, viewGroup, false);
        contexto = v.getContext();
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TiempoAdapter.ViewHolder holder, int i) {

        Dia dia = lista_datos.get(i);

        for (int x = 0; x < 6; x++) {
            String d = dia.getEstado_cielo().get(x).getDescripcion();
            if (!d.isEmpty()) {
                holder.estado_cielo.setText(d);
                Picasso.with(contexto)
                        .load(Utils.cambiarImagen(d))
                        .placeholder(android.R.drawable.ic_menu_rotate)
                        .into(holder.cielo);
            }
            break;
        }

        holder.fecha.setText(Utils.formatearFechaDate(dia.getFecha()));
        holder.temp_max.setText(dia.getTemperatura().getTemp_max() + "º");
        holder.temp_min.setText(dia.getTemperatura().getTemp_min() + "º");
        holder.sens_max.setText(dia.getSens_termica().getMaxima() + "º");
        holder.sens_min.setText(dia.getSens_termica().getMinima() + "º");

        int tam_array_prec = dia.getProb_precipitacion().size();
        
        //CÁLCULO DE LAS PRECIPITACIONES

        int num_precipitaciones = 0;
        for (int y = 0; y < tam_array_prec; y++) {

            if (dia.getProb_precipitacion().get(y).getValor() != null) {
                int dia_obtenido = Integer.parseInt(dia.getProb_precipitacion().get(y).getValor());
                num_precipitaciones = num_precipitaciones + dia_obtenido;
            }
        }
        int total_prec = num_precipitaciones / tam_array_prec;
        holder.prob_prec.setText(total_prec + " %");


        //CÁLCULO DE LA COTA DE NIEVE

        int tam_array_cotanieve = dia.getCota_nieve_prov().size();
        int num_cotas = 0;
        for (int n = 0; n < tam_array_cotanieve; n++) {
            if (dia.getCota_nieve_prov().get(n).getValor_cota() != null) {
                int cota_obtenida = Integer.parseInt(dia.getCota_nieve_prov().get(n).getValor_cota());
                num_cotas = num_cotas + cota_obtenida;
            }
        }
        int total_cot = num_cotas / tam_array_cotanieve;
        holder.prob_niev.setText(total_cot +  " m");


    }

    @Override
    public int getItemCount() {
        return lista_datos.size();
    }
}
