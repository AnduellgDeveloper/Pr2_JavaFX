package co.edu.uniquindio.marketplace_fx.marketplace_app.viewcontroller;

import co.edu.uniquindio.marketplace_fx.marketplace_app.controller.ProductController;
import co.edu.uniquindio.marketplace_fx.marketplace_app.controller.SellerController;
import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.ProductDto;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.User;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static co.edu.uniquindio.marketplace_fx.marketplace_app.utils.ProductConstants.*;

public class ProductViewController {
    private String username;
    private SellerController sellerController;
    private ProductController productController;
    private ObservableList<ProductDto> products = FXCollections.observableArrayList();
    private ProductDto selectProduct;
    private User user;
    @FXML
    private Button btnAddProduct;
    @FXML
    private Button btnClearFields;
    @FXML
    private Button btnUpdateProduct;
    @FXML
    private RadioButton rdBtnCancelled;
    @FXML
    private RadioButton rdBtnPublished;
    @FXML
    private RadioButton rdBtnSold;
    @FXML
    private TableView<ProductDto> tbProducts;
    @FXML
    private TableColumn<ProductDto, String> tcCategory;
    @FXML
    private TableColumn<ProductDto, String> tcStatus;
    @FXML
    private TableColumn<ProductDto, String> tcImagePath;
    @FXML
    private TableColumn<ProductDto, String> tcName;
    @FXML
    private TableColumn<ProductDto, Integer> tcPrice;
    @FXML
    private TableColumn<ProductDto, LocalDate> tcPublicationDate;
    @FXML
    private DatePicker dpPublicationDate;
    @FXML
    private TextField txtCategory;
    @FXML
    private TextField txtImage;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtPrice;
    @FXML
    private ImageView imgProduct;


    // Método de acción para limpiar los campos de texto
    @FXML
    void onClearFields(ActionEvent event) {
        clearFields();
    }
    // Método de acción para agregar un producto
    @FXML
    void onAddProduct(ActionEvent event) {
        addProduct();
    }
    // Método de acción para actualizar un producto
    @FXML
    void onUpdateProduct(ActionEvent event) {
        updateProduct();
    }
    // Método de acción para remover un producto
    @FXML
    void onRemoveProduct(ActionEvent event) {
        removeProduct();
    }
    public void setUsername(String username) {
        this.username = username;
        getProducts(); // Actualiza los productos al establecer el username
    }


    // Método de inicialización que se llama al cargar la vista
    @FXML
    void initialize() {
        productController = new ProductController();
        sellerController = new SellerController();
        initView();
        toggleGroupRdBtns();
        if (username != null) {
            getProducts();
        } else {
            products.clear();
        }
    }
    // Método que carga la vista inicial y los productos en la tabla
    private void initView() {
        initDataBinding();
        getProducts();
        tbProducts.getItems().clear();
        tbProducts.setItems(products);
        listenerSelection();
    }
    // Método que agrupa los botones de radio para seleccionar el estado del producto
    private void toggleGroupRdBtns() {
        ToggleGroup toggleGroup = new ToggleGroup();
        rdBtnPublished.setToggleGroup(toggleGroup);
        rdBtnSold.setToggleGroup(toggleGroup);
        rdBtnCancelled.setToggleGroup(toggleGroup);
        rdBtnPublished.setSelected(true);
    }
    // Método que obtiene los productos del controlador y los añade a la lista
    private void getProducts() {
        if (username == null || username.isEmpty()) {
            showMessage("Información", "Por favor, seleccione un usuario válido.", "Aviso", Alert.AlertType.INFORMATION);
        } else {
            products.clear();
            List<ProductDto> userProducts = productController.getProducts(username);
            if (userProducts.isEmpty()) {
                showMessage("Información", "No hay productos disponibles para este usuario.", "Aviso", Alert.AlertType.INFORMATION);
            } else {
                products.addAll(userProducts);
            }
        }

    }

