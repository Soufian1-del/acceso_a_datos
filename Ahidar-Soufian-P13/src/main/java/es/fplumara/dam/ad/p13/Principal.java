package es.fplumara.dam.ad.p13;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
/**
 * P13 · Metadatos.
 * App de recetas: un mini explorador que descubre la estructura de la base de datos.
 */
public class Principal {
    // Fichero de configuración, dentro de src/main/resources
    private static final String FICHERO_CONFIGURACION = "conexion.properties";

    public static void main(String[] args) {
        try {
            Properties configuracion = cargarConfiguracion();
            try (Connection conexion = conectar(configuracion)) {
                System.out.println("Conectado a MariaDB.");
                // Paso 1
                mostrarGestor(conexion);
                // Paso 2
                List<String> tablas = listarTablas(conexion);
                System.out.println();
                System.out.println("Tablas de " + conexion.getCatalog() + ": " +
                        tablas.size());
                for (int i = 0; i < tablas.size(); i++) {
                    System.out.println(" " + tablas.get(i));
                }
                // Paso 3
                System.out.println();
                System.out.println("Columnas de receta:");
                mostrarColumnas(conexion, "receta");
                // Paso 4
                System.out.println();
                mostrarConsulta(conexion, "SELECT titulo, minutos, vegetariana FROM receta "
                        + "WHERE minutos <= 5 ORDER BY minutos, titulo");
                System.out.println();
                mostrarConsulta(conexion, "SELECT c.nombre AS categoria, COUNT(*) AS recetas, "
                        + "MIN(r.minutos) AS la_mas_rapida "
                        + "FROM receta r JOIN categoria c ON c.id = r.id_categoria "
                        + "GROUP BY c.nombre ORDER BY recetas DESC, categoria");
                // Paso 5
                explorar(conexion);
            }
        } catch (IOException e) {
            System.out.println("Error al leer la configuración: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error de base de datos: " + e.getMessage());
        }
    }

    /**
     * Lee el fichero conexion.properties de src/main/resources.
     *
     * @return la configuración con las claves url, usuario y contrasena
     * @throws IOException si el fichero no existe o no se puede leer
     */
    public static Properties cargarConfiguracion() throws IOException {
        InputStream entrada =
                Principal.class.getClassLoader().getResourceAsStream(FICHERO_CONFIGURACION);
        if (entrada == null) {
            throw new IOException("No se encuentra " + FICHERO_CONFIGURACION + " en src/main/resources");
        }
        Properties configuracion = new Properties();
        try (Reader lector = new InputStreamReader(entrada, StandardCharsets.UTF_8)) {
            configuracion.load(lector);
        }
        return configuracion;
    }

    /**
     * Abre una conexión con MariaDB usando los datos de la configuración.
     */
    public static Connection conectar(Properties configuracion) throws SQLException {
        String url = configuracion.getProperty("url");
        String usuario = configuracion.getProperty("usuario");
        String contrasena = configuracion.getProperty("contrasena");
        return DriverManager.getConnection(url, usuario, contrasena);
    }
    // Paso 1

    /**
     * Muestra qué gestor, qué conector y qué usuario hay al otro lado de la
     * conexión.
     */
    public static void mostrarGestor(Connection conexion) throws SQLException {
        DatabaseMetaData metadatos = conexion.getMetaData();
        System.out.println("Gestor: " + metadatos.getDatabaseProductName()
                + " " + metadatos.getDatabaseProductVersion());
        System.out.println("Conector: " + metadatos.getDriverName()
                + " " + metadatos.getDriverVersion());
        System.out.println("URL: " + metadatos.getURL());
        System.out.println("Usuario: " + metadatos.getUserName());
        System.out.println("Base de datos actual: " + conexion.getCatalog());
    }
    // Paso 2

    /**
     * Devuelve los nombres de las tablas de la base de datos actual, en orden
     * alfabético.
     */
    public static List<String> listarTablas(Connection conexion) throws SQLException {
        List<String> tablas = new ArrayList<>();
        DatabaseMetaData metadatos = conexion.getMetaData();
        String[] tipos = {"TABLE"};
        try (ResultSet resultado = metadatos.getTables(conexion.getCatalog(), null,
                "%", tipos)) {
            while (resultado.next()) {
                tablas.add(resultado.getString("TABLE_NAME"));
            }
        }
        return tablas;
    }
    // Paso 3

    /**
     * Muestra las columnas de una tabla: nombre, tipo, tamaño y si admite NULL.
     */
    public static void mostrarColumnas(Connection conexion, String tabla) throws
            SQLException {
        DatabaseMetaData metadatos = conexion.getMetaData();
        try (ResultSet resultado = metadatos.getColumns(conexion.getCatalog(), null,
                tabla, "%")) {
            while (resultado.next()) {
                String nombre = resultado.getString("COLUMN_NAME");
                String tipo = resultado.getString("TYPE_NAME");
                int tamanio = resultado.getInt("COLUMN_SIZE");
                String nulos = "obligatoria";
                if (resultado.getString("IS_NULLABLE").equals("YES")) {
                    nulos = "admite NULL";
                }
                System.out.printf(" %-16s %-9s %6d %s%n", nombre, tipo, tamanio,
                        nulos);
            }
        }
    }

    // Paso 4

    /**
     * Ejecuta cualquier SELECT y muestra el resultado con sus columnas,
     * sin saber de antemano cuántas columnas tiene ni cómo se llaman.
     */
    public static void mostrarConsulta(Connection conexion, String sql) throws
            SQLException {
        try (PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {
            ResultSetMetaData columnas = resultado.getMetaData();
            int numeroColumnas = columnas.getColumnCount();
            // Cabecera: nombre y tipo de cada columna
            String cabecera = "";
            for (int i = 1; i <= numeroColumnas; i++) {
                cabecera = cabecera + columnas.getColumnLabel(i) + " (" +
                        columnas.getColumnTypeName(i) + ")";
                if (i < numeroColumnas) {
                    cabecera = cabecera + " | ";
                }
            }
            System.out.println(cabecera);
            // Filas: cada valor leído como texto, por su número de columna
            int filas = 0;
            while (resultado.next()) {
                String linea = "";
                for (int i = 1; i <= numeroColumnas; i++) {
                    linea = linea + resultado.getString(i);
                    if (i < numeroColumnas) {
                        linea = linea + " | ";
                    }
                }
                System.out.println(linea);
                filas++;
            }
            System.out.println("(" + filas + " filas)");
        }

    }

    // Paso 5

    /**
     * Recorre todas las tablas y muestra de cada una sus columnas,
     * cuántas filas tiene y sus tres primeras filas.
     */
    public static void explorar(Connection conexion) throws SQLException {
        List<String> tablas = listarTablas(conexion);
        for (int i = 0; i < tablas.size(); i++) {
            String tabla = tablas.get(i);
            System.out.println();
            System.out.println("=== " + tabla + " (" + contarFilas(conexion, tabla) +
                    " filas) ===");
            mostrarColumnas(conexion, tabla);
            System.out.println("Primeras filas:");
            mostrarConsulta(conexion, "SELECT * FROM " + tabla + " ORDER BY 1 LIMIT 3");
        }
    }

    /**
     * Cuenta las filas de una tabla. El nombre de la tabla viene de los metadatos,
     * no del usuario.
     */
    public static int contarFilas(Connection conexion, String tabla) throws
            SQLException {
        String sql = "SELECT COUNT(*) FROM " + tabla;
        try (PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {
            resultado.next();
            return resultado.getInt(1);
        }

    }
}