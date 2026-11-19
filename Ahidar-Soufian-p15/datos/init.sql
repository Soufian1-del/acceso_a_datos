-- P15 · Procedimientos y funciones almacenadas
-- Liga de fútbol 7 de barrio
-- Crea la base de datos ad_p15, sus tablas, los datos, dos procedimientos y dos funciones.
-- Ejecútalo entero en DBeaver con Alt+X. Volver a ejecutarlo deja los datos como al principio.

CREATE DATABASE IF NOT EXISTS ad_p15 CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE ad_p15;

DROP TABLE IF EXISTS partidos;
DROP TABLE IF EXISTS jugadores;
DROP TABLE IF EXISTS equipos;

CREATE TABLE equipos (
    id INT PRIMARY KEY,
    nombre VARCHAR(40) NOT NULL,
    barrio VARCHAR(40) NOT NULL
);

CREATE TABLE jugadores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL,
    apellidos VARCHAR(40) NOT NULL,
    id_equipo INT NOT NULL,
    dorsal INT NOT NULL,
    posicion VARCHAR(20) NOT NULL,
    goles INT NOT NULL DEFAULT 0,
    CONSTRAINT fk_jugadores_equipo FOREIGN KEY (id_equipo) REFERENCES equipos (id),
    CONSTRAINT uk_dorsal UNIQUE (id_equipo, dorsal)
);

CREATE TABLE partidos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    jornada INT NOT NULL,
    fecha DATE NOT NULL,
    id_local INT NOT NULL,
    id_visitante INT NOT NULL,
    goles_local INT NULL,
    goles_visitante INT NULL,
    CONSTRAINT fk_partidos_local FOREIGN KEY (id_local) REFERENCES equipos (id),
    CONSTRAINT fk_partidos_visitante FOREIGN KEY (id_visitante) REFERENCES equipos (id)
);

INSERT INTO equipos (id, nombre, barrio) VALUES
    (1, 'CD Las Acacias', 'Arganzuela'),
    (2, 'Unión Pradolongo', 'Usera'),
    (3, 'Racing Orcasitas', 'Usera'),
    (4, 'Halcones de Tetuán', 'Tetuán'),
    (5, 'Estrella de Entrevías', 'Puente de Vallecas'),
    (6, 'Deportivo San Blas', 'San Blas-Canillejas'),
    (7, 'Leones de Carabanchel', 'Carabanchel'),
    (8, 'CF Barrio del Pilar', 'Fuencarral-El Pardo'),
    (9, 'Juventud Moratalaz', 'Moratalaz'),
    (10, 'Tormenta de Lavapiés', 'Centro');

