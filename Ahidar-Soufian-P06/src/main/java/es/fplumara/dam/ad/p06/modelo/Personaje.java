package es.fplumara.dam.ad.p06.modelo;

import java.io.Serializable;
import java.util.List;

/**
 * Un personaje del grupo, con su inventario.
 */
public class Personaje implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private String clase;
    private int nivel;
    private int puntosVida;
    private List<Objeto> inventario;

    public Personaje(String nombre, String clase, int nivel, int puntosVida, List<Objeto> inventario) {
        this.nombre = nombre;
        this.clase = clase;
        this.nivel = nivel;
        this.puntosVida = puntosVida;
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

    public int getPuntosVida() {
        return puntosVida;
    }

    public void setPuntosVida(int puntosVida) {
        this.puntosVida = puntosVida;
    }

    public List<Objeto> getInventario() {
        return inventario;
    }

    public void setInventario(List<Objeto> inventario) {
        this.inventario = inventario;
    }

    @Override
    public String toString() {
        return nombre + " (" + clase + ", nivel " + nivel + ", " + puntosVida + " PV, "
                + inventario.size() + " objetos)";
    }
}
