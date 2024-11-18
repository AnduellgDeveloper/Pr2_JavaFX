package co.edu.uniquindio.marketplace_fx.marketplace_app.viewcontroller;

import co.edu.uniquindio.marketplace_fx.marketplace_app.controller.ProductController;

import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.ProductDto;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.facade.Theme;

import co.edu.uniquindio.marketplace_fx.marketplace_app.service.service_observer.Observer;
import co.edu.uniquindio.marketplace_fx.marketplace_app.service.service_abstractFactory.IComponentFactory;
import co.edu.uniquindio.marketplace_fx.marketplace_app.viewcontroller.abstractFactory_components.ComponentFactory;
import co.edu.uniquindio.marketplace_fx.marketplace_app.viewcontroller.abstractFactory_components.PostWallManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.File;
import java.net.URL;
import java.util.*;

import static co.edu.uniquindio.marketplace_fx.marketplace_app.utils.PostWallConstants.*;

public class PostWallViewController implements Observer {
    private final IComponentFactory componentFactory = new ComponentFactory();
    private PostWallManager postWallManager = new PostWallManager(componentFactory);
    private Theme tema;
    private String username;
    private final ProductController productController = new ProductController();
    private Map<String, List<String>> productComments = new HashMap<>();
    private Map<String, List<String>> productLikes = new HashMap<>();

    @FXML
    private AnchorPane fondo;
    @FXML
    private Pane Slider;
    @FXML
    private GridPane postWallContainer;
    @FXML
    private Button btnTema;
    @FXML
    private Label label;
    @FXML
    private ListView<String> listComments, listLikes;
    public PostWallViewController() {
    }
    @FXML
    public void initialize() {
        this.tema = new Theme();
        postWallContainer.getChildren().add(postWallManager.getPostWall());

        productController.addObserver(this);

        if (username != null) {
            List<ProductDto> sellerProducts = productController.getProducts(username);
            populateWall(sellerProducts);
        }

        listComments.getItems().clear();
        listLikes.getItems().clear();
    }


    // Metodo para setterar el username y actualizar el muro
    public void setUsername(String username) {
        this.username = username;
        List<ProductDto> sellerProducts = productController.getProducts(username);
        populateWall(sellerProducts);
    }

    @Override
    public void update() {
        List<ProductDto> updatedProducts = productController.getProducts(username);
        populateWall(updatedProducts);
    }


    // Crear los elementos de el muro de publicaciones (se crean en orden segun la fecha
    private void populateWall(List<ProductDto> sellerProducts) {
        try {
            postWallManager.clearPosts();
            List<ProductDto> sortedProducts = new ArrayList<>(sellerProducts);
            sortedProducts.sort((p1, p2) -> p2.publicationDate().compareTo(p1.publicationDate()));

            for (ProductDto product : sortedProducts) {
                String imagePath = product.image();
                Image image = loadImageFromDirectories(imagePath);
                if (image != null) {
                    postWallManager.createPost(
                            product.name(),
                            product.publicationDate(),
                            image.getUrl(),
                            () -> onLike(product),
                            () -> onComment(product)
                    );
                }
            }
        } catch (Exception e) {
            logError(e);
            showMessage("Error", "Error al poblar el muro: " + e.getMessage(), "Error", Alert.AlertType.ERROR);
        }
    }

    // Handle liking a product
    // Handle liking a product
    public void onLike(ProductDto product) {
        if (username == null || username.isEmpty()) {
            showMessage("Error", "Debe estar identificado para dar 'me gusta'.", "Error", Alert.AlertType.ERROR);
            return;
        }

        String likeMessage = "Usuario: " + username + " -> Producto: " + product.name();

        // Agregar el mensaje al mapa de likes
        productLikes.computeIfAbsent(product.name(), k -> new ArrayList<>()).add(username);

        // Actualizar la vista de la lista de likes
        listLikes.getItems().add(likeMessage);

        // Mostrar notificación
        showMessage("Like", "¡Le diste me gusta al producto: " + product.name() + "!", "Información", Alert.AlertType.INFORMATION);
    }


