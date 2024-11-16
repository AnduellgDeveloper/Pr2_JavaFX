package co.edu.uniquindio.marketplace_fx.marketplace_app.viewcontroller.abstractFactory_components;

import co.edu.uniquindio.marketplace_fx.marketplace_app.service.service_components.ISellerInfo;
import javafx.scene.Node;
import javafx.scene.control.TextField;

public class JavaFxSellerInfo implements ISellerInfo {
    private TextField sellerNameField;

    public JavaFxSellerInfo() {
        sellerNameField = new TextField();
        sellerNameField.getStyleClass().add("seller-name");
        sellerNameField.setEditable(false);
    }
    @Override
    public void setSellerName(String name) {
        sellerNameField.setText(name);
    }
    @Override
    public Node getSellerInfoNode() {
        return sellerNameField;
    }
}
