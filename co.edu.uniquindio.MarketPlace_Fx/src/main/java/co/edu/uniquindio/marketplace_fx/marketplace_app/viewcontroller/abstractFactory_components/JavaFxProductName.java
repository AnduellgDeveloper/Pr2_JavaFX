package co.edu.uniquindio.marketplace_fx.marketplace_app.viewcontroller.abstractFactory_components;

import co.edu.uniquindio.marketplace_fx.marketplace_app.service.service_abstractFactory.IProductName;
import javafx.scene.Node;
import javafx.scene.control.TextField;

public class JavaFxProductName implements IProductName {
    private TextField productNameField;

    public JavaFxProductName() {
        productNameField = new TextField();

        productNameField.getStyleClass().add("seller-name");
        productNameField.setEditable(false);
        productNameField.setMaxHeight(20);
        productNameField.setMaxWidth(200);
        productNameField.setStyle("-fx-background-color: #ecb0f7;");
    }
    @Override
    public void setProductName(String name) {
        productNameField.setText(name);
    }

    @Override
    public Node getProductInfoNode() {
        return productNameField;
    }
}
