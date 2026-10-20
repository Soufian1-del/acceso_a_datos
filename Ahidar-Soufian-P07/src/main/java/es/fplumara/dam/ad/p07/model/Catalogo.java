package es.fplumara.dam.ad.p07.model;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
/**
 * El catálogo completo: corresponde al elemento raíz del XML.
 */
@XmlRootElement(name = "catalogo")
@XmlAccessorType(XmlAccessType.FIELD)
public class Catalogo {
    @XmlAttribute
    private String plataforma;
    @XmlElement(name = "serie")
    private List<Serie> series = new ArrayList<>();
    public Catalogo() {
    }
    public Catalogo(String plataforma, List<Serie> series) {
        this.plataforma = plataforma;
        this.series = series;
    }
    public String getPlataforma() {
        return plataforma;
    }
    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }
    public List<Serie> getSeries() {
        return series;
    }
    public void setSeries(List<Serie> series) {
        this.series = series;
    }
    @Override
    public String toString() {
        return "Catalogo de " + plataforma + " con " + series.size() + " series";
    }
}