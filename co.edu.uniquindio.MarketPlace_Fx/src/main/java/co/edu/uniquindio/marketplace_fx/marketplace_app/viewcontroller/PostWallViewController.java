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
            URL resource = getClass().getResource(baseDir + "/" + imageName);
            if (resource == null) {
                System.err.println("Imagen no encontrada en el classpath: " + baseDir + "/" + imageName);
                showMessage(TITULO_ERRROR_IMAGEN, BODY_ERRROR_IMAGEN + "Imagen no encontrada: " + imageName, HEADER, Alert.AlertType.ERROR);
                return null;
            }
            String fullPath = resource.toExternalForm();
            System.out.println("Cargando imagen desde: " + fullPath);
            return new Image(fullPath);
        } catch (Exception e) {
            e.printStackTrace();
            showMessage(TITULO_ERRROR_IMAGEN, BODY_ERRROR_IMAGEN + e.getMessage(), HEADER, Alert.AlertType.ERROR);
        }
        return null;
    }

//    private Image loadImageFromResources(String imageName) {
//        String resourcePath = "/co/edu/uniquindio/marketplace_fx/marketplace_app/images/" + imageName;
//        try {
//            InputStream imageStream = getClass().getResourceAsStream(resourcePath);
//            if (imageStream != null) {
//                return new Image(imageStream);
//            } else {
//                System.err.println("Imagen no encontrada en el classpath: " + resourcePath);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.err.println("Error al cargar la imagen: " + imageName);
//        }
//        return null; // Fallback: Podrías devolver una imagen por defecto aquí si prefieres.
//    }
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


//    // Método que busca y carga la imagen desde los recursos
//    private Image loadImageFromDirectories(String imageName) {
//        try {
//            // Ruta relativa dentro de src/main/resources
//            String resourcePath = "co/edu/uniquindio/marketplace_fx/marketplace_app/images/" + imageName;
//            InputStream imageStream = getClass().getClassLoader().getResourceAsStream(resourcePath);
//            if (imageStream != null) {
//                return new Image(imageStream);
//            } else {
//                // Log de error en caso de no encontrar la imagen
//                System.err.println("Imagen no encontrada: " + resourcePath);
//                showMessage("Error de Imagen", "No se encontró la imagen: " + resourcePath, "Error", Alert.AlertType.ERROR);
//            }
//        } catch (Exception e) {
//            System.err.println("Error cargando imagen: " + e.getMessage());
//            showMessage("Error de Imagen", "Error cargando imagen: " + e.getMessage(), "Error", Alert.AlertType.ERROR);
//        }
//        return null;
//    }
//
//    // Método que obtiene los productos y los añade a la interfaz
//    private void populateWall(List<ProductDto> sellerProducts) {
//        try {
//            ImageView[] imageViews = {
//                    imgSeller1_1, imgSeller1_2, imgSeller1_3,
//                    imgSeller2_1, imgSeller2_2, imgSeller2_3
//            };
//            for (int i = 0; i < imageViews.length; i++) {
//                if (i < sellerProducts.size()) {
//                    ProductDto product = sellerProducts.get(i);
//                    String imagePath = product.image(); // Obtiene el nombre de la imagen desde el DTO
//                    Image image = loadImageFromDirectories(imagePath); // Carga la imagen
//                    if (image != null) {
//                        imageViews[i].setImage(image);
//                    } else {
//                        imageViews[i].setImage(null); // Limpia el ImageView si la imagen no se pudo cargar
//                    }
//                } else {
//                    imageViews[i].setImage(null); // Limpia el ImageView si no hay más productos
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            showMessage("Error", "Ocurrió un error al cargar el muro de productos: " + e.getMessage(), "Error", Alert.AlertType.ERROR);
//        }
//    }
//
//
//
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
