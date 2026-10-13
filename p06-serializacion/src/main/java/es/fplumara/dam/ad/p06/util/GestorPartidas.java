package es.fplumara.dam.ad.p06.util;
import es.fplumara.dam.ad.p06.modelo.Partida;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
/**
 * Guarda y carga partidas en ficheros binarios mediante serialización.
 */
public class GestorPartidas {
    // Paso 2
    public static void guardar(Partida partida, Path ruta) throws IOException {
        partida.setFechaGuardado(LocalDate.now());
        try (ObjectOutputStream salida = new ObjectOutputStream(
                new BufferedOutputStream(Files.newOutputStream(ruta)))) {
            salida.writeObject(partida);
        }
    }
    // Paso 4
    public static Partida cargar(Path ruta) throws IOException,
            ClassNotFoundException {
        try (ObjectInputStream entrada = new ObjectInputStream(
                new BufferedInputStream(Files.newInputStream(ruta)))) {
            return (Partida) entrada.readObject();
        }
    }
}