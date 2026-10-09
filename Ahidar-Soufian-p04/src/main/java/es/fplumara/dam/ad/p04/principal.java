package es.fplumara.dam.ad.p04;
import es.fplumara.dam.ad.p04.model.Puntuacion;
import es.fplumara.dam.ad.p04.util.FicheroRecords;
import es.fplumara.dam.ad.p04.util.LectorCsv;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
public class principal {
    private static final String RUTA_CSV = "datos/puntuaciones.csv";
    private static final String RUTA_DAT = "datos/records.dat";
    private static final String RUTA_TXT = "datos/records.txt";
    public static void main(String[] args) {
        try {
            // Paso 3: cargar las partidas del CSV
            ArrayList<Puntuacion> partidas = LectorCsv.leer(RUTA_CSV);
            System.out.println("Partidas leidas del CSV: " + partidas.size());
            // Paso 4: guardarlas en binario
            FicheroRecords.escribir(RUTA_DAT, partidas);
            System.out.println("Fichero binario creado: " + RUTA_DAT);
            // Paso 5: tamano del fichero binario
            System.out.println("Tamano de records.dat: " +
                    Files.size(Path.of(RUTA_DAT)) + " bytes");
            // Paso 6: leer el fichero binario
            ArrayList<Puntuacion> leidas = FicheroRecords.leer(RUTA_DAT);
            System.out.println("Partidas leidas del binario: " + leidas.size());
            // Paso 7: tabla de records y mejor partida
            mostrarTabla(leidas);
            // Paso 8: anadir una partida nueva al final
            Puntuacion nueva = new Puntuacion("ZAHRA", 275000, 10, 430.5, true,
                    "2026-09-27");
            FicheroRecords.anadir(RUTA_DAT, nueva);
            leidas = FicheroRecords.leer(RUTA_DAT);
            System.out.println("Partidas tras anadir a ZAHRA: " + leidas.size());
            System.out.println("Ultima partida: " + leidas.get(leidas.size() - 1));
            // Paso 9: comparar con un fichero de texto
            FicheroRecords.escribirTexto(RUTA_TXT, leidas);
            System.out.println();
            System.out.println("Comparacion de tamanos con " + leidas.size() + " partidas:");
            System.out.println(" Binario (records.dat): " +
                    Files.size(Path.of(RUTA_DAT)) + " bytes");
            System.out.println(" Texto (records.txt): " +
                    Files.size(Path.of(RUTA_TXT)) + " bytes");
        } catch (IOException e) {
            System.out.println("Error de entrada/salida: " + e.getMessage());
        }
    }
    private static void mostrarTabla(ArrayList<Puntuacion> lista) {
        System.out.println();
        System.out.println("=== TABLA DE RECORDS - COMETA TURBO ===");
        Puntuacion mejor = lista.get(0);
        for (int i = 0; i < lista.size(); i++) {
            Puntuacion p = lista.get(i);
            System.out.println(p);
            if (p.getPuntos() > mejor.getPuntos()) {
                mejor = p;
            }
        }
        System.out.println("Mejor partida: " + mejor.getAlias() + " con " +
                mejor.getPuntos() + " puntos");
        System.out.println();
    }
}