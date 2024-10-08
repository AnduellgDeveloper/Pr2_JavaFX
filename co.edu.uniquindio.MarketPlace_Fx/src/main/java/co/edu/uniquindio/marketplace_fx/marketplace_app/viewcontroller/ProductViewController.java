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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static co.edu.uniquindio.marketplace_fx.marketplace_app.utils.ProductConstants.*;

public class ProductViewController {
    private ProductController productController;
    private ObservableList<ProductDto> listProducts = FXCollections.observableArrayList();
    private ProductDto selectProduct;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");

    @FXML
    private Button btnAddProduct;

    @FXML
    private Button btnRemoveProduct;

    @FXML
    private Button btnNewClearFields;

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
    private TableColumn<ProductDto, LocalDateTime> tcPublicationDate;

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

    // Limpiar los campos cuando se presiona el botón "Nuevo"
    @FXML
    void onNewClearFields(ActionEvent event) {
        clearFields();
    }

    // Agregar un producto cuando se presiona el botón "Agregar"
    @FXML
    void onAddProduct(ActionEvent event) {
        addProduct();
    }

    // Eliminar un producto cuando se presiona el botón "Eliminar"
    @FXML
    void onRemoveProduct(ActionEvent event) {
        removeProduct();
    }

    @FXML
    void onStatusCancelled(ActionEvent event) {
    }

    @FXML
    void onStatusPublished(ActionEvent event) {
    }

    @FXML
    void onStatusSold(ActionEvent event) {
    }

    // Actualizar un producto cuando se presiona el botón "Actualizar"
    @FXML
    void onUpdateProduct(ActionEvent event) {
        updateProduct();
    }

    // Inicializar la vista y el controlador
    @FXML
    void initialize() {
        productController = new ProductController();
        initView();
        toggleGroupRdBtns();
    }

    // Inicializa la vista de la tabla y enlaza los datos
    private void initView() {
        initDataBinding();
        getProducts();
        tbProducts.getItems().clear();
        tbProducts.setItems(listProducts);
        listenerSelection();
    }

