package es.fplumara.dam.ad.p08.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.ArrayList;
import java.util.List;
/**
 * Un festival del cartel de verano de la app Festiverso.
 */
@JsonPropertyOrder({"id", "nombre", "ciudad", "fechaInicio", "dias", "precio",
        "agotado", "generos", "cabezasCartel"})
public class Festival {
    private String id;
    private String nombre;
    private String ciudad;
    private String fechaInicio;
    private int dias;
    // En Java se llama precioAbono, pero en el JSON sigue llamándose "precio"
    @JsonProperty("precio")
    private double precioAbono;
    private boolean agotado;
    private List<String> generos = new ArrayList<>();
    private List<String> cabezasCartel = new ArrayList<>();
    // Jackson necesita un constructor sin parámetros para crear los objetos
    public Festival() {
    }
    public Festival(String id, String nombre, String ciudad, String fechaInicio, int
                            dias,
                    double precioAbono, boolean agotado, List<String> generos,
                    List<String> cabezasCartel) {
        this.id = id;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.fechaInicio = fechaInicio;
        this.dias = dias;
        this.precioAbono = precioAbono;
        this.agotado = agotado;
        this.generos = generos;
        this.cabezasCartel = cabezasCartel;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCiudad() {
        return ciudad;
    }
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    public String getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public int getDias() {
        return dias;
    }
    public void setDias(int dias) {
        this.dias = dias;
    }
    public double getPrecioAbono() {
        return precioAbono;
    }
    public void setPrecioAbono(double precioAbono) {
        this.precioAbono = precioAbono;
    }
    public boolean isAgotado() {
        return agotado;
    }
    public void setAgotado(boolean agotado) {
        this.agotado = agotado;
    }
    public List<String> getGeneros() {
        return generos;
    }
    public void setGeneros(List<String> generos) {
        this.generos = generos;
    }
    public List<String> getCabezasCartel() {
        return cabezasCartel;
    }
    public void setCabezasCartel(List<String> cabezasCartel) {
        this.cabezasCartel = cabezasCartel;
    }
    // Dato calculado: no se guarda en el JSON
    @JsonIgnore
    public double getPrecioPorDia() {
        return Math.round(precioAbono * 100.0 / dias) / 100.0;
    }
    @Override
    public String toString() {
        String estado = "con entradas";
        if (agotado) {
            estado = "agotado";
        }
        return id + " | " + nombre + " | " + ciudad + " | " + fechaInicio + " | días: " + dias + " | " + precioAbono + " € | " + estado + " | " + generos;
    }
}