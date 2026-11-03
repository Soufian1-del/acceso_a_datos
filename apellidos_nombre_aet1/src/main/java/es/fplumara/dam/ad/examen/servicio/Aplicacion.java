package es.fplumara.dam.ad.examen.servicio;

import es.fplumara.dam.ad.examen.modelo.Anuncio;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Aplicacion {
    public List<Anuncio> pasarAObjeto() throws IOException {
        try (BufferedReader lector = Files.newBufferedReader(Path.of("datos", "anuncios.csv"), StandardCharsets.UTF_8)) {
            String cabecera = lector.readLine();
            String[] nombres = cabecera.split(";");
            String primera = lector.readLine();
            String[] campos = primera.split(";");
            List<Anuncio> anuncios = new ArrayList<>();
            List<String> lineas = leerFichero(Path.of("datos", "anuncios.csv"));
            for(String l: lineas) {
                Anuncio anuncio = new Anuncio(
                        Integer.parseInt(campos[0]),
                        campos[1],
                        campos[2],
                        Integer.parseInt(campos[3]),
                        campos[4],
                        campos[5],
                        campos[6],
                        Integer.parseInt(campos[7]),
                        Boolean.parseBoolean(campos[8])
                );
                anuncios.add(anuncio);
            }
            return(anuncios);
        }
    }

    public List<String> leerFichero(Path ruta) throws IOException{
        List<String> lineas = new ArrayList<>();
        try (BufferedReader lector = Files.newBufferedReader(Path.of("datos", "anuncios.csv"), StandardCharsets.UTF_8)) {
            String linea = lector.readLine(); // null = fin de fichero
            while (linea != null) {
                linea = lector.readLine();
                lineas.add(linea);
            }
            lineas.removeLast();
        }
        return lineas;
    }
}

