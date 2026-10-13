package es.fplumara.dam.ad.model;

public class Personaje {
    private String nombre;
    private String clase;
    private int nivel;
    private Double vida;
    private int inventario;

    public Personaje(String nombre, String clase, int nivel, Double vida, int inventario) {
        this.nombre = nombre;
        this.clase = clase;
        this.nivel = nivel;
        this.vida = vida;
        this.inventario = inventario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public Double getVida() {
        return vida;
    }

    public void setVida(Double vida) {
        this.vida = vida;
    }

    public int getInventario() {
        return inventario;
    }

    public void setInventario(int inventario) {
        this.inventario = inventario;
    }

    @Override
    public String toString() {
        return "Personaje{" +
                "nombre='" + nombre + '\'' +
                ", clase='" + clase + '\'' +
                ", nivel=" + nivel +
                ", vida=" + vida +
                ", inventario=" + inventario +
                '}';
    }
}
