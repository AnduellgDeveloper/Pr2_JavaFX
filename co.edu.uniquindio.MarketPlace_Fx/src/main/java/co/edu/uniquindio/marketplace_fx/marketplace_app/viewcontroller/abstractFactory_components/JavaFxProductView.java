package co.edu.uniquindio.marketplace_fx.marketplace_app.viewcontroller.abstractFactory_components;

import co.edu.uniquindio.marketplace_fx.marketplace_app.service.service_abstractFactory.IProductView;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class JavaFxProductView implements IProductView {
    private ImageView productImageView;

    public JavaFxProductView() {
        productImageView = new ImageView();
        productImageView.setFitWidth(200);
        productImageView.setFitHeight(200);
        productImageView.setPreserveRatio(false);
    }
    @Override
    public void setProductImage(String imageUrl) {
        Image image = new Image(imageUrl);
        productImageView.setImage(image);
    }
    @Override
    public Node getProductViewNode() {
        return productImageView;
    }
}
