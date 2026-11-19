package es.fplumara.dam.ad.p15.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexion {

    private static final String FICHERO = "conexion.properties";

    // Lee los datos de conexion.properties y abre una conexión con MariaDB
    public static Connection abrir() throws IOException, SQLException {
        Properties propiedades = new Properties();
        try (InputStream entrada = Conexion.class.getClassLoader().getResourceAsStream(FICHERO)) {
            if (entrada == null) {
                throw new IOException("No se encuentra " + FICHERO + " en src/main/resources");
            }
            propiedades.load(entrada);
        }
        String url = propiedades.getProperty("url");
        String usuario = propiedades.getProperty("usuario");
        String contrasena = propiedades.getProperty("contrasena");
        return DriverManager.getConnection(url, usuario, contrasena);
    }
}
