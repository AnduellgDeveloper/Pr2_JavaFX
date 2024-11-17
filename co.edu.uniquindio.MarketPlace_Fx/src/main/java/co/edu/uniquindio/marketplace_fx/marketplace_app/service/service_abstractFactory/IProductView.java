package co.edu.uniquindio.marketplace_fx.marketplace_app.service.service_abstractFactory;

import javafx.scene.Node;

public interface IProductView {
    void setProductImage(String imageUrl);
    Node getProductViewNode();
}
