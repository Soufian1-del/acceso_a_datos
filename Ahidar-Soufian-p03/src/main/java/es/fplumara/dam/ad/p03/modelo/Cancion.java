package es.fplumara.dam.ad.p03.modelo;

import org.w3c.dom.ls.LSOutput;

/**
 * Una canción de una lista de reproducción.
 * Cada objeto corresponde a una línea del fichero canciones.csv.
 */
public class Cancion {
    private String lista;
    private String titulo;
    private String artista;
    private String genero;
    private int duracionSegundos;
    private int reproducciones;
    private String fechaAlta;
    public Cancion() {
    }
    public Cancion(String lista, String titulo, String artista, String genero,
                   int duracionSegundos, int reproducciones, String fechaAlta) {
        this.lista = lista;
        this.titulo = titulo;
        this.artista = artista;
        this.genero = genero;
        this.duracionSegundos = duracionSegundos;
        this.reproducciones = reproducciones;
        this.fechaAlta = fechaAlta;
    }
    public String getLista() {
        return lista;
    }
    public void setLista(String lista) {
        this.lista = lista;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getArtista() {
        return artista;
    }
    public void setArtista(String artista) {
        this.artista = artista;
    }
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public int getDuracionSegundos() {
        return duracionSegundos;
    }
    public void setDuracionSegundos(int duracionSegundos) {
        this.duracionSegundos = duracionSegundos;
    }
    public int getReproducciones() {
        return reproducciones;
    }
    public void setReproducciones(int reproducciones) {
        this.reproducciones = reproducciones;
    }
    public String getFechaAlta() {
        return fechaAlta;
    }
    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }
    /**
     * Devuelve la duración con el formato minutos:segundos, por ejemplo 3:34.
     */
    public String duracionFormateada() {
        return formatear(duracionSegundos);
    }

    /**
     * Convierte una cantidad de segundos al formato minutos:segundos.
     * Los segundos siempre llevan dos cifras: 185 segundos son 3:05.
     */
    public static String formatear(int segundos) {
        int minutos = segundos / 60;
        int resto = segundos % 60;
        if (resto < 10) {
            return minutos + ":0" + resto;
        }
        return minutos + ":" + resto;
    }
    @Override
    public String toString() {
        return titulo + " - " + artista + " [" + lista + ", " + genero + ", "
                + duracionFormateada() + ", " + reproducciones + " reproducciones, alta " + fechaAlta + "]";
    }
}
