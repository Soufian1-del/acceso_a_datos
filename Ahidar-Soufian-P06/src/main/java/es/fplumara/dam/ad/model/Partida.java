package es.fplumara.dam.ad.model;

import java.time.LocalDate;

public class Partida {
    private String jugador;
    private LocalDate fecha;
    private Double horas;
    private String grupo;
    private long codigoSesion;


    public Partida(String jugador, LocalDate fecha, Double horas, String grupo, long codigoSesion) {
        this.jugador = jugador;
        this.fecha = fecha;
        this.horas = horas;
        this.grupo = grupo;
        this.codigoSesion = codigoSesion;
    }

    public String getJugador() {
        return jugador;
    }

    public void setJugador(String jugador) {
        this.jugador = jugador;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Double getHoras() {
        return horas;
    }

    public void setHoras(Double horas) {
        this.horas = horas;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public long getCodigoSesion() {
        return codigoSesion;
    }

    public void setCodigoSesion(long codigoSesion) {
        this.codigoSesion = codigoSesion;
    }

    @Override
    public String toString() {
        return "Partida{" +
                "jugador='" + jugador + '\'' +
                ", fecha=" + fecha +
                ", horas=" + horas +
                ", grupo='" + grupo + '\'' +
                ", codigoSesion=" + codigoSesion +
                '}';
    }
}