    // Método que inicializa el enlace de datos para las columnas de la tabla
    private void initDataBinding() {
        tcName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().name()));
        tcCategory.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().category()));
        tcStatus.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().status()));
        tcImagePath.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().image()));
        tcPrice.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().price()));
        tcPublicationDate.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().publicationDate()));
        tcPublicationDate.setCellFactory(_ -> new TableCell<>() {
            @Override
            protected void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.toString());
                }
            }
        });
    }
    // Método que añade un listener para seleccionar un producto de la tabla
    private void listenerSelection() {
        tbProducts.getSelectionModel().selectedItemProperty().addListener((_, _, newSelection) -> {
            selectProduct = newSelection;
            showProductInformation(selectProduct);
        });
    }
    // Método que muestra la información del producto seleccionado en los campos de texto
    private void showProductInformation(ProductDto selectProduct) {
        if (selectProduct != null) {
            txtName.setText(selectProduct.name());
            txtCategory.setText(selectProduct.category());
            txtImage.setText(selectProduct.image());
            txtPrice.setText(String.valueOf(selectProduct.price()));
            dpPublicationDate.setValue(selectProduct.publicationDate());

            loadImage(selectProduct.image());
        }
    }
    // Método que carga la imagen del producto en el ImageView
    private void loadImage(String imageName) {
        try {
            String baseDir = "/co/edu/uniquindio/marketplace_fx/marketplace_app/images";
            try (Stream<Path> paths = Files.walk(Paths.get(getClass().getResource(baseDir).toURI()))) {
                for (Path path : (Iterable<Path>) paths::iterator) {
                    if (Files.isRegularFile(path) && path.getFileName().toString().equals(imageName)) {
                        String fullPath = path.toUri().toString();
                        Image image = new Image(fullPath);
                        imgProduct.setImage(image);
                        return;
                    }
                }
            }
            imgProduct.setImage(null);
            showMessage(TITULO_ERRROR_IMAGEN, BODY_ERRROR_IMAGEN + " Imagen no encontrada", HEADER, Alert.AlertType.ERROR);

        } catch (Exception e) {
            imgProduct.setImage(null);
            showMessage(TITULO_ERRROR_IMAGEN, BODY_ERRROR_IMAGEN + e.getMessage(), HEADER, Alert.AlertType.ERROR);
        }
    }
    // Método que agrega un nuevo producto a la lista y a la base de datos
    private void addProduct() {
        try {
            // Asegúrate de que el usuario esté disponible
            if (user == null || user.getUsername() == null) {
                showMessage("Error", "Usuario no disponible", "Error", Alert.AlertType.ERROR);
                return;
            }

            // Crear el DTO del producto
            ProductDto productDto = createProductDto();

            // Validar que el producto no sea nulo y que los datos sean válidos
            if (productDto == null || !validDataProduct(productDto)) {
                showMessage(TITULO_INCOMPLETO, BODY_INCOMPLETO, HEADER, Alert.AlertType.WARNING);
                return;
            }

            // Comprobar si el producto ya existe
            if (isProductDuplicate(productDto)) {
                showMessage(TITULO_PRODUCTO_DUPLICADO, BODY_PRODUCTO_DUPLICADO, HEADER, Alert.AlertType.WARNING);
                return;
            }

            // Usar el 'user' para agregar el producto a través del controlador
            boolean isAdded = productController.addProduct(productDto);

            // Verificar si el producto fue agregado correctamente
            if (isAdded) {
                products.add(productDto);
                clearFields();
                showMessage(TITULO_PRODUCTO_AGREGADO, BODY_PRODUCTO_AGREGADO, HEADER, Alert.AlertType.INFORMATION);
            } else {
                showMessage(TITULO_PRODUCTO_NO_AGREGADO, BODY_PRODUCTO_NO_AGREGADO, HEADER, Alert.AlertType.ERROR);
            }
        } catch (Exception e) {
            // Manejo de cualquier excepción inesperada
            e.printStackTrace();
            showMessage("Error", "Ocurrió un error al agregar el producto. Inténtalo de nuevo.", "Error", Alert.AlertType.ERROR);
        }
    }

    // Método que actualiza el producto seleccionado en la lista y en la base de datos
    private void updateProduct() {
        if (selectProduct != null) {
            ProductDto updatedProduct = createProductDto();
            if (updatedProduct != null && validDataProduct(updatedProduct)) {
                productController.updateProduct(updatedProduct);
                products.set(products.indexOf(selectProduct), updatedProduct);
                showProductInformation(updatedProduct);
                showMessage(TITULO_PRODUCTO_ACTUALIZADO, BODY_PRODUCTO_ACTUALIZADO, HEADER, Alert.AlertType.INFORMATION);
            } else {
                showMessage(TITULO_INCOMPLETO, BODY_INCOMPLETO, HEADER, Alert.AlertType.WARNING);
            }
        } else {
            showMessage(TITULO_PRODUCTO_NO_SELECCIONADO, BODY_PRODUCTO_NO_SELECCIONADO, HEADER, Alert.AlertType.WARNING);
        }
    }
    // Método que elimina el producto seleccionado de la lista y de la base de datos
    private void removeProduct() {
        if (selectProduct != null) {
            productController.removeProduct(selectProduct);
            products.remove(selectProduct);
            clearFields();
            showMessage(TITULO_PRODUCTO_REMOVIDO, BODY_PRODUCTO_REMOVIDO, HEADER, Alert.AlertType.INFORMATION);
        } else {
            showMessage(TITULO_PRODUCTO_NO_REMOVIDO, BODY_PRODUCTO_NO_REMOVIDO, HEADER, Alert.AlertType.WARNING);
        }
    }
    // Método que crea un objeto ProductDto a partir de los datos ingresados en los campos de texto
    private ProductDto createProductDto() {
        String name = txtName.getText();
        String category = txtCategory.getText();
        String image = txtImage.getText();
        String status = "";
        LocalDate publicationDate = dpPublicationDate.getValue();
        if (rdBtnPublished.isSelected()) {
            status = "Published";
        } else if (rdBtnSold.isSelected()) {
            status = "Sold";
        } else if (rdBtnCancelled.isSelected()) {
            status = "Cancelled";
        } else {
            showMessage(TITULO_ERROR_DEL_ESTADO, BODY_ERROR_DEL_ESTADO, HEADER, Alert.AlertType.ERROR);
            return null;
        }

        int price;
        try {
            price = Integer.parseInt(txtPrice.getText());
        } catch (NumberFormatException e) {
            showMessage(TITULO_ERROR_EN_PRECIO, BODY_NUMERO_INVALIDO, HEADER, Alert.AlertType.ERROR);
            return null;
        }

        if (publicationDate == null) {
            showMessage(TITULO_ERROR_FECHA, BODY_FECHA_INVALIDA, HEADER, Alert.AlertType.ERROR);
            return null;
        }

        return new ProductDto(name, image, category, price, status, publicationDate);
    }
    // Método que valida los datos del producto antes de añadirlo o actualizarlo
    private boolean validDataProduct(ProductDto productDto) {
        return productDto.name() != null && !productDto.name().isEmpty() &&
                productDto.category() != null && !productDto.category().isEmpty() &&
                productDto.price() > 0 &&
                productDto.publicationDate() != null;
    }
    // Método que verifica si el producto ya existe en la lista
    private boolean isProductDuplicate(ProductDto productDto) {
        return products.stream().anyMatch(p -> p.name().equalsIgnoreCase(productDto.name()));
    }
    // Método que muestra un mensaje en un cuadro de diálogo
    private void showMessage(String title, String message, String header, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }
    // Método que limpia los campos de texto y el ImageVitr6ew
    private void clearFields() {
        txtName.clear();
        txtCategory.clear();
        txtImage.clear();
        txtPrice.clear();
        dpPublicationDate.setValue(null);
        imgProduct.setImage(null);
    }
}