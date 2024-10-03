package co.edu.uniquindio.marketplace_fx.marketplace_app.viewcontroller;

import co.edu.uniquindio.marketplace_fx.marketplace_app.controller.ProductController;
import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.ProductDto;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

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
    private TableColumn<ProductDto, String> tcName;

    @FXML
    private TableColumn<ProductDto, Integer> tcPrice;  // Ahora de tipo Integer

    @FXML
    private TableColumn<ProductDto, String> tcPublicationDate;  // Declaramos la columna para la fecha

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
    void onAddProduct(ActionEvent event) {
    }
    @FXML
    void onRemoveProduct(ActionEvent event) {
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
    }
    @FXML
    void initialize() {
        productController = new ProductController();
        initView();
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
        tcPrice.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().price()));
        tcPublicationDate.setCellValueFactory(cellData -> {
            LocalDateTime publicationDate = cellData.getValue().publicationDate();
            String formattedDate = publicationDate.format(formatter);
            return new SimpleStringProperty(formattedDate);
        });
    }
    private void listenerSelection() {
        tbProducts.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectProduct = newSelection;
            showProductInformation(selectProduct);
        });
    }
    private void showProductInformation(ProductDto selectProduct) {
        if(selectProduct != null){
            txtName.setText(selectProduct.name());
            txtCategory.setText(selectProduct.category());
            txtImage.setText(selectProduct.image());
        }
    }
}
