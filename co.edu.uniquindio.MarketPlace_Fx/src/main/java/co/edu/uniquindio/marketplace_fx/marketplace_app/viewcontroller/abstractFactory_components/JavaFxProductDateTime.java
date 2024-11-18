package co.edu.uniquindio.marketplace_fx.marketplace_app.viewcontroller.abstractFactory_components;

import co.edu.uniquindio.marketplace_fx.marketplace_app.service.service_abstractFactory.IProductDateTime;
import javafx.scene.Node;
import javafx.scene.control.TextField;

import java.time.LocalDateTime;

public class JavaFxProductDateTime implements IProductDateTime {
    private TextField productDateTimeField;

    public JavaFxProductDateTime(){
        productDateTimeField = new TextField();

        productDateTimeField.getStyleClass().add("seller-date");
        productDateTimeField.setEditable(false);
        productDateTimeField.setMaxHeight(15);
        productDateTimeField.setMaxWidth(200);
        productDateTimeField.setStyle("-fx-background-color: #ecb0f7;");
    }

    @Override
    public void setProductDateTime(LocalDateTime productDate) {
        productDateTimeField.setText(String.valueOf(productDate));
    }
    @Override
    public Node getProductDateTimeNode() {
        return productDateTimeField;
    }
}
