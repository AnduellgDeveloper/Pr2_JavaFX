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

    @FXML
    void onNewClearFields(ActionEvent event) {
        clearFields();
    }

    @FXML
    void onAddProduct(ActionEvent event) {
        addProduct();
    }

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

    @FXML
    void onUpdateProduct(ActionEvent event) {
        updateProduct();
    }

    @FXML
    void initialize() {
        productController = new ProductController();
        initView();
        toggleGroupRdBtns();
    }

    private void initView() {
        initDataBinding();
        getProducts();
        tbProducts.getItems().clear();
        tbProducts.setItems(listProducts);
        listenerSelection();
    }

    private void toggleGroupRdBtns() {
        ToggleGroup toggleGroup = new ToggleGroup();
        rdBtnPublished.setToggleGroup(toggleGroup);
        rdBtnSold.setToggleGroup(toggleGroup);
        rdBtnCancelled.setToggleGroup(toggleGroup);
        rdBtnPublished.setSelected(true);

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

    private void getProducts() {
        listProducts.addAll(productController.getProducts());
    }

    private void initDataBinding() {
        tcName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().name()));
        tcCategory.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().category()));
        tcStatus.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().status()));
        tcImagePath.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().image()));
        tcPrice.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().price()));
        tcPublicationDate.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().publicationDate()));

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

    private void showProductInformation(ProductDto selectProduct) {
        if (selectProduct != null) {
            txtName.setText(selectProduct.name());
            txtCategory.setText(selectProduct.category());
            txtImage.setText(selectProduct.image());
            txtPrice.setText(String.valueOf(selectProduct.price()));
        }
    }

    private void addProduct() {
        ProductDto productDto = createProductDto();
        assert productDto != null;
        if (validDataProduct(productDto)) {
            if (productController.addProduct(productDto)) {
                listProducts.add(productDto);
                clearFields();
                showMessage(TITULO_PRODUCTO_AGREGADO, BODY_PRODUCTO_AGREGADO, Alert.AlertType.INFORMATION);
            } else {
                showMessage(TITULO_PRODUCTO_NO_AGREGADO, BODY_PRODUCTO_NO_AGREGADO, Alert.AlertType.ERROR);
            }
        } else {
            showMessage(TITULO_INCOMPLETO, BODY_INCOMPLETO, Alert.AlertType.WARNING);
        }
    }

    private void updateProduct() {
        if (selectProduct != null) {
            ProductDto updatedProduct = createProductDto();
            assert updatedProduct != null;
            if (validDataProduct(updatedProduct)) {
                productController.updateProduct(updatedProduct);
                listProducts.set(listProducts.indexOf(selectProduct), updatedProduct);
                showProductInformation(updatedProduct);
                showMessage(TITULO_PRODUCTO_ACTUALIZADO, BODY_PRODUCTO_ACTUALIZADO, Alert.AlertType.INFORMATION);
                clearFields();
            } else {
                showMessage(TITULO_INCOMPLETO, BODY_INCOMPLETO, Alert.AlertType.WARNING);
            }
        } else {
            showMessage(TITULO_PRODUCTO_NO_SELECCIONADO, BODY_PRODUCTO_NO_SELECCIONADO, Alert.AlertType.ERROR);
        }
    }

    private void removeProduct() {
        if (selectProduct != null) {
            productController.removeProduct(selectProduct);
            listProducts.remove(selectProduct);
            clearFields();
            showMessage(TITULO_PRODUCTO_REMOVIDO, BODY_PRODUCTO_REMOVIDO, Alert.AlertType.INFORMATION);
        } else {
            showMessage(TITULO_PRODUCTO_NO_REMOVIDO, BODY_PRODUCTO_NO_REMOVIDO, Alert.AlertType.ERROR);
        }
    }

    private boolean validDataProduct(ProductDto productDto) {
        return !productDto.name().isBlank() &&
                !productDto.image().isBlank() &&
                productDto.price() != 0 &&
                productDto.publicationDate() != null;
    }

    private ProductDto createProductDto() {
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
            showMessage(TITULO_ERROR_DEL_ESTADO, BODY_ERROR_DEL_ESTADO, Alert.AlertType.ERROR);
            return null;
        }

        int price;
        try {
            price = Integer.parseInt(txtPrice.getText());
        } catch (NumberFormatException e) {
            showMessage(TITULO_ERROR_EN_PRECIO, BODY_NUMERO_INVALIDO , Alert.AlertType.ERROR);
            return null;
        }

        return new ProductDto(
                name,
                image,
                category,
                price,
                status,
                LocalDateTime.now()
        );
    }

    private void clearFields() {
        txtName.clear();
        txtCategory.clear();
        txtPrice.clear();
        txtImage.clear();
        imgProduct.setImage(null);
    }

    private void showMessage(String title, String body, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(co.edu.uniquindio.marketplace_fx.marketplace_app.utils.ProductConstants.HEADER);
        alert.setContentText(body);
        alert.showAndWait();
    }
}
