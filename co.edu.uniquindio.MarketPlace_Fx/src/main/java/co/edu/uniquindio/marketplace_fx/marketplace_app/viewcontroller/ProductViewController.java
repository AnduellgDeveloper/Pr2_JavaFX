package co.edu.uniquindio.marketplace_fx.marketplace_app.viewcontroller;

import co.edu.uniquindio.marketplace_fx.marketplace_app.controller.ProductController;
import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.ProductDto;
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
import javafx.scene.image.Image;
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
    void onAddProduct(ActionEvent event) {
        try {
            Integer price = Integer.parseInt(txtPrice.getText());
            ProductDto newProduct = new ProductDto(
                    txtName.getText(),
                    txtCategory.getText(),
                    "Published",
                    price,
                    LocalDateTime.now()
            );

            productController.addProduct(newProduct);
            listProducts.add(newProduct);
            clearFields();

        } catch (NumberFormatException e) {
            System.out.println("El precio debe ser un número válido.");

        }
    }

    @FXML
    void onRemoveProduct(ActionEvent event) {
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
            selectProduct = new ProductDto(
                    selectProduct.name(),
                    selectProduct.category(),
                    "Cancelled",
                    selectProduct.price(),
                    selectProduct.publicationDate()
            );
            tbProducts.refresh();
        }
    }

    @FXML
    void onStatusPublished(ActionEvent event) {
        if (selectProduct != null) {
            selectProduct = new ProductDto(
                    selectProduct.name(),
                    selectProduct.category(),
                    "Published",
                    selectProduct.price(),
                    selectProduct.publicationDate()
            );
            tbProducts.refresh();
        }
    }

    @FXML
    void onStatusSold(ActionEvent event) {
        if (selectProduct != null) {
            selectProduct = new ProductDto(
                    selectProduct.name(),
                    selectProduct.category(),
                    "Sold",
                    selectProduct.price(),
                    selectProduct.publicationDate()
            );
            tbProducts.refresh();
        }
    }

    @FXML
    void onUpdateProduct(ActionEvent event) {
        if (selectProduct != null) {
            try {
                Integer price = Integer.parseInt(txtPrice.getText());

                selectProduct = new ProductDto(
                        txtName.getText(),
                        txtCategory.getText(),
                        selectProduct.status(),
                        price,
                        selectProduct.publicationDate()
                );

                productController.updateProduct(selectProduct);

                tbProducts.refresh();
                clearFields();

            } catch (NumberFormatException e) {
                System.out.println("El precio debe ser un número válido.");
            }
        } else {
            System.out.println("No se ha seleccionado ningún producto.");
        }
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
    // Seleccion de la tabla
    private void listenerSelection() {
        tbProducts.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectProduct = newSelection;
            showProductInformation(selectProduct);
        });
    }

    // Muestra la información del producto
    private void showProductInformation(ProductDto selectProduct) {
        if (selectProduct != null) {
            txtName.setText(selectProduct.name());
            txtCategory.setText(selectProduct.category());
            txtImage.setText(selectProduct.image());
            txtPrice.setText(String.valueOf(selectProduct.price()));


            loadImage(selectProduct.image());
        }
    }
    // Carga la imagen del producto
    private void loadImage(String imagePath) {
        try {
            Image image = new Image(getClass().getResourceAsStream(imagePath));
            viewProduct.setImage(image);
        } catch (Exception e) {
            System.out.println("No se pudo cargar la imagen: " + imagePath);
        }
    }
    // Limpias los campos de txt
    private void clearFields() {
        txtName.clear();
        txtCategory.clear();
        txtPrice.clear();
        txtImage.clear();
    }
}
