package es.fplumara.dam.ad.p02.retos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class Azul {
    private static final Path DESCARGAS = Path.of("datos", "descargas");
    private static final Path ORGANIZADO = Path.of("datos", "organizado");
    static void main() {
        try {
            ListarConTamaño(DESCARGAS);
        } catch (IOException e) {
            System.out.println("error");
        }
    }
    private static void ListarConTamaño(Path carpeta) throws IOException{
        System.out.println("Contenido de " + carpeta + ":");
        int total = 0;
        try (Stream<Path> flujo = Files.list(carpeta)) {
            List<Path> rutas = flujo.sorted().toList();
            for (Path ruta : rutas) {
                if (Files.isDirectory(ruta)) {
                    System.out.println(" [DIR] " + ruta.getFileName() );
                } else {
                    System.out.println(" [FIC] " + ruta.getFileName()+ " Tamaño:" + Files.size(ruta)/1024 + "Kb");
                }
                total++;
            }
        }
        System.out.println("Total: " + total + " elementos");
    }
}
