CREATE DATABASE challenge_duxsoftware;
USE challenge_duxsoftware;

# Tabla Rubros
CREATE TABLE rubros (
	id_rubro BIGINT AUTO_INCREMENT PRIMARY KEY,
    rubro VARCHAR(60)
);

# Tabla Productos
CREATE TABLE productos (
	codigo VARCHAR(200) PRIMARY KEY,
    nombre VARCHAR(60),
    fecha_creacion DATE,
    id_rubro BIGINT,
    FOREIGN KEY (id_rubro) REFERENCES rubros (id_rubro) 
);

# Tabla Clientes
CREATE TABLE clientes (
	id_cliente BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(60),
    apellido VARCHAR(60),
    cuit VARCHAR(20)
);

# Tabla Ventas
CREATE TABLE ventas (
	id_venta BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE,
    cantidad BIGINT,
    precio_unitario DOUBLE,
    id_cliente BIGINT,
    codigo_producto VARCHAR(200),
    FOREIGN KEY (codigo_producto) REFERENCES productos (codigo),
	FOREIGN KEY (id_cliente) REFERENCES clientes (id_cliente) 
);

# Inserción datos tabla rubros
INSERT INTO rubros (rubro) VALUES 
	("Electrodomésticos"), 
    ("Ropa"), 
    ("Cosméticos"), 
    ("Deportes"), 
    ("Jardín"), 
    ("Librería"), 
    ("Bazar");

# Inserción datos tabla productos
INSERT INTO productos VALUES 
	("adfpbo2398665algpxxkfjru234", "Persiana", CURRENT_DATE, 7),
    ("adfpbo2398665algpxxkfjru235", "Lápiz", CURRENT_DATE, 6),
    ("adfpbo2398665algpxxkfjru236", "Remera", CURRENT_DATE, 2),
    ("adfpbo2398665algpxxkfjru237", "Lápiz labial", CURRENT_DATE, 3),
    ("adfpbo2398665algpxxkfjru238", "Pelota", CURRENT_DATE, 4),
    ("adfpbo2398665algpxxkfjru239", "Planta", CURRENT_DATE, 5),
    ("adfpbo2398665algpxxkfjru210", "Televisor", "2023-04-18", 1),
    ("adfpbo2398665algpxxkfjru211", "Cuadro", CURRENT_DATE, 7),
    ("adfpbo2398665algpxxkfjru212", "Lapicera", "2023-04-18", 6);    

# Inserción datos tabla clientes
INSERT INTO clientes (nombre, apellido, cuit) VALUES 
	("Alberto", "Rodríguez", "18-23405234-6"), 
    ("Celeste", "Fernández", "18-63405234-5"), 
    ("Tomás", "Descalzo", "18-33405234-6"), 
    ("Victoria", "Llorens", "18-13405234-6"), 
    ("Marcos", "Callao", "18-63405234-6");

