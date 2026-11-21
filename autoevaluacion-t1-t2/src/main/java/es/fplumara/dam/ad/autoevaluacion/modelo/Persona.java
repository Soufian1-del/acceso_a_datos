package es.fplumara.dam.ad.autoevaluacion.modelo;

/**
 * Una persona que trabaja en el cine (un objeto de personas.json).
 */
public class Persona {

    private int id;

    private String nombre;

    // Jackson necesita un constructor sin parámetros para crear los objetos
    public Persona() {
    }

    public Persona(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return id + " | " + nombre;
    }
}
