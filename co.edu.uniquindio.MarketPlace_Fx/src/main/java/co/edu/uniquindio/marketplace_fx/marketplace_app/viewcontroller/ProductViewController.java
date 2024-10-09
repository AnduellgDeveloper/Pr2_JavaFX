package co.edu.uniquindio.marketplace_fx.marketplace_app.viewcontroller;

import co.edu.uniquindio.marketplace_fx.marketplace_app.controller.ProductController;
import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.ProductDto;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.time.LocalDate;
import java.util.Objects;

import static co.edu.uniquindio.marketplace_fx.marketplace_app.utils.ProductConstants.*;

public class ProductViewController {
    private ProductController productController;
    private ObservableList<ProductDto> listProducts = FXCollections.observableArrayList();
    private ProductDto selectProduct;
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
    // Método de inicialización que se llama al cargar la vista
    @FXML
    void initialize() {
        productController = new ProductController();
        initView();
        toggleGroupRdBtns();
    }
    // Método que carga la vista inicial y los productos en la tabla
    private void initView() {
        initDataBinding();
        getProducts();
        tbProducts.getItems().clear();
        tbProducts.setItems(listProducts);
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
        listProducts.addAll(productController.getProducts());
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
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/co/edu/uniquindio/marketplace_fx/marketplace_app/images/Men/" + imageName)));
            imgProduct.setImage(image);
        } catch (Exception e) {
            imgProduct.setImage(null);
            showMessage(TITULO_ERRROR_IMAGEN, BODY_ERRROR_IMAGEN+ e.getMessage(), HEADER, Alert.AlertType.ERROR);
        }
    }
    // Método que agrega un nuevo producto a la lista y a la base de datos
    private void addProduct() {
        ProductDto productDto = createProductDto();
        if (productDto != null && validDataProduct(productDto)) {
            if (isProductDuplicate(productDto)) {
                showMessage(TITULO_PRODUCTO_DUPLICADO, BODY_PRODUCTO_DUPLICADO, HEADER, Alert.AlertType.WARNING);
            } else {
                if (productController.addProduct(productDto)) {
                    listProducts.add(productDto);
                    clearFields();
                    showMessage(TITULO_PRODUCTO_AGREGADO, BODY_PRODUCTO_AGREGADO, HEADER, Alert.AlertType.INFORMATION);
                } else {
                    showMessage(TITULO_PRODUCTO_NO_AGREGADO, BODY_PRODUCTO_NO_AGREGADO, HEADER, Alert.AlertType.ERROR);
                }
            }
        } else {
            showMessage(TITULO_INCOMPLETO, BODY_INCOMPLETO, HEADER, Alert.AlertType.WARNING);
        }
    }
    // Método que actualiza el producto seleccionado en la lista y en la base de datos
    private void updateProduct() {
        if (selectProduct != null) {
            ProductDto updatedProduct = createProductDto();
            if (updatedProduct != null && validDataProduct(updatedProduct)) {
                productController.updateProduct(updatedProduct);
                listProducts.set(listProducts.indexOf(selectProduct), updatedProduct);
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
            listProducts.remove(selectProduct);
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
        return listProducts.stream().anyMatch(p -> p.name().equalsIgnoreCase(productDto.name()));
    }
    // Método que muestra un mensaje en un cuadro de diálogo
    private void showMessage(String title, String message, String header, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }
    // Método que limpia los campos de texto y el ImageView
    private void clearFields() {
        txtName.clear();
        txtCategory.clear();
        txtImage.clear();
        txtPrice.clear();
        dpPublicationDate.setValue(null);
        imgProduct.setImage(null);
    }
}