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

    // Método para agregar un producto a la lista y actualizar la tabla
    @FXML
    void onAddProduct(ActionEvent event) {
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

    // Método para eliminar el producto seleccionado
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

    // Método para actualizar el estado del producto a "Cancelled"
    @FXML
    void onStatusCancelled(ActionEvent event) {
        if (selectProduct != null) {
            selectProduct = new ProductDto(
                    selectProduct.name(),
                    selectProduct.category(),
                    "Cancelled",
                    selectProduct.price(),
                    selectProduct.image(),
                    selectProduct.publicationDate()
            );
            tbProducts.refresh();
        }
    }

    // Método para actualizar el estado del producto a "Published"
    @FXML
    void onStatusPublished(ActionEvent event) {
        if (selectProduct != null) {
            selectProduct = new ProductDto(
                    selectProduct.name(),
                    selectProduct.category(),
                    "Published",
                    selectProduct.price(),
                    selectProduct.image(),
                    selectProduct.publicationDate()
            );
            tbProducts.refresh();
        }
    }

    // Método para actualizar el estado del producto a "Sold"
    @FXML
    void onStatusSold(ActionEvent event) {
        if (selectProduct != null) {
            selectProduct = new ProductDto(
                    selectProduct.name(),
                    selectProduct.category(),
                    "Sold",
                    selectProduct.price(),
                    selectProduct.image(),
                    selectProduct.publicationDate()
            );
            tbProducts.refresh();
        }
    }

    // Método para actualizar la información de un producto
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
                        txtImage.getText(),
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

    // Método que inicializa el controlador y configura la vista
    @FXML
    void initialize() {
        productController = new ProductController();
        initView();
    }

    // Método para inicializar la vista, las tablas y los listeners
    private void initView() {
        initDataBinding();
        getProducts();
        tbProducts.getItems().clear();
        tbProducts.setItems(listProducts);
        listenerSelection();
    }

    // Método para cargar los productos de la base de datos
    private void getProducts() {
        listProducts.addAll(productController.getProducts());
    }

    // Método que enlaza los datos de los productos con las columnas de la tabla
    private void initDataBinding() {
        tcName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().name()));
        tcCategory.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().category()));
        tcEstatus.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().status()));
        tcPrice.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().price()));

        tcPublicationDate.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().publicationDate()));
        tcPublicationDate.setCellFactory(column -> {
            return new javafx.scene.control.TableCell<ProductDto, LocalDateTime>() {
                @Override
                protected void updateItem(LocalDateTime item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item.format(formatter));
                    }
                }
            };
        });
    }

    // Método para escuchar los cambios de selección en la tabla
    private void listenerSelection() {
        tbProducts.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
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

            loadImage(selectProduct.image());
        }
    }

    // Método para cargar la imagen del producto en el ImageView
    private void loadImage(String imagePath) {
        try {
            Image image = new Image(getClass().getResourceAsStream(imagePath));
            viewProduct.setImage(image);
        } catch (Exception e) {
            System.out.println("No se pudo cargar la imagen: " + imagePath);
        }
    }

    // Método para limpiar los campos de texto
    private void clearFields() {
        txtName.clear();
        txtCategory.clear();
        txtPrice.clear();
        txtImage.clear();
    }
}