# Inserción datos tabla ventas
INSERT INTO ventas (fecha, cantidad, precio_unitario, id_cliente, codigo_producto) VALUES 
	("2023-01-04", 2, 3000.44, 1, "adfpbo2398665algpxxkfjru234"), 
    ("2023-03-04", 22, 2000.03, 2, "adfpbo2398665algpxxkfjru235"), 
    (CURRENT_DATE, 1, 10000.13, 3, "adfpbo2398665algpxxkfjru236"), 
    (CURRENT_DATE, 1, 300000.99, 4, "adfpbo2398665algpxxkfjru237"), 
    (CURRENT_DATE, 5, 500.70, 5, "adfpbo2398665algpxxkfjru238"), 
    ("2023-07-25", 30, 5000.60, 1, "adfpbo2398665algpxxkfjru239"), 
    ("2023-07-13", 100, 7030.33, 2, "adfpbo2398665algpxxkfjru210"),
    ("2023-02-13", 2, 7100.33, 3, "adfpbo2398665algpxxkfjru211"),
    ("2023-03-13", 2, 7200.33, 5, "adfpbo2398665algpxxkfjru212"),
    ("2023-01-04", 2, 3000.44, 1, "adfpbo2398665algpxxkfjru234"), 
    ("2023-03-04", 2, 2000.03, 2, "adfpbo2398665algpxxkfjru235"), 
    (CURRENT_DATE, 2, 10000.13, 3, "adfpbo2398665algpxxkfjru236"), 
    (CURRENT_DATE, 2, 300000.99, 4, "adfpbo2398665algpxxkfjru237"), 
    (CURRENT_DATE, 2, 500.70, 5, "adfpbo2398665algpxxkfjru238"), 
    ("2023-07-25", 2, 5000.60, 1, "adfpbo2398665algpxxkfjru239"), 
    ("2023-07-13", 2, 7030.33, 2, "adfpbo2398665algpxxkfjru210"),
    ("2023-02-13", 2, 7100.33, 3, "adfpbo2398665algpxxkfjru211"),
    ("2023-03-13", 2, 7200.33, 5, "adfpbo2398665algpxxkfjru212"),
    ("2023-01-04", 2, 3000.44, 1, "adfpbo2398665algpxxkfjru234"), 
    ("2023-03-04", 2, 2000.03, 2, "adfpbo2398665algpxxkfjru235"), 
    (CURRENT_DATE, 2, 10000.13, 3, "adfpbo2398665algpxxkfjru236"), 
    (CURRENT_DATE, 2, 300000.99, 4, "adfpbo2398665algpxxkfjru237"), 
    (CURRENT_DATE, 2, 500.70, 5, "adfpbo2398665algpxxkfjru238"), 
    ("2023-07-25", 2, 5000.60, 1, "adfpbo2398665algpxxkfjru239"), 
    ("2023-07-13", 2, 7030.33, 2, "adfpbo2398665algpxxkfjru210"),
    ("2023-02-13", 2, 7100.33, 3, "adfpbo2398665algpxxkfjru211"),
    ("2023-03-13", 2, 7200.33, 5, "adfpbo2398665algpxxkfjru212");
       
# Todos los productos del rubro "librería", creados hoy. 
SELECT codigo, nombre, fecha_creacion, rubro FROM productos 
JOIN rubros ON productos.id_rubro = rubros.id_rubro 
WHERE rubro = "librería" AND fecha_creacion = CURRENT_DATE;    

# Monto total vendido por cliente (mostrar nombre del cliente y monto). 
SELECT 
	CONCAT (nombre, ' ', apellido) AS nombre_completo, 
    SUM(cantidad * precio_unitario) AS monto_total
FROM clientes 
JOIN ventas ON clientes.id_cliente = ventas.id_cliente 
GROUP BY nombre_completo;  

# Cantidad de ventas por producto 
SELECT nombre AS producto, COUNT(*) AS cantidad_ventas
FROM ventas
JOIN productos ON ventas.codigo_producto = productos.codigo
GROUP BY codigo_producto;
 
# Cantidad de productos diferentes comprados por cliente en el mes actual. 
SELECT 
	CONCAT (clientes.nombre, ' ', apellido) AS nombre_completo, 
    COUNT(DISTINCT codigo_producto) AS productos_diferentes_comprados
FROM clientes 
JOIN ventas ON clientes.id_cliente = ventas.id_cliente 
JOIN productos ON ventas.codigo_producto = productos.codigo
WHERE MONTH(fecha) = MONTH(CURRENT_DATE)
GROUP BY clientes.id_cliente;

# Ventas que tienen al menos un producto del rubro "bazar". 
SELECT fecha, cantidad, precio_unitario, productos.nombre, rubro 
FROM ventas
JOIN productos ON ventas.codigo_producto = productos.codigo
JOIN rubros ON productos.id_rubro = rubros.id_rubro
WHERE rubro = "bazar";
 
# Rubros que no tienen ventas en los últimos 2 meses.
SELECT rubro
FROM rubros
LEFT JOIN productos ON rubros.id_rubro = productos.id_rubro
LEFT JOIN ventas ON productos.codigo = ventas.codigo_producto AND MONTH(fecha) >= MONTH(CURRENT_DATE) - 2
GROUP BY rubro
HAVING COUNT(id_venta) = 0;








