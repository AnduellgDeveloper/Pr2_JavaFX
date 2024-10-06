package co.edu.uniquindio.marketplace_fx.marketplace_app.viewcontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AdministratorViewController {

    @FXML
    private Button btnAddSeller;

    @FXML
    private Button btnDeleteSeller;

    @FXML
    private Button btnUpdateSeller;

    @FXML
    private TableView<?> tableSeller;

    @FXML
    private TableColumn<?, ?> tcAddress;

    @FXML
    private TableColumn<?, ?> tcIdNumber;

    @FXML
    private TableColumn<?, ?> tcLastName;

    @FXML
    private TableColumn<?, ?> tcName;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtIdNumber;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    void btnEliminarVendedor(ActionEvent event) {

    }

    @FXML
    void onActualizarVendedor(ActionEvent event) {

    }

    @FXML
    void onAgregarVendedor(ActionEvent event) {

    }

}
