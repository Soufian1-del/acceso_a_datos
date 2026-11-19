package es.fplumara.dam.ad.p15.servicio;

import java.sql.*;
import java.sql.Types;

public class Liga {

    private Connection conexion;

    public Liga(Connection conexion) {
        this.conexion = conexion;
    }

    // Devuelve el nombre de un equipo, o null si no existe
    public String nombreEquipo(int idEquipo) throws SQLException {
        String sql = "SELECT nombre FROM equipos WHERE id = ?";
        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setInt(1, idEquipo);
            try (ResultSet rs = sentencia.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("nombre");
                }
                return null;
            }
        }
    }

    // Muestra un partido con su resultado, o "pendiente" si aún no se ha jugado
    public void mostrarPartido(int idPartido) throws SQLException {
        String sql = "SELECT p.jornada, l.nombre AS local, v.nombre AS visitante, "
                + "p.goles_local, p.goles_visitante "
                + "FROM partidos p "
                + "JOIN equipos l ON p.id_local = l.id "
                + "JOIN equipos v ON p.id_visitante = v.id "
                + "WHERE p.id = ?";
        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setInt(1, idPartido);
            try (ResultSet rs = sentencia.executeQuery()) {
                if (rs.next()) {
                    int jornada = rs.getInt("jornada");
                    String local = rs.getString("local");
                    String visitante = rs.getString("visitante");
                    int golesLocal = rs.getInt("goles_local");
                    if (rs.wasNull()) {
                        System.out.println("Jornada " + jornada + ": " + local + " - " + visitante + " (pendiente)");
                    } else {
                        int golesVisitante = rs.getInt("goles_visitante");
                        System.out.println("Jornada " + jornada + ": " + local + " " + golesLocal + "-"
                                + golesVisitante + " " + visitante);
                    }
                } else {
                    System.out.println("No existe el partido " + idPartido);
                }
            }
        }
    }

    // Paso 2
// Guarda el resultado de un partido llamando al procedimiento registrar_resultado
    public void registrarResultado(int idPartido, int golesLocal, int golesVisitante)
            throws SQLException {
        String sql = "{call registrar_resultado(?, ?, ?)}";
        try (CallableStatement llamada = conexion.prepareCall(sql)) {
            llamada.setInt(1, idPartido);
            llamada.setInt(2, golesLocal);
            llamada.setInt(3, golesVisitante);
            llamada.execute();
        }
        System.out.println("Resultado registrado");
    }

    // Paso 3
// Muestra los partidos jugados y los puntos de un equipo con el procedimiento estadisticas_equipo
    public void mostrarEstadisticas(int idEquipo) throws SQLException {
        String sql = "{call estadisticas_equipo(?, ?, ?)}";
        try (CallableStatement llamada = conexion.prepareCall(sql)) {
            llamada.setInt(1, idEquipo);
            llamada.registerOutParameter(2, Types.INTEGER);
            llamada.registerOutParameter(3, Types.INTEGER);
            llamada.execute();
            int jugados = llamada.getInt(2);
            int puntos = llamada.getInt(3);
            System.out.println(nombreEquipo(idEquipo) + ": " + jugados + " partidos jugados, " + puntos + " puntos");
        }
    }

    // Paso 4
// Devuelve los puntos de un equipo llamando a la función puntos_equipo
    public int obtenerPuntos(int idEquipo) throws SQLException {
        String sql = "{? = call puntos_equipo(?)}";
        try (CallableStatement llamada = conexion.prepareCall(sql)) {
            llamada.registerOutParameter(1, Types.INTEGER);
            llamada.setInt(2, idEquipo);
            llamada.execute();
            return llamada.getInt(1);
        }
    }

    // Paso 5
// Muestra la clasificación usando la función puntos_equipo dentro de un SELECT
    public void mostrarClasificacion() throws SQLException {
        String sql = "SELECT nombre, puntos_equipo(id) AS puntos FROM equipos ORDER BY puntos DESC, nombre";
        try (Statement sentencia = conexion.createStatement();
             ResultSet rs = sentencia.executeQuery(sql)) {
            int posicion = 1;
            while (rs.next()) {
                System.out.printf("%2d. %-22s %2d puntos%n", posicion,
                        rs.getString("nombre"), rs.getInt("puntos"));
                posicion++;
            }
        }
    }
}
