package es.fplumara.dam.ad.p02.retos;

import jdk.swing.interop.SwingInterOpUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class Verde {
    private static final Path DESCARGAS = Path.of("datos", "descargas");
    private static final Path ORGANIZADO = Path.of("datos", "organizado");
    static void main() {
        try {
            buscar(DESCARGAS, ".mp4");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void buscar(Path carpeta, String extension) throws IOException{
        System.out.println("Recorrido de " + carpeta + ":");
        int ficheros = 0;
        long bytes = 0;
        // creamos una lista flujo que tiene todas las carpetas y su contenido y luego lo metemos dentro de una lista rutas
        try (Stream<Path> flujo = Files.walk(carpeta)) {
            List<Path> rutas = flujo.sorted().toList();
            for (Path ruta : rutas) {
                if (ruta.toString().toLowerCase().endsWith(extension)) {
                } else {
                    ficheros++;
                    bytes = bytes + Files.size(ruta);
                    // sin esto salen solos los archivos de la subcarpeta, con esto sale todo
                    System.out.println(ruta.getFileName());
                    if (ruta.getNameCount() > carpeta.getNameCount() + 1) {
                        // Solo mostramos lo que está dentro de subcarpetas
                        System.out.println(" " + carpeta.relativize(ruta));
                    }
                }
            }
        }
        System.out.println("Ficheros: " + ficheros);
        System.out.println("Tamaño total: " + bytes / 1024 + " KB");
    }
}