    // Handle commenting on a product
    public void onComment(ProductDto product) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Comentario");
        alert.setHeaderText("Agregar comentario para el producto: " + product.name());
        alert.setContentText("Escribe tu comentario abajo:");

        TextArea commentField = new TextArea();
        commentField.setPromptText("Escribe tu comentario aquí...");
        commentField.setWrapText(true);

        alert.getDialogPane().setContent(commentField);

        ButtonType submitButton = new ButtonType("Enviar");
        ButtonType cancelButton = new ButtonType("Cancelar");
        alert.getButtonTypes().setAll(submitButton, cancelButton);

        alert.showAndWait().ifPresent(response -> {
            if (response == submitButton) {
                String comment = commentField.getText().trim();
                if (!comment.isEmpty()) {
                    productComments.computeIfAbsent(product.name(), k -> new ArrayList<>()).add(comment);
                    listComments.getItems().add("Producto: " + product.name() + " -> " + comment);
                    System.out.printf("\nComentario enviado a %s: %s", product.name(), comment);
                }
            } else {
                System.out.println("Comentario cancelado.");
            }
        });
    }

    // Load image from directories
    private Image loadImageFromDirectories(String imageName) {
        try {
            String baseDir = "/co/edu/uniquindio/marketplace_fx/marketplace_app/images";
            URL baseResource = getClass().getResource(baseDir);
            if (baseResource == null) {
                showMessage("Error Imagen", "Directorio no encontrado: " + baseDir, "Error", Alert.AlertType.ERROR);
                return null;
            }
            File baseDirectory = new File(baseResource.toURI());
            if (!baseDirectory.exists() || !baseDirectory.isDirectory()) {
                showMessage("Error Imagen", "Directorio inválido: " + baseDirectory.getAbsolutePath(), "Error", Alert.AlertType.ERROR);
                return null;
            }
            File imageFile = findImageInDirectory(baseDirectory, imageName);
            if (imageFile == null) {
                showMessage("Error Imagen", "Imagen no encontrada: " + imageName, "Error", Alert.AlertType.ERROR);
                return null;
            }
            return new Image(imageFile.toURI().toString());
        } catch (Exception e) {
            logError(e);
            showMessage("Error Imagen", "Error al cargar la imagen: " + e.getMessage(), "Error", Alert.AlertType.ERROR);
            return null;
        }
    }

    // Recursive method to find image in directory
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

    // Show message dialog
    private void showMessage(String title, String message, String header, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Change the theme mode (Light/Dark)
    @FXML
    void onThem(ActionEvent event) {
        themeMode();
    }

    // Select light or dark theme
    private void themeMode() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Seleccionar Tema");
        alert.setHeaderText("Elige un modo:");
        alert.setContentText("Selecciona entre Modo Claro o Modo Oscuro:");

        ButtonType lightMode = new ButtonType("Modo Claro");
        ButtonType darkMode = new ButtonType("Modo Oscuro");
        ButtonType cancel = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(lightMode, darkMode, cancel);

        alert.showAndWait().ifPresent(response -> {
            if (response == lightMode) {
                applyLightMode();
            } else if (response == darkMode) {
                applyDarkMode();
            } else {
                System.out.println("Selección de tema cancelada.");
            }
        });
    }

    // Apply light mode styles
    private void applyLightMode() {
        tema.modLight(label, fondo, Slider, btnTema, postWallContainer);
    }

    // Apply dark mode styles
    private void applyDarkMode() {
        tema.modDark(label, fondo, Slider, btnTema, postWallContainer);
    }

    // Error logging helper
    private void logError(Exception e) {
        // Here you can implement a proper logging framework
        System.err.println("Error: " + e.getMessage());
        e.printStackTrace();
    }
}
