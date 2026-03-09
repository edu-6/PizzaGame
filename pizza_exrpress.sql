CREATE DATABASE pizzaDB;
USE pizzaDB;

CREATE TABLE configuracion_global (
    id_configuracion INT AUTO_INCREMENT PRIMARY KEY,
    tiempo_partida INT NOT NULL,
    tiempo_nivel_1 INT NOT NULL,
    tiempo_nivel_2 INT NOT NULL,
    tiempo_nivel_3 INT NOT NULL,
    puntos_nivel_2 INT NOT NULL,
    puntos_nivel_3 INT NOT NULL,
    tiempo_entre_pedidos INT NOT NULL,
    limite_pedidos_activos INT NOT NULL
);

CREATE TABLE configuracion_punteos (
    id_punteo INT AUTO_INCREMENT PRIMARY KEY,
    bono_pedido_correcto INT NOT NULL,
    bono_tiempo_optimo INT NOT NULL,
    bono_eficiencia INT NOT NULL,
    pedido_cancelado INT NOT NULL,
    pedido_incompleto INT NOT NULL
);

CREATE TABLE sucursal (
    id_sucursal INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    ubicacion VARCHAR(255) NOT NULL
);

CREATE TABLE usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nickname VARCHAR(50) NOT NULL UNIQUE,
    nombre VARCHAR(100) NOT NULL,
    contraseña VARCHAR(255) NOT NULL,
    rol VARCHAR(50) NOT NULL,
    id_sucursal INT NULL,
    CONSTRAINT fk_usuario_sucursal FOREIGN KEY (id_sucursal) 
        REFERENCES sucursal(id_sucursal) ON DELETE SET NULL
);

CREATE TABLE producto (
    id_producto INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(200) NOT NULL,
    id_sucursal INT NOT NULL,
    veces_pedidas INT NOT NULL DEFAULT 0,
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    CONSTRAINT fk_producto_sucursal FOREIGN KEY (id_sucursal) 
        REFERENCES sucursal(id_sucursal) ON DELETE CASCADE
);

CREATE TABLE partida (
    id_partida INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    id_sucursal INT NOT NULL,
    fecha DATETIME NOT NULL,
    nivel_alcanzado INT NOT NULL DEFAULT 1,
    pedidos_exitosos INT NOT NULL,
    pedidos_incompletos INT NOT NULL,
    pedidos_cancelados INT NOT NULL,
    puntos_bono_eficiencia INT NOT NULL,
    penalizaciones INT NOT NULL,
    puntos_totales INT NOT NULL,
    CONSTRAINT fk_partida_usuario FOREIGN KEY (id_usuario) 
        REFERENCES usuario(id_usuario) ON DELETE CASCADE,
    CONSTRAINT fk_partida_sucursal FOREIGN KEY (id_sucursal) 
        REFERENCES sucursal(id_sucursal) ON DELETE CASCADE
);

CREATE TABLE pedido (
    id_pedido INT AUTO_INCREMENT PRIMARY KEY,
    id_sucursal INT NOT NULL,
    id_partida INT NOT NULL,
    CONSTRAINT fk_pedido_sucursal FOREIGN KEY (id_sucursal) 
        REFERENCES sucursal(id_sucursal) ON DELETE CASCADE,
    CONSTRAINT fk_pedido_partida FOREIGN KEY (id_partida) 
        REFERENCES partida(id_partida) ON DELETE CASCADE
);

CREATE TABLE producto_pedido (
    id_pedido INT NOT NULL,
    id_producto INT NOT NULL,
    cantidad INT NOT NULL,
    PRIMARY KEY (id_pedido, id_producto),
    CONSTRAINT fk_pp_pedido FOREIGN KEY (id_pedido) 
        REFERENCES pedido(id_pedido) ON DELETE CASCADE,
    CONSTRAINT fk_pp_producto FOREIGN KEY (id_producto) 
        REFERENCES producto(id_producto) ON DELETE CASCADE
);

CREATE TABLE tabla_estados (
    id_estado INT AUTO_INCREMENT PRIMARY KEY,
    id_pedido INT NOT NULL,
    recibida BOOLEAN DEFAULT FALSE,
    preparando BOOLEAN DEFAULT FALSE,
    en_horno BOOLEAN DEFAULT FALSE,
    entregado BOOLEAN DEFAULT FALSE,
    no_entregado BOOLEAN DEFAULT FALSE,
    cancelado BOOLEAN DEFAULT FALSE,
    CONSTRAINT fk_estados_pedido FOREIGN KEY (id_pedido) 
    REFERENCES pedido(id_pedido) ON DELETE CASCADE
);


INSERT INTO configuracion_global (
    tiempo_partida, 
    tiempo_nivel_1, 
    tiempo_nivel_2, 
    tiempo_nivel_3, 
    puntos_nivel_2, 
    puntos_nivel_3, 
    tiempo_entre_pedidos,
    limite_pedidos_activos
) VALUES (180, 60, 45, 30, 1000, 2500, 15,3);

INSERT INTO configuracion_punteos (
    bono_pedido_correcto, 
    bono_tiempo_optimo, 
    bono_eficiencia, 
    pedido_cancelado, 
    pedido_incompleto
) VALUES (100, 50, 25, 30, 60);


INSERT INTO sucursal (nombre, ubicacion) 
VALUES ('PizzaExpress Central', 'Xela');

INSERT INTO usuario (nickname, nombre, contraseña, rol, id_sucursal) 
VALUES ('admin', 'Domingo', 'admin', 'ADMIN_SISTEMA', NULL);

INSERT INTO usuario (nickname, nombre, contraseña, rol, id_sucursal) 
VALUES ('tendero', 'Juan', 'tendero', 'TENDERO', 1);

INSERT INTO usuario (nickname, nombre, contraseña, rol, id_sucursal) 
VALUES ('cocinero', 'Chef Principal', 'cocinero', 'COCINERO', 1);
