

CREATE DATABASE IF NOT EXISTS gymflow
  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE gymflow;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS series;
DROP TABLE IF EXISTS entrenamientos;
DROP TABLE IF EXISTS rutina_ejercicio;
DROP TABLE IF EXISTS rutinas;
DROP TABLE IF EXISTS objetivos;
DROP TABLE IF EXISTS ejercicios;
DROP TABLE IF EXISTS usuarios;
DROP TABLE IF EXISTS niveles;
DROP TABLE IF EXISTS grupos_musculares;
DROP TABLE IF EXISTS tipos_ejercicio;
DROP TABLE IF EXISTS tipos_objetivo;
DROP TABLE IF EXISTS estados_objetivo;

SET FOREIGN_KEY_CHECKS = 1;

-- ================================================================
-- TABLAS DE DOMINIO  (elimina ENUMs duplicados → cumple 3FN)
-- ================================================================

-- D1. Niveles de dificultad / usuario
CREATE TABLE niveles (
  idNivel  INT AUTO_INCREMENT PRIMARY KEY,
  nombre   VARCHAR(20) NOT NULL UNIQUE   -- PRINCIPIANTE | MEDIO | AVANZADO
);

-- D2. Grupos musculares
CREATE TABLE grupos_musculares (
  idGrupo  INT AUTO_INCREMENT PRIMARY KEY,
  nombre   VARCHAR(50) NOT NULL UNIQUE   -- Pecho, Espalda, Pierna…
);

-- D3. Tipos de ejercicio
CREATE TABLE tipos_ejercicio (
  idTipo  INT AUTO_INCREMENT PRIMARY KEY,
  nombre  VARCHAR(30) NOT NULL UNIQUE    -- FUERZA | CARDIO | FLEXIBILIDAD
);

-- D4. Tipos de objetivo
CREATE TABLE tipos_objetivo (
  idTipo  INT AUTO_INCREMENT PRIMARY KEY,
  nombre  VARCHAR(30) NOT NULL UNIQUE    -- PESO | FUERZA | RESISTENCIA | FLEXIBILIDAD
);

-- D5. Estados de objetivo
CREATE TABLE estados_objetivo (
  idEstado  INT AUTO_INCREMENT PRIMARY KEY,
  nombre    VARCHAR(20) NOT NULL UNIQUE  -- ACTIVO | COMPLETADO | CANCELADO
);

-- ================================================================
-- TABLAS DE DATOS
-- ================================================================

-- 1. USUARIOS
--    · nivel → FK a niveles  (elimina ENUM local → 3FN)
CREATE TABLE usuarios (
  idUsuario      INT AUTO_INCREMENT PRIMARY KEY,
  nombre         VARCHAR(100) NOT NULL,
  email          VARCHAR(150) NOT NULL UNIQUE,
  contrasena     VARCHAR(64)  NOT NULL,            -- SHA2('pwd', 256) = 64 hex chars
  peso           DECIMAL(5,2),
  altura         DECIMAL(5,2),
  idNivel        INT NOT NULL DEFAULT 1,           -- FK → niveles
  fechaRegistro  DATE DEFAULT (CURRENT_DATE),
  FOREIGN KEY (idNivel) REFERENCES niveles(idNivel)
);

-- 2. OBJETIVOS
--    · tipo  → FK a tipos_objetivo   (elimina ENUM local → 3FN)
--    · estado→ FK a estados_objetivo (elimina ENUM local → 3FN)
CREATE TABLE objetivos (
  idObjetivo   INT AUTO_INCREMENT PRIMARY KEY,
  idUsuario    INT NOT NULL,
  idTipo       INT NOT NULL,                       -- FK → tipos_objetivo
  descripcion  TEXT,
  valorMeta    DECIMAL(8,2) NOT NULL,
  valorActual  DECIMAL(8,2) DEFAULT 0,
  fechaInicio  DATE NOT NULL,
  fechaFin     DATE,
  idEstado     INT NOT NULL DEFAULT 1,             -- FK → estados_objetivo
  FOREIGN KEY (idUsuario) REFERENCES usuarios(idUsuario)         ON DELETE CASCADE,
  FOREIGN KEY (idTipo)    REFERENCES tipos_objetivo(idTipo),
  FOREIGN KEY (idEstado)  REFERENCES estados_objetivo(idEstado)
);

-- 3. EJERCICIOS
--    · grupoMuscular → FK a grupos_musculares (elimina VARCHAR libre → 3FN)
--    · tipo          → FK a tipos_ejercicio   (elimina ENUM local   → 3FN)
--    · nivel         → FK a niveles           (elimina ENUM local   → 3FN)
CREATE TABLE ejercicios (
  idEjercicio  INT AUTO_INCREMENT PRIMARY KEY,
  nombre       VARCHAR(100) NOT NULL,
  descripcion  TEXT,
  idGrupo      INT,                                -- FK → grupos_musculares
  idTipo       INT NOT NULL,                       -- FK → tipos_ejercicio
  idNivel      INT,                                -- FK → niveles
  imagenUrl    VARCHAR(255),
  FOREIGN KEY (idGrupo)  REFERENCES grupos_musculares(idGrupo),
  FOREIGN KEY (idTipo)   REFERENCES tipos_ejercicio(idTipo),
  FOREIGN KEY (idNivel)  REFERENCES niveles(idNivel)
);

