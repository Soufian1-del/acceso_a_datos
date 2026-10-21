package es.fplumara.dam.ad.p08;
import es.fplumara.dam.ad.p08.model.Festival;
import es.fplumara.dam.ad.p08.service.FestivalesJson;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.node.ArrayNode;
import tools.jackson.databind.node.ObjectNode;
import java.util.ArrayList;
import java.util.List;
public class Principal {
    private static final String RUTA_FESTIVAL = "datos/festival.json";
    private static final String RUTA_FESTIVALES = "datos/festivales.json";
    private static final String RUTA_CARTEL = "datos/cartel-ondas-del-sur.json";
    public static void main(String[] args) {
        // Paso 1
        FestivalesJson json = new FestivalesJson();
        Festival festival = json.leerFestival(RUTA_FESTIVAL);
        System.out.println("Festival leído: " + festival);
        System.out.println("Cabezas de cartel: " + festival.getCabezasCartel());
        // Paso 2
        List<Festival> festivales = json.leerFestivales(RUTA_FESTIVALES);
        System.out.println("Festivales leídos: " + festivales.size());
        for (Festival f : festivales) {
            System.out.println(f);
        }
        // Paso 3
        List<Festival> economicos = new ArrayList<>();
        for (Festival f : festivales) {
            if (f.getPrecioAbono() < 80 && !f.isAgotado()) {
                economicos.add(f);
            }
        }
        json.guardarFestivales(economicos, "datos/festivales-economicos.json");
        System.out.println("Guardados " + economicos.size() + " festivales en datos/festivales-economicos.json");
        // Paso 4
        Festival primero = economicos.get(0);
        System.out.println("Precio por día de " + primero.getNombre() + ": " + primero.getPrecioPorDia() + " €");
        // Paso 5
        JsonNode cartel = json.leerArbol(RUTA_CARTEL);
        System.out.println("Cartel de " + cartel.get("festival").asString()
                + " (edición " + cartel.get("edicion").asInt() + ")");
        JsonNode web = cartel.path("web");
        if (web.isMissingNode()) {
            System.out.println("Web: no indicada");
        }
        JsonNode dias = cartel.get("dias");
        for (JsonNode dia : dias) {
            int actuaciones = 0;
            for (JsonNode escenario : dia.get("escenarios")) {
                actuaciones = actuaciones + escenario.get("actuaciones").size();
            }
            System.out.println(dia.get("fecha").asString() + ": " + actuaciones + " actuaciones");
        }
        // Paso 6
        ObjectNode resumen = json.nuevoObjeto();
        resumen.put("festival", cartel.get("festival").asString());
        resumen.put("ciudad", cartel.get("ciudad").asString());
        ArrayNode cabezas = resumen.putArray("cabezasCartel");
        int total = 0;
        for (JsonNode dia : dias) {
            for (JsonNode escenario : dia.get("escenarios")) {
                for (JsonNode actuacion : escenario.get("actuaciones")) {
                    total++;
                    if (actuacion.get("duracion_min").asInt() >= 90) {
                        cabezas.add(actuacion.get("artista").asString());
                    }
                }
            }
        }
        resumen.put("totalActuaciones", total);
        json.guardarArbol(resumen, "datos/resumen-ondas-del-sur.json");
        System.out.println("Resumen con " + total + " actuaciones guardado en datos/resumen-ondas-del-sur.json");


    }
}
