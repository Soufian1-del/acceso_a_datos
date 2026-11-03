-- P10 · Socios del gimnasio de barrio «Barrio Fit»
-- Crea la tabla si no existe y carga 25 socios (id 4 a 28).
-- El programa lo ejecuta con RUNSCRIPT FROM 'datos/socios.sql'.
-- También se puede ejecutar a mano en DBeaver con Alt+X.

CREATE TABLE IF NOT EXISTS socio (
    id INT PRIMARY KEY,
    nombre VARCHAR(80) NOT NULL,
    plan VARCHAR(20),
    cuota DECIMAL(5,2),
    fecha_alta DATE,
    activo BOOLEAN
);

INSERT INTO socio (id, nombre, plan, cuota, fecha_alta, activo) VALUES
(4, 'Andrea Popescu', 'Completo', 39.90, '2024-03-18', TRUE),
(5, 'Kevin Mendoza Quispe', 'Básico', 24.90, '2025-01-07', TRUE),
(6, 'Fatoumata Diallo', 'Nocturno', 19.90, '2023-11-20', TRUE),
(7, 'Javier Ruiz Moreno', 'Completo', 39.90, '2023-02-14', FALSE),
(8, 'Yasmin Benali', 'Básico', 24.90, '2026-02-03', TRUE),
(9, 'Daniel Okafor', 'Nocturno', 19.90, '2024-09-30', TRUE),
(10, 'Carmen Navarro Díaz', 'Básico', 24.90, '2023-05-08', FALSE),
(11, 'Iván Petrov', 'Completo', 39.90, '2025-06-21', TRUE),
(12, 'María José Santos', 'Básico', 24.90, '2024-10-12', TRUE),
(13, 'Hamza Ouali', 'Completo', 39.90, '2026-01-09', TRUE),
(14, 'Valentina Rojas Pérez', 'Nocturno', 19.90, '2025-03-27', TRUE),
(15, 'Sergio Gil Herrera', 'Básico', 24.90, '2023-09-01', TRUE),
(16, 'Ana Lucía Torres', 'Completo', 39.90, '2024-05-16', FALSE),
(17, 'Bilal Rahman', 'Nocturno', 19.90, '2026-04-22', TRUE),
(18, 'Elena Martín Vega', 'Completo', 39.90, '2023-01-23', TRUE),
(19, 'Joel Agyeman', 'Básico', 24.90, '2025-10-05', TRUE),
(20, 'Nerea Castillo', 'Básico', 24.90, '2024-07-01', TRUE),
(21, 'Li Na Zhang', 'Completo', 39.90, '2025-11-14', TRUE),
(22, 'Rubén Ortega Sanz', 'Nocturno', 19.90, '2023-12-11', FALSE),
(23, 'Aisha Mbaye', 'Completo', 39.90, '2026-06-30', TRUE),
(24, 'Pablo Ramírez Cano', 'Básico', 24.90, '2024-02-29', TRUE),
(25, 'Irina Kovalenko', 'Nocturno', 19.90, '2025-08-19', TRUE),
(26, 'Óscar León Prieto', 'Completo', 39.90, '2023-07-04', TRUE),
(27, 'Samira Haddad', 'Básico', 24.90, '2026-09-08', TRUE),
(28, 'Marcos Delgado Ruiz', 'Nocturno', 19.90, '2024-11-25', FALSE);
