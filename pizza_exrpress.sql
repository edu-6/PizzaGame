CREATE DATABASE pizzaDB;
USE pizzaDB;

CREATE TABLE configuracion_global (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tiempo_partida INT NOT NULL,
    tiempo_nivel_1 INT NOT NULL,
    tiempo_nivel_2 INT NOT NULL,
    tiempo_nivel_3 INT NOT NULL,
    puntos_nivel_2 INT NOT NULL,
    puntos_nivel_3 INT NOT NULL,
    tiempo_entre_pedidos INT NOT NULL
);

CREATE TABLE configuracion_punteos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    bono_pedido_correcto INT NOT NULL,
    bono_tiempo_optimo INT NOT NULL,
    bono_eficiencia INT NOT NULL,
    pedido_cancelado INT NOT NULL,
    pedido_incompleto INT NOT NULL
);

CREATE TABLE sucursal (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    ubicacion VARCHAR(255) NOT NULL
);

CREATE TABLE usuario (
    nickname VARCHAR(50) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    contraseña VARCHAR(255) NOT NULL,
    rol VARCHAR(50)  NOT NULL,
    id_sucursal INT NULL,
    CONSTRAINT fk_usuario_sucursal FOREIGN KEY (id_sucursal) 
        REFERENCES sucursal(id) ON DELETE SET NULL
);

CREATE TABLE producto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    id_sucursal INT NOT NULL,
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    CONSTRAINT fk_producto_sucursal FOREIGN KEY (id_sucursal) 
        REFERENCES sucursal(id) ON DELETE CASCADE
);

CREATE TABLE partida (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_cocinero VARCHAR(50) NOT NULL,
    id_sucursal INT NOT NULL,
    tiempo_partida INT NOT NULL,
    puntos_totales INT NOT NULL DEFAULT 0,
    fecha DATETIME NOT NULL,
    nivel_alcanzado INT NOT NULL DEFAULT 1,
    pedidos_exitosos INT NOT NULL DEFAULT 0,
    pedidos_incompletos INT NOT NULL DEFAULT 0,
    pedidos_cancelados INT NOT NULL DEFAULT 0,
    penalizacion_incompletos INT NOT NULL DEFAULT 0,
    penalizacion_cancelados INT NOT NULL DEFAULT 0,
    puntos_pedido_correcto INT NOT NULL DEFAULT 0,
    puntos_tiempo_optimo INT NOT NULL DEFAULT 0,
    puntos_bono_eficiencia INT NOT NULL DEFAULT 0,
    CONSTRAINT fk_partida_usuario FOREIGN KEY (id_cocinero) 
        REFERENCES usuario(nickname) ON DELETE CASCADE,
    CONSTRAINT fk_partida_sucursal FOREIGN KEY (id_sucursal) 
        REFERENCES sucursal(id) ON DELETE CASCADE
);

CREATE TABLE pedido (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_sucursal INT NOT NULL,
    id_partida INT NOT NULL,
    CONSTRAINT fk_pedido_sucursal FOREIGN KEY (id_sucursal) 
        REFERENCES sucursal(id) ON DELETE CASCADE,
    CONSTRAINT fk_pedido_partida FOREIGN KEY (id_partida) 
        REFERENCES partida(id) ON DELETE CASCADE
);

CREATE TABLE producto_pedido (
    id_pedido INT NOT NULL,
    id_producto INT NOT NULL,
    cantidad INT NOT NULL,
    PRIMARY KEY (id_pedido, id_producto),
    CONSTRAINT fk_pp_pedido FOREIGN KEY (id_pedido) 
        REFERENCES pedido(id) ON DELETE CASCADE,
    CONSTRAINT fk_pp_producto FOREIGN KEY (id_producto) 
        REFERENCES producto(id) ON DELETE CASCADE
);

CREATE TABLE tabla_estados (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_pedido INT NOT NULL,
    recibida BOOLEAN DEFAULT FALSE,
    preparando BOOLEAN DEFAULT FALSE,
    en_horno BOOLEAN DEFAULT FALSE,
    entregado BOOLEAN DEFAULT FALSE,
    no_entregado BOOLEAN DEFAULT FALSE,
    cancelado BOOLEAN DEFAULT FALSE,
    CONSTRAINT fk_estados_pedido FOREIGN KEY (id_pedido) 
        REFERENCES pedido(id) ON DELETE CASCADE
);
