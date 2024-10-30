package co.edu.uniquindio.marketplace_fx.marketplace_app.viewcontroller;

import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.SellerDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SellerViewController {
    @FXML
    private Button btnAddSeller;

    @FXML
    private Button btnClearFields;

    @FXML
    private Button btnRemoveSeller;

    @FXML
    private Button btnUpdateSeller;

    @FXML
    private TableView<SellerDto> tabSeller;

    @FXML
    private TableColumn<SellerDto, String> tcAddress;

    @FXML
    private TableColumn<SellerDto, String> tcIdNumber;

    @FXML
    private TableColumn<SellerDto, String> tcLastName;

    @FXML
    private TableColumn<SellerDto, String> tcPassword;

    @FXML
    private TableColumn<SellerDto,String> tcSeller;

    @FXML
    private TableColumn<SellerDto,String> tcUserName;

    @FXML
    private TextField txtIdNumber;


    @FXML
    private TextField txtAddressSeller;

    @FXML
    private TextField txtLastNameSeller;

    @FXML
    private TextField txtNameSeller;

    @FXML
    private TextField txtPasswordSeller;

    @FXML
    private TextField txtUserNameSeller;

    @FXML
    void onAddSeller(ActionEvent event) {

    }

    @FXML
    void onClearFields(ActionEvent event) {
    clearFields();

    }

    @FXML
    void onRemoveSeller(ActionEvent event) {

    }

    @FXML
    void onUpdateSeller(ActionEvent event) {

    }
    private void clearFields() {
        txtNameSeller.clear();
        txtLastNameSeller.clear();
        txtIdNumber.clear();
        txtAddressSeller.clear();
        txtUserNameSeller.clear();
        txtPasswordSeller.clear();
    }
}
