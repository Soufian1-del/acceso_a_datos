package es.fplumara.dam.ad.p06;
import es.fplumara.dam.ad.p06.modelo.Partida;
import es.fplumara.dam.ad.p06.modelo.Personaje;
import es.fplumara.dam.ad.p06.util.DatosIniciales;
import es.fplumara.dam.ad.p06.util.GestorPartidas;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Principal {
    public static void main(String[] args) {
        Partida partida = DatosIniciales.crearPartida();
        System.out.println("=== Partida en memoria ===");
        mostrarPartida(partida);
        // Paso 2
        Path ruta = Path.of("datos", "partida.sav");
        try {
            GestorPartidas.guardar(partida, ruta);
            System.out.println("Partida guardada en " + ruta + " (" + Files.size(ruta) + " bytes)");
            // Paso 4
            Partida cargada = GestorPartidas.cargar(ruta);
            System.out.println("=== Partida cargada del fichero ===");
            mostrarPartida(cargada);
            System.out.println("¿Es el mismo objeto? " + (partida == cargada));
            // Paso 5
            System.out.println("Código de sesión antes de guardar: " + partida.getCodigoSesion());
            System.out.println("Código de sesión después de cargar: " + cargada.getCodigoSesion());
        } catch (IOException e) {
            System.out.println("Error de E/S: " + e);
        } catch (ClassNotFoundException e) {
            System.out.println("Clase no encontrada: " + e);        }
    }
    public static void mostrarPartida(Partida partida) {
        System.out.println(partida);
        for (int i = 0; i < partida.getGrupo().size(); i++) {
            Personaje personaje = partida.getGrupo().get(i);
            System.out.println(" - " + personaje);
        }
    }


}