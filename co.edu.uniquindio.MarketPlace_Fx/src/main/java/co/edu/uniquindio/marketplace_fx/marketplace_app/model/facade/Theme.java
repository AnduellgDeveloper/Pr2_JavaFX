package co.edu.uniquindio.marketplace_fx.marketplace_app.model.facade;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Theme {
    private Label label;
    private Background background;
    private Slider slider;
    private ButtonTheme buttonTheme;
    private Container container;

    public Theme() {
        this.background = new Background();
        this.label=new Label();
        this.slider = new Slider();
        this.buttonTheme = new ButtonTheme();
        this.container =new Container();
    }

    public void modLight(Label label, AnchorPane anchorPane, Pane pane, Button button,GridPane gridPane) {
        label.setText("Modo Dia Activado");
        background.modeLight(anchorPane);
        slider.modeLigth(pane);
        buttonTheme.modeLight(button);
        container.modeLight(gridPane);
    }

    public void modDark(Label label, AnchorPane anchorPane, Pane pane, Button button,GridPane gridPane) {
        label.setText("Modo Oscuro Activado");
        background.modeDark(anchorPane);
        slider.modeDark(pane);
        buttonTheme.modeDark(button);
        container.modeDark(gridPane);
    }
}

