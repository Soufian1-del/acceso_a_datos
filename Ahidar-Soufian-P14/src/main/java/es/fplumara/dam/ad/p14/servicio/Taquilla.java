package es.fplumara.dam.ad.p14.servicio;

import java.math.BigDecimal;
import java.sql.*;

public class Taquilla {

    private Connection conexion;

    public Taquilla(Connection conexion) {
        this.conexion = conexion;
    }

    // Muestra el saldo del cliente y las entradas libres del concierto
    public void mostrarEstado(int idCliente, int idConcierto) throws SQLException {
        String sqlCliente = "SELECT nombre, saldo FROM clientes WHERE id = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sqlCliente)) {
            ps.setInt(1, idCliente);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println(rs.getString("nombre") + " tiene "
                            + rs.getBigDecimal("saldo") + " € de saldo");
                }
            }
        }
        String sqlConcierto = "SELECT artista, entradas_disponibles FROM conciertos WHERE id = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sqlConcierto)) {
            ps.setInt(1, idConcierto);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println(rs.getString("artista") + " tiene "
                            + rs.getInt("entradas_disponibles") + " entradas libres");
                }
            }
        }
    }

    // Devuelve el precio de una entrada del concierto
    public BigDecimal obtenerPrecio(int idConcierto) throws SQLException {
        String sql = "SELECT precio FROM conciertos WHERE id = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idConcierto);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getBigDecimal("precio");
                }
            }
        }
        throw new SQLException("No existe el concierto " + idConcierto);
    }

    // Resta el importe del saldo del cliente. Devuelve las filas afectadas
    public int restarSaldo(int idCliente, BigDecimal importe) throws SQLException {
        String sql = "UPDATE clientes SET saldo = saldo - ? WHERE id = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setBigDecimal(1, importe);
            ps.setInt(2, idCliente);
            return ps.executeUpdate();
        }
    }

    // Resta las entradas vendidas del concierto. Devuelve las filas afectadas
    public int restarEntradas(int idConcierto, int cantidad) throws SQLException {
        String sql = "UPDATE conciertos SET entradas_disponibles = entradas_disponibles - ? WHERE id = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, cantidad);
            ps.setInt(2, idConcierto);
            return ps.executeUpdate();
        }
    }

    // Guarda la venta con la fecha de hoy. Devuelve las filas afectadas
    public int registrarVenta(int idCliente, int idConcierto, int cantidad, BigDecimal importe)
            throws SQLException {
        String sql = "INSERT INTO ventas (id_cliente, id_concierto, cantidad, importe, fecha_compra) "
                + "VALUES (?, ?, ?, ?, CURRENT_DATE)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            ps.setInt(2, idConcierto);
            ps.setInt(3, cantidad);
            ps.setBigDecimal(4, importe);
            return ps.executeUpdate();
        }
    }
    // Paso 2
    // Compra en modo autocommit: cada sentencia se guarda en cuanto se ejecuta
    public void comprarSinTransaccion(int idCliente, int idConcierto, int cantidad, boolean simularFallo)
            throws SQLException {
        BigDecimal importe = obtenerPrecio(idConcierto).multiply(new
                BigDecimal(cantidad));
        restarSaldo(idCliente, importe);
        System.out.println("Saldo descontado: " + importe + " €");
        if (simularFallo) {
            throw new SQLException("Fallo simulado: el pago se ha interrumpido a mitad de la compra");
        }
        restarEntradas(idConcierto, cantidad);
        registrarVenta(idCliente, idConcierto, cantidad, importe);
        System.out.println("Compra terminada: " + cantidad + " entradas por " +
                importe + " €");
    }
    // Paso 3
    // Compra dentro de una transacción: o se guardan las tres sentencias o ninguna
    public void comprar(int idCliente, int idConcierto, int cantidad, boolean
            simularFallo)
            throws SQLException {
        conexion.setAutoCommit(false);
        try {
            BigDecimal importe = obtenerPrecio(idConcierto).multiply(new
                    BigDecimal(cantidad));
            restarSaldo(idCliente, importe);
            if (simularFallo) {
                throw new SQLException("Fallo simulado: el pago se ha interrumpido a mitad de la compra");
            }
            restarEntradas(idConcierto, cantidad);
            registrarVenta(idCliente, idConcierto, cantidad, importe);
            conexion.commit();
            System.out.println("Compra confirmada: " + cantidad + " entradas por " +
                    importe + " €");
        } catch (SQLException e) {
            conexion.rollback();
            System.out.println("Compra cancelada, se deshacen los cambios: " +
                    e.getMessage());
        } finally {
            conexion.setAutoCommit(true);
        }
    }
    // Paso 5
    // Compra varias entradas en una transacción. Si un concierto falla,
    // se deshace solo esa línea y se sigue con los demás
    public void comprarAbono(int idCliente, int[] idsConciertos, int cantidad) throws
            SQLException {
        conexion.setAutoCommit(false);
        int compradas = 0;
        try {
            for (int i = 0; i < idsConciertos.length; i++) {
                int idConcierto = idsConciertos[i];
                Savepoint antesDeLaLinea = conexion.setSavepoint("linea" + (i + 1));
                try {
                    BigDecimal importe = obtenerPrecio(idConcierto).multiply(new
                            BigDecimal(cantidad));
                    restarSaldo(idCliente, importe);
                    restarEntradas(idConcierto, cantidad);
                    registrarVenta(idCliente, idConcierto, cantidad, importe);
                    compradas++;
                    System.out.println("Concierto " + idConcierto + ": " + cantidad
                            + " entradas por " + importe + " €");
                } catch (SQLException e) {
                    conexion.rollback(antesDeLaLinea);
                    System.out.println("Concierto " + idConcierto + ": no se puede comprar ("
                                    + e.getMessage() + ")");
                }
            }
            conexion.commit();
            System.out.println("Abono confirmado: " + compradas + " de " +
                    idsConciertos.length + " conciertos");
        } catch (SQLException e) {
            conexion.rollback();
            System.out.println("Abono cancelado, se deshacen los cambios: " +
                    e.getMessage());
        } finally {
            conexion.setAutoCommit(true);
        }
    }

}
