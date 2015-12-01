package com.dam.adpspsalesianostriana.clienteaemet.Pojo_AEMET;

import org.simpleframework.xml.Element;

/**
 * @author Jesús Pallares
 */
public class Sens_termica {

    @Element(name = "maxima")
    private String maxima;
    @Element (name = "minima")
    private String minima;

    public Sens_termica(){}

    public Sens_termica(String maxima, String minima) {
        this.maxima = maxima;
        this.minima = minima;
    }

    public String getMaxima() {
        return maxima;
    }

    public void setMaxima(String maxima) {
        this.maxima = maxima;
    }

    public String getMinima() {
        return minima;
    }

    public void setMinima(String minima) {
        this.minima = minima;
    }

    @Override
    public String toString() {
        return "Sens_termica{" +
                "maxima='" + maxima + '\'' +
                ", minima='" + minima + '\'' +
                '}';
    }
}
