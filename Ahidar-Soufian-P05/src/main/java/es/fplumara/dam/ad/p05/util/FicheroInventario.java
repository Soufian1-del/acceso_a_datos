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
    // Distancia en bytes desde el inicio del registro hasta el campo stock (es el último campo)
    public static final int DESPLAZAMIENTO_STOCK = TAMANO_REGISTRO - 4;
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
    /**
     * Crea el fichero de registros a partir del CSV. Si ya existía, lo vacía antes.
     * Devuelve el número de registros escritos.
     */
    public int crearDesdeCsv(String rutaCsv) throws IOException {
        int contador = 0;
        try (BufferedReader lector = new BufferedReader(new FileReader(rutaCsv, StandardCharsets.UTF_8));
             RandomAccessFile raf = new RandomAccessFile(ruta, "rw")) {
            raf.setLength(0);
            lector.readLine(); // se salta la cabecera
            String linea = lector.readLine();
            while (linea != null) {
                String[] campos = linea.split(";");
                Zapatilla z = new Zapatilla(
                        Integer.parseInt(campos[0]),
                        campos[1],
                        campos[2],
                        campos[3],
                        Double.parseDouble(campos[4]),
                        Double.parseDouble(campos[5]),
                        Integer.parseInt(campos[6]));
                escribirRegistro(raf, z);
                contador++;
                linea = lector.readLine();
            }
        }
        return contador;
    }
    /**
     * Lee un texto de longitud fija y le quita los espacios de relleno.
     */
    public static String leerTexto(RandomAccessFile raf, int longitud) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < longitud; i++) {
            sb.append(raf.readChar());
        }
        return sb.toString().trim();
    }
    /**
     * Lee una zapatilla a partir de la posición actual del puntero.
     */
    public static Zapatilla leerRegistro(RandomAccessFile raf) throws IOException {
        Zapatilla z = new Zapatilla();
        z.setCodigo(raf.readInt());
        z.setModelo(leerTexto(raf, LONGITUD_MODELO));
        z.setMarca(leerTexto(raf, LONGITUD_MARCA));
        z.setCategoria(leerTexto(raf, LONGITUD_CATEGORIA));
        z.setTalla(raf.readDouble());
        z.setPrecio(raf.readDouble());
        z.setStock(raf.readInt());
        return z;
    }
    /**
     * Lee el registro número n. Devuelve null si no existe.
     */
    public Zapatilla leer(int numero) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(ruta, "r")) {
            long total = raf.length() / TAMANO_REGISTRO;
            if (numero < 1 || numero > total) {
                return null;
            }
            raf.seek(posicion(numero));
            return leerRegistro(raf);
        }
    }
    /**
     * Calcula cuántos registros tiene el fichero a partir de su tamaño.
     */
    public int contarRegistros() throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(ruta, "r")) {
            return (int) (raf.length() / TAMANO_REGISTRO);
        }
    }
    /**
     * Lee todos los registros, del primero al último.
     */
    public List<Zapatilla> leerTodos() throws IOException {
        List<Zapatilla> lista = new ArrayList<>();
        try (RandomAccessFile raf = new RandomAccessFile(ruta, "r")) {
            long total = raf.length() / TAMANO_REGISTRO;
            raf.seek(0);
            for (int i = 0; i < total; i++) {
                lista.add(leerRegistro(raf));
            }
        }
        return lista;
    }
    /**
            * Cambia el stock del registro n sin tocar el resto del registro.
            * Devuelve false si el registro no existe.
*/
    public boolean modificarStock(int numero, int nuevoStock) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(ruta, "rw")) {
            long total = raf.length() / TAMANO_REGISTRO;
            if (numero < 1 || numero > total) {
                return false;
            }
            raf.seek(posicion(numero) + DESPLAZAMIENTO_STOCK);
            raf.writeInt(nuevoStock);
            return true;
        }
    }
}