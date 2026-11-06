package es.fplumara.dam.ad.p11;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
/**
 * P11 · CRUD con MariaDB y PreparedStatement.
 * Tienda de videojuegos: altas, cambios y bajas con sentencias con parámetros.
 */
public class Principal {
    // Fichero de configuración, dentro de src/main/resources
    private static final String FICHERO_CONFIGURACION = "conexion.properties";
    public static void main(String[] args) {
        try {
            // Paso 1
            Properties configuracion = cargarConfiguracion();
            System.out.println("Configuración cargada: " +
                    configuracion.getProperty("url"));
            // Paso 2
            try (Connection conexion = conectar(configuracion)) {
                System.out.println("Conectado a MariaDB.");
                System.out.println("Juegos en la tienda: " + contarJuegos(conexion));
                // Paso 4
                int idNuevo = insertarJuego(conexion, "Huerto Vertical", "Móvil",
                        "Simulación", 6.99, 35);
                System.out.println("Nuevo juego con id " + idNuevo);
                mostrarJuego(conexion, idNuevo);
                System.out.println("Juegos en la tienda: " + contarJuegos(conexion));
                // Paso 5
                int cambiados = actualizarPrecio(conexion, idNuevo, 4.99);
                System.out.println("Juegos actualizados: " + cambiados);
                mostrarJuego(conexion, idNuevo);
                cambiados = actualizarPrecio(conexion, 999, 4.99);
                System.out.println("Juegos actualizados con id 999: " + cambiados);
                // Paso 6
                int borrados = borrarJuego(conexion, idNuevo);
                System.out.println("Juegos borrados: " + borrados);
                System.out.println("Juegos en la tienda: " + contarJuegos(conexion));
                // paso 8
                String tituloEscrito = "x' OR '1'='1";
                borrados = borrarPorTitulo(conexion, tituloEscrito);
                System.out.println("Juegos borrados: " + borrados);
                System.out.println("Juegos en la tienda: " + contarJuegos(conexion));
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
        try (Reader lector = new InputStreamReader(entrada, StandardCharsets.UTF_8))
        {
            configuracion.load(lector);
        }
        return configuracion;
    }
    // Paso 2
    /**
     * Abre una conexión con MariaDB usando los datos de la configuración.
     */
    public static Connection conectar(Properties configuracion) throws SQLException {
        String url = configuracion.getProperty("url");
        String usuario = configuracion.getProperty("usuario");
        String contrasena = configuracion.getProperty("contrasena");
        return DriverManager.getConnection(url, usuario, contrasena);
    }
    /**
     * Cuenta los juegos de la tabla videojuego.
     */
    public static int contarJuegos(Connection conexion) throws SQLException {
        String sql = "SELECT COUNT(*) FROM videojuego";
        try (Statement sentencia = conexion.createStatement();
             ResultSet resultado = sentencia.executeQuery(sql)) {
            resultado.next();
            return resultado.getInt(1);
        }
    }
    /**
     * Muestra en una línea el juego con ese id, o un aviso si no existe.
     * Usa PreparedStatement, que se explica en el paso 3.
     */
    public static void mostrarJuego(Connection conexion, int id) throws SQLException
    {
        String sql = "SELECT id, titulo, plataforma, genero, precio, stock FROM videojuego WHERE id = ?";
        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setInt(1, id);
            try (ResultSet resultado = sentencia.executeQuery()) {
                if (resultado.next()) {
                    System.out.println(" " + resultado.getInt("id") + " | " +
                            resultado.getString("titulo")
                            + " | " + resultado.getString("plataforma") + " | " +
                            resultado.getString("genero")
                            + " | " + resultado.getDouble("precio") + " € | stock " +
                            resultado.getInt("stock"));
                } else {
                    System.out.println(" No existe ningún juego con id " + id);
                }
            }
        }
    }

    // Paso 4
    /**
     * Inserta un juego y devuelve el id que le ha asignado MariaDB.
     */
    public static int insertarJuego(Connection conexion, String titulo, String
                                            plataforma,
                                    String genero, double precio, int stock) throws
            SQLException {
        String sql = "INSERT INTO videojuego (titulo, plataforma, genero, precio, stock) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement sentencia = conexion.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
            sentencia.setString(1, titulo);
            sentencia.setString(2, plataforma);
            sentencia.setString(3, genero);
            sentencia.setDouble(4, precio);
            sentencia.setInt(5, stock);
            int filas = sentencia.executeUpdate();
            System.out.println("Filas insertadas: " + filas);
            try (ResultSet claves = sentencia.getGeneratedKeys()) {
                claves.next();
                return claves.getInt(1);
            }
        }
    }

    // Paso 5
    /**
     * Cambia el precio del juego con ese id. Devuelve las filas afectadas.
     */
    public static int actualizarPrecio(Connection conexion, int id, double precio)
            throws SQLException {
        String sql = "UPDATE videojuego SET precio = ? WHERE id = ?";
        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setDouble(1, precio);
            sentencia.setInt(2, id);
            return sentencia.executeUpdate();
        }
    }
    // Paso 6
    /**
     * Borra el juego con ese id. Devuelve las filas afectadas.
     */
    public static int borrarJuego(Connection conexion, int id) throws SQLException {
        String sql = "DELETE FROM videojuego WHERE id = ?";
        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setInt(1, id);
            return sentencia.executeUpdate();
        }
    }
    // Paso 7
    /**
     * Versión INSEGURA: pega el título dentro del SQL. Solo sirve para ver la
     inyección SQL.
     */
    public static int borrarPorTituloInseguro(Connection conexion, String titulo)
            throws SQLException {
        String sql = "DELETE FROM videojuego WHERE titulo = '" + titulo + "'";
        System.out.println("SQL enviado: " + sql);
        try (Statement sentencia = conexion.createStatement()) {
            return sentencia.executeUpdate(sql);
        }
    }

    // Paso 8
    /**
     * Versión segura: el título viaja como parámetro y nunca se mezcla con el SQL.
     */
    public static int borrarPorTitulo(Connection conexion, String titulo) throws
            SQLException {
        String sql = "DELETE FROM videojuego WHERE titulo = ?";
        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setString(1, titulo);
            return sentencia.executeUpdate();
        }
    }
}