package com.abc.proyectoejemplojavafxmvc;

import com.abc.proyectoejemplojavafxmvc.modelos.ConexionDB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.IOException;



public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("MainVista.fxml"));
        // Verifica si el archivo fue cargado correctamente
        if (fxmlLoader.getLocation() == null) {
            throw new IllegalStateException("No se pudo encontrar el archivo FXML.");
        }
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        // Cambiando la imagen
        Image logo = new Image (getClass().getResource("iconos/logo.png").toExternalForm());
        stage.getIcons().add(logo);
        stage.setTitle("Gestión de Personas");
        stage.setScene(scene);
        //stage.show();
        //Metodo para probar la conexión
        ConexionDB.testConexion();
    }

    public static void main(String[] args) {
        launch();
    }

}