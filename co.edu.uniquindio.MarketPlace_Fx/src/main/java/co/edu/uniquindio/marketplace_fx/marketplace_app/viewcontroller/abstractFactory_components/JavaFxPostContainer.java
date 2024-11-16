package co.edu.uniquindio.marketplace_fx.marketplace_app.viewcontroller.abstractFactory_components;

import co.edu.uniquindio.marketplace_fx.marketplace_app.service.service_components.IPostContainer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class JavaFxPostContainer implements IPostContainer {
    private VBox container;
    public JavaFxPostContainer() {
        container = new VBox(10);
        container.setPadding(new Insets(10));
        container.getStyleClass().add("post-container");
    }
    @Override
    public void setLayout() {
        container.setAlignment(Pos.CENTER);
    }
    @Override
    public Node getContainer() {
        return container;
    }
}
