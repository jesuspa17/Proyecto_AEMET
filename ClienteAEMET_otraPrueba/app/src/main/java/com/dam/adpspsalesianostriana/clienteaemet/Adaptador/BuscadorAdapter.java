package com.dam.adpspsalesianostriana.clienteaemet.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.dam.adpspsalesianostriana.clienteaemet.CodigoProvincia;
import com.dam.adpspsalesianostriana.clienteaemet.Pojo_Busqueda.Municipio;
import com.dam.adpspsalesianostriana.clienteaemet.R;

import java.util.List;

/**
 * @author Jesús Pallares on 14/11/2015.
 */
public class BuscadorAdapter extends ArrayAdapter<Municipio> implements Filterable {

    List<Municipio> lista;
    Context contexto;

    public BuscadorAdapter(Context context, List<Municipio> values) {
        super(context, -1, values);
        this.contexto = context;
        this.lista = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) contexto
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.list_item_buscador, parent, false);
        TextView nombreCiudad = (TextView) v.findViewById(R.id.textViewCiudadBuscada);
        TextView nombreLoc = (TextView) v.findViewById(R.id.textViewLocalicacion);
        Municipio ciudadActual = lista.get(position);

        nombreCiudad.setText(ciudadActual.getNombre());

        String provincia_completo = CodigoProvincia.getProvincia(ciudadActual.getCpro());
        nombreLoc.setText(provincia_completo);

        return v;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint != null) {

                    filterResults.values = lista;
                    filterResults.count = lista.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && results.count > 0) {
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }
        };
        return filter;
    }
}
