package es.fplumara.dam.ad.p05;
import es.fplumara.dam.ad.p05.model.Zapatilla;
import es.fplumara.dam.ad.p05.util.FicheroInventario;

public class Principal {
    public static void main(String[] args) {
// Paso 2: probar la clase Zapatilla
        Zapatilla prueba = new Zapatilla(0, "Prueba", "Sin marca", "casual", 42.0,
                10.0, 1);
        System.out.println("Zapatilla de prueba: " + prueba);
        // Paso 3: tamaño de un registro
        System.out.println("Tamaño de un registro: " + FicheroInventario.TAMANO_REGISTRO + " bytes");
        System.out.println("El registro 2 empieza en el byte " + FicheroInventario.posicion(2));
    }
}