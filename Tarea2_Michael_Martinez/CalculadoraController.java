package com.example.cap6_demo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CalculadoraController implements Initializable {
    @FXML
    private Label resultado;

    @FXML
    private TextField operando1;

    @FXML
    private TextField operando2;

    private Alert alerta;

    @FXML private Slider slider1;
    @FXML private Slider slider2;

    @FXML void cambiarValorSlider1() {
        operando1.setText(String.valueOf((int)slider1.getValue()));
    }

    @FXML void cambiarValorSlider2() {
        operando2.setText(String.valueOf((int)slider2.getValue()));
    }

    @FXML void cambiarValorTextEdit1() {
        if (operando1.getText().equals("")) return;

        int valor = Integer.parseInt(operando1.getText());
        slider1.setValue(valor);
    }
    private void mostrarAdvertencia() {
        alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("Advertencia");
        alerta.setHeaderText("Operacion no valida");
        alerta.setContentText("Esta es una frase mas larga para explicar un error");
        alerta.show();
    }
    @FXML
    protected void sumarClick() {
        if (!validar()) {
            mostrarAdvertencia();
        }
        int valor1 = Integer.parseInt(operando1.getText());
        int valor2 = Integer.parseInt(operando2.getText());

        resultado.setText(String.valueOf(valor1 + valor2));
    }

    @FXML
    protected void restarClick() {
        if (!validar()) {
            mostrarAdvertencia();
        }
        int valor1 = Integer.parseInt(operando1.getText());
        int valor2 = Integer.parseInt(operando2.getText());

        resultado.setText(String.valueOf(valor1 - valor2));
    }

    @FXML
    protected void multiplicarClick() {
        if (!validar()) {
            mostrarAdvertencia();
        }
        int valor1 = Integer.parseInt(operando1.getText());
        int valor2 = Integer.parseInt(operando2.getText());

        resultado.setText(String.valueOf(valor1 * valor2));
    }

    @FXML
    protected void dividirClick() {
        if (!validar()) {
            mostrarAdvertencia();
        }
        int valor1 = Integer.parseInt(operando1.getText());
        int valor2 = Integer.parseInt(operando2.getText());

        try {
            resultado.setText(String.valueOf((double)valor1 / valor2));
        } catch (ArithmeticException e) {
            resultado.setText("0");
        }
    }

    @FXML
    protected void limpiarClick() {
        resultado.setText("0");
        operando1.clear();
        operando2.clear();
    }

    private boolean validar() {
        return (!operando1.getText().equals("") && !operando2.getText().equals(""));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // este evento ocurre cuando se carga la vista
        slider1.valueProperty().addListener(
                e -> cambiarValorSlider1()
        );

        slider2.valueProperty().addListener(
                e -> cambiarValorSlider2()
        );

        operando1.textProperty().addListener(
                e -> cambiarValorTextEdit1()
        );
    }
}
