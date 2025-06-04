-- Crear la base de datos 
CREATE DATABASE IF NOT EXISTS POO;
USE POO;

-- Usa la base de datos POO
USE POO;
GO

-- Verifica si la tabla empleado existe, y si no, la crea
IF OBJECT_ID('dbo.Persona', 'U') IS NULL
BEGIN
    CREATE TABLE empleado (
        id INT IDENTITY(1,1) PRIMARY KEY,
        nombre NVARCHAR(100) NOT NULL,
        aNac INT NOT NULL,
        genero NVARCHAR(20) NOT NULL
    );
END
GO

SElECT * FROM Persona;

SELECT SUSER_SNAME();
