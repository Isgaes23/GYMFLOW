
CREATE DATABASE IF NOT EXISTS gymflow
  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE gymflow;

-- 1. USUARIOS: información principal del usuario
CREATE TABLE usuarios (
  idUsuario      INT AUTO_INCREMENT PRIMARY KEY,
  nombre         VARCHAR(100) NOT NULL,
  email          VARCHAR(150) NOT NULL UNIQUE,
  contrasena     VARCHAR(255) NOT NULL,       -- almacenada con hash bcrypt
  peso           DECIMAL(5,2),
  altura         DECIMAL(5,2),
  nivel          ENUM('PRINCIPIANTE','MEDIO','AVANZADO') DEFAULT 'PRINCIPIANTE',
  fechaRegistro  DATE DEFAULT (CURRENT_DATE)
);

-- 2. OBJETIVOS: metas personales de cada usuario
CREATE TABLE objetivos (
  idObjetivo   INT AUTO_INCREMENT PRIMARY KEY,
  idUsuario    INT NOT NULL,
  tipo         ENUM('PESO','FUERZA','RESISTENCIA','FLEXIBILIDAD') NOT NULL,
  descripcion  TEXT,
  valorMeta    DECIMAL(8,2) NOT NULL,
  valorActual  DECIMAL(8,2) DEFAULT 0,
  fechaInicio  DATE NOT NULL,
  fechaFin     DATE,
  estado       ENUM('ACTIVO','COMPLETADO','CANCELADO') DEFAULT 'ACTIVO',
  FOREIGN KEY (idUsuario) REFERENCES usuarios(idUsuario) ON DELETE CASCADE
);

-- 3. EJERCICIOS: catálogo global de ejercicios
CREATE TABLE ejercicios (
  idEjercicio    INT AUTO_INCREMENT PRIMARY KEY,
  nombre         VARCHAR(100) NOT NULL,
  descripcion    TEXT,
  grupoMuscular  VARCHAR(50),               -- Pecho, Espalda, Pierna...
  tipo           ENUM('FUERZA','CARDIO','FLEXIBILIDAD') NOT NULL,
  nivel          ENUM('PRINCIPIANTE','MEDIO','AVANZADO'),
  imagenUrl      VARCHAR(255)
);

-- 4. RUTINAS: plantillas de entrenamiento por usuario
CREATE TABLE rutinas (
  idRutina       INT AUTO_INCREMENT PRIMARY KEY,
  idUsuario      INT NOT NULL,
  nombre         VARCHAR(100) NOT NULL,
  descripcion    TEXT,
  nivel          ENUM('PRINCIPIANTE','MEDIO','AVANZADO'),
  diasSemana     INT DEFAULT 3,
  fechaCreacion  DATE DEFAULT (CURRENT_DATE),
  FOREIGN KEY (idUsuario) REFERENCES usuarios(idUsuario) ON DELETE CASCADE
);

-- 5. RUTINA_EJERCICIO: tabla pivote N:M rutinas ↔ ejercicios
CREATE TABLE rutina_ejercicio (
  idRutina        INT NOT NULL,
  idEjercicio     INT NOT NULL,
  series          INT NOT NULL DEFAULT 3,
  repeticiones    INT NOT NULL DEFAULT 10,
  pesoSugerido    DECIMAL(5,2),
  orden           INT NOT NULL DEFAULT 1,
  PRIMARY KEY (idRutina, idEjercicio),
  FOREIGN KEY (idRutina)    REFERENCES rutinas(idRutina)     ON DELETE CASCADE,
  FOREIGN KEY (idEjercicio) REFERENCES ejercicios(idEjercicio) ON DELETE CASCADE
);

-- 6. ENTRENAMIENTOS: registro de cada sesión completada
CREATE TABLE entrenamientos (
  idEntrenamiento    INT AUTO_INCREMENT PRIMARY KEY,
  idUsuario          INT NOT NULL,
  idRutina           INT,                             -- puede ser libre (NULL)
  fecha              DATE NOT NULL,
  duracionMinutos    INT,
  caloriasQuemadas   DECIMAL(7,2),
  notas              TEXT,
  FOREIGN KEY (idUsuario) REFERENCES usuarios(idUsuario) ON DELETE CASCADE,
  FOREIGN KEY (idRutina)  REFERENCES rutinas(idRutina)   ON DELETE SET NULL
);

-- 7. SERIES: detalle de cada serie dentro de un entrenamiento
CREATE TABLE series (
  idSerie              INT AUTO_INCREMENT PRIMARY KEY,
  idEntrenamiento      INT NOT NULL,
  idEjercicio          INT NOT NULL,
  numSerie             INT NOT NULL,
  repeticionesHechas   INT NOT NULL,
  pesoUsado            DECIMAL(5,2),
  descansoSegundos     INT,
  FOREIGN KEY (idEntrenamiento) REFERENCES entrenamientos(idEntrenamiento) ON DELETE CASCADE,
  FOREIGN KEY (idEjercicio)     REFERENCES ejercicios(idEjercicio)         ON DELETE CASCADE
);

-- ============================================
-- DATOS DE PRUEBA
-- ============================================

INSERT INTO usuarios (nombre, email, contrasena, peso, altura, nivel) VALUES
  ('Miguel Díaz',   'miguel@gymflow.com',  '$2b$12$hash1', 80.5, 178.0, 'MEDIO'),
  ('Ismael Gavilán', 'ismael@gymflow.com', '$2b$12$hash2', 75.0, 175.0, 'PRINCIPIANTE');

INSERT INTO ejercicios (nombre, grupoMuscular, tipo, nivel) VALUES
  ('Press Banca',      'Pecho',    'FUERZA',       'MEDIO'),
  ('Sentadilla',       'Pierna',   'FUERZA',       'MEDIO'),
  ('Dominadas',        'Espalda',  'FUERZA',       'AVANZADO'),
  ('Carrera 5km',      'Global',   'CARDIO',       'PRINCIPIANTE'),
  ('Curl Bíceps',      'Bíceps',   'FUERZA',       'PRINCIPIANTE'),
  ('Plancha Abdominal','Core',     'FLEXIBILIDAD', 'PRINCIPIANTE');

INSERT INTO objetivos (idUsuario, tipo, descripcion, valorMeta, fechaInicio, fechaFin) VALUES
  (1, 'FUERZA',     'Press banca 100kg',  100.0, '2026-06-01', '2026-12-01'),
  (2, 'PESO',       'Llegar a 70kg',       70.0,  '2026-06-01', '2026-09-01');