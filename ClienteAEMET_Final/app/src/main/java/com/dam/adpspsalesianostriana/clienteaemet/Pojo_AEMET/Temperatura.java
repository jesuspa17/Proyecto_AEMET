package com.dam.adpspsalesianostriana.clienteaemet.Pojo_AEMET;

import org.simpleframework.xml.Element;

/**
 * Created by Jesús Pallares
 */
public class Temperatura {

    @Element(name="maxima")
    private String temp_max;
    @Element(name="minima")
    private String temp_min;

    public Temperatura(){}

    public Temperatura(String temp_max, String temp_min) {
        this.temp_max = temp_max;
        this.temp_min = temp_min;
    }

    public String getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(String temp_max) {
        this.temp_max = temp_max;
    }

    public String getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(String temp_min) {
        this.temp_min = temp_min;
    }

    @Override
    public String toString() {
        return "Temperatura{" +
                "temp_max=" + temp_max +
                ", temp_min=" + temp_min +
                '}';
    }
}
