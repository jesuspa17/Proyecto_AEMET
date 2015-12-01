package com.dam.adpspsalesianostriana.clienteaemet.Pojo_AEMET;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.Date;
import java.util.List;

/**
 * Created by Jesús Pallares
 */
public class Dia {

    @Attribute(name="fecha")
    private Date fecha;

    @Element(name="temperatura")
    private Temperatura temperatura;

    @ElementList(name = "cota_nieve_prov", inline = true)
    private List<Cota_nieve_prov> cota_nieve_prov;

    @ElementList(name = "prob_precipitacion", inline = true)
    private List<Prob_precipitacion> prob_precipitacion;

    @ElementList(name = "estado_cielo", inline = true)
    private List<Estado_cielo> estado_cielo;

    @Element(name = "sens_termica")
    private Sens_termica sens_termica;


    public Dia(){}

    public Dia(Date fecha, Temperatura temperatura, List<Cota_nieve_prov> cota_nieve_prov, List<Prob_precipitacion> prob_precipitacion, List<Estado_cielo> estado_cielo, Sens_termica sens_termica) {
        this.fecha = fecha;
        this.temperatura = temperatura;
        this.cota_nieve_prov = cota_nieve_prov;
        this.prob_precipitacion = prob_precipitacion;
        this.estado_cielo = estado_cielo;
        this.sens_termica = sens_termica;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Temperatura getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Temperatura temperatura) {
        this.temperatura = temperatura;
    }

    public List<Cota_nieve_prov> getCota_nieve_prov() {
        return cota_nieve_prov;
    }

    public void setCota_nieve_prov(List<Cota_nieve_prov> cota_nieve_prov) {
        this.cota_nieve_prov = cota_nieve_prov;
    }

    public List<Prob_precipitacion> getProb_precipitacion() {
        return prob_precipitacion;
    }

    public void setProb_precipitacion(List<Prob_precipitacion> prob_precipitacion) {
        this.prob_precipitacion = prob_precipitacion;
    }

    public List<Estado_cielo> getEstado_cielo() {
        return estado_cielo;
    }

    public void setEstado_cielo(List<Estado_cielo> estado_cielo) {
        this.estado_cielo = estado_cielo;
    }

    public Sens_termica getSens_termica() {
        return sens_termica;
    }

    public void setSens_termica(Sens_termica sens_termica) {
        this.sens_termica = sens_termica;
    }

    @Override
    public String toString() {
        return "Dia{" +
                "fecha=" + fecha +
                ", temperatura=" + temperatura +
                ", cota_nieve_prov=" + cota_nieve_prov +
                ", prob_precipitacion=" + prob_precipitacion +
                ", estado_cielo=" + estado_cielo +
                ", sens_termica=" + sens_termica +
                '}';
    }
}
