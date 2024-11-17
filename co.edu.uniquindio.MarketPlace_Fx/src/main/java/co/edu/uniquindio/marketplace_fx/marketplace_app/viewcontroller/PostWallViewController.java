package co.edu.uniquindio.marketplace_fx.marketplace_app.viewcontroller;

import co.edu.uniquindio.marketplace_fx.marketplace_app.controller.ProductController;

import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.ProductDto;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.Product;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.facade.Theme;

import co.edu.uniquindio.marketplace_fx.marketplace_app.service.service_observer.Observer;
import co.edu.uniquindio.marketplace_fx.marketplace_app.service.service_abstractFactory.IComponentFactory;
import co.edu.uniquindio.marketplace_fx.marketplace_app.viewcontroller.abstractFactory_components.ComponentFactory;
import co.edu.uniquindio.marketplace_fx.marketplace_app.viewcontroller.abstractFactory_components.PostWallManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class PostWallViewController {
    private final IComponentFactory componentFactory;
    private PostWallManager postWallManager;
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
        this.componentFactory = new ComponentFactory();
        this.postWallManager = new PostWallManager(new ComponentFactory());
    }

    @FXML
    public void initialize() {
        this.tema = new Theme();
        postWallManager = new PostWallManager(componentFactory);
        postWallContainer.getChildren().add(postWallManager.getPostWall());

        List<ProductDto> sellerProducts = productController.getProducts(username);
        populateWall(sellerProducts);

        IComponentFactory factory = new ComponentFactory();
        postWallManager = new PostWallManager(factory);

        postWallContainer.getChildren().add(postWallManager.getPostWall());

    }
    public void setUsername(String username) {
        this.username = username;
        List<ProductDto> sellerProducts = productController.getProducts(username);
        populateWall(sellerProducts);
    }

    private void populateWall(List<ProductDto> sellerProducts) {
        try {
            postWallManager.clearPosts();
            for (ProductDto product : sellerProducts) {
                String imagePath = product.image();
                Image image = loadImageFromDirectories(imagePath);
                if (image != null) {
                    postWallManager.createPost(
                            product.name(),
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
        showMessage(TITULO_LIKE, BODY_LIKE_PRODUCTO + product.name(), HEADER_LIKE, Alert.AlertType.INFORMATION);
        String likeMessage = "Producto: " + product.name() + " -> ¡Le gusta a alguien!";
        productLikes.computeIfAbsent(product.name(), k -> new ArrayList<>()).add(likeMessage);

        listLikes.getItems().add(likeMessage);


    }

    // Alert Type personalizado para que el usuario pueda escribir el comentario
    private void onComment(ProductDto product) {
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
                } else {
                    System.out.println("No se escribió ningún comentario.");
                }
            } else {
                System.out.println("El comentario fue cancelado.");
            }
        });
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

    @FXML
    void onThem(ActionEvent event) {
        themeMode();
    }

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
                aplylightMode();
            } else if (response == darkMode) {
                aplyDarkMode();
            } else {
                System.out.println("Selección de tema cancelada.");
            }
        });
    }

    private void aplylightMode() {
        tema.modLight(label, fondo, Slider, btnTema, postWallContainer);
    }

    private void aplyDarkMode() {
        tema.modDark(label, fondo, Slider, btnTema, postWallContainer);
    }
}