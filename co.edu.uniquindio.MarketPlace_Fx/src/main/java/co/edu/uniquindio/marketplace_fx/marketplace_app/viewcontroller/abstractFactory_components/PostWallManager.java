package co.edu.uniquindio.marketplace_fx.marketplace_app.viewcontroller.abstractFactory_components;

import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.ProductDto;
import co.edu.uniquindio.marketplace_fx.marketplace_app.service.service_abstractFactory.*;

import co.edu.uniquindio.marketplace_fx.marketplace_app.service.service_observer.Observer;
import co.edu.uniquindio.marketplace_fx.marketplace_app.viewcontroller.PostWallViewController;
import co.edu.uniquindio.marketplace_fx.marketplace_app.viewcontroller.ProductViewController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PostWallManager implements Observer {
    private PostWallViewController postWallViewController;
    private ProductViewController productViewController;
    private IComponentFactory factory;
    private GridPane postWallContainer;
    private int currentRow = 0;
    private int currentColumn = 0;
    private final int maxColumns = 6;
    private List<ProductDto> products = new ArrayList<>();

    public void updateProducts(List<ProductDto> newProducts) {
        products.clear();
        products.addAll(newProducts);
    }
    @Override
    public void update() {
        updateProducts(productViewController.getProductList());
    }


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
    public void createPost(String productName, LocalDateTime productDate, String imageUrl, Runnable onLike, Runnable onComment) {
        IPostContainer postContainer = factory.createPostContainer();
        IProductDateTime productDateTime = factory.createProductDateTime();
        IProductName product = factory.createProductName();
        IProductView productDisplay = factory.createProductDisplay();
        IFeedBackPanel interactionPanel = factory.createInteractionPanel();

        productDateTime.setProductDateTime(productDate);
        product.setProductName(productName);
        productDisplay.setProductImage(imageUrl);

        // Incrementar likes y ejecutar acción personalizada
        interactionPanel.setLikeAction(_ -> {
            onLike.run();
        });

        interactionPanel.setCommentAction(_ -> onComment.run());

        VBox post = (VBox) postContainer.getContainer();
        post.setPrefWidth(200);
        post.setMaxWidth(200);
        post.setMinWidth(200);
        post.setSpacing(10);
        post.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 5, 0, 0, 2);");

        post.setAlignment(Pos.CENTER);
        post.getChildren().addAll(
                productDateTime.getProductDateTimeNode(),
                product.getProductInfoNode(),
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
