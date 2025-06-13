package com.abc.proyectoejemplojavafxmvc.controladores;

import com.abc.proyectoejemplojavafxmvc.modelos.Persona;
import com.abc.proyectoejemplojavafxmvc.modelos.PersonaDAO;
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
    //variables de instancia
    private int id;
    private String nombre;
    private String genero;
    private LocalDate fNac;
    private PersonaDAO personaDAO;


    @FXML
    private TextField nombreTextField;

    @FXML
    private DatePicker fNacPicker;

    @FXML
    private Label datosLabel;

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
    private VBox formVBox;

    @FXML
    private ComboBox<String> generoComboBox;
    ObservableList<String> listaGenero = FXCollections.observableArrayList("Femenino", "Masculino");

    @FXML
    public void initialize() {
        persona = new Persona();
        personaDAO = new PersonaDAO();
        generoComboBox.setItems(listaGenero);
        actButton.setDisable(true);
        eliminarButton.setDisable(true);


        //Desactivar boton guardar
     /*   guardarButton.disableProperty().bind(
                nombreTextField.textProperty().isEmpty()
                        .or(generoComboBox.getSelectionModel().selectedItemProperty().isNull())
                        .or(fNacPicker.valueProperty().isNull())
        );*/
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

        // se almacenan los datos en el objeto persona
        persona.setNombre(nombre);
        persona.setANac(fNac.getYear());
        persona.setGenero(genero);

        //guardar los datos en la BD
        personaDAO.insertar(persona);

        //se genera el alert de confirmación
        AlertUtil.mostrarConfirmacion("Confirmación", "Datos guardados exitosamente");


        limpiar();

        mostrarButton.setDisable(false);

    }

    /**
     * Buscar por id en la BD
     */

    @FXML
    private void buscar() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Buscar Persona");
        dialog.setHeaderText("Buscar Persona");
        dialog.setContentText("Buscar Persona, ingresa el nombre");
        dialog.showAndWait().ifPresent(input -> {
            id = Integer.parseInt(dialog.getResult());
            Persona personaEncontrada = personaDAO.buscarPersonaPorId(id);

            if (personaEncontrada != null) {
                nombreTextField.setText(personaEncontrada.getNombre());
                generoComboBox.getSelectionModel().select(personaEncontrada.getGenero());
                int aNac = personaEncontrada.getANac();
                LocalDate fechaNacimiento = LocalDate.of(aNac, 1, 1);
                fNacPicker.setValue(fechaNacimiento);
                this.persona = personaEncontrada;
                this.nombre = personaEncontrada.getNombre();
                this.genero = personaEncontrada.getGenero();
                this.fNac = LocalDate.of(personaEncontrada.getANac(), 1, 1);

                // Deshabilitar formulario
                formVBox.setDisable(true);
                // Cambiar texto del botón a "Editar"
                actButton.setText("Editar");
                eliminarButton.setDisable(false);
                actButton.setDisable(false);
                guardarButton.setDisable(true);
            } else {
                AlertUtil.mostrarError("Error", "No existe una persona con ese id");
            }
        });

    }

    /**
     * Metodo de actualizar datos del objeto y la BD
     */
    @FXML
    private void actualizar() {
        String nombreBoton = actButton.getText();
        if (nombreBoton.equals("Editar")) {
            // Cambiar a modo edición
            formVBox.setDisable(false);
            actButton.setText("Actualizar");

        } else if (nombreBoton.equals("Actualizar")) {

            // validar nulos y fecha
            if (!validarNulos() || !validarFecha()) {
                return;
            }
            try {
                // establecer en persona los valores de los componentes
                persona.setNombre(nombreTextField.getText());
                persona.setANac(fNacPicker.getValue().getYear());
                persona.setGenero(generoComboBox.getSelectionModel().getSelectedItem());

                // llamar al metodo actualizar de DAO
                personaDAO.actualizar(persona, id);

                // mostrar alerta de confirmación
                AlertUtil.mostrarConfirmacion("Registro actualizado", "Datos actualizados exitosamente");

                //Deshabilitar el formulario
                formVBox.setDisable(true);

                // cambiar el texto del boton actualizar a editar
                actButton.setText("Editar");

            } catch (Exception e) {
                e.printStackTrace();
                // mostrar alerta de error
                AlertUtil.mostrarError("Error", "Error al actualizar el registro de la persona");
            }

        }
    }

    /**
     * Eliminar datos del objeto y la BD según id
     */
    @FXML
    private void eliminar() {
        //Crear un componente Dialog con 2 botones ok, cancel
        Dialog dialog = new Dialog();
        dialog.setTitle("Eliminar registro de persona");
        dialog.setContentText("¿Deseas eliminar el registro de persona?");
        dialog.getDialogPane().getButtonTypes().setAll(ButtonType.CANCEL, ButtonType.OK);
        //dialog.showAndWait();
        if (dialog.showAndWait().get() == ButtonType.OK) {
            // llamar al metodo eliminar de DAO
            personaDAO.eliminar(id);
            // limpiar el formulario
            limpiar();
            // habilitar el formulario y boton guardar
            formVBox.setDisable(false);
            guardarButton.setDisable(false);
            // alerta de confirmacion
            AlertUtil.mostrarConfirmacion("Registro eliminado", "Datos eliminados exitosamente");
        }

    }

    /**
     * Metodo para imprimir los datos
     */
    @FXML
    private void mostrar() {

        //Obtener de la BD
        List<Persona> registrosPersonas = personaDAO.listarPersonas();
        //Validar datos en la BD
        if (registrosPersonas.isEmpty()) {
            AlertUtil.mostrarAdvertencia("Registro personas", "No hay registros de personas");
            return;
        }
        ObservableList<Persona> observableListPersonas = FXCollections.observableArrayList(registrosPersonas);
        //Creando componente listView
        ListView<Persona> listView = new ListView<>(observableListPersonas);
        listView.setPrefSize(550, 250);

        // Crear el diálogo
        Dialog<Persona> dialog = new Dialog<>();
        dialog.setTitle("Registro de personas");
        dialog.setHeaderText("Detalle de las personas registradas");

        VBox contenedor = new VBox(listView);


        dialog.getDialogPane().setContent(contenedor);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK);

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
