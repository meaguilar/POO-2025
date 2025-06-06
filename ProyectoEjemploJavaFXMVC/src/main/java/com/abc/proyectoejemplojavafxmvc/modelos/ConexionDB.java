package com.abc.proyectoejemplojavafxmvc.modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=POO;encrypt=true;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASSWORD = "123456";
    private static Connection connection;

    public static Connection getConexion() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL,USER, PASSWORD);
        }
        return connection;
    }

    // Metodo para probar la conexión
    public static void testConexion() {
        try (Connection conn = getConexion()) {
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
