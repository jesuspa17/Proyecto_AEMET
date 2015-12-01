package com.dam.adpspsalesianostriana.clienteaemet;

import java.util.TreeMap;

/**
 * @author Jesús Pallares
 */
public class CodigoProvincia {

    private static final TreeMap<String, String> codigos = createMap();

    private static TreeMap<String, String> createMap() {
        TreeMap<String, String> map = new TreeMap<String, String>();

        map.put("01","Araba/Álava (Pais Vasco)");
        map.put("02","Albacete (Castilla La Mancha)");
        map.put("03","Alicante/Alacant (Comunidad Valenciana)");
        map.put("04","Almería (Andalucía)");
        map.put("05","Ávila (Castilla y León)");
        map.put("06","Badajoz (Extremadura)");
        map.put("07","Balears, Illes/Islas Baleares");
        map.put("08","Barcelona (Cataluña)");
        map.put("09","Burgos (Castilla y León)");
        map.put("10","Cáceres (Extremadura)");
        map.put("11","Cádiz (Andalucía)");
        map.put("12","Castellón/Castelló (Comunidad Valenciana)");
        map.put("13","Ciudad Real (Castilla La Mancha)");
        map.put("14","Córdoba (Andalucía)");
        map.put("15","Coruña, A/La Coruña (Galicia)");
        map.put("16","Cuenca (Castilla La Mancha)");
        map.put("17","Girona (Cataluña)");
        map.put("18","Granada (Andalucía)");
        map.put("19","Guadalajara (Castilla La Mancha)");
        map.put("20","Guizpúzcoa/Gipuzkoa (Pais Vasco)");
        map.put("21","Huelva (Andalucía)");
        map.put("22","Huesca (Aragón)");
        map.put("23","Jaén (Andalucía)");
        map.put("24","León (Castilla y León)");
        map.put("25","Lleida (Cataluña)");
        map.put("26","Rioja, La");
        map.put("27","Lugo (Galicia)");
        map.put("28","Madrid");
        map.put("29","Málaga (Andalucía)");
        map.put("30","Murcia");
        map.put("31","Navarra");
        map.put("32","Ourense (Galicia)");
        map.put("33","Asturias");
        map.put("34","Palencia (Castilla y León)");
        map.put("35","Palmas, Las (Islas Canarias)");
        map.put("36","Pontevedra (Galicia)");
        map.put("37","Salamanca (Castilla y León)");
        map.put("38","Santa Cruz de Tenerife (Islas Canarias)");
        map.put("39","Cantabria");
        map.put("40","Segovia (Castilla y León)");
        map.put("41","Sevilla (Andalucía)");
        map.put("42","Soria (Castilla y León)");
        map.put("43","Tarragona (Cataluña)");
        map.put("44","Teruel (Aragón)");
        map.put("45","Toledo (Castilla La Mancha)");
        map.put("46","Valencia/València (Comunidad Valenciana)");
        map.put("47","Valladolid (Castilla y León)");
        map.put("48","Vizcaya/Bizkaia (Pais Vasco)");
        map.put("49","Zamora (Castilla y León)");
        map.put("50","Zaragoza (Aragón)");
        map.put("51","Ceuta");
        map.put("52","Melilla");
        return map;
    }

    public static String getProvincia(String code) {
        return codigos.get(code);
    }



}
