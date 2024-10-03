package co.edu.uniquindio.marketplace_fx.marketplace_app.viewcontroller;

import co.edu.uniquindio.marketplace_fx.marketplace_app.controller.ProductController;
import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.ProductDto;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class ProductViewController {

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
    private TableColumn<ProductDto,String> tcCategory;

    @FXML
    private TableColumn<ProductDto,String> tcEstatus;

    @FXML
    private TableColumn<ProductDto,String> tcName;

    @FXML
    private TableColumn<ProductDto,String> tcPrice;

    @FXML
    private TableColumn<ProductDto,String> tcPublication;

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
        tbProducts.setItems(listaClientes);
        listenerSelection();
    }

    private void getProducts() {
        listProducts.addAll(productController.getProducts());
    }

    private void initDataBinding() {
        tcName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombre()));
        tc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().apellido()));
        tcCedula.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().cedula()));
        tcEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().email()));
        tcDireccion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().direccion()));
    }

    private void listenerSelection() {
        tableCliente.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            clienteSeleccionado = newSelection;
            mostrarInformacionCliente(clienteSeleccionado);
        });
    }

    private void mostrarInformacionCliente(ClienteDto clienteSeleccionado) {
        if(clienteSeleccionado != null){
            txtNombre.setText(clienteSeleccionado.nombre());
            txtApellido.setText(clienteSeleccionado.apellido());
            txtCedula.setText(clienteSeleccionado.cedula());
            txtEmail.setText(clienteSeleccionado.email());
            txtdireccion.setText(clienteSeleccionado.direccion());
        }
    }

}
