package es.fplumara.dam.ad.p06.retos;

import es.fplumara.dam.ad.p06.modelo.Partida;
import es.fplumara.dam.ad.p06.util.DatosIniciales;
import es.fplumara.dam.ad.p06.util.GestorPartidas;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static es.fplumara.dam.ad.p06.Principal.mostrarPartida;

public class Azul {
    static void main() {
        Partida partida = DatosIniciales.crearPartida();
        partida.setOro(69);
        Path ruta = Path.of("datos", "partida-oro.sav");
        mostrarPartida(partida);
        try {
            GestorPartidas.guardar(partida, ruta);
            System.out.println("Partida guardada en " + ruta + " (" +
                    Files.size(ruta) + " bytes)");
            partida.setOro(5);
            // Paso 4
            partida = GestorPartidas.cargar(ruta);
            System.out.println("=== Partida cargada del fichero ===");
            mostrarPartida(partida);
        } catch (IOException e) {
            System.out.println("Error de E/S: " + e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
