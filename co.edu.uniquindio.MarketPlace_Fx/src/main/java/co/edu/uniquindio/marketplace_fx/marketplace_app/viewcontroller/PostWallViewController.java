package co.edu.uniquindio.marketplace_fx.marketplace_app.viewcontroller;

import co.edu.uniquindio.marketplace_fx.marketplace_app.controller.ProductController;

import co.edu.uniquindio.marketplace_fx.marketplace_app.controller.SellerController;
import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.ProductDto;
import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.SellerDto;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.Marketplace;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.Seller;
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
    private Marketplace marketplace = new Marketplace();
    private SellerController sellerController = new SellerController();
    private final IComponentFactory componentFactory = new ComponentFactory();
    private PostWallManager postWallManager = new PostWallManager(componentFactory);
    private Theme tema;
    private String username;
    private Seller currentSeller;
    private final ProductController productController = new ProductController();
    private Map<String, List<String>> productComments = new HashMap<>();
    private Map<String, List<String>> productLikes = new HashMap<>();
    private MarketPlaceAppController market;

    public PostWallViewController() {
    }
    @FXML
    private ListView<String> friendsList;
    @FXML
    private Button btnAddFriend, btnTema;
    @FXML
    private AnchorPane fondo;
    @FXML
    private Pane Slider;
    @FXML
    private GridPane postWallContainer;
    @FXML
    private Label label;
    @FXML
    private ListView<String> listComments, listLikes;

    // Llama el metodo de cambiar el tema
    @FXML
    void onThem(ActionEvent event) {
        themeMode();
    }
    @FXML
    public void initialize() {
        this.tema = new Theme();
        postWallContainer.getChildren().add(postWallManager.getPostWall());
        setupFriendsList();
        productController.addObserver(this);
        btnAddFriend.setOnAction(event -> showAddFriendDialog());
        if (username != null) {
            updateCurrentSeller();
            List<ProductDto> allProducts = getAllVisibleProducts();
            populateWall(allProducts, username);
        }
        listComments.getItems().clear();
        listLikes.getItems().clear();
    }
    // Metodo para setterar el username y actualizar el muro
    public void setUsername(String username) {
        this.username = username;
        List<ProductDto> allProducts = getAllVisibleProducts();
        populateWall(allProducts, username);
    }
    @Override
    public void update() {
        List<ProductDto> products = getAllVisibleProducts();
        updateCurrentSeller();
        populateWall(products,username);
    }
    private void populateWall(List<ProductDto> products, String username) {
        try {
            postWallManager.clearPosts();
            List<ProductDto> sortedProducts = new ArrayList<>(products);
            sortedProducts.sort((p1, p2) -> p2.publicationDate().compareTo(p1.publicationDate()));
            for (ProductDto product : sortedProducts) {
                String imagePath = product.image();
                Image image = loadImageFromDirectories(imagePath);
                if (image != null) {
                    String postTitle = product.name();
                    postWallManager.createPost(
                            postTitle,
                            product.publicationDate(),
                            image.getUrl(),
                            () -> onLike(product),
                            () -> onComment(product)
                    );
                }
            }
        } catch (Exception e) {
            logError(e);
            showMessage(TITULO_ERROR, "Error al poblar el muro: " + e.getMessage(), ERROR,
                    Alert.AlertType.ERROR);
        }
    }
    // Obtener todos los productos visibles
    private List<ProductDto> getAllVisibleProducts() {
        List<ProductDto> allProducts = new ArrayList<>();

        SellerDto currentSeller = sellerController.getListSellers().stream()
                .filter(seller -> seller.username().equals(username))
                .findFirst()
                .orElse(null);

        if (currentSeller != null) {
            allProducts.addAll(productController.getProducts(username));
            for (Seller friend : currentSeller.friends()) {
                allProducts.addAll(productController.getProducts(friend.getUsername()));
            }
        }
        return allProducts;
    }
    // Accion al dar like a un producto
    public void onLike(ProductDto product) {
        if (username == null || username.isEmpty()) {
            showMessage(TITULO_ERROR, "Debe estar identificado para dar 'me gusta'.", ERROR, Alert.AlertType.ERROR);
            return;
        }
        productLikes.computeIfAbsent(product.name(), k -> new ArrayList<>()).add(username);
        updateLikesList(product);
    }
    // Accion al dar click en el boton de comentario
    public void onComment(ProductDto product) {
        if (username == null || username.isEmpty()) {
            showMessage(TITULO_ERROR, "Debe estar identificado para comentar.",ERROR, Alert.AlertType.ERROR);
            return;
        }
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
                    String commentText = comment + " - " + username;
                    productComments.computeIfAbsent(product.name(), k -> new ArrayList<>()).add(commentText);
                    updateCommentsList(product);
                }
            } else {
                System.out.println("Comentario cancelado.");
            }
        });
    }
    // Actualizar lista de likes
    private void updateLikesList(ProductDto product) {
        List<String> likes = productLikes.get(product.name());
        for (String user : likes) {
            listLikes.getItems().add(user + " le dio me gusta a " + product.name());
        }
    }
    // Actualiza la lista de comentarios
    private void updateCommentsList(ProductDto product) {
        List<String> comments = productComments.get(product.name());
        for (String comment : comments) {
            listComments.getItems().add("Comentario a " + product.name() + ": " + comment);
        }
    }
    // Carga la iamgen desde un directorio (itera buscando igualdad con el nombre de la imagen)
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

    // Busca la imagen en el directorio asignado
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
    // Ver mensaje
    private void showMessage(String title, String message, String header, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }
    // Interfaz de mensaje para elegir el tema (oscuro o claro)
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
    // Aplicar el modo claro
    private void applyLightMode() {
        tema.modLight(label, fondo, Slider, btnTema, postWallContainer);
    }
    // Aplicar el modo oscuro
    private void applyDarkMode() {
        tema.modDark(label, fondo, Slider, btnTema, postWallContainer);
    }
    private void logError(Exception e) {
        System.err.println("Error: " + e.getMessage());
        e.printStackTrace();
    }
    // Configurar la lista de amigos
    private void setupFriendsList() {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem removeItem = new MenuItem("Eliminar amigo");
        removeItem.setOnAction(event -> removeFriend());
        contextMenu.getItems().add(removeItem);

        friendsList.setContextMenu(contextMenu);

        friendsList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                String selectedFriend = friendsList.getSelectionModel().getSelectedItem();
                if (selectedFriend != null) {
                    showFriendProducts(selectedFriend);
                }
            }
        });
    }
    // Actualizar el usuario actual
    private void updateCurrentSeller() {
        currentSeller = marketplace.getListSellers().stream()
                .filter(seller -> seller.getUsername().equals(username))
                .findFirst()
                .orElse(null);
        updateFriendsList();
    }
    // Actualizar la lista de amigos o contactos
    private void updateFriendsList() {
        friendsList.getItems().clear();
        if (marketplace != null) {
            marketplace.getSellerFriends().forEach(friend ->
                    friendsList.getItems().add(friend.getUsername())
            );
        }
    }
    // Mensaje emergete para añadir un nuevo amigo o contacto
    private void showAddFriendDialog() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Agregar Amigo");
        dialog.setHeaderText("Agregar nuevo amigo");
        dialog.setContentText("Username del vendedor:");
        dialog.showAndWait().ifPresent(username -> {
            try {
                addFriend(username);
            } catch (Exception e) {
                showMessage("Error", e.getMessage(), "Error al agregar amigo", Alert.AlertType.ERROR);
            }
        });
    }
    // Metodo para añadir un amigo
    private void addFriend(String friendUsername) {
        if (marketplace == null) {
            throw new IllegalStateException("No hay un vendedor actual");
        }
        if (friendUsername.equals(currentSeller.getUsername())) {
            throw new IllegalArgumentException("No puedes agregarte a ti mismo como amigo");
        }

        Seller friendSeller = marketplace.getListSellers().stream()
                .filter(seller -> seller.getUsername().equals(friendUsername))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Vendedor no encontrado"));

        if (marketplace.getSellerFriends().contains(friendSeller)) {
            throw new IllegalArgumentException("Este vendedor ya es tu amigo");
        }
        marketplace.getSellerFriends().add(friendSeller);
        updateFriendsList();
        showMessage("Éxito", "Amigo agregado correctamente", "Nuevo amigo", Alert.AlertType.INFORMATION);

        List<ProductDto> allProducts = getAllVisibleProducts();
        populateWall(allProducts, username);
    }
    // Remover amigo o contacto
    private void removeFriend() {
        String selectedFriend = friendsList.getSelectionModel().getSelectedItem();
        if (selectedFriend == null) {
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Eliminar Amigo");
        alert.setHeaderText("¿Estás seguro de que quieres eliminar a " + selectedFriend + " de tu lista de amigos?");
        alert.setContentText("Esta acción no se puede deshacer.");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Seller friendToRemove = marketplace.getSellerFriends().stream()
                        .filter(friend -> friend.getUsername().equals(selectedFriend))
                        .findFirst()
                        .orElse(null);

                if (friendToRemove != null) {
                    marketplace.getSellerFriends().remove(friendToRemove);
                    updateFriendsList();
                    List<ProductDto> allProducts = getAllVisibleProducts();
                    populateWall(allProducts, username);
                    showMessage("Éxito", "Amigo eliminado correctamente", "Eliminar amigo", Alert.AlertType.INFORMATION);
                }
            }
        });
    }
    private void showFriendProducts(String friendUsername) {
        List<ProductDto> friendProducts = productController.getProducts(friendUsername);
        populateWall(friendProducts, username);
    }
}
