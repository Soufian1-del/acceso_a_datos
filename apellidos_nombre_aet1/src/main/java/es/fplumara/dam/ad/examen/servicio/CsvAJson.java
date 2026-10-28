package es.fplumara.dam.ad.examen.servicio;

import es.fplumara.dam.ad.examen.modelo.Anuncio;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;

public class CsvAJson {
    public ObjectMapper mapper = new ObjectMapper();

    public void jsonConverter(List<Anuncio> anuncios, String ruta) {
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(ruta), anuncios);
    }

}
