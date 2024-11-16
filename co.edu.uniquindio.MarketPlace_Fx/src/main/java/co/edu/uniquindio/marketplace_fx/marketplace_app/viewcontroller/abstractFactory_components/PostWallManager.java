package co.edu.uniquindio.marketplace_fx.marketplace_app.viewcontroller.abstractFactory_components;

import co.edu.uniquindio.marketplace_fx.marketplace_app.service.service_components.*;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class PostWallManager {
    private IComponentFactory factory;
    private VBox postWallContainer;

    public PostWallManager(IComponentFactory factory) {
        this.factory = factory;
        this.postWallContainer = new VBox(20);
        this.postWallContainer.setPadding(new Insets(20));
    }
    public void clearPosts() {
        postWallContainer.getChildren().clear();
    }
    public void createPost(String sellerName, String imageUrl,Runnable onLike, Runnable onComment) {
        IPostContainer postContainer = factory.createPostContainer();
        ISellerInfo sellerInfo = factory.createSellerInfo();
        IProductView productDisplay = factory.createProductDisplay();
        IFeedBackPanel interactionPanel = factory.createInteractionPanel();

        sellerInfo.setSellerName(sellerName);
        productDisplay.setProductImage(imageUrl);

        interactionPanel.setLikeAction(_ -> onLike());
        interactionPanel.setCommentAction(_ -> onComment());

        VBox post = (VBox) postContainer.getContainer();
        post.getChildren().addAll(
                sellerInfo.getSellerInfoNode(),
                productDisplay.getProductViewNode(),
                interactionPanel.getInteractionNode()
        );
        postWallContainer.getChildren().add(post);
    }
    private void onLike() {
        System.out.println("Like clicked!");
    }
    private void onComment() {
        System.out.println("Comment clicked!");
    }
    public Node getPostWall() {
        return postWallContainer;
    }

}
