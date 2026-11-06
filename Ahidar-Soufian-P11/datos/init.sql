-- P11 · CRUD con MariaDB y PreparedStatement
-- Tienda de videojuegos: crea la base de datos ad_p11 y la tabla videojuego con 30 juegos.
-- Se ejecuta en DBeaver con Alt+X. Volver a ejecutarlo deja los datos como al principio.

SET NAMES utf8mb4;

CREATE DATABASE IF NOT EXISTS ad_p11
  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE ad_p11;

DROP TABLE IF EXISTS videojuego;

CREATE TABLE videojuego (
  id         INT AUTO_INCREMENT PRIMARY KEY,
  titulo     VARCHAR(100) NOT NULL,
  plataforma VARCHAR(30),
  genero     VARCHAR(30),
  precio     DECIMAL(6,2),
  stock      INT
);

INSERT INTO videojuego (titulo, plataforma, genero, precio, stock) VALUES
('Ecos de Nébula', 'PC', 'Aventura', 29.99, 12),
('Pixel Brigada', 'Portátil', 'Plataformas', 19.99, 8),
('Turbo Asfalto 3', 'Sobremesa', 'Carreras', 49.99, 5),
('Liga Callejera 26', 'Sobremesa', 'Deportes', 59.99, 15),
('Reinos de Ceniza', 'PC', 'Estrategia', 39.99, 4),
('Cubos y Cables', 'Móvil', 'Puzle', 4.99, 30),
('Leyendas del Faro', 'PC', 'Rol', 44.99, 6),
('Granja Lunar', 'PC', 'Simulación', 24.99, 10),
('Ritmo Urbano', 'Sobremesa', 'Musical', 34.99, 7),
('Saltarín Galáctico', 'Móvil', 'Plataformas', 2.99, 50),
('Derrape Infinito', 'Móvil', 'Carreras', 1.99, 40),
('Mareas de Cristal', 'Sobremesa', 'Aventura', 54.99, 3),
('Torre de Runas', 'Portátil', 'Rol', 39.99, 9),
('Cancha Total', 'Portátil', 'Deportes', 29.99, 11),
('Ciudad en Obras', 'PC', 'Simulación', 19.99, 14),
('Laberinto Neón', 'PC', 'Puzle', 9.99, 20),
('Imperio de Arena', 'Sobremesa', 'Estrategia', 49.99, 2),
('Bajo Cero Rally', 'PC', 'Carreras', 39.99, 6),
('Cocina Exprés', 'Portátil', 'Simulación', 24.99, 13),
('La Isla Susurrante', 'Portátil', 'Aventura', 34.99, 0),
('Dúo de Pinceles', 'Sobremesa', 'Puzle', 14.99, 9),
('Bandas de Garaje', 'PC', 'Musical', 19.99, 5),
('Clan de las Brújulas', 'Sobremesa', 'Rol', 59.99, 7),
('Skate Horizonte', 'Móvil', 'Deportes', 3.99, 25),
('Colonia Marte', 'PC', 'Estrategia', 29.99, 8),
('Mapas del Abuelo', 'Móvil', 'Aventura', 5.99, 18),
('Pingüinos al Rescate', 'Sobremesa', 'Plataformas', 39.99, 10),
('Karting Vecinal', 'Portátil', 'Carreras', 29.99, 12),
('Detectives de Barrio', 'PC', 'Aventura', 14.99, 6),
('Estudio de Baile 2', 'Móvil', 'Musical', 4.99, 22);
