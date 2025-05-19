package com.abc.proyectoejemplojavafxmvc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("PersonaVista.fxml"));
        // Verifica si el archivo fue cargado correctamente
        if (fxmlLoader.getLocation() == null) {
            throw new IllegalStateException("No se pudo encontrar el archivo FXML.");
        }
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Gestión de Personas");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        launch();
    }
}
