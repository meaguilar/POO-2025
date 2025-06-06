package com.abc.proyectoejemplojavafxmvc.modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class PersonaDAO {

    /**
     * Metodo para insertar los datos de persona en la BD
     * @param persona
     */
    public void insertar(Persona persona) {
        String sql = "INSERT INTO Persona (nombre, aNac, genero) VALUES (?, ?, ?)";

        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, persona.getNombre());
            stmt.setInt(2, persona.getANac());
            stmt.setString(3, persona.getGenero());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
