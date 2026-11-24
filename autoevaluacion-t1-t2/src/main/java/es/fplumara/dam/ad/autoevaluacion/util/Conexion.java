package es.fplumara.dam.ad.autoevaluacion.util;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Datos de conexión a MariaDB, leídos de src/main/resources/conexion.properties.
 */
public class Conexion {
    private static final String FICHERO = "conexion.properties";
    // El pool se crea UNA vez y se reutiliza
    private static HikariDataSource pool;
    // Crea el pool la primera vez; las siguientes devuelve el mismo
    private static HikariDataSource obtenerPool() throws IOException {
        if (pool == null) {
            Properties propiedades = new Properties();
            try (InputStream entrada = Conexion.class.getClassLoader().getResourceAsStream(FICHERO)) {
                if (entrada == null) {
                    throw new IOException("No se encuentra " + FICHERO + " en src/main/resources");
                }
                propiedades.load(entrada);
            }
            HikariConfig configuracion = new HikariConfig();
            configuracion.setJdbcUrl(propiedades.getProperty("url"));
            configuracion.setUsername(propiedades.getProperty("usuario"));
            configuracion.setPassword(propiedades.getProperty("contrasena"));
            configuracion.setMaximumPoolSize(5); // EDITAR: nº máximo de conexiones del pool
            pool = new HikariDataSource(configuracion);
        }
        return pool;
    }
    // PIDE una conexión prestada al pool (ya no abre una nueva cada vez)
    public static Connection abrir() throws IOException, SQLException {
        return obtenerPool().getConnection();
    }
    // Se llama UNA vez, al terminar el programa
    public static void cerrarPool() {
        if (pool != null) {
            pool.close();
            pool = null;
        }
    }
}
