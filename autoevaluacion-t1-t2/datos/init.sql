-- Autoevaluación de los temas 1 y 2 - Base de datos del cine
-- Crea la base de datos ad_cine con sus tres tablas vacías.
-- Los datos los carga tu programa desde los ficheros JSON de la carpeta datos/.
-- Para volver a empezar desde cero, ejecuta este script otra vez con Alt+X en DBeaver.

CREATE DATABASE IF NOT EXISTS ad_cine
    DEFAULT CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE ad_cine;

DROP TABLE IF EXISTS puestos;
DROP TABLE IF EXISTS peliculas;
DROP TABLE IF EXISTS personas;

-- Una fila por película (peliculas.json)
CREATE TABLE peliculas (
    id_pelicula INT UNSIGNED NOT NULL,
    titulo      VARCHAR(250) NOT NULL,
    estreno     DATE NULL,
    puntuacion  DECIMAL(3,1) NULL,
    descripcion VARCHAR(250) NOT NULL,
    PRIMARY KEY (id_pelicula)
) ENGINE=InnoDB;

-- Una fila por persona (personas.json)
CREATE TABLE personas (
    id_persona INT UNSIGNED NOT NULL,
    nombre     VARCHAR(250) NOT NULL,
    PRIMARY KEY (id_persona)
) ENGINE=InnoDB;

-- Qué hace cada persona en cada película (puestos.json).
-- Una persona puede tener varios puestos en la misma película.
CREATE TABLE puestos (
    fk_pelicula INT UNSIGNED NOT NULL,
    fk_persona  INT UNSIGNED NOT NULL,
    puesto      VARCHAR(16) NOT NULL,
    PRIMARY KEY (fk_pelicula, fk_persona, puesto),
    CONSTRAINT fk_puestos_pelicula FOREIGN KEY (fk_pelicula) REFERENCES peliculas (id_pelicula),
    CONSTRAINT fk_puestos_persona FOREIGN KEY (fk_persona) REFERENCES personas (id_persona),
    CONSTRAINT chk_puesto CHECK (puesto IN ('DIRECCION', 'GUION', 'REPARTO'))
) ENGINE=InnoDB;
select  peli.*, persona.* from peliculas left join puestos on fk_pelicula = id_pelicula left join personas on fk_persona = id_persona;