package es.fplumara.dam.ad.p14;
import es.fplumara.dam.ad.p14.servicio.Taquilla;
import es.fplumara.dam.ad.p14.util.Conexion;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Principal {

    public static void main(String[] args) {

        try (Connection conexion = Conexion.abrir()) {
            Taquilla taquilla = new Taquilla(conexion);
            // Paso 1
            System.out.println("Autocommit activado: " + conexion.getAutoCommit());
            taquilla.mostrarEstado(1, 1);
            // Paso 2
            System.out.println();
            System.out.println("--- Compra sin transacción ---");
            try {
                taquilla.comprarSinTransaccion(1, 1, 2, true);
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
            taquilla.mostrarEstado(1, 1);
            // Paso 3
            System.out.println();
            System.out.println("--- Compra con transacción ---");
            taquilla.mostrarEstado(2, 4);
            taquilla.comprar(2, 4, 2, false);
            taquilla.mostrarEstado(2, 4);
            // Paso 4
            System.out.println();
            System.out.println("--- Compra con fallo y rollback ---");
            taquilla.mostrarEstado(3, 6);
            taquilla.comprar(3, 6, 2, true);
            taquilla.mostrarEstado(3, 6);
            // Paso 5
            System.out.println();
            System.out.println("--- Abono con puntos de guardado ---");
            taquilla.mostrarEstado(4, 3);
            int[] abono = {5, 3, 7};
            taquilla.comprarAbono(4, abono, 2);
            taquilla.mostrarEstado(4, 3);
        } catch (IOException | SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
