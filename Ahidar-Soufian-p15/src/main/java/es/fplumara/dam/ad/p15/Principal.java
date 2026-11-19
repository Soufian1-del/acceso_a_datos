package es.fplumara.dam.ad.p15;
import es.fplumara.dam.ad.p15.servicio.Liga;
import es.fplumara.dam.ad.p15.util.Conexion;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
public class Principal {
    public static void main(String[] args) {
        try (Connection conexion = Conexion.abrir()) {
            Liga liga = new Liga(conexion);
            System.out.println("Conexión correcta con " + conexion.getCatalog());
// Paso 2
            System.out.println();
            System.out.println("--- Registrar un resultado ---");
            liga.mostrarPartido(36);
            liga.registrarResultado(36, 3, 1);
            liga.mostrarPartido(36);
            // Paso 3
            System.out.println();
            System.out.println("--- Estadísticas de los dos equipos ---");
            liga.mostrarEstadisticas(3);
            liga.mostrarEstadisticas(1);
            // Paso 4
            System.out.println();
            System.out.println("--- Puntos con una función ---");
            int puntos = liga.obtenerPuntos(7);
            System.out.println(liga.nombreEquipo(7) + " tiene " + puntos + " puntos");
            // Paso 5
            System.out.println();
            System.out.println("--- Clasificación ---");
            // Paso 6
            System.out.println();
            System.out.println("--- Errores lanzados por el procedimiento ---");
            probarResultado(liga, 99, 2, 0);
            probarResultado(liga, 37, -1, 2);
            liga.mostrarPartido(37);
            liga.mostrarClasificacion();
        } catch (IOException | SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Paso 6
// Intenta registrar un resultado y, si el procedimiento lanza un error, lo muestra
    private static void probarResultado(Liga liga, int idPartido, int golesLocal, int
            golesVisitante) {
        try {
            liga.registrarResultado(idPartido, golesLocal, golesVisitante);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState() + ", código de error: "
                    + e.getErrorCode());
        }

    }
}
