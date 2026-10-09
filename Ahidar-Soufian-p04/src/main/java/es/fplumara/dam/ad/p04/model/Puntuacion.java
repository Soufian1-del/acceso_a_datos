package es.fplumara.dam.ad.p04.model;
/**
 * Una partida guardada en la tabla de records de la maquina "Cometa Turbo".
 */
public class Puntuacion {
    private String alias;
    private int puntos;
    private int nivel;
    private double tiempo;
    private boolean modoExperto;
    private String fecha;
    public Puntuacion() {
    }
    public Puntuacion(String alias, int puntos, int nivel, double tiempo,
                      boolean modoExperto, String fecha) {
        this.alias = alias;
        this.puntos = puntos;
        this.nivel = nivel;
        this.tiempo = tiempo;
        this.modoExperto = modoExperto;
        this.fecha = fecha;
    }
    public String getAlias() {
        return alias;
    }
    public void setAlias(String alias) {
        this.alias = alias;
    }
    public int getPuntos() {
        return puntos;
    }
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
    public int getNivel() {
        return nivel;
    }
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    public double getTiempo() {
        return tiempo;
    }
    public void setTiempo(double tiempo) {
        this.tiempo = tiempo;
    }
    public boolean isModoExperto() {
        return modoExperto;
    }
    public void setModoExperto(boolean modoExperto) {
        this.modoExperto = modoExperto;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    @Override
    public String toString() {
        String modo = "normal";
        if (modoExperto) {
            modo = "experto";
        }
        return String.format("%-8s %7d pts nivel %2d %7.2f s %-7s %s",
                alias, puntos, nivel, tiempo, modo, fecha);
    }
}