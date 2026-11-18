-- Campo-Vet: tablas usadas por los módulos de INVENTARIO (PRODUCTO) y GASTOS (GASTO).
-- ROL y USUARIO se incluyen porque GASTO se relaciona con USUARIO (1 usuario registra * gastos).
-- Ejecutar una sola vez sobre una base de datos nueva.
CREATE DATABASE IF NOT EXISTS campovet CHARACTER SET utf8mb4;
USE campovet;

CREATE TABLE IF NOT EXISTS rol (
    id_rol INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre     VARCHAR(100) NOT NULL,
    contrasena VARCHAR(255) NOT NULL,
    id_rol     INT NOT NULL,
    FOREIGN KEY (id_rol) REFERENCES rol (id_rol)
);

CREATE TABLE IF NOT EXISTS producto (
    id_producto         INT AUTO_INCREMENT PRIMARY KEY,
    nombre              VARCHAR(100)  NOT NULL,
    descripcion         VARCHAR(255),
    cantidad_disponible INT           NOT NULL DEFAULT 0,
    precio_venta        DECIMAL(12,2) NOT NULL,
    fecha_registro      DATE          NOT NULL
);

CREATE TABLE IF NOT EXISTS gasto (
    id_gasto    INT AUTO_INCREMENT PRIMARY KEY,
    fecha       DATE          NOT NULL,
    monto       DECIMAL(12,2) NOT NULL,
    descripcion VARCHAR(255)  NOT NULL,
    id_usuario  INT           NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario)
);

-- Datos iniciales (la autenticación real se desarrolla en otra fase)
INSERT INTO rol (nombre) VALUES ('ADMINISTRADOR'), ('OPERADOR');
INSERT INTO usuario (nombre, contrasena, id_rol)
VALUES ('Administrador', 'demo123', (SELECT id_rol FROM rol WHERE nombre = 'ADMINISTRADOR'));
