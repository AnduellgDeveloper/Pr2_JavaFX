package co.edu.uniquindio.marketplace_fx.marketplace_app.model.facade;

import javafx.scene.layout.AnchorPane;

public class Background {
    public void modeDark(AnchorPane anchorPane){
        anchorPane.setStyle("-fx-background-color: #460949");

    }
    public void modeLight(AnchorPane anchorPane){
        anchorPane.setStyle("-fx-background-color: #ECB0F7FF");
    }
}
