package com.dam.adpspsalesianostriana.clienteaemet.Pojo_AEMET;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

/**
 * @author Jesús Pallares on 25/11/2015.
 */
@Root(strict = false)
public class Cota_nieve_prov {

    @Attribute(name ="periodo", required = false)
    private String periodo;

    @Text(required = false)
    private String valor_cota;


    public Cota_nieve_prov(){}

    public Cota_nieve_prov(String periodo, String valor_cota) {
        this.periodo = periodo;
        this.valor_cota = valor_cota;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getValor_cota() {
        return valor_cota;
    }

    public void setValor_cota(String valor_cota) {
        this.valor_cota = valor_cota;
    }

    @Override
    public String toString() {
        return "Cota_nieve_prov{" +
                "periodo='" + periodo + '\'' +
                ", valor_cota='" + valor_cota + '\'' +
                '}';
    }
}
