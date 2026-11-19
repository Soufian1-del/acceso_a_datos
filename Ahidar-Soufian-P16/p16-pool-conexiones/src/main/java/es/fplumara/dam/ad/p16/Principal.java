package es.fplumara.dam.ad.p16;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariPoolMXBean;
import es.fplumara.dam.ad.p16.servicio.Reparto;
import es.fplumara.dam.ad.p16.util.Conexion;
import es.fplumara.dam.ad.p16.util.Pool;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Principal {
    // Consulta que se repite en las mediciones
    private static final String CONSULTA = "SELECT COUNT(*) FROM pedidos WHERE id_restaurante = ?";
    public static void main(String[] args) {
        try (HikariDataSource pool = Pool.crear(3)) {
// Paso 1
            System.out.println("--- Conexiones con DriverManager ---");
            long tiempoDriverManager = medirDriverManager(100);
            System.out.println("100 consultas con DriverManager: " +
                    tiempoDriverManager + " ms");
// Paso 2
            System.out.println();
            System.out.println("Pool " + pool.getPoolName() + " creado con un máximo de " + pool.getMaximumPoolSize() + " conexiones");
            // Paso 3
            System.out.println();
            System.out.println("--- Pedir y devolver conexiones ---");
            esperar(1000);
            mostrarEstado(pool);
            try (Connection primera = pool.getConnection();
                 Connection segunda = pool.getConnection()) {
                System.out.println("Prestadas: " + primera.getClass().getSimpleName()
                        + " y "
                        + segunda.getClass().getSimpleName());
                mostrarEstado(pool);
            }
            System.out.println("Devueltas las dos conexiones");
            mostrarEstado(pool);
            // Paso 4
            System.out.println();
            System.out.println("--- Conexiones con el pool ---");
            long tiempoPool = medirPool(pool, 100);
            System.out.println("100 consultas con el pool: " + tiempoPool + " ms");
            double veces = (double) tiempoDriverManager / tiempoPool;
            System.out.printf("El pool ha sido %.1f veces más rápido%n", veces);
            mostrarEstado(pool);
            // Paso 5
            System.out.println();
            System.out.println("--- El servicio usa el pool ---");
            Reparto reparto = new Reparto(pool);
            reparto.mostrarRestaurante(3);
            System.out.println("Pedidos registrados: " + reparto.contarPedidos(3));
            mostrarEstado(pool);
            System.out.println();
        } catch (IOException | SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
// Paso 1
// Hace la misma consulta varias veces abriendo y cerrando cada vez una conexión nueva
    // con DriverManager. Devuelve los milisegundos que ha tardado
    public static long medirDriverManager(int veces) throws IOException, SQLException
    {
        long inicio = System.nanoTime();
        for (int i = 0; i < veces; i++) {
            try (Connection conexion = Conexion.abrir();
                 PreparedStatement sentencia = conexion.prepareStatement(CONSULTA)) {
                sentencia.setInt(1, i % 15 + 1);
                try (ResultSet rs = sentencia.executeQuery()) {
                    rs.next();
                }
            }
        }
        long fin = System.nanoTime();
        return (fin - inicio) / 1000000;
    }

    // Paso 3
// Muestra cuántas conexiones del pool están prestadas, cuántas libres y cuántas hay en total
    public static void mostrarEstado(HikariDataSource pool) {
        HikariPoolMXBean estado = pool.getHikariPoolMXBean();
        System.out.println("Activas: " + estado.getActiveConnections()
                + " | Libres: " + estado.getIdleConnections()
                + " | Total: " + estado.getTotalConnections());
    }
    // Paso 3
// Hace una pausa de los milisegundos indicados
    public static void esperar(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            System.out.println("Pausa interrumpida");
        }
    }

    // Paso 4
// Hace la misma consulta varias veces pidiendo cada vez la conexión al pool.
// Devuelve los milisegundos que ha tardado
    public static long medirPool(DataSource origen, int veces) throws SQLException {
        long inicio = System.nanoTime();
        for (int i = 0; i < veces; i++) {
            try (Connection conexion = origen.getConnection();
                 PreparedStatement sentencia = conexion.prepareStatement(CONSULTA)) {
                sentencia.setInt(1, i % 15 + 1);
                try (ResultSet rs = sentencia.executeQuery()) {
                    rs.next();
                }
            }
        }
        long fin = System.nanoTime();
        return (fin - inicio) / 1000000;

    }

}