package com.dam.adpspsalesianostriana.clienteaemet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jesús Pallares on 26/11/2015.
 */
public class Utils {

    public static String formatearFechaDate(Date date) {
        SimpleDateFormat d = new SimpleDateFormat("dd 'de' MMMM");
        String dias = d.format(date).substring(0, 5);
        String mes_normal = d.format(date);
        String mes_capitalize = mes_normal.substring(6, 7).toUpperCase() + mes_normal.substring(7);
        String fecha_final = dias + " " + mes_capitalize;
        return fecha_final;
    }

    public static String formatearFechaString(String formato, String fecha) {
        //formato de entrada
        SimpleDateFormat f = new SimpleDateFormat(formato);
        //formato de salida
        SimpleDateFormat f1 = new SimpleDateFormat("dd/MM/yyyy");
        String fecha_formateada = "";
        try {
            Date date = f.parse(fecha);
            fecha_formateada = f1.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return fecha_formateada;
    }


    public static int cambiarImagen(String estado_cielo) {
        int imagen = 0;
        switch (estado_cielo) {
            case "Despejado":
                imagen = R.drawable.despejado;
                break;
            case "Poco nuboso":
                imagen = R.drawable.poco_nuboso;
                break;
            case "Intervalos nubosos":
                imagen = R.drawable.inter_nubosos;
                break;
            case "Nuboso":
                imagen = R.drawable.nuboso;
                break;
            case "Muy nuboso":
                imagen = R.drawable.muy_nuboso;
                break;
            case "Cubierto":
                imagen = R.drawable.muy_nuboso;
                break;
            case "Nubes altas":
                imagen = R.drawable.muy_nuboso;
                break;
            case "Intervalos nubosos con lluvia":
                imagen = R.drawable.inter_nub_lluvia;
                break;
            case "Intervalos nubosos con lluvia escasa":
                imagen = R.drawable.inter_nub_lluvia;
                break;
            case "Nuboso con lluvia":
                imagen = R.drawable.nub_pocalluvia;
                break;
            case "Muy nuboso con lluvia escasa":
                imagen = R.drawable.nub_pocalluvia;
                break;
            case "Muy nuboso con lluvia":
                imagen = R.drawable.nub_conlluvia;

            case "Cubierto con lluvia":
                imagen = R.drawable.nub_conlluvia;
                break;
            case "Intervalos nubosos con nieve":
                imagen = R.drawable.inter_nub_nieve;
                break;
            case "Intervalos nubosos con nieve escasa":
                imagen = R.drawable.inter_nub_nieve;
                break;
            case "Nuboso con nieve":
                imagen = R.drawable.poco_nub_nieve;
                break;
            case "Muy nuboso con nieve":
                imagen = R.drawable.nub_nieve;
                break;
            case "Cubierto con nieve":
                imagen = R.drawable.nub_nieve;
                break;
            case "Chubascos":
                imagen = R.drawable.chubascos;
                break;
            case "Tormenta":
                imagen = R.drawable.tormenta;
                break;
            case "Granizo":
                imagen = R.drawable.granizo;
                break;
            case "Bruma":
                imagen = R.drawable.bruma;
                break;
            case "Niebla":
                imagen = R.drawable.niebla;
                break;
            case "Calima":
                imagen = R.drawable.calima;
                break;
            default:
                imagen = R.drawable.ic_na;
        }
        return imagen;
    }
}