    // Configura los RadioButtons en un ToggleGroup para manejar el estado del producto
    private void toggleGroupRdBtns() {
        ToggleGroup toggleGroup = new ToggleGroup();
        rdBtnPublished.setToggleGroup(toggleGroup);
        rdBtnSold.setToggleGroup(toggleGroup);
        rdBtnCancelled.setToggleGroup(toggleGroup);
        rdBtnPublished.setSelected(true);

        // Añade un listener para manejar el cambio de selección en los RadioButtons
        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (rdBtnPublished.isSelected()) {
                onStatusPublished(null);
            } else if (rdBtnSold.isSelected()) {
                onStatusSold(null);
            } else if (rdBtnCancelled.isSelected()) {
                onStatusCancelled(null);
            }
        });
    }

    // Cargar los productos desde el controlador
    private void getProducts() {
        listProducts.addAll(productController.getProducts());
    }

    // Enlazar los datos de los productos con las columnas de la tabla
    private void initDataBinding() {
        tcName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().name()));
        tcCategory.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().category()));
        tcStatus.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().status()));
        tcImagePath.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().image()));
        tcPrice.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().price()));
        tcPublicationDate.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().publicationDate()));

        // Formatear las fechas en las celdas de la tabla
        tcPublicationDate.setCellFactory(column -> new TableCell<ProductDto, LocalDateTime>() {
            @Override
            protected void updateItem(LocalDateTime item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.format(formatter));
                }
            }
        });
    }

    // Añadir un listener para manejar la selección de un producto en la tabla
    private void listenerSelection() {
        tbProducts.getSelectionModel().selectedItemProperty().addListener((_, oldSelection, newSelection) -> {
            selectProduct = newSelection;
            showProductInformation(selectProduct);
            if (selectProduct != null && selectProduct.image() != null && !selectProduct.image().isEmpty()) {
                imgProduct.setImage(new Image(selectProduct.image()));
            } else {
                imgProduct.setImage(null);
            }
        });
    }

    // Mostrar la información del producto seleccionado en los campos de texto
    private void showProductInformation(ProductDto selectProduct) {
        if (selectProduct != null) {
            txtName.setText(selectProduct.name());
            txtCategory.setText(selectProduct.category());
            txtImage.setText(selectProduct.image());
            txtPrice.setText(String.valueOf(selectProduct.price()));
        }
    }

    // Agregar un producto a la lista
    private void addProduct() {
        ProductDto productDto = createProductDto(null);
        assert productDto != null;
        if (validDataProduct(productDto)) {
            if (isProductDuplicate(productDto)) {
                showMessage(TITULO_PRODUCTO_DUPLICADO, BODY_PRODUCTO_DUPLICADO, "Duplicado", Alert.AlertType.WARNING);
            } else {
                if (productController.addProduct(productDto)) {
                    listProducts.add(productDto);
                    clearFields();
                    showMessage(TITULO_PRODUCTO_AGREGADO, BODY_PRODUCTO_AGREGADO, "Producto Agregado", Alert.AlertType.INFORMATION);
                } else {
                    showMessage(TITULO_PRODUCTO_NO_AGREGADO, BODY_PRODUCTO_NO_AGREGADO, "Error", Alert.AlertType.ERROR);
                }
            }
        } else {
            showMessage(TITULO_INCOMPLETO, BODY_INCOMPLETO, "Datos incompletos", Alert.AlertType.WARNING);
        }
    }

    // Actualizar el producto seleccionado en la lista
    private void updateProduct() {
        if (selectProduct != null) {
            ProductDto updatedProduct = createProductDto(selectProduct.publicationDate());
            assert updatedProduct != null;
            if (validDataProduct(updatedProduct)) {
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

    // Eliminar el producto seleccionado
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

//    // Crear un DTO de producto usando los datos de los campos de texto
//    private ProductDto createProductDto(LocalDateTime publicationDate) {
//        try {
//            String name = txtName.getText();
//            String category = txtCategory.getText();
//            String image = txtImage.getText();
//            int price = Integer.parseInt(txtPrice.getText());
//            String status = rdBtnPublished.isSelected() ? "Published" : rdBtnSold.isSelected() ? "Sold" : "Cancelled";
//
//            LocalDateTime pubDate = publicationDate != null ? publicationDate : LocalDateTime.now();
//            return new ProductDto(name, image, category, price, status, pubDate);
//        } catch (NumberFormatException e) {
//            showMessage(TITULO_DATOS_INVALIDOS, BODY_DATOS_INVALIDOS, HEADER, Alert.AlertType.ERROR);
//            return null;
//        }
//    }
    //crear producto dto
    private ProductDto createProductDto(LocalDateTime publicationDate) {
        String name = txtName.getText();
        String category = txtCategory.getText();
        String image = txtImage.getText();
        String status = "";

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
        LocalDateTime finalPublicationDate = (publicationDate != null) ? publicationDate : LocalDateTime.now();

        return new ProductDto(
                name,
                image,
                category,
                price,
                status,
                finalPublicationDate
        );
    }
    // Validar que los campos del producto no estén vacíos o inválidos
    private boolean validDataProduct(ProductDto productDto) {
        return productDto.name() != null && !productDto.name().isEmpty() &&
                productDto.category() != null && !productDto.category().isEmpty() &&
                productDto.price() > 0;
    }
    // Verificar si el producto ya existe en la lista
    private boolean isProductDuplicate(ProductDto productDto) {
        return listProducts.stream().anyMatch(p -> p.name().equalsIgnoreCase(productDto.name()));
    }
    // Mostrar un mensaje de alerta al usuario con título, mensaje y header
    private void showMessage(String title, String message, String header, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }
    // Limpiar todos los campos del formulario
    private void clearFields() {
        txtName.clear();
        txtCategory.clear();
        txtImage.clear();
        txtPrice.clear();
        imgProduct.setImage(null);
    }
}
