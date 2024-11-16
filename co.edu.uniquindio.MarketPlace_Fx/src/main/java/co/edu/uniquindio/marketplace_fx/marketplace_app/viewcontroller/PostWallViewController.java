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

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import java.util.stream.Stream;

import static co.edu.uniquindio.marketplace_fx.marketplace_app.utils.ProductConstants.*;

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
        List<ProductDto> sellerProducts = productController.getProducts(username);
        populateWall(sellerProducts);
    }
    @FXML
    public void initialize() {
//        setUsername(username);
        List<ProductDto> sellerProducts = productController.getProducts(username);
        populateWall(sellerProducts);

    }
    private Image loadImageFromDirectories(String imageName) {
        try {
            String baseDir = "/co/edu/uniquindio/marketplace_fx/marketplace_app/images";
            URL baseResource = getClass().getResource(baseDir);

            if (baseResource == null) {
                System.err.println("Directorio base de imágenes no encontrado: " + baseDir);
                showMessage(TITULO_ERRROR_IMAGEN, BODY_ERRROR_IMAGEN + "Directorio no encontrado: " + baseDir, HEADER, Alert.AlertType.ERROR);
                return null;
            }
            File baseDirectory = new File(baseResource.toURI());
            if (!baseDirectory.exists() || !baseDirectory.isDirectory()) {
                System.err.println("El directorio base no es válido o no existe: " + baseDirectory.getAbsolutePath());
                showMessage(TITULO_ERRROR_IMAGEN, BODY_ERRROR_IMAGEN + "Directorio inválido: " + baseDirectory.getAbsolutePath(), HEADER, Alert.AlertType.ERROR);
                return null;
            }
            File imageFile = findImageInDirectory(baseDirectory, imageName);
            if (imageFile == null) {
                System.err.println("Imagen no encontrada: " + imageName);
                showMessage(TITULO_ERRROR_IMAGEN, BODY_ERRROR_IMAGEN + "Imagen no encontrada: " + imageName, HEADER, Alert.AlertType.ERROR);
                return null;
            }
            String fullPath = imageFile.toURI().toString();
            return new Image(fullPath);

        } catch (Exception e) {
            e.printStackTrace();
            showMessage(TITULO_ERRROR_IMAGEN, BODY_ERRROR_IMAGEN + e.getMessage(), HEADER, Alert.AlertType.ERROR);
            return null;
        }
    }

    // Método auxiliar para buscar recursivamente la imagen en un directorio
    private File findImageInDirectory(File directory, String imageName) {
        for (File file : directory.listFiles()) {
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
            System.err.println("Error al poblar el muro de productos: " + e.getMessage());
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
