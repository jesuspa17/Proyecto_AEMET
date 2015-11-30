package com.dam.adpspsalesianostriana.clienteaemet.Pojo_AEMET;

import org.simpleframework.xml.Attribute;

/**
 * @author Jesús Pallares on 25/11/2015.
 */
public class Estado_cielo {

    @Attribute(name = "periodo", required = false)
    private String periodo;

    @Attribute(name = "descripcion", required = false)
    private String descripcion;

    public Estado_cielo(){}


    public Estado_cielo(String periodo, String descripcion) {
        this.periodo = periodo;
        this.descripcion = descripcion;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Estado_cielo{" +
                "periodo='" + periodo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }

}
