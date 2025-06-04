package com.abc.proyectoejemplojavafxmvc.controladores;

import com.abc.proyectoejemplojavafxmvc.modelos.Persona;
import com.abc.proyectoejemplojavafxmvc.util.AlertUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;


import java.time.LocalDate;
import java.util.List;



public class PersonaControlador {

    private Persona persona;
    private int id;
    private String nombre;
    private String genero;
    private LocalDate fNac;


    @FXML
    private TextField nombreTextField;

    @FXML
    private DatePicker fNacPicker;

    @FXML
    private Button guardarButton;

    @FXML
    private Button mostrarButton;

    @FXML
    private Button eliminarButton;

    @FXML
    private Button actButton;

    @FXML
    private Button buscarButton;


    @FXML
    private ComboBox<String> generoComboBox;
    ObservableList<String> listaGenero = FXCollections.observableArrayList("Femenino", "Masculino");

    @FXML
    public void initialize() {
        persona = new Persona();
        generoComboBox.setItems(listaGenero);
        actButton.setDisable(true);
        eliminarButton.setDisable(true);
    }

    /**
     * Metodo para guardar datos en objeto y la BD
     */
    @FXML
    private void guardar() {

        nombre = nombreTextField.getText();
        genero = generoComboBox.getSelectionModel().getSelectedItem();
        fNac = fNacPicker.getValue();

        if (!validarNulos() || !validarFecha()) {
            return;
        }

        // se almacenan los datos
        persona.setNombre(nombre);
        persona.setANac(fNac.getYear());
        persona.setGenero(genero);


        //se genera el alert de confirmación
        AlertUtil.mostrarConfirmacion("Confirmación", "Datos guardados exitosamente");


        limpiar();

        mostrarButton.setDisable(false);

    }

    /**
     * Metodo buscar por id en la BD
     */
    @FXML
    private void buscar() {

    }

    /**
     * Metodo de actualizar datos del objeto y la BD
     */
    @FXML
    private void actualizar() {

    }

    /**
     * Eliminar datos del objeto y la BD según id
     */
    @FXML
    private void eliminar() {

    }

    /**
     * Metodo para imprimir los datos
     */
    @FXML
    private void mostrar() {

        Dialog dialog = new Dialog();
        dialog.setContentText(persona.toString());
        dialog.getDialogPane().getButtonTypes().setAll(ButtonType.OK);
        dialog.showAndWait();
        //datosLabel.setText(persona.getNombre() + " - " + persona.getGenero() + " - " + persona.calcularEdad() + "años");
    }

    /**
     * Validación de datos, campos vacios
     */
    private boolean validarNulos() {

        boolean error = false;

        if (nombre.isEmpty()) {
            nombreTextField.setStyle("-fx-border-color: #cc010d");
            error = true;
        } else {
            nombreTextField.setStyle(null);
        }
        if (genero == null) {
            generoComboBox.setStyle("-fx-border-color: #cc010d");
            error = true;
        } else {
            generoComboBox.setStyle(null);
        }
        if (fNac == null) {
            fNacPicker.setStyle("-fx-border-color: #cc010d");
            error = true;
        } else {
            fNacPicker.setStyle(null);
        }

        //Alerta
        if (error) {
            AlertUtil.mostrarAdvertencia("Validación de datos", "Favor, completar los campos requeridos");
        }

        return !error;
    }

    /**
     * Validacion de fecha
     */
    private boolean validarFecha() {

        LocalDate fechaActual = LocalDate.now();

        if (fNac.isAfter(fechaActual)) {
            fNacPicker.setStyle("-fx-border-color: red;");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validación de datos");
            alert.setContentText("Fecha inválida");
            alert.showAndWait();
            return false;

        } else {
            fNacPicker.setStyle(null);
            return true;
        }
    }

    /**
     * Resetear los controles del formulario
     */
    private void limpiar() {
        nombreTextField.setText("");
        generoComboBox.getSelectionModel().clearSelection();
        fNacPicker.setValue(null);
    }

}
