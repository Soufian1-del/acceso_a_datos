-- P14 · Transacciones · Venta de entradas para conciertos
-- Crea la base de datos ad_p14, borra y vuelve a crear las tablas y carga los datos.
-- Ejecútalo en DBeaver con Alt+X. Volver a ejecutarlo deja los datos como al principio.

CREATE DATABASE IF NOT EXISTS ad_p14 CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE ad_p14;

DROP TABLE IF EXISTS ventas;
DROP TABLE IF EXISTS conciertos;
DROP TABLE IF EXISTS clientes;

CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(60) NOT NULL,
    email VARCHAR(80) NOT NULL UNIQUE,
    saldo DECIMAL(8,2) NOT NULL DEFAULT 0,
    CONSTRAINT chk_saldo CHECK (saldo >= 0)
) ENGINE=InnoDB;

CREATE TABLE conciertos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    artista VARCHAR(60) NOT NULL,
    sala VARCHAR(60) NOT NULL,
    fecha DATE NOT NULL,
    precio DECIMAL(6,2) NOT NULL,
    aforo INT NOT NULL,
    entradas_disponibles INT NOT NULL,
    CONSTRAINT chk_entradas CHECK (entradas_disponibles BETWEEN 0 AND aforo)
) ENGINE=InnoDB;

CREATE TABLE ventas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT NOT NULL,
    id_concierto INT NOT NULL,
    cantidad INT NOT NULL,
    importe DECIMAL(8,2) NOT NULL,
    fecha_compra DATE NOT NULL,
    CONSTRAINT fk_ventas_cliente FOREIGN KEY (id_cliente) REFERENCES clientes (id),
    CONSTRAINT fk_ventas_concierto FOREIGN KEY (id_concierto) REFERENCES conciertos (id)
) ENGINE=InnoDB;

INSERT INTO clientes (id, nombre, email, saldo) VALUES
    (1, 'Aitana Ruiz', 'aitana.ruiz@correo.test', 100.00),
    (2, 'Mohamed El Amrani', 'mohamed.amrani@correo.test', 75.00),
    (3, 'Wei Chen', 'wei.chen@correo.test', 60.00),
    (4, 'Daniela Pérez', 'daniela.perez@correo.test', 80.00),
    (5, 'Kevin Mendoza', 'kevin.mendoza@correo.test', 50.00),
    (6, 'Fátima Bouzid', 'fatima.bouzid@correo.test', 45.00),
    (7, 'Iker Gómez', 'iker.gomez@correo.test', 5.00),
    (8, 'Ana Popescu', 'ana.popescu@correo.test', 120.00),
    (9, 'Jhon Cárdenas', 'jhon.cardenas@correo.test', 0.00),
    (10, 'Lucía Fernández', 'lucia.fernandez@correo.test', 90.00),
    (11, 'Omar Diallo', 'omar.diallo@correo.test', 35.50),
    (12, 'Valeria Rojas', 'valeria.rojas@correo.test', 64.00),
    (13, 'Sara Moreno', 'sara.moreno@correo.test', 150.00),
    (14, 'Andrei Ionescu', 'andrei.ionescu@correo.test', 22.00),
    (15, 'Nerea Castillo', 'nerea.castillo@correo.test', 70.00),
    (16, 'Bilal Khan', 'bilal.khan@correo.test', 58.00),
    (17, 'Paula Ortega', 'paula.ortega@correo.test', 40.00),
    (18, 'Darío Asante', 'dario.asante@correo.test', 95.00),
    (19, 'Carmen Jiménez', 'carmen.jimenez@correo.test', 110.00),
    (20, 'Hugo Martín', 'hugo.martin@correo.test', 30.00);

INSERT INTO conciertos (id, artista, sala, fecha, precio, aforo, entradas_disponibles) VALUES
    (1, 'Lúa Serrano', 'Sala Brújula', '2026-12-04', 18.00, 300, 120),
    (2, 'Los Tranvías Azules', 'Nave Cobalto', '2026-12-11', 22.50, 800, 2),
    (3, 'Kora Nomad', 'Sala Mirlo', '2026-12-12', 15.00, 200, 0),
    (4, 'DJ Ámbar Quintero', 'Nave Cobalto', '2026-12-19', 25.00, 800, 350),
    (5, 'Mapache Eléctrico', 'Sala Brújula', '2027-01-15', 16.00, 300, 90),
    (6, 'Yeray y la Banda del Sur', 'Teatro Vereda', '2027-01-22', 30.00, 500, 210),
    (7, 'Nadia Okafor', 'Sala Mirlo', '2027-01-29', 20.00, 200, 45),
    (8, 'Colectivo Azotea', 'Sala Mirlo', '2027-02-05', 12.00, 150, 60),
    (9, 'Mei Lin Trío', 'Teatro Vereda', '2027-02-12', 28.00, 500, 5),
    (10, 'Valquiria Sintética', 'Nave Cobalto', '2027-02-19', 24.00, 800, 400),
    (11, 'Rumba Satélite', 'Sala Brújula', '2027-02-26', 14.50, 300, 150),
    (12, 'Ingrid Matos', 'Auditorio Ribera', '2027-03-05', 35.00, 1000, 520);

INSERT INTO ventas (id, id_cliente, id_concierto, cantidad, importe, fecha_compra) VALUES
    (1, 8, 9, 2, 56.00, '2026-10-01'),
    (2, 13, 12, 4, 140.00, '2026-10-02'),
    (3, 1, 5, 1, 16.00, '2026-10-03'),
    (4, 19, 4, 2, 50.00, '2026-10-03'),
    (5, 10, 1, 2, 36.00, '2026-10-05'),
    (6, 18, 6, 3, 90.00, '2026-10-06'),
    (7, 2, 7, 1, 20.00, '2026-10-07'),
    (8, 12, 11, 2, 29.00, '2026-10-08'),
    (9, 15, 3, 2, 30.00, '2026-10-09'),
    (10, 16, 10, 1, 24.00, '2026-10-10'),
    (11, 3, 8, 2, 24.00, '2026-10-11'),
    (12, 17, 2, 1, 22.50, '2026-10-12'),
    (13, 20, 1, 1, 18.00, '2026-10-13'),
    (14, 11, 11, 1, 14.50, '2026-10-14'),
    (15, 14, 8, 1, 12.00, '2026-10-15'),
    (16, 4, 4, 2, 50.00, '2026-10-16'),
    (17, 5, 12, 1, 35.00, '2026-10-17'),
    (18, 6, 9, 1, 28.00, '2026-10-18'),
    (19, 9, 3, 1, 15.00, '2026-10-19'),
    (20, 13, 6, 2, 60.00, '2026-10-20'),
    (21, 19, 10, 3, 72.00, '2026-10-21'),
    (22, 7, 5, 1, 16.00, '2026-10-22'),
    (23, 18, 2, 2, 45.00, '2026-10-23'),
    (24, 10, 7, 1, 20.00, '2026-10-24'),
    (25, 12, 4, 1, 25.00, '2026-10-25'),
    (26, 1, 3, 2, 30.00, '2026-10-26'),
    (27, 15, 12, 2, 70.00, '2026-10-27'),
    (28, 8, 1, 1, 18.00, '2026-10-28'),
    (29, 16, 6, 1, 30.00, '2026-10-29'),
    (30, 20, 11, 2, 29.00, '2026-10-30');
