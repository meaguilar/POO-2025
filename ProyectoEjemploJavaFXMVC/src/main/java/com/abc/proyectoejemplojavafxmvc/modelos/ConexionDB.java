package com.abc.proyectoejemplojavafxmvc.modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=POO;encrypt=true;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASSWORD = "123456";


    // Instancia única de la conexión (Singleton)
    private static Connection connection;
    private static ConexionDB instancia;

    // Constructor privado para evitar instanciación directa
    private ConexionDB() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo para obtener la instancia única de la clase
    public static ConexionDB getInstancia() {
        if (instancia == null) {
            instancia = new ConexionDB();
        }
        return instancia;
    }


    // Metodo para obtener la conexión activa
    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // Metodo para probar la conexión
    public static void testConexion() {
        Connection conn = ConexionDB.getInstancia().getConnection();
        try {
            if (conn != null && !conn.isClosed()) {
                System.out.println("¡Conexión exitosa a SQL Server!");
            } else {
                System.out.println("No se pudo conectar a la base de datos.");
            }
        } catch (Exception e) {
            System.out.println("Error al intentar conectar con la base de datos:");
            e.printStackTrace();
        }
    }

}
