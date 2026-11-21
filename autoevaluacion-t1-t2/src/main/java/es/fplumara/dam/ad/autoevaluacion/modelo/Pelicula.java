package es.fplumara.dam.ad.autoevaluacion.modelo;

/**
 * Una película del catálogo (un objeto de peliculas.json).
 * La fecha de estreno se guarda como texto con el formato aaaa-mm-dd.
 */
public class Pelicula {

    private int id;

    private String titulo;

    private String estreno;

    private double puntuacion;

    private String descripcion;

    // Jackson necesita un constructor sin parámetros para crear los objetos
    public Pelicula() {
    }

    public Pelicula(int id, String titulo, String estreno, double puntuacion, String descripcion) {
        this.id = id;
        this.titulo = titulo;
        this.estreno = estreno;
        this.puntuacion = puntuacion;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEstreno() {
        return estreno;
    }

    public void setEstreno(String estreno) {
        this.estreno = estreno;
    }

    public double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(double puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return id + " | " + titulo + " | " + estreno + " | " + puntuacion + " | " + descripcion;
    }
}
