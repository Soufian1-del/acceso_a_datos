package es.fplumara.dam.ad.autoevaluacion.modelo;

/**
 * Lo que hace una persona en una película (un objeto de puestos.json).
 * El puesto es DIRECCION, GUION o REPARTO.
 */
public class Puesto {

    private int idPelicula;

    private int idPersona;

    private String puesto;

    // Jackson necesita un constructor sin parámetros para crear los objetos
    public Puesto() {
    }

    public Puesto(int idPelicula, int idPersona, String puesto) {
        this.idPelicula = idPelicula;
        this.idPersona = idPersona;
        this.puesto = puesto;
    }

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    @Override
    public String toString() {
        return "película " + idPelicula + " | persona " + idPersona + " | " + puesto;
    }
}
