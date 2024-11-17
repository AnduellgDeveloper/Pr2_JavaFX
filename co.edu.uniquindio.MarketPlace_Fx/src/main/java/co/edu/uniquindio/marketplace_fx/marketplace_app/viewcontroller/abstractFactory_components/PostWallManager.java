package co.edu.uniquindio.marketplace_fx.marketplace_app.viewcontroller.abstractFactory_components;

import co.edu.uniquindio.marketplace_fx.marketplace_app.service.service_components.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class PostWallManager {
    private IComponentFactory factory;
    private GridPane postWallContainer;
    private int currentRow = 0;
    private int currentColumn = 0;
    private final int maxColumns = 6;


    public PostWallManager(IComponentFactory factory) {
        this.factory = factory;

        this.postWallContainer = new GridPane();
        this.postWallContainer.setHgap(20);
        this.postWallContainer.setVgap(20);
        this.postWallContainer.setPadding(new Insets(20));
        this.postWallContainer.setAlignment(Pos.TOP_LEFT);

        this.postWallContainer.setPrefSize(800, 600);
        this.postWallContainer.setMinSize(400, 400);

        postWallContainer.setPadding(new Insets(20, 0, 0, 50));
        this.postWallContainer.getStyleClass().add("grid-pane");


    }
    public void createPost(String productName, String imageUrl, Runnable onLike, Runnable onComment) {
        IPostContainer postContainer = factory.createPostContainer();
        IProductInfo productInfo = factory.createProductInfo();
        IProductView productDisplay = factory.createProductDisplay();
        IFeedBackPanel interactionPanel = factory.createInteractionPanel();

        productInfo.setProductName(productName);
        productDisplay.setProductImage(imageUrl);

        interactionPanel.setLikeAction(_ -> onLike.run());
        interactionPanel.setCommentAction(_ -> onComment.run());

        VBox post = (VBox) postContainer.getContainer();
        post.setPrefWidth(200);
        post.setMaxWidth(200);
        post.setMinWidth(200);
        post.setSpacing(10);
        post.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 5, 0, 0, 2);");


        post.setAlignment(Pos.CENTER);
        post.getChildren().addAll(
                productInfo.getProductInfoNode(),
                productDisplay.getProductViewNode(),
                interactionPanel.getInteractionNode()
        );
        postWallContainer.add(post, currentColumn, currentRow);

        currentColumn++;
        if (currentColumn >= maxColumns) {
            currentColumn = 0;
            currentRow++;
        }
    }
    public void clearPosts() {
        postWallContainer.getChildren().clear();
        currentRow = 0;
        currentColumn = 0;
    }

    public Node getPostWall() {
        return postWallContainer;
    }

}
