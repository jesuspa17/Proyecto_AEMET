package com.dam.adpspsalesianostriana.clienteaemet.Pojo_AEMET;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Jesús Pallares on 20/11/2015.
 */
@Root(strict = false) //se pone esto para cojer la información que queramos del xml.
public class Tiempo {

    @Element(name="nombre")
    private String nombre;
    @Element(name="provincia")
    private String nombre_provincia;
    @Element(name="prediccion")
    private Prediccion predicciones;

    @Element(name = "elaborado")
    private String elaborado;

    public Tiempo(){}

    public Tiempo(String nombre, String nombre_provincia, Prediccion predicciones, String elaborado) {
        this.nombre = nombre;
        this.nombre_provincia = nombre_provincia;
        this.predicciones = predicciones;
        this.elaborado = elaborado;
    }

    public String getNombre_provincia() {
        return nombre_provincia;
    }

    public void setNombre_provincia(String nombre_provincia) {
        this.nombre_provincia = nombre_provincia;
    }

    public Prediccion getPredicciones() {
        return predicciones;
    }

    public void setPredicciones(Prediccion predicciones) {
        this.predicciones = predicciones;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getElaborado() {
        return elaborado;
    }

    public void setElaborado(String elaborado) {
        this.elaborado = elaborado;
    }

    @Override
    public String toString() {
        return "Tiempo{" +
                "nombre='" + nombre + '\'' +
                ", nombre_provincia='" + nombre_provincia + '\'' +
                ", predicciones=" + predicciones +
                ", elaborado='" + elaborado + '\'' +
                '}';
    }

}
