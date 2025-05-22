package com.abc.proyectoejemplojavafxmvc;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PersonaControlador {
    @FXML
    private TextField nombreTextField;

    @FXML
    private ComboBox generoComboBox;
    ObservableList<String> generos = FXCollections.observableArrayList("Femenino", "Masculino");

    @FXML
    private DatePicker aNacDatePicker;

    @FXML
    private Label resultadosLabel;

    private Persona persona;

    @FXML
    public void initialize() {
        persona = new Persona();
        generoComboBox.setItems(generos);
    }

    /**
     * Metodo para guardar datos en objeto
     */
    @FXML
    private void guardarDatos() {
        persona.setNombre(nombreTextField.getText());
        persona.setGenero(generoComboBox.getSelectionModel().getSelectedItem().toString());
        persona.setANac(aNacDatePicker.getValue().getYear());

    }
    /**
     * Metodo para imprimir los datos
     */
    @FXML
    private void mostrarDatos() {

        resultadosLabel.setText(persona.getNombre() + " " + persona.getGenero() + " " + persona.calcularEdad());
    }
}