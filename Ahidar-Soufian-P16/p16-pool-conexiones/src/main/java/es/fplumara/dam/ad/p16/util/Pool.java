package es.fplumara.dam.ad.p16.util;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.io.IOException;
import java.util.Properties;
public class Pool {
    // Paso 2
// Crea un pool de conexiones con los datos de conexion.properties
    public static HikariDataSource crear(int tamanioMaximo) throws IOException {
        Properties propiedades = Conexion.leerPropiedades();
        HikariConfig configuracion = new HikariConfig();
        configuracion.setJdbcUrl(propiedades.getProperty("url"));
        configuracion.setUsername(propiedades.getProperty("usuario"));
        configuracion.setPassword(propiedades.getProperty("contrasena"));
        configuracion.setPoolName("PoolReparto");
        configuracion.setMaximumPoolSize(tamanioMaximo);
        return new HikariDataSource(configuracion);
    }
}