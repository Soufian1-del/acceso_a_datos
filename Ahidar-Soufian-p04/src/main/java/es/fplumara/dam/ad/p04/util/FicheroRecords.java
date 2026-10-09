package es.fplumara.dam.ad.p04.util;
import es.fplumara.dam.ad.p04.model.Puntuacion;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
/**
 * Guarda y recupera las partidas en un fichero binario.
 * Cada registro se escribe SIEMPRE en este orden:
 * alias (UTF), puntos (int), nivel (int), tiempo (double),
 * modoExperto (boolean) y fecha (UTF).
 */
public class FicheroRecords {
    // Paso 4: escribir todas las partidas (borra lo que hubiera)
    public static void escribir(String ruta, ArrayList<Puntuacion> lista) throws
            IOException {
        try (DataOutputStream salida = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream(ruta)))) {
            for (int i = 0; i < lista.size(); i++) {
                escribirPuntuacion(salida, lista.get(i));
            }
        }
    }
    private static void escribirPuntuacion(DataOutputStream salida, Puntuacion p)
            throws IOException {
        salida.writeUTF(p.getAlias());
        salida.writeInt(p.getPuntos());
        salida.writeInt(p.getNivel());
        salida.writeDouble(p.getTiempo());
        salida.writeBoolean(p.isModoExperto());
        salida.writeUTF(p.getFecha());
    }
    // Paso 6: leer todas las partidas hasta el final del fichero
    public static ArrayList<Puntuacion> leer(String ruta) throws IOException {
        ArrayList<Puntuacion> lista = new ArrayList<Puntuacion>();
        try (DataInputStream entrada = new DataInputStream(
                new BufferedInputStream(new FileInputStream(ruta)))) {
            boolean fin = false;
            while (!fin) {
                try {
                    String alias = entrada.readUTF();
                    int puntos = entrada.readInt();
                    int nivel = entrada.readInt();
                    double tiempo = entrada.readDouble();
                    boolean modoExperto = entrada.readBoolean();
                    String fecha = entrada.readUTF();
                    lista.add(new Puntuacion(alias, puntos, nivel, tiempo,
                            modoExperto, fecha));
                } catch (EOFException e) {
                    fin = true; // no quedan mas bytes: hemos terminado
                }
            }
        }
        return lista;
    }
    // Paso 8: anadir una partida al final sin borrar las anteriores
    public static void anadir(String ruta, Puntuacion p) throws IOException {
        try (DataOutputStream salida = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream(ruta, true)))) {
            escribirPuntuacion(salida, p);
        }

    }
    // Paso 9: guardar las mismas partidas como texto para comparar
    public static void escribirTexto(String ruta, ArrayList<Puntuacion> lista) throws
            IOException {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(ruta,
                StandardCharsets.UTF_8))) {
            for (int i = 0; i < lista.size(); i++) {
                Puntuacion p = lista.get(i);
                escritor.write(p.getAlias() + ";" + p.getPuntos() + ";" +
                        p.getNivel() + ";"
                        + p.getTiempo() + ";" + p.isModoExperto() + ";" +
                        p.getFecha());
                escritor.newLine();
            }
        }
    }
}
