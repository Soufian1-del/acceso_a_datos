package es.fplumara.dam.ad.p06.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * Una partida guardada de «Crónicas de Ébano»: el jugador, su grupo de personajes
 * y algunos datos de la sesión.
 */
public class Partida implements Serializable {
    private static final long serialVersionUID = 1L;
    private String jugador;
    private LocalDate fechaGuardado;
    private double horasJugadas;
    private List<Personaje> grupo;
    private transient String codigoSesion;
    //reto azul
    private int oro;

    @Override
    public String toString() {
        String fecha = "sin guardar";
        if (fechaGuardado != null) {
            fecha = fechaGuardado.toString();
        }
        return "Partida de " + jugador + " | " + horasJugadas + " h jugadas | guardada: " + fecha
                + " | " + grupo.size() + " personajes | " + oro + " monedas de oro";
    }

    public int getOro() {
        return oro;
    }

    public void setOro(int oro) {
        this.oro = oro;
    }

    public Partida(String jugador, double horasJugadas, List<Personaje> grupo, String codigoSesion) {
        this.jugador = jugador;
        this.horasJugadas = horasJugadas;
        this.grupo = grupo;
        this.codigoSesion = codigoSesion;
    }

    public String getJugador() {
        return jugador;
    }

    public void setJugador(String jugador) {
        this.jugador = jugador;
    }

    public LocalDate getFechaGuardado() {
        return fechaGuardado;
    }

    public void setFechaGuardado(LocalDate fechaGuardado) {
        this.fechaGuardado = fechaGuardado;
    }

    public double getHorasJugadas() {
        return horasJugadas;
    }

    public void setHorasJugadas(double horasJugadas) {
        this.horasJugadas = horasJugadas;
    }

    public List<Personaje> getGrupo() {
        return grupo;
    }

    public void setGrupo(List<Personaje> grupo) {
        this.grupo = grupo;
    }

    public String getCodigoSesion() {
        return codigoSesion;
    }

    public void setCodigoSesion(String codigoSesion) {
        this.codigoSesion = codigoSesion;
    }

}
