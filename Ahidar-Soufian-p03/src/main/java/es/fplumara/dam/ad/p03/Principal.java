package es.fplumara.dam.ad.p03;
import es.fplumara.dam.ad.p03.modelo.Cancion;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
/**
 * P03. Listas de reproducción musicales.
 * Lee un CSV con BufferedReader y escribe un informe con BufferedWriter, siempre en
 UTF-8.
 */
public class Principal {
    // Rutas relativas a la carpeta del proyecto (Working directory)
    public static final Path CANCIONES = Path.of("datos", "canciones.csv");
    public static final Path SALIDA = Path.of("datos", "salida");
    public static final Path INFORME = SALIDA.resolve("informe-listas.txt");
    public static final Path HISTORIAL = SALIDA.resolve("historial.txt");
    // Formato del CSV: separado por punto y coma, con 7 campos por línea
    public static final String SEPARADOR = ";";
    public static final int NUMERO_CAMPOS = 7;
    // Cuántas líneas no se pudieron convertir en la última lectura
    private static int lineasDescartadas = 0;
    public static void main(String[] args) {
        try {
            // Paso 1
            mostrarFichero(CANCIONES, 5);
            // Paso 2
            System.out.println();
            mostrarCampos();
            // Paso 3
            System.out.println();
            probarConversion();
            // Paso 4
            System.out.println();
            List<Cancion> canciones = leerCanciones(CANCIONES);
            System.out.println("Canciones leídas: " + canciones.size());
            System.out.println("Líneas descartadas: " + lineasDescartadas);
            // Paso 5
            System.out.println();
            List<String> informe = crearInforme(canciones);
            for (String linea : informe) {
                System.out.println(linea);
            }
            // Paso 6
            System.out.println();
            escribirFichero(INFORME, informe);
            // Paso 7
            System.out.println();
            mostrarFichero(INFORME, 3);
            anotarEnHistorial(canciones.size());
            mostrarFichero(HISTORIAL, 10);
        } catch (IOException e) {
            System.out.println("Error de entrada/salida: " + e);
        }
    }
    // Paso 1. Leer un fichero de texto línea a línea
    public static void mostrarFichero(Path ruta, int maximo) throws IOException {
        System.out.println("Primeras " + maximo + " líneas de " + ruta + ":");
        int numero = 0;
        try (BufferedReader lector = Files.newBufferedReader(ruta,
                StandardCharsets.UTF_8)) {
            String linea = lector.readLine();
            while (linea != null) {
                numero++;
                if (numero <= maximo) {
                    System.out.println(" " + numero + ": " + linea);
                }
                linea = lector.readLine();
            }
        }
        System.out.println("Líneas en total: " + numero);
    }
    // Paso 2. Separar los campos de una línea con split
    private static void mostrarCampos() throws IOException {
        try (BufferedReader lector = Files.newBufferedReader(CANCIONES,
                StandardCharsets.UTF_8)) {
            String cabecera = lector.readLine();
            String[] nombres = cabecera.split(SEPARADOR);
            System.out.println("La cabecera tiene " + nombres.length + " campos:");
            for (int i = 0; i < nombres.length; i++) {
                System.out.println(" campos[" + i + "] = " + nombres[i]);
            }
            String primera = lector.readLine();
            String[] campos = primera.split(SEPARADOR);
            System.out.println("Primera canción: " + campos[1] + ", de " + campos[2]
                    + " (" + campos[4] + " segundos)");
        }
    }
    // Paso 3. Convertir una línea del CSV en un objeto Cancion
    public static Cancion convertirLinea(String linea) {
        String[] campos = linea.split(SEPARADOR);
        if (campos.length != NUMERO_CAMPOS) {
            throw new IllegalArgumentException("tiene " + campos.length
                    + " campos y se esperaban " + NUMERO_CAMPOS);
        }
        int duracion = Integer.parseInt(campos[4].trim());
        int reproducciones = Integer.parseInt(campos[5].trim());
        return new Cancion(campos[0], campos[1], campos[2], campos[3],
                duracion, reproducciones, campos[6]);
    }
    // Paso 3. Probar la conversión con una línea escrita a mano
    private static void probarConversion() {
        String linea = "Entreno;Subida a Cercedilla;Kairo Beats;Electrónica;214;184320;2025-11-03";
        Cancion cancion = convertirLinea(linea);
        System.out.println("Convertida: " + cancion);
        System.out.println("Título: " + cancion.getTitulo() + " | Duración: " +
                cancion.duracionFormateada());
    }

