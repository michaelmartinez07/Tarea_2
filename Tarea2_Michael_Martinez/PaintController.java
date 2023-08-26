package com.example.cap6_demo;

import com.example.cap6_demo.datos.Figuras;
import com.example.cap6_demo.datos.Punto2D;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class PaintController implements Initializable {
    @FXML private Canvas canvas;
    @FXML private Slider slider;
    @FXML private RadioButton radioLinea;
    @FXML private RadioButton radioOvalo;
    @FXML private RadioButton radioRectangulo;
    @FXML private Label sliderStatus;

    private Figuras figura;
    private GraphicsContext graphicsContext;
    private ToggleGroup toggleGroupFiguras;
    private Punto2D origen;
    private Punto2D destino;

    @FXML protected void ovaloClick() {
        figura = Figuras.OVALO;
    }

    @FXML protected void rectanguloClick() {
        figura = Figuras.RECTANGULO;
    }

    @FXML protected void lineaClick() {
        figura = Figuras.LINEA;
    }

    @FXML protected void canvasMousePressed(MouseEvent e) {
        //System.out.println("PRESSED: " + e.getX() + ", " + e.getY());
    }

    @FXML protected void canvasMouseClicked(MouseEvent e) {
        //System.out.println("CLIKED: " + e.getX() + ", " + e.getY());

        if (origen == null) {
            origen = new Punto2D();
            origen.x = e.getX();
            origen.y = e.getY();
            return;
        } else if (destino == null) {
            destino = new Punto2D();
            destino.x = e.getX();
            destino.y = e.getY();
        }

        double altura = Math.abs(destino.y - origen.y);
        double anchura = Math.abs(destino.x - origen.x);

        if (figura == Figuras.RECTANGULO) {
            graphicsContext.strokeRect(origen.x, origen.y, anchura, altura);
        } else if (figura == Figuras.OVALO) {
            graphicsContext.strokeOval(origen.x, origen.y,  anchura, altura);
        } else if (figura == Figuras.LINEA) {
            graphicsContext.strokeLine(origen.x, origen.y, destino.x, destino.y);
        }

        // reinicio el dibujo
        origen = null;
        destino = null;
    }

    @FXML protected void canvasMouseReleased(MouseEvent e) {
        //System.out.println("RELEASED: " + e.getX() + ", " + e.getY());
    }

    @FXML protected void sliderChange(){

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        origen = null;
        destino = null;

        graphicsContext = canvas.getGraphicsContext2D();
        toggleGroupFiguras = new ToggleGroup();
        radioLinea.setToggleGroup(toggleGroupFiguras);
        radioOvalo.setToggleGroup(toggleGroupFiguras);
        radioRectangulo.setToggleGroup(toggleGroupFiguras);

        radioLinea.setSelected(true);
        figura = Figuras.LINEA;

        toggleGroupFiguras.selectedToggleProperty().addListener(
                (observableValue, oldToggle, newToggle) -> {
                    var rb = (RadioButton) newToggle;
                    switch (rb.getText()) {
                        case "Linea" -> figura = Figuras.LINEA;
                        case "Ovalo" -> figura = Figuras.OVALO;
                        case "Rectangulo" -> figura = Figuras.RECTANGULO;
                    }
                }
        );

        slider.valueProperty().addListener(
                (observable, oldValue, newValue) -> {
                    graphicsContext.setLineWidth(newValue.doubleValue());
                    sliderStatus.setText(String.format("%.1f",
                            newValue.doubleValue()));
                }
        );
    }
}

// Variable to determine if the user is in erase mode
private boolean isErasing = false;

// Method to toggle erase mode
public void toggleEraseMode() {
    isErasing = !isErasing;
}

// Modify the drawing method to use lines of the canvas color when isErasing is true
// Assume that the canvas color is white (you can customize this)
public void handleDrawMethod(MouseEvent event) {
    double x = event.getX();
    double y = event.getY();
    if (isErasing) {
        gc.setStroke(Color.WHITE);  // Set the stroke color to the canvas color
        gc.strokeLine(x, y, x, y);  // Draw a line at the point where the mouse is (effectively erasing)
    } else {
        // existing drawing logic
    }
}