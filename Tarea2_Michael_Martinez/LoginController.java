package com.example.cap6_demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML private TextField usuario;
    @FXML private PasswordField password;

    private final String USUARIO = "admin";
    private final String PASSWORD = "1234";

    private void cambiarEscena(String archivo, int ancho, int alto) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(archivo));
        Scene scene = new Scene(fxmlLoader.load(), ancho, alto);

        Stage ventana = (Stage) usuario.getScene().getWindow();
        ventana.setScene(scene);
    }

    @FXML protected void entrarClick() throws IOException {
        if (usuario.getText().equals(USUARIO) && password.getText().equals(PASSWORD)) {
            // cambiar la escena para mostrar la calculadora
            cambiarEscena("calculadora-view.fxml", 600, 400);
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText("No tiene acceso");
            alerta.setContentText("Revise sus credenciales");
            alerta.show();
        }
    }
}
