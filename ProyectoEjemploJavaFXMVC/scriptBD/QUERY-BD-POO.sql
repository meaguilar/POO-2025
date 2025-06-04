-- Crear la base de datos 
CREATE DATABASE POO;
USE POO;


-- Verifica si la tabla empleado existe, y si no, la crea
CREATE TABLE Persona (
        id INT IDENTITY(1,1) PRIMARY KEY,
        nombre NVARCHAR(100) NOT NULL,
        aNac INT NOT NULL,
        genero NVARCHAR(20) NOT NULL
    );


