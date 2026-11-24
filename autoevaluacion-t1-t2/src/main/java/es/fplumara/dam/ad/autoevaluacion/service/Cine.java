package es.fplumara.dam.ad.autoevaluacion.service;

import es.fplumara.dam.ad.autoevaluacion.modelo.Pelicula;
import es.fplumara.dam.ad.autoevaluacion.modelo.Persona;
import es.fplumara.dam.ad.autoevaluacion.modelo.Puesto;
import es.fplumara.dam.ad.autoevaluacion.util.Conexion;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.sql.*;
import java.util.List;

public class Cine {
    private Connection Conexion;

    public Cine(Connection conexion){
        this.Conexion = conexion;
    }

    // EJERCICIO 1
    public  List<Pelicula> leerPeliculas(String ruta){
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(ruta), new TypeReference<List<Pelicula>>() {});
    }
    public  List<Persona> leerPersona(String ruta){
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(ruta), new TypeReference<List<Persona>>() {});
    }
    public  List<Puesto> leerPuesto(String ruta){
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(ruta), new TypeReference<List<Puesto>>() {});
    }

    // EJERCICIO 2
    public void guardarTodo(Connection conexion, List<Puesto> puestos, List<Persona> personas, List<Pelicula> peliculas) throws SQLException{
        conexion.setAutoCommit(false);
        try {
            insertarPersona(conexion, personas);
            insertarPelicula(conexion, peliculas);
            insertarPuesto(conexion, puestos);
            conexion.commit();
        } catch (SQLException e){
            conexion.rollback();
            System.out.println("tienes un error" + e.getMessage());
        } finally {
            conexion.setAutoCommit(true);
        }
    }
    public  void insertarPelicula(Connection conexion, List<Pelicula> peliculas) throws SQLException {
        String sql = "INSERT INTO peliculas (id_pelicula, titulo, estreno, puntuacion, descripcion) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            for (int i = 0; i < peliculas.size(); i++) {
                Pelicula pelicula = peliculas.get(i);
                sentencia.setInt(1, pelicula.getId());
                sentencia.setString(2, pelicula.getTitulo());
                sentencia.setString(3, pelicula.getEstreno());
                sentencia.setDouble(4, pelicula.getPuntuacion());
                sentencia.setString(5, pelicula.getDescripcion());
                sentencia.executeUpdate();
            }
        }
    }
    public  void insertarPersona(Connection conexion, List<Persona> Personas) throws SQLException {
        String sql = "INSERT INTO personas (id_persona, nombre) VALUES (?, ?)";
        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            for (int i = 0; i < Personas.size(); i++) {
                Persona persona = Personas.get(i);
                sentencia.setInt(1, persona.getId());
                sentencia.setString(2, persona.getNombre());
                sentencia.executeUpdate();
            }
        }
    }
    public  void insertarPuesto(Connection conexion, List<Puesto> puestos) throws SQLException {
        String sql = "INSERT INTO puestos (fk_pelicula,fk_persona, puesto) VALUES (?, ?, ?)";
        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            for (int i = 0; i < puestos.size(); i++) {
                Puesto puesto = puestos.get(i);
                sentencia.setInt(1, puesto.getIdPelicula());
                sentencia.setInt(2, puesto.getIdPersona());
                sentencia.setString(3, puesto.getPuesto());
                sentencia.executeUpdate();
            }
        }
    }

    // EJERCICIO 3

    public  void listarVideojuegos(Connection conexion, int id_pelicula) throws SQLException {
        String sql = "select  peli.*, persona.* from peliculas peli join puestos on id_pelicula = 1 and id_pelicula = fk_pelicula join personas persona on fk_persona = id_persona";
        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setInt(1, id_pelicula);
            try(ResultSet resultado = sentencia.executeQuery() ) {
                while (resultado.next()) {
                    int peliID = resultado.getInt("id_pelicula");
                    String titulo = resultado.getString("Titulo");
                    Date estreno = resultado.getDate("estreno");
                    Double puntuacion = resultado.getDouble("puntuacion");
                    String descripcion = resultado.getString("descripcion");
                    int persona = resultado.getInt("id_persona");
                    String nombre = resultado.getString("nombre");
                    System.out.printf(peliID + " " + titulo + " " + estreno + " " + puntuacion + " " + descripcion + " " + persona + " " + nombre + "\n");
                }
            }

        }
    }

}
