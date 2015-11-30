package com.dam.adpspsalesianostriana.clienteaemet.Pojo_AEMET;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Text;

/**
 * @author Jesús Pallares on 25/11/2015.
 */
public class Prob_precipitacion {

    @Attribute(name = "periodo",required = false)
    private String periodo;

    @Text(required = false)
    private String valor;

    public Prob_precipitacion(){}

    public Prob_precipitacion(String periodo, String valor) {
        this.periodo = periodo;
        this.valor = valor;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getValor() {
        return valor;
    }

    public void setTexto(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Prob_precipitacion{" +
                "periodo='" + periodo + '\'' +
                ", valor='" + valor + '\'' +
                '}';
    }
}
