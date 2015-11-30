package com.dam.adpspsalesianostriana.clienteaemet.Pojo_Busqueda;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

/**
 * @author Jesús Pallares on 26/11/2015.
 */
@ElementList(inline = true)
public class Municipio {

    @Element
    private String cpro;
    @Element
    private String cmun;
    @Element
    private String nombre;

    public Municipio(){}

    public Municipio(String nombre) {
        this.nombre = nombre;
    }


    public Municipio(String cpro, String cmun, String nombre) {
        this.cpro = cpro;
        this.cmun = cmun;
        this.nombre = nombre;
    }

    public String getCpro() {
        return cpro;
    }

    public void setCpro(String cpro) {
        this.cpro = cpro;
    }

    public String getCmun() {
        return cmun;
    }

    public void setCmun(String cmun) {
        this.cmun = cmun;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Municipio{" +
                "cpro='" + cpro + '\'' +
                ", cmun='" + cmun + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
