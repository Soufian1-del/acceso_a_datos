package es.fplumara.dam.ad.p08.service;
import es.fplumara.dam.ad.p08.model.Festival;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ObjectNode;
import java.io.File;
import java.util.List;
/**
 * Lee y escribe los festivales en JSON con Jackson.
 */
public class FestivalesJson {
    private ObjectMapper mapper;

    // Paso 1
    public FestivalesJson() {
        mapper = new ObjectMapper();
    }

    // Paso 1
    public Festival leerFestival(String ruta) {
        return mapper.readValue(new File(ruta), Festival.class);
    }

    // Paso 2
    public List<Festival> leerFestivales(String ruta) {
        return mapper.readValue(new File(ruta), new TypeReference<List<Festival>>() {
        });
    }

    // Paso 3
    public void guardarFestivales(List<Festival> festivales, String ruta) {
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(ruta), festivales);
    }

    // Paso 5
    public JsonNode leerArbol(String ruta) {
        return mapper.readTree(new File(ruta));
    }
    // Paso 6
    public ObjectNode nuevoObjeto() {
        return mapper.createObjectNode();
    }
    // Paso 6
    public void guardarArbol(JsonNode arbol, String ruta) {
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(ruta), arbol);
    }

}
