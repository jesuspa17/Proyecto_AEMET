package com.dam.adpspsalesianostriana.clienteaemet.Pojo_AEMET;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * @author Jesús Pallares on 25/11/2015.
 */
@Root(strict = false)
public class Cota_nieve_prov {

    @Attribute(name ="periodo", required = false)
    private String periodo;

    public Cota_nieve_prov(){}

    public Cota_nieve_prov(String periodo) {
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
        return "Cota_nieve_prov{" +
                "periodo='" + periodo + '\'' +
                '}';
    }
}