INSERT INTO jugadores (id, nombre, apellidos, id_equipo, dorsal, posicion, goles) VALUES
    (1, 'Bryan', 'Zhang', 1, 1, 'Portero', 0),
    (2, 'Iker', 'Herrera', 1, 3, 'Defensa', 1),
    (3, 'Ángel', 'Mbaye', 1, 5, 'Defensa', 0),
    (4, 'Samuel', 'Chen', 1, 6, 'Centrocampista', 4),
    (5, 'Khadija', 'Tazi', 1, 7, 'Centrocampista', 2),
    (6, 'Ainhoa', 'Mbaye', 1, 15, 'Centrocampista', 6),
    (7, 'Naiara', 'Ruiz', 1, 16, 'Delantero', 5),
    (8, 'Youssef', 'Medina', 1, 17, 'Delantero', 6),
    (9, 'Precious', 'Iglesias', 2, 1, 'Portero', 0),
    (10, 'Carla', 'Medina', 2, 7, 'Defensa', 2),
    (11, 'Pau', 'Rahmani', 2, 10, 'Defensa', 0),
    (12, 'Jhon', 'Cabrera', 2, 11, 'Centrocampista', 1),
    (13, 'Salma', 'Rojas', 2, 18, 'Centrocampista', 1),
    (14, 'Rubén', 'Mendoza', 2, 19, 'Centrocampista', 1),
    (15, 'Yasmin', 'Vargas', 2, 20, 'Delantero', 2),
    (16, 'Esther', 'Suárez', 2, 21, 'Delantero', 6),
    (17, 'Sofía', 'Martín', 3, 1, 'Portero', 0),
    (18, 'Mateo', 'Chen', 3, 4, 'Defensa', 2),
    (19, 'Vera', 'Delgado', 3, 9, 'Defensa', 2),
    (20, 'Sara', 'Mensah', 3, 12, 'Centrocampista', 2),
    (21, 'Valeria', 'Mensah', 3, 14, 'Centrocampista', 4),
    (22, 'Martina', 'Popescu', 3, 17, 'Centrocampista', 2),
    (23, 'Raúl', 'Rojas', 3, 19, 'Delantero', 6),
    (24, 'Jimena', 'Ndiaye', 3, 23, 'Delantero', 2),
    (25, 'Bilal', 'Sow', 4, 1, 'Portero', 0),
    (26, 'Ana', 'Herrera', 4, 2, 'Defensa', 1),
    (27, 'Hiba', 'Iglesias', 4, 3, 'Defensa', 0),
    (28, 'Jian', 'Rahmani', 4, 4, 'Centrocampista', 0),
    (29, 'Paula', 'Vidal', 4, 8, 'Centrocampista', 1),
    (30, 'Fátima', 'Ndiaye', 4, 12, 'Centrocampista', 1),
    (31, 'Zaida', 'Okafor', 4, 20, 'Delantero', 9),
    (32, 'Marcos', 'Moreno', 4, 22, 'Delantero', 3),
    (33, 'Mohamed', 'Rahmani', 5, 1, 'Portero', 0),
    (34, 'Erik', 'Cruz', 5, 3, 'Defensa', 1),
    (35, 'Laura', 'Popescu', 5, 10, 'Defensa', 2),
    (36, 'Omar', 'Chen', 5, 11, 'Centrocampista', 5),
    (37, 'Hana', 'Ionescu', 5, 14, 'Centrocampista', 1),
    (38, 'Aisha', 'Ndiaye', 5, 16, 'Centrocampista', 2),
    (39, 'Christian', 'Zhang', 5, 19, 'Delantero', 2),
    (40, 'Gabriel', 'Santos', 5, 22, 'Delantero', 2),
    (41, 'Nicolás', 'Bouzid', 6, 1, 'Portero', 0),
    (42, 'Hugo', 'Molina', 6, 3, 'Defensa', 2),
    (43, 'Diego', 'Quintero', 6, 8, 'Defensa', 1),
    (44, 'Kevin', 'Wang', 6, 10, 'Centrocampista', 2),
    (45, 'Amina', 'Delgado', 6, 13, 'Centrocampista', 3),
    (46, 'Irene', 'Mensah', 6, 15, 'Centrocampista', 2),
    (47, 'Aitana', 'Garrido', 6, 20, 'Delantero', 5),
    (48, 'Thiago', 'Romero', 6, 22, 'Delantero', 8),
    (49, 'Lucía', 'Garrido', 7, 1, 'Portero', 0),
    (50, 'Camila', 'Paredes', 7, 2, 'Defensa', 1),
    (51, 'Claudia', 'Castillo', 7, 9, 'Defensa', 2),
    (52, 'Wei', 'Suárez', 7, 15, 'Centrocampista', 2),
    (53, 'Óscar', 'Vidal', 7, 16, 'Centrocampista', 1),
    (54, 'Yeray', 'Popescu', 7, 20, 'Centrocampista', 0),
    (55, 'Álex', 'Lin', 7, 21, 'Delantero', 6),
    (56, 'Ioana', 'Santos', 7, 22, 'Delantero', 3),
    (57, 'Mario', 'Haddad', 8, 1, 'Portero', 0),
    (58, 'Andrei', 'Gómez', 8, 2, 'Defensa', 1),
    (59, 'Darío', 'Ionescu', 8, 4, 'Defensa', 0),
    (60, 'Rayan', 'Wang', 8, 8, 'Centrocampista', 0),
    (61, 'Ousmane', 'Ionescu', 8, 9, 'Centrocampista', 0),
    (62, 'Kofi', 'Vargas', 8, 14, 'Centrocampista', 2),
    (63, 'Inés', 'Alonso', 8, 19, 'Delantero', 5),
    (64, 'Rocío', 'Rahmani', 8, 21, 'Delantero', 6),
    (65, 'Nerea', 'Mendoza', 9, 1, 'Portero', 0),
    (66, 'Luca', 'Sow', 9, 6, 'Defensa', 0),
    (67, 'Adrián', 'Haddad', 9, 10, 'Defensa', 1),
    (68, 'Alba', 'Mensah', 9, 14, 'Centrocampista', 0),
    (69, 'Nour', 'Sow', 9, 15, 'Centrocampista', 3),
    (70, 'Joel', 'Rahmani', 9, 16, 'Centrocampista', 2),
    (71, 'Edwin', 'Paredes', 9, 18, 'Delantero', 1),
    (72, 'Moussa', 'Paredes', 9, 19, 'Delantero', 5),
    (73, 'Daniela', 'Haddad', 10, 1, 'Portero', 0),
    (74, 'Noa', 'Zhang', 10, 4, 'Defensa', 1),
    (75, 'Elena', 'Santos', 10, 8, 'Defensa', 0),
    (76, 'Mariam', 'Okafor', 10, 10, 'Centrocampista', 2),
    (77, 'Chloe', 'Haddad', 10, 12, 'Centrocampista', 1),
    (78, 'Lina', 'El Amrani', 10, 13, 'Centrocampista', 1),
    (79, 'Mei', 'Jiménez', 10, 14, 'Delantero', 2),
    (80, 'Ibrahima', 'Pérez', 10, 15, 'Delantero', 2);

