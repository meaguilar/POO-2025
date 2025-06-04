module com.abc.proyectoejemplojavafxmvc {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;


    opens com.abc.proyectoejemplojavafxmvc to javafx.fxml;
    exports com.abc.proyectoejemplojavafxmvc;
    exports com.abc.proyectoejemplojavafxmvc.util;
    opens com.abc.proyectoejemplojavafxmvc.util to javafx.fxml;
    exports com.abc.proyectoejemplojavafxmvc.modelos;
    opens com.abc.proyectoejemplojavafxmvc.modelos to javafx.fxml;
    exports com.abc.proyectoejemplojavafxmvc.controladores;
    opens com.abc.proyectoejemplojavafxmvc.controladores to javafx.fxml;

}