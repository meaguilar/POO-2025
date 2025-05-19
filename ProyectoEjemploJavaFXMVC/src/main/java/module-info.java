module com.abc.proyectoejemplojavafxmvc {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.abc.proyectoejemplojavafxmvc to javafx.fxml;
    exports com.abc.proyectoejemplojavafxmvc;
}