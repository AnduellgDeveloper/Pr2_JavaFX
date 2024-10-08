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

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ProductViewController {

    ProductController productController;
    ObservableList<ProductDto> listProducts = FXCollections.observableArrayList();
    ProductDto selectProduct;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");

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
    private TableColumn<ProductDto, String> tcEstatus;

    @FXML
    private TableColumn<ProductDto, String> tcImage;

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
    private ImageView viewProduct;

    @FXML
    void onNewClearFields(ActionEvent event) {clearFields();}
    @FXML
    void onAddProduct(ActionEvent event) {addProduct();}
    @FXML
    void onRemoveProduct(ActionEvent event) {
        removeProduct();
    }

    private void addProduct() {
        try {
            Integer price = Integer.parseInt(txtPrice.getText());
            ProductDto newProduct = new ProductDto(
                    txtName.getText(),
                    txtCategory.getText(),
                    "Published",
                    price,
                    txtImage.getText(),
                    LocalDateTime.now()
            );
            productController.addProduct(newProduct);
            listProducts.add(newProduct);
            clearFields();
        } catch (NumberFormatException e) {
            System.out.println("El precio debe ser un número válido.");
        }
    }

    private void removeProduct() {
        if (selectProduct != null) {
            productController.removeProduct(selectProduct);
            listProducts.remove(selectProduct);
            clearFields();
        } else {
            System.out.println("No se ha seleccionado ningún producto.");
        }
    }

    @FXML
    void onStatusCancelled(ActionEvent event) {
        if (selectProduct != null) {
            ProductDto updatedProduct = new ProductDto(
                    selectProduct.name(),
                    selectProduct.category(),
                    "Cancelled",
                    selectProduct.price(),
                    selectProduct.image(),
                    selectProduct.publicationDate()
            );
            updateProductInTable(updatedProduct);
        }
    }

    @FXML
    void onStatusPublished(ActionEvent event) {
        if (selectProduct != null) {
            ProductDto updatedProduct = new ProductDto(
                    selectProduct.name(),
                    selectProduct.category(),
                    "Published",
                    selectProduct.price(),
                    selectProduct.image(),
                    selectProduct.publicationDate()
            );
            updateProductInTable(updatedProduct);
        }
    }

    @FXML
    void onStatusSold(ActionEvent event) {
    }

    @FXML
    void onUpdateProduct(ActionEvent event) {

    }

    @FXML
    void initialize() {
        ToggleGroup toggleGroup = new ToggleGroup();
        productController = new ProductController();
        rdBtnPublished.setToggleGroup(toggleGroup);
        rdBtnSold.setToggleGroup(toggleGroup);
        rdBtnCancelled.setToggleGroup(toggleGroup);
        initView();
        txtImage.textProperty().addListener((observable, oldValue, newValue) -> {
            changeImage(newValue);
        });
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

    private void changeImage(String imagePath) {
        File imageFile = new File(imagePath);
        if (imageFile.exists()) {
            Image newImage = new Image(imageFile.toURI().toString());
            viewProduct.setImage(newImage);
        } else {
            System.out.println("El archivo de imagen no existe: " + imagePath);
        }
    }

    private void loadImage(String imagePath) {
        File imageFile = new File(imagePath);
        if (imageFile.exists()) {
            Image image = new Image(imageFile.toURI().toString());
            viewProduct.setImage(image);
        } else {
            System.out.println("El archivo de imagen no existe: " + imagePath);
        }
    }

    private void initView() {
        initDataBinding();
        getProducts();
        tbProducts.getItems().clear();
        tbProducts.setItems(listProducts);
        listenerSelection();
    }

    private void getProducts() {
        listProducts.addAll(productController.getProducts());
    }

    private void initDataBinding() {
        tcName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().name()));
        tcCategory.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().category()));
        tcEstatus.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().status()));
        tcImage.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().image()));
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
        tbProducts.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectProduct = newSelection;
            showProductInformation(selectProduct);
        });
    }

    private void showProductInformation(ProductDto selectProduct) {
        if (selectProduct != null) {
            txtName.setText(selectProduct.name());
            txtCategory.setText(selectProduct.category());
            txtImage.setText(selectProduct.image());
            txtPrice.setText(String.valueOf(selectProduct.price()));
            loadImage(selectProduct.image());
        }
    }

    private void clearFields() {
        txtName.clear();
        txtCategory.clear();
        txtPrice.clear();
        txtImage.clear();
        viewProduct.setImage(null);
    }

    private void updateProductInTable(ProductDto updatedProduct) {
        int index = listProducts.indexOf(selectProduct);
        listProducts.set(index, updatedProduct);
        tbProducts.refresh();
    }
}
