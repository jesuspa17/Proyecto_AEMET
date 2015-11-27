package com.dam.adpspsalesianostriana.clienteaemet.Pojo_Busqueda;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * @author Jesús Pallares on 26/11/2015.
 */
@Root(strict = false)
public class Municipios {

    @ElementList(inline = true)
    private List<Municipio> municipio;

    public Municipios() {
    }

    public Municipios(List<Municipio> municipio) {
        this.municipio = municipio;
    }

    public List<Municipio> getMunicipio() {
        return municipio;
    }

    public void setMunicipio(List<Municipio> municipio) {
        this.municipio = municipio;
    }

    @Override
    public String toString() {
        return "Municipios{" +
                "municipio=" + municipio +
                '}';
    }
}
