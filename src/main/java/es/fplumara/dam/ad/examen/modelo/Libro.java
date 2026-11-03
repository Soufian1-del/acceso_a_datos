package es.fplumara.dam.ad.examen.modelo;

public class Libro {

    private int id;
    private String titulo;
    private String autor;
    private String genero;       // Fantasía, Ciencia ficción, Clásico, Misterio, Terror, Cómic o Juvenil
    private int anioPublicacion; // año de la primera edición, por ejemplo 1965

    public Libro() {
    }

    public Libro(int id, String titulo, String autor, String genero, int anioPublicacion) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.anioPublicacion = anioPublicacion;
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

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    @Override
    public String toString() {
        return "Libro{id=" + id + ", titulo='" + titulo + "', autor='" + autor
                + "', genero='" + genero + "', anioPublicacion=" + anioPublicacion + "}";
    }
}
