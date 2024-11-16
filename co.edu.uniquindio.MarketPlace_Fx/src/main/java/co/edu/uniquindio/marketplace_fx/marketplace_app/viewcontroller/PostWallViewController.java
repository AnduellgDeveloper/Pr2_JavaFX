package co.edu.uniquindio.marketplace_fx.marketplace_app.viewcontroller;

import co.edu.uniquindio.marketplace_fx.marketplace_app.controller.ProductController;
import co.edu.uniquindio.marketplace_fx.marketplace_app.controller.SellerController;
import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.ProductDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import java.util.stream.Stream;
import static co.edu.uniquindio.marketplace_fx.marketplace_app.utils.PostWallConstants.*;

public class PostWallViewController {
    private String username;

    private final ProductController productController = new ProductController();
    private final SellerController sellerController = new SellerController();
    // ------------------------------------ Image View ------------------------------------
    @FXML
    private ImageView imgSeller1_1, imgSeller1_2, imgSeller1_3;
    @FXML
    private ImageView imgSeller2_1, imgSeller2_2, imgSeller2_3;

    // ------------------------------------ Buttons ------------------------------------
    @FXML
    private Pane Slider;
    public void setUsername(String username) {
        this.username = username;
        productController.getProducts(username);
    }
    @FXML
    public void initialize() {
//        setUsername(username);
        List<ProductDto> sellerProducts = productController.getProducts(username);
        populateWall(sellerProducts);
    }

    // Método que busca y carga la imagen desde las carpetas
    private Image loadImageFromDirectories(String imageName) {
        try {
            String baseDir = "/co/edu/uniquindio/marketplace_fx/marketplace_app/images";
            try (Stream<Path> paths = Files.walk(Paths.get(getClass().getResource(baseDir).toURI()))) {
                for (Path path : (Iterable<Path>) paths::iterator) {
                    if (Files.isRegularFile(path) && path.getFileName().toString().equals(imageName)) {
                        String fullPath = path.toUri().toString();
                        return new Image(fullPath);
                    }
                }
            }
            showMessage(TITULO_ERRROR_IMAGEN, BODY_ERRROR_IMAGEN + "Imagen no encontrada: " + imageName, HEADER, Alert.AlertType.ERROR);
        } catch (Exception e) {
            showMessage(TITULO_ERRROR_IMAGEN, BODY_ERRROR_IMAGEN + e.getMessage(), HEADER, Alert.AlertType.ERROR);
        }
        return null;
    }

    // Método que obtiene los productos y los añade a la interfaz
    private void populateWall(List<ProductDto> sellerProducts) {
        try {
            ImageView[] imageViews = {
                    imgSeller1_1, imgSeller1_2, imgSeller1_3,
                    imgSeller2_1, imgSeller2_2, imgSeller2_3
            };
            for (int i = 0; i < imageViews.length; i++) {
                if (i < sellerProducts.size()) {
                    ProductDto product = sellerProducts.get(i);
                    String imagePath = product.image();
                    Image image = loadImageFromDirectories(imagePath);
                    if (image != null) {
                        imageViews[i].setImage(image);
                    } else {
                        imageViews[i].setImage(null);
                    }
                } else {
                    imageViews[i].setImage(null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método que muestra un mensaje en un cuadro de diálogo
    private void showMessage(String title, String message, String header, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Métodos para manejar los eventos de los botones de comentarios y likes
    @FXML
    void onBtnLike1(ActionEvent event) {
    }
    @FXML
    void onBtnComment1(ActionEvent event) {
    }
    @FXML
    void onBtnLike2(ActionEvent event) {
    }
    @FXML
    void onBtnComment2(ActionEvent event) {
    }
    @FXML
    void btnLike5(ActionEvent event) {
    }
    @FXML
    void onBtnComment3(ActionEvent event) {
    }
    @FXML
    void onBtnComment5(ActionEvent event) {
    }
    @FXML
    void onBtnComment6(ActionEvent event) {
    }
    @FXML
    void onBtnLike3(ActionEvent event) {
    }
    @FXML
    void onBtnLike4(ActionEvent event) {
    }
    @FXML
    void onBtnLike6(ActionEvent event) {
    }
    @FXML
    void onComment4(ActionEvent event) {

    }
}
