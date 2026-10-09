package es.fplumara.dam.ad.p05.util;
import es.fplumara.dam.ad.p05.model.Zapatilla;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
/**
 * Guarda el inventario en un fichero de registros de longitud fija
 * y permite leer y modificar cualquier registro con acceso aleatorio.
 */
public class FicheroInventario {
    // Longitud de cada texto, en caracteres
    public static final int LONGITUD_MODELO = 20;
    public static final int LONGITUD_MARCA = 15;
    public static final int LONGITUD_CATEGORIA = 12;
// Tamaño de un registro en bytes:
// codigo (int: 4) + tres textos (2 bytes por carácter) + talla y precio (double:8 + 8) + stock (int: 4)
    public static final int TAMANO_REGISTRO = 4 + LONGITUD_MODELO * 2 + LONGITUD_MARCA * 2 + LONGITUD_CATEGORIA * 2 + 8 + 8 + 4;
    private String ruta;
    public FicheroInventario(String ruta) {
        this.ruta = ruta;
    }
    /**
     * Devuelve el byte donde empieza el registro número n (el primero es el 1).
     */
    public static long posicion(int numero) {
        return (long) (numero - 1) * TAMANO_REGISTRO;
    }
    // PASO 4
    /**
     * Escribe un texto ocupando siempre el mismo número de caracteres:
     * si es corto lo rellena con espacios y si es largo lo corta.
     */
    public static void escribirTexto(RandomAccessFile raf, String texto, int longitud) throws IOException {
        StringBuilder sb = new StringBuilder(texto);
        if (sb.length() > longitud) {
            sb.setLength(longitud);
        }
        while (sb.length() < longitud) {
            sb.append(' ');
        }
        raf.writeChars(sb.toString());
    }
    /**
     * Escribe una zapatilla a partir de la posición actual del puntero.
     */
    public static void escribirRegistro(RandomAccessFile raf, Zapatilla z) throws IOException {
        raf.writeInt(z.getCodigo());
        escribirTexto(raf, z.getModelo(), LONGITUD_MODELO);
        escribirTexto(raf, z.getMarca(), LONGITUD_MARCA);
        escribirTexto(raf, z.getCategoria(), LONGITUD_CATEGORIA);
        raf.writeDouble(z.getTalla());
        raf.writeDouble(z.getPrecio());
        raf.writeInt(z.getStock());
    }
}