package es.fplumara.dam.ad.p06.modelo;

import java.io.Serializable;

/**
 * Un objeto del inventario de un personaje.
 */
public class Objeto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private String tipo;
    private int valor;

    public Objeto(String nombre, String tipo, int valor) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return nombre + " (" + tipo + ", " + valor + " monedas)";
    }
}
