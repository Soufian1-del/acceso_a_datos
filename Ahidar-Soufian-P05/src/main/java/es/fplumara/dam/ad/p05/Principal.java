package es.fplumara.dam.ad.p05;
import es.fplumara.dam.ad.p05.model.Zapatilla;
import es.fplumara.dam.ad.p05.util.FicheroInventario;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Principal {
    public static void main(String[] args) {
// Paso 2: probar la clase Zapatilla
        Zapatilla prueba = new Zapatilla(0, "Prueba", "Sin marca", "casual", 42.0,
                10.0, 1);
        System.out.println("Zapatilla de prueba: " + prueba);
        // Paso 3: tamaño de un registro
        System.out.println("Tamaño de un registro: " + FicheroInventario.TAMANO_REGISTRO + " bytes");
        System.out.println("El registro 2 empieza en el byte " + FicheroInventario.posicion(2));
        FicheroInventario fichero = new FicheroInventario("datos/inventario.dat");
        try {
// Paso 5: crear el fichero de registros a partir del CSV
            int cargados = fichero.crearDesdeCsv("datos/zapatillas.csv");
            System.out.println("Registros cargados desde el CSV: " + cargados);
            System.out.println("Tamaño de inventario.dat: " + Files.size(Path.of("datos/inventario.dat")) + " bytes");
            // Paso 6: leer el registro n
            System.out.println();
            System.out.println("Registro 1: " + fichero.leer(1));
            System.out.println("El registro 35 empieza en el byte " +
                    FicheroInventario.posicion(35));
            System.out.println("Registro 35: " + fichero.leer(35));
            System.out.println("Registro 23: " + fichero.leer(23));
            Zapatilla buscada = fichero.leer(99);
            if (buscada == null) {
                System.out.println("El registro 99 no existe");
            }
            // Paso 7: contar los registros
            System.out.println();
            System.out.println("Número de registros: " + fichero.contarRegistros());
            // Paso 8: listar todo el inventario
            System.out.println();
            System.out.println("Inventario completo:");
            List<Zapatilla> todas = fichero.leerTodos();
            for (int i = 0; i < todas.size(); i++) {
                System.out.println(todas.get(i));
            }
            // Paso 9: modificar el stock del registro 12
            System.out.println();
            System.out.println("Antes: " + fichero.leer(12));
            fichero.modificarStock(12, 15);
            System.out.println("Después: " + fichero.leer(12));
        } catch (IOException e) {
            System.out.println("Error de entrada/salida: " + e.getMessage());
        }

    }
}