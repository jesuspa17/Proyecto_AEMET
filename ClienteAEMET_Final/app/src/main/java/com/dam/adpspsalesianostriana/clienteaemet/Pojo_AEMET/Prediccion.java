package com.dam.adpspsalesianostriana.clienteaemet.Pojo_AEMET;

import org.simpleframework.xml.ElementList;

import java.util.List;

/**
 * Created by Jesús Pallares
 */
public class Prediccion {

    @ElementList(name="dia", inline = true)
    private List<Dia> lista_dias;

    public Prediccion(){}

    public Prediccion(List<Dia> lista_dias) {
        this.lista_dias = lista_dias;
    }

    public List<Dia> getLista_dias() {
        return lista_dias;
    }

    public void setLista_dias(List<Dia> lista_dias) {
        this.lista_dias = lista_dias;
    }


    @Override
    public String toString() {
        return "Prediccion{" +
                "lista_dias=" + lista_dias +
                '}';
    }
}