    // Paso 4. Leer todas las canciones y descartar las líneas defectuosas
    public static List<Cancion> leerCanciones(Path ruta) throws IOException {
        List<Cancion> canciones = new ArrayList<>();
        lineasDescartadas = 0;
        int numeroLinea = 0;
        try (BufferedReader lector = Files.newBufferedReader(ruta,
                StandardCharsets.UTF_8)) {
            String linea = lector.readLine(); // la cabecera no es una canción
            numeroLinea++;
            linea = lector.readLine();
            while (linea != null) {
                numeroLinea++;
                if (!linea.isBlank()) {
                    try {
                        canciones.add(convertirLinea(linea));
                    } catch (NumberFormatException e) {
                        System.out.println("Línea " + numeroLinea + " descartada:número no válido ("
                                        + e.getMessage() + ")");
                        lineasDescartadas++;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Línea " + numeroLinea + " descartada: " +
                                e.getMessage());
                        lineasDescartadas++;
                    }
                }
                linea = lector.readLine();
            }
        }
        return canciones;
    }
    public static int getLineasDescartadas() {
        return lineasDescartadas;
    }

    // Paso 5. Preparar las líneas del informe
    private static List<String> crearInforme(List<Cancion> canciones) {
        List<String> lineas = new ArrayList<>();
        lineas.add("INFORME DE LISTAS DE REPRODUCCIÓN");
        lineas.add("Canciones leídas: " + canciones.size());
        lineas.add("Líneas descartadas: " + lineasDescartadas);
        lineas.add("");
        List<String> nombres = nombresDeListas(canciones);
        for (String nombre : nombres) {
            int numero = 0;
            int segundos = 0;
            for (Cancion cancion : canciones) {
                if (cancion.getLista().equals(nombre)) {
                    numero++;
                    segundos = segundos + cancion.getDuracionSegundos();
                }
            }
            lineas.add(nombre + ": " + numero + " canciones, " +
                    Cancion.formatear(segundos));
        }
        if (!canciones.isEmpty()) {
            Cancion masEscuchada = canciones.get(0);
            for (Cancion cancion : canciones) {
                if (cancion.getReproducciones() > masEscuchada.getReproducciones()) {
                    masEscuchada = cancion;
                }
            }
            lineas.add("");
            lineas.add("Más escuchada: " + masEscuchada.getTitulo() + ", de " +
                    masEscuchada.getArtista()
                    + " (" + masEscuchada.getReproducciones() + " reproducciones)");
        }
        return lineas;
    }
    // Paso 5. Nombres de las listas, sin repetir y en el orden en que aparecen
    public static List<String> nombresDeListas(List<Cancion> canciones) {
        List<String> nombres = new ArrayList<>();
        for (Cancion cancion : canciones) {
            if (!nombres.contains(cancion.getLista())) {
                nombres.add(cancion.getLista());
            }
        }
        return nombres;
    }
    // Paso 6. Escribir líneas en un fichero de texto (si existe, se sustituye)
    public static void escribirFichero(Path ruta, List<String> lineas) throws
            IOException {
        Files.createDirectories(ruta.getParent());
        try (BufferedWriter escritor = Files.newBufferedWriter(ruta,
                StandardCharsets.UTF_8)) {
            for (String linea : lineas) {
                escritor.write(linea);
                escritor.newLine();
            }
        }
        System.out.println("Escrito " + ruta + " (" + lineas.size() + " líneas)");
    }
    // Paso 7. Añadir una línea al final del historial sin borrar lo anterior
    private static void anotarEnHistorial(int numeroCanciones) throws IOException {
        Files.createDirectories(HISTORIAL.getParent());
        try (BufferedWriter escritor = Files.newBufferedWriter(HISTORIAL,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            escritor.write(LocalDateTime.now().withNano(0) + " - informe generado con "
                            + numeroCanciones + " canciones");
            escritor.newLine();
        }
        System.out.println("Anotado en " + HISTORIAL);
    }
}
