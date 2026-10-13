package es.fplumara.dam.ad.model;

public class Objeto {
    private String nombre;
    private String tipo;
    private int valorEnMonetario;

    public Objeto(String nombre, String tipo, int valorEnMonetario) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.valorEnMonetario = valorEnMonetario;
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

    public int getValorEnMonetario() {
        return valorEnMonetario;
    }

    public void setValorEnMonetario(int valorEnMonetario) {
        this.valorEnMonetario = valorEnMonetario;
    }

    @Override
    public String toString() {
        return "Objeto{" +
                "nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", valorEnMonetario=" + valorEnMonetario +
                '}';
    }
}
