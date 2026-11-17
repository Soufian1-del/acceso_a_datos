package es.fplumara.dam.ad.p14.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexion {

    private static final String FICHERO = "conexion.properties";

    // Lee conexion.properties y abre una conexión nueva con MariaDB
    public static Connection abrir() throws IOException, SQLException {
        Properties propiedades = new Properties();
        try (InputStream entrada = Conexion.class.getClassLoader().getResourceAsStream(FICHERO)) {
            if (entrada == null) {
                throw new IOException("No se encuentra " + FICHERO + " en src/main/resources");
            }
            propiedades.load(new InputStreamReader(entrada, StandardCharsets.UTF_8));
        }
        String url = propiedades.getProperty("url");
        String usuario = propiedades.getProperty("usuario");
        String contrasena = propiedades.getProperty("contrasena");
        return DriverManager.getConnection(url, usuario, contrasena);
    }
}
