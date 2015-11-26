package com.dam.adpspsalesianostriana.clienteaemet.Pojo_AEMET;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * @author Jesús Pallares on 25/11/2015.
 */
@Root
public class Prob_precipitacion {

    @Attribute(name = "periodo", required = false)
    private String periodo;

    public Prob_precipitacion(){}

    public Prob_precipitacion(String periodo) {
        this.periodo = periodo;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    @Override
    public String toString() {
        return "Prob_precipitacion{"+
                "periodo='" + periodo + '\'' +
                '}';
    }
}
