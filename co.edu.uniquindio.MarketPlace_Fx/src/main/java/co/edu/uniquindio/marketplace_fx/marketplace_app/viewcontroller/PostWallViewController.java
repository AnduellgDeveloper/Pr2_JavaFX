package co.edu.uniquindio.marketplace_fx.marketplace_app.viewcontroller;

import co.edu.uniquindio.marketplace_fx.marketplace_app.controller.ProductController;
import co.edu.uniquindio.marketplace_fx.marketplace_app.controller.SellerController;
import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.ProductDto;
import co.edu.uniquindio.marketplace_fx.marketplace_app.service.IObserverProduct;
import co.edu.uniquindio.marketplace_fx.marketplace_app.service.service_components.IComponentFactory;
import co.edu.uniquindio.marketplace_fx.marketplace_app.viewcontroller.abstractFactory_components.ComponentFactory;
import co.edu.uniquindio.marketplace_fx.marketplace_app.viewcontroller.abstractFactory_components.PostWallManager;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import static co.edu.uniquindio.marketplace_fx.marketplace_app.utils.PostWallConstants.*;

public class PostWallViewController implements IObserverProduct {
    private String username;
    private final ProductController productController = new ProductController();
    private final SellerController sellerController = new SellerController();
    private ProductViewController productViewController;
    private final IComponentFactory componentFactory;
    private PostWallManager postWallManager;

    @FXML
    private GridPane postWallContainer;

    public PostWallViewController() {
        this.componentFactory = new ComponentFactory();
    }

    @FXML
    public void initialize() {
        postWallManager = new PostWallManager(componentFactory);
        postWallContainer.getChildren().add(postWallManager.getPostWall());

        if (username != null) {
            List<ProductDto> sellerProducts = productController.getProducts(username);
            populateWall(sellerProducts);
        }
        IComponentFactory factory = new ComponentFactory();
        postWallManager = new PostWallManager(factory);

        postWallContainer.getChildren().add(postWallManager.getPostWall());
    }

    public void setUsername(String username) {
        this.username = username;
        List<ProductDto> sellerProducts = productController.getProducts(username);
        populateWall(sellerProducts);
    }

    @Override
    public void onProductsChanged(List<ProductDto> updatedProducts) {
        populateWall(updatedProducts);
    }

    private void populateWall(List<ProductDto> sellerProducts) {
        try {
            postWallManager.clearPosts();
            for (ProductDto product : sellerProducts) {
                String imagePath = product.image();
                Image image = loadImageFromDirectories(imagePath);
                if (image != null) {
                    postWallManager.createPost(
                            username,
                            image.getUrl(),
                            () -> onLike(product),
                            () -> onComment(product)
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            showMessage(TITULO_ERROR_IMAGEN, BODY_ERROR_POBLAR_MURO + e.getMessage(), HEADER, Alert.AlertType.ERROR);
        }
    }

    private void onLike(ProductDto product) {
        System.out.println("Like en producto: " + product.name());
    }

    private void onComment(ProductDto product) {
        System.out.println("Comentario en producto: " + product.name());
    }

    private Image loadImageFromDirectories(String imageName) {
        try {
            String baseDir = "/co/edu/uniquindio/marketplace_fx/marketplace_app/images";
            URL baseResource = getClass().getResource(baseDir);

            if (baseResource == null) {
                showMessage(TITULO_ERROR_IMAGEN,
                        String.format("%s Directorio no encontrado: %s", BODY_ERROR_IMAGEN, baseDir), HEADER, Alert.AlertType.ERROR);
                return null;
            }
            File baseDirectory = new File(baseResource.toURI());
            if (!baseDirectory.exists() || !baseDirectory.isDirectory()) {
                showMessage(TITULO_ERROR_IMAGEN,
                        String.format("%s Directorio inválido: %s", BODY_ERROR_IMAGEN, baseDirectory.getAbsolutePath()), HEADER, Alert.AlertType.ERROR);
                return null;
            }

            File imageFile = findImageInDirectory(baseDirectory, imageName);
            if (imageFile == null) {
                showMessage(TITULO_ERROR_IMAGEN,
                        String.format("%s Imagen no encontrada: %s", BODY_ERROR_IMAGEN, imageName), HEADER, Alert.AlertType.ERROR);
                return null;
            }

            return new Image(imageFile.toURI().toString());
        } catch (Exception e) {
            e.printStackTrace();
            showMessage(TITULO_ERROR_IMAGEN, BODY_ERROR_IMAGEN + e.getMessage(), HEADER, Alert.AlertType.ERROR);
            return null;
        }
    }

    private File findImageInDirectory(File directory, String imageName) {
        for (File file : Objects.requireNonNull(directory.listFiles())) {
            if (file.isDirectory()) {
                File found = findImageInDirectory(file, imageName);
                if (found != null) {
                    return found;
                }
            } else if (file.getName().equalsIgnoreCase(imageName)) {
                return file;
            }
        }
        return null;
    }
    private void showMessage(String title, String message, String header, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }
}