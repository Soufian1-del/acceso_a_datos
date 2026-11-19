# P15 · Procedimientos y funciones almacenadas

Liga de fútbol 7 de barrio. Proyecto base de la práctica.

## Cómo arrancar

1. Comprueba que el servicio de MariaDB está en marcha (`services.msc`).
2. En DBeaver, abre `datos/init.sql` con tu conexión de MariaDB y ejecútalo entero con Alt+X.
   Crea la base de datos `ad_p15` con sus tablas, sus datos, dos procedimientos y dos funciones.
3. En `src/main/resources/conexion.properties`, cambia `TU_USUARIO` y `TU_CONTRASENA` por tus datos de MariaDB.
4. Ejecuta `es.fplumara.dam.ad.p15.Principal`.

Para volver a empezar desde cero, vuelve a ejecutar `datos/init.sql`.

## Retos hechos

(Indica aquí qué retos has hecho y cómo se prueban.)
