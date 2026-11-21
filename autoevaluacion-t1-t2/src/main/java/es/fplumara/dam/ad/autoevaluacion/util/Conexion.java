package es.fplumara.dam.ad.autoevaluacion.util;

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

    private static final String FICHERO_CONFIGURACION = "conexion.properties";

    /**
     * Lee conexion.properties.
     *
     * @return las propiedades url, usuario y contrasena
     * @throws IOException si el fichero no existe o no se puede leer
     */
    public static Properties leerPropiedades() throws IOException {
        InputStream entrada = Conexion.class.getClassLoader().getResourceAsStream(FICHERO_CONFIGURACION);
        if (entrada == null) {
            throw new IOException("No se encuentra " + FICHERO_CONFIGURACION + " en src/main/resources");
        }
        Properties propiedades = new Properties();
        try (Reader lector = new InputStreamReader(entrada, StandardCharsets.UTF_8)) {
            propiedades.load(lector);
        }
        return propiedades;
    }

    /**
     * Abre una conexión nueva con DriverManager.
     *
     * @return una conexión abierta con la base de datos ad_cine
     */
    public static Connection abrir() throws IOException, SQLException {
        Properties propiedades = leerPropiedades();
        return DriverManager.getConnection(propiedades.getProperty("url"),
                propiedades.getProperty("usuario"), propiedades.getProperty("contrasena"));
    }
}