-- 4. RUTINAS
--    · nivel → FK a niveles (elimina ENUM local → 3FN)
CREATE TABLE rutinas (
  idRutina      INT AUTO_INCREMENT PRIMARY KEY,
  idUsuario     INT NOT NULL,
  nombre        VARCHAR(100) NOT NULL,
  descripcion   TEXT,
  idNivel       INT,                               -- FK → niveles
  diasSemana    INT DEFAULT 3,
  fechaCreacion DATE DEFAULT (CURRENT_DATE),
  FOREIGN KEY (idUsuario) REFERENCES usuarios(idUsuario) ON DELETE CASCADE,
  FOREIGN KEY (idNivel)   REFERENCES niveles(idNivel)
);

-- 5. RUTINA_EJERCICIO (tabla pivote N:M)
--    PK compuesta → todos los atributos dependen del par completo (2FN ✓)
CREATE TABLE rutina_ejercicio (
  idRutina      INT NOT NULL,
  idEjercicio   INT NOT NULL,
  series        INT NOT NULL DEFAULT 3,
  repeticiones  INT NOT NULL DEFAULT 10,
  pesoSugerido  DECIMAL(5,2),
  orden         INT NOT NULL DEFAULT 1,
  PRIMARY KEY (idRutina, idEjercicio),
  FOREIGN KEY (idRutina)    REFERENCES rutinas(idRutina)       ON DELETE CASCADE,
  FOREIGN KEY (idEjercicio) REFERENCES ejercicios(idEjercicio) ON DELETE CASCADE
);

-- 6. ENTRENAMIENTOS
CREATE TABLE entrenamientos (
  idEntrenamiento  INT AUTO_INCREMENT PRIMARY KEY,
  idUsuario        INT NOT NULL,
  idRutina         INT,                            -- NULL si sesión libre
  fecha            DATE NOT NULL,
  duracionMinutos  INT,
  caloriasQuemadas DECIMAL(7,2),
  notas            TEXT,
  FOREIGN KEY (idUsuario) REFERENCES usuarios(idUsuario) ON DELETE CASCADE,
  FOREIGN KEY (idRutina)  REFERENCES rutinas(idRutina)   ON DELETE SET NULL
);

-- 7. SERIES
CREATE TABLE series (
  idSerie            INT AUTO_INCREMENT PRIMARY KEY,
  idEntrenamiento    INT NOT NULL,
  idEjercicio        INT NOT NULL,
  numSerie           INT NOT NULL,
  repeticionesHechas INT NOT NULL,
  pesoUsado          DECIMAL(5,2),
  descansoSegundos   INT,
  FOREIGN KEY (idEntrenamiento) REFERENCES entrenamientos(idEntrenamiento) ON DELETE CASCADE,
  FOREIGN KEY (idEjercicio)     REFERENCES ejercicios(idEjercicio)         ON DELETE CASCADE
);

-- ================================================================
-- DATOS DE PRUEBA
-- ================================================================

-- Dominio: niveles
INSERT INTO niveles (nombre) VALUES
  ('PRINCIPIANTE'), ('MEDIO'), ('AVANZADO');

-- Dominio: grupos musculares
INSERT INTO grupos_musculares (nombre) VALUES
  ('Pecho'), ('Espalda'), ('Pierna'), ('Bíceps'), ('Tríceps'), ('Hombros'), ('Core'), ('Global');

-- Dominio: tipos de ejercicio
INSERT INTO tipos_ejercicio (nombre) VALUES
  ('FUERZA'), ('CARDIO'), ('FLEXIBILIDAD');

-- Dominio: tipos de objetivo
INSERT INTO tipos_objetivo (nombre) VALUES
  ('PESO'), ('FUERZA'), ('RESISTENCIA'), ('FLEXIBILIDAD');

-- Dominio: estados de objetivo
INSERT INTO estados_objetivo (nombre) VALUES
  ('ACTIVO'), ('COMPLETADO'), ('CANCELADO');

-- Usuarios  (contraseña: 1234 → SHA2('1234', 256))
INSERT INTO usuarios (nombre, email, contrasena, peso, altura, idNivel) VALUES
  ('Miguel Díaz',    'miguel@gymflow.com',  SHA2('1234', 256), 80.5, 178.0, 2),  -- MEDIO
  ('Ismael Gavilán', 'ismael@gymflow.com',  SHA2('1234', 256), 75.0, 175.0, 1);  -- PRINCIPIANTE

-- Ejercicios
--   idGrupo: 1=Pecho 2=Espalda 3=Pierna 4=Bíceps 7=Core 8=Global
--   idTipo:  1=FUERZA 2=CARDIO 3=FLEXIBILIDAD
--   idNivel: 1=PRINCIPIANTE 2=MEDIO 3=AVANZADO
INSERT INTO ejercicios (nombre, idGrupo, idTipo, idNivel) VALUES
  ('Press Banca',       1, 1, 2),
  ('Sentadilla',        3, 1, 2),
  ('Dominadas',         2, 1, 3),
  ('Carrera 5km',       8, 2, 1),
  ('Curl Bíceps',       4, 1, 1),
  ('Plancha Abdominal', 7, 3, 1);

-- Objetivos
--   idTipo:   1=PESO 2=FUERZA 3=RESISTENCIA
--   idEstado: 1=ACTIVO
INSERT INTO objetivos (idUsuario, idTipo, descripcion, valorMeta, fechaInicio, fechaFin, idEstado) VALUES
  (1, 2, 'Press banca 100kg', 100.0, '2026-06-01', '2026-12-01', 1),
  (2, 1, 'Llegar a 70kg',      70.0, '2026-06-01', '2026-09-01', 1);

