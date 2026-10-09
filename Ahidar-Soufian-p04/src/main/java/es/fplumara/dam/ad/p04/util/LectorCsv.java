package es.fplumara.dam.ad.p04.util;
import es.fplumara.dam.ad.p04.model.Puntuacion;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
/**
 * Lee el fichero CSV de partidas (ya visto en P03).
 */
public class LectorCsv {
    public static ArrayList<Puntuacion> leer(String ruta) throws IOException {
        ArrayList<Puntuacion> lista = new ArrayList<Puntuacion>();
        try (BufferedReader lector = new BufferedReader(new FileReader(ruta,
                StandardCharsets.UTF_8))) {
            String linea = lector.readLine(); // saltamos la cabecera
            linea = lector.readLine();
            while (linea != null) {
                String[] campos = linea.split(";");
                String alias = campos[0];
                int puntos = Integer.parseInt(campos[1]);
                int nivel = Integer.parseInt(campos[2]);
                double tiempo = Double.parseDouble(campos[3]);
                boolean modoExperto = campos[4].equals("si");
                String fecha = campos[5];
                lista.add(new Puntuacion(alias, puntos, nivel, tiempo, modoExperto,
                        fecha));
                linea = lector.readLine();
            }
        }
        return lista;
    }

}