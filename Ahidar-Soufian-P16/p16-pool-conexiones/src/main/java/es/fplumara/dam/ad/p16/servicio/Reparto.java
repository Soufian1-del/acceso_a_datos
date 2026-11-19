package es.fplumara.dam.ad.p16.servicio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
public class Reparto {
    // Paso 5
// Origen de las conexiones: el pool
    private DataSource origen;
    public Reparto(DataSource origen) {
        this.origen = origen;
    }
// Muestra el nombre, el tipo de cocina, el barrio y la valoración de un restaurante
    public void mostrarRestaurante(int id) throws SQLException {
        String sql = "SELECT nombre, tipo_cocina, barrio, valoracion FROM restaurantes WHERE id = ?";
        try (Connection conexion = origen.getConnection();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setInt(1, id);
            try (ResultSet rs = sentencia.executeQuery()) {
                if (rs.next()) {
                    String valoracion = rs.getString("valoracion");
                    if (valoracion == null) {
                        valoracion = "sin valoración";
                    } else {
                        valoracion = "valoración " + valoracion;
                    }
                    System.out.println(rs.getString("nombre") + " (" +
                            rs.getString("tipo_cocina") + ", "
                            + rs.getString("barrio") + ") - " + valoracion);
                } else {
                    System.out.println("No existe el restaurante " + id);
                }
            }
        }
    }
    // Devuelve cuántos pedidos tiene un restaurante
    public int contarPedidos(int idRestaurante) throws SQLException {
        String sql = "SELECT COUNT(*) FROM pedidos WHERE id_restaurante = ?";
        try (Connection conexion = origen.getConnection();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setInt(1, idRestaurante);
            try (ResultSet rs = sentencia.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            }
        }
    }
}