# Autoevaluación de los temas 1 y 2 - Cine

Alumno o alumna: (escribe aquí tu nombre)

## Puesta en marcha

1. Abre la carpeta `autoevaluacion-t1-t2` en IntelliJ IDEA con **File > Open** y espera a que Maven descargue las dependencias.
2. Comprueba que el servicio de MariaDB está en marcha (`services.msc`).
3. En DBeaver, abre `datos/init.sql` y ejecútalo completo con **Alt+X**. Crea la base de datos `ad_cine` con las tablas `peliculas`, `personas` y `puestos`, vacías.
4. Escribe tu usuario y tu contraseña de MariaDB en `src/main/resources/conexion.properties`.
5. Ejecuta `Principal`. Debe salir `Conexión correcta con ad_cine`.

Para volver a empezar desde cero, ejecuta otra vez `datos/init.sql` con **Alt+X**.

## Lo que trae el proyecto

| Elemento | Qué es |
|---|---|
| `datos/personas.json` | 145 personas |
| `datos/peliculas.json` | 30 películas |
| `datos/puestos.json` | 186 filas: qué persona hace qué en cada película (`DIRECCION`, `GUION` o `REPARTO`) |
| `datos/init.sql` | Crea la base de datos `ad_cine` con las tres tablas vacías |
| `modelo.Persona`, `modelo.Pelicula`, `modelo.Puesto` | Una clase por fichero JSON, con las mismas claves |
| `util.Conexion` | Lee `conexion.properties` con `leerPropiedades()` y abre una conexión con `abrir()` |
| `Principal` | Comprueba la conexión. Aquí empieza tu programa |

El `pom.xml` ya trae Jackson 3, el conector de MariaDB, HikariCP y slf4j-simple.

## Qué he hecho

(Indica qué apartados has hecho y cómo probarlos.)

## Antes de entregar

1. Vuelve a poner `TU_USUARIO` y `TU_CONTRASENA` en `conexion.properties`.
2. En el panel Maven, ejecuta **Lifecycle > clean**.
3. Comprime la carpeta en `apellido1-nombre-autoevaluacion.zip`, sin `target/` ni `.idea/`.
