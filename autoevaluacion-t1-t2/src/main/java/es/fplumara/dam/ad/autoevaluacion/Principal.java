package es.fplumara.dam.ad.autoevaluacion;

import es.fplumara.dam.ad.autoevaluacion.service.Cine;
import es.fplumara.dam.ad.autoevaluacion.util.Conexion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
/**
 * Autoevaluación de los temas 1 y 2: el catálogo del cine.
 */
public class Principal {

    // Ficheros de datos, relativos a la carpeta del proyecto
    private static final String RUTA_PERSONAS = "datos/personas.json";
    private static final String RUTA_PELICULAS = "datos/peliculas.json";
    private static final String RUTA_PUESTOS = "datos/puestos.json";

    public static void main(String[] args) {
        // Comprobación inicial: borra estas líneas cuando empieces el apartado 4
        try (Connection conexion = Conexion.abrir()) {
            System.out.println("Conexión correcta con " + conexion.getCatalog());
            Cine cine = new Cine(conexion);
            // EJERCICIO 1
            cine.leerPeliculas(RUTA_PELICULAS);
            cine.leerPersona(RUTA_PERSONAS);
            cine.leerPuesto(RUTA_PUESTOS);
            // EJERCICIO 2
            cine.guardarTodo(conexion, cine.leerPuesto(RUTA_PUESTOS),cine.leerPersona(RUTA_PERSONAS),cine.leerPeliculas(RUTA_PELICULAS));
            // cine.insertarPelicula(conexion, cine.leerPeliculas(RUTA_PELICULAS));
            // cine.insertarPersona(conexion, cine.leerPersona(RUTA_PERSONAS));
            // cine.insertarPuesto(conexion, cine.leerPuesto(RUTA_PUESTOS));
            // EJERCICIO 3

        } catch (IOException | SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Apartado 1: leer los tres ficheros JSON

        // Apartado 2: guardar los datos en MariaDB con JDBC

        // Apartado 3: pedir el id de una película y mostrar sus datos y sus personas

        // Apartado 4: hacer las conexiones con un pool
    }
}
