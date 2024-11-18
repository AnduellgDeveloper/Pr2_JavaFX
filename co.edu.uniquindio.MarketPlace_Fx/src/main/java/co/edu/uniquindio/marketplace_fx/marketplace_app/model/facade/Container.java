package co.edu.uniquindio.marketplace_fx.marketplace_app.model.facade;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class Container {
    public void modeDark(GridPane gridPane){
        gridPane.setStyle("-fx-background-color: #460949");
    }
    public void modeLight(GridPane gridPane){
        gridPane.setStyle("-fx-background-color: #ECB0F7FF");
    }
}