-- Jornadas 1 a 7 jugadas. El partido 23 (jornada 5) está aplazado.
-- Jornadas 8 y 9 pendientes: sin resultado (NULL).
INSERT INTO partidos (id, jornada, fecha, id_local, id_visitante, goles_local, goles_visitante) VALUES
    (1, 1, '2026-09-26', 1, 10, 6, 0),
    (2, 1, '2026-09-26', 9, 2, 3, 1),
    (3, 1, '2026-09-26', 3, 8, 6, 0),
    (4, 1, '2026-09-26', 7, 4, 4, 3),
    (5, 1, '2026-09-26', 5, 6, 4, 6),
    (6, 2, '2026-10-03', 9, 1, 1, 1),
    (7, 2, '2026-10-03', 8, 10, 3, 1),
    (8, 2, '2026-10-03', 2, 7, 1, 1),
    (9, 2, '2026-10-03', 6, 3, 4, 4),
    (10, 2, '2026-10-03', 4, 5, 4, 1),
    (11, 3, '2026-10-10', 1, 8, 5, 2),
    (12, 3, '2026-10-10', 7, 9, 0, 0),
    (13, 3, '2026-10-10', 10, 6, 2, 0),
    (14, 3, '2026-10-10', 5, 2, 2, 1),
    (15, 3, '2026-10-10', 3, 4, 2, 5),
    (16, 4, '2026-10-17', 7, 1, 3, 2),
    (17, 4, '2026-10-17', 6, 8, 4, 6),
    (18, 4, '2026-10-17', 9, 5, 2, 5),
    (19, 4, '2026-10-17', 4, 10, 0, 1),
    (20, 4, '2026-10-17', 2, 3, 1, 5),
    (21, 5, '2026-10-24', 1, 6, 3, 3),
    (22, 5, '2026-10-24', 5, 7, 1, 2),
    (23, 5, '2026-10-24', 8, 4, NULL, NULL),
    (24, 5, '2026-10-24', 3, 9, 0, 0),
    (25, 5, '2026-10-24', 10, 2, 2, 4),
    (26, 6, '2026-10-31', 5, 1, 2, 2),
    (27, 6, '2026-10-31', 4, 6, 2, 1),
    (28, 6, '2026-10-31', 7, 3, 5, 1),
    (29, 6, '2026-10-31', 2, 8, 1, 1),
    (30, 6, '2026-10-31', 9, 10, 4, 1),
    (31, 7, '2026-11-07', 1, 4, 5, 1),
    (32, 7, '2026-11-07', 3, 5, 2, 0),
    (33, 7, '2026-11-07', 6, 2, 5, 4),
    (34, 7, '2026-11-07', 10, 7, 2, 0),
    (35, 7, '2026-11-07', 8, 9, 2, 2),
    (36, 8, '2026-11-14', 3, 1, NULL, NULL),
    (37, 8, '2026-11-14', 2, 4, NULL, NULL),
    (38, 8, '2026-11-14', 5, 10, NULL, NULL),
    (39, 8, '2026-11-14', 9, 6, NULL, NULL),
    (40, 8, '2026-11-14', 7, 8, NULL, NULL),
    (41, 9, '2026-11-21', 1, 2, NULL, NULL),
    (42, 9, '2026-11-21', 10, 3, NULL, NULL),
    (43, 9, '2026-11-21', 4, 9, NULL, NULL),
    (44, 9, '2026-11-21', 8, 5, NULL, NULL),
    (45, 9, '2026-11-21', 6, 7, NULL, NULL);

