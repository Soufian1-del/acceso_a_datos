package es.fplumara.dam.ad.p07.model;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
/**
 * Una serie del catálogo de la plataforma.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Serie {
    @XmlAttribute
    private String id;
    @XmlElement
    private String titulo;
    @XmlElement
    private String genero;
    @XmlElement
    private int anio;
    @XmlElement
    private int temporadas;
    @XmlElement
    private double valoracion;
    // JAXB necesita un constructor sin parámetros para crear los objetos
    public Serie() {
    }
    public Serie(String id, String titulo, String genero, int anio, int temporadas,
                 double valoracion) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.anio = anio;
        this.temporadas = temporadas;
        this.valoracion = valoracion;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public int getAnio() {
        return anio;
    }
    public void setAnio(int anio) {
        this.anio = anio;
    }
    public int getTemporadas() {
        return temporadas;
    }
    public void setTemporadas(int temporadas) {
        this.temporadas = temporadas;
    }
    public double getValoracion() {
        return valoracion;
    }
    public void setValoracion(double valoracion) {
        this.valoracion = valoracion;
    }
    @Override
    public String toString() {
        return id + " | " + titulo + " | " + genero + " | " + anio
                + " | " + temporadas + " temp. | " + valoracion;
    }
}