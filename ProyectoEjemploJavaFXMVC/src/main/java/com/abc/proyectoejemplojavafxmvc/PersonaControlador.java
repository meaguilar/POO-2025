package com.abc.proyectoejemplojavafxmvc;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PersonaControlador {
    @FXML
    private TextField nombreField;

    @FXML
    private TextField generoField;

    @FXML
    private TextField aNacField;

    @FXML
    private Label datosLabel;

    private Persona persona;

    @FXML
    public void initialize() {
        persona = new Persona();
    }

    @FXML
    /**
     * Metodo para guardar datos en objeto
     */
    private void guardarDatos() {
        persona.setNombre(nombreField.getText());

    }
    /**
     * Metodo para imprimir los datos
     */
    @FXML
    private void mostrarDatos() {
        datosLabel.setText(persona.getNombre());
    }
}