-- Rutinas almacenadas. Se borran y se vuelven a crear en cada ejecución.
DROP PROCEDURE IF EXISTS registrar_resultado;
DROP PROCEDURE IF EXISTS estadisticas_equipo;
DROP FUNCTION IF EXISTS puntos_equipo;
DROP FUNCTION IF EXISTS goles_a_favor;

DELIMITER //

-- Función: puntos de un equipo (3 por victoria, 1 por empate) en los partidos jugados
CREATE FUNCTION puntos_equipo(p_id_equipo INT) RETURNS INT
READS SQL DATA
BEGIN
    DECLARE v_puntos INT;
    SELECT COALESCE(SUM(
               CASE
                   WHEN goles_local = goles_visitante THEN 1
                   WHEN id_local = p_id_equipo AND goles_local > goles_visitante THEN 3
                   WHEN id_visitante = p_id_equipo AND goles_visitante > goles_local THEN 3
                   ELSE 0
               END), 0)
    INTO v_puntos
    FROM partidos
    WHERE (id_local = p_id_equipo OR id_visitante = p_id_equipo)
      AND goles_local IS NOT NULL;
    RETURN v_puntos;
END //

-- Función: goles marcados por un equipo en los partidos jugados
CREATE FUNCTION goles_a_favor(p_id_equipo INT) RETURNS INT
READS SQL DATA
BEGIN
    DECLARE v_goles INT;
    SELECT COALESCE(SUM(
               CASE
                   WHEN id_local = p_id_equipo THEN goles_local
                   ELSE goles_visitante
               END), 0)
    INTO v_goles
    FROM partidos
    WHERE (id_local = p_id_equipo OR id_visitante = p_id_equipo)
      AND goles_local IS NOT NULL;
    RETURN v_goles;
END //

-- Procedimiento: guarda el resultado de un partido (tres parámetros IN)
CREATE PROCEDURE registrar_resultado(IN p_id_partido INT, IN p_goles_local INT, IN p_goles_visitante INT)
BEGIN
    IF p_goles_local < 0 OR p_goles_visitante < 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Los goles no pueden ser negativos';
    END IF;
    IF NOT EXISTS (SELECT 1 FROM partidos WHERE id = p_id_partido) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'No existe ese partido';
    END IF;
    UPDATE partidos
    SET goles_local = p_goles_local, goles_visitante = p_goles_visitante
    WHERE id = p_id_partido;
END //

-- Procedimiento: partidos jugados y puntos de un equipo (un parámetro IN y dos OUT)
CREATE PROCEDURE estadisticas_equipo(IN p_id_equipo INT, OUT p_jugados INT, OUT p_puntos INT)
BEGIN
    SELECT COUNT(*)
    INTO p_jugados
    FROM partidos
    WHERE (id_local = p_id_equipo OR id_visitante = p_id_equipo)
      AND goles_local IS NOT NULL;
    SET p_puntos = puntos_equipo(p_id_equipo);
END //

DELIMITER ;
