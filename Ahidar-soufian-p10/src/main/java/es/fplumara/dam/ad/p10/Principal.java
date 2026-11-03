package es.fplumara.dam.ad.p10;
import java.math.BigDecimal;
import java.sql.*;

public class Principal {
    // Datos de conexión a la base de datos H2 en modo fichero
    private static final String URL = "jdbc:h2:./datos/gimnasio";
    private static final String USUARIO = "alumno";
    private static final String CLAVE = "alumno";
    // Paso 2
    public static void main(String[] args) {
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
             Statement sentencia = conexion.createStatement()) {
            System.out.println("Conectado a la base de datos del gimnasio.");
            crearTabla(sentencia);
            insertarSocios(sentencia);
            cargarScript(sentencia);
            listarSocios(sentencia);
        } catch (SQLException e) {
            System.out.println("Error de base de datos: " + e.getMessage());
        }

    }
    // Paso 2
    private static void crearTabla(Statement sentencia) throws SQLException {
        sentencia.execute("DROP TABLE IF EXISTS socio");
        sentencia.execute("CREATE TABLE socio ("
                + "id INT PRIMARY KEY, "
                + "nombre VARCHAR(80) NOT NULL, "
                + "plan VARCHAR(20), "
                + "cuota DECIMAL(5,2), "
                + "fecha_alta DATE, "
                + "activo BOOLEAN)");
        System.out.println("Tabla socio creada.");
    }

    // Paso 3
    private static void insertarSocios(Statement sentencia) throws SQLException {
        int filas = 0;
        filas = filas + sentencia.executeUpdate("INSERT INTO socio VALUES "
                + "(1, 'Lucía Fernández Gómez', 'Completo', 39.90, '2025-09-15', TRUE)");
        filas = filas + sentencia.executeUpdate("INSERT INTO socio VALUES "
                + "(2, 'Mohamed El Amrani', 'Básico', 24.90, '2024-01-10', TRUE)");
        filas = filas + sentencia.executeUpdate("INSERT INTO socio VALUES "
                + "(3, 'Wei Chen Li', 'Nocturno', 19.90, '2023-06-02', FALSE)");
        System.out.println("Socios insertados a mano: " + filas);
    }
    // Paso 4
    private static void cargarScript(Statement sentencia) throws SQLException {
        sentencia.execute("RUNSCRIPT FROM 'datos/socios.sql'");
        System.out.println("Script datos/socios.sql cargado.");
    }
    // Paso 5
    private static void listarSocios(Statement sentencia) throws SQLException {
        String sql = "SELECT id, nombre, plan, cuota, fecha_alta, activo FROM socio ORDER BY id";
        try (ResultSet resultado = sentencia.executeQuery(sql)) {
            int total = 0;
            System.out.println("ID | Nombre | Plan | Cuota | Alta | Estado");
            while (resultado.next()) {
                int id = resultado.getInt("id");
                String nombre = resultado.getString("nombre");
                String plan = resultado.getString("plan");
                BigDecimal cuota = resultado.getBigDecimal("cuota");
                Date fechaAlta = resultado.getDate("fecha_alta");
                boolean activo = resultado.getBoolean("activo");
                String estado = "baja";
                if (activo) {
                    estado = "activo";
                }
                System.out.printf("%2d | %-24s | %-8s | %5s | %s | %s%n",
                        id, nombre, plan, cuota, fechaAlta, estado);
                total++;
            }
            System.out.println("Total de socios: " + total);
        }
    }
}