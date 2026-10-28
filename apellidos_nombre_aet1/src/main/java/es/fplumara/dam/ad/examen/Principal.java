package es.fplumara.dam.ad.examen;

import es.fplumara.dam.ad.examen.servicio.Aplicacion;
import es.fplumara.dam.ad.examen.servicio.CsvAJson;

import java.io.IOException;
import java.nio.file.Path;

public class Principal {
    private static final Path ANUNCIOSJSON = Path.of("datos", "anunciosjson.json");
    public static void main(String[] args) throws IOException {
        Aplicacion app = new Aplicacion();
        System.out.println(app.pasarAObjeto());
        CsvAJson appjson = new CsvAJson();
        appjson.jsonConverter(app.pasarAObjeto(), "datos/anuncios-json.json");
    }
}
