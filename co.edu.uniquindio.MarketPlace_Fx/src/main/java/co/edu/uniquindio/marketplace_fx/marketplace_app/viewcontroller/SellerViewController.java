package co.edu.uniquindio.marketplace_fx.marketplace_app.viewcontroller;

import co.edu.uniquindio.marketplace_fx.marketplace_app.controller.SellerController;
import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.SellerDto;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javax.print.DocFlavor;
import java.util.ResourceBundle;

public class SellerViewController {
    SellerController sellerController;
    ObservableList<SellerDto>listSellers = FXCollections.observableArrayList();
    SellerDto selectSeller;

        @FXML
        private ResourceBundle resources;

        @FXML
        private DocFlavor.URL location;

        @FXML
        private Button btnAddSeller;

        @FXML
        private Button btnClearFields;

        @FXML
        private Button btnRemoveSeller;

        @FXML
        private Button btnUpdateSeller;

        @FXML
        private TableView<SellerDto> tbSeller;

        @FXML
        private TableColumn<SellerDto,String> tcAddress;

        @FXML
        private TableColumn<SellerDto,String> tcIdNumber;

        @FXML
        private TableColumn<SellerDto,String> tcLastName;

        @FXML
        private TableColumn<SellerDto,String> tcName;

        @FXML
        private TableColumn<SellerDto,String> tcPassword;

        @FXML
        private TableColumn<SellerDto,String> tcUserName;

        @FXML
        private TextField txtAddress;

        @FXML
        private TextField txtLastName;

        @FXML
        private TextField txtName;

        @FXML
        private TextField txtPassword;

        @FXML
        private TextField txtUserName;

        @FXML
        private TextField txtidNumber;

        @FXML
        void onAddSeller(ActionEvent event) {

        }

        @FXML
        void onClearFields(ActionEvent event) {

        }

        @FXML
        void onRemoveSeller(ActionEvent event) {

        }

        @FXML
        void onUpdateSeller(ActionEvent event) {

        }




    private void clearFields() {
        txtName.clear();
        txtLastName.clear();
        txtidNumber.clear();
        txtAddress.clear();
        txtUserName.clear();
        txtPassword.clear();
    }
    @FXML
    void initialize(){
        sellerController = new SellerController();
        initView();
    }

    private void initView() {
        initDataBinding();
//        getSeller();
        tbSeller.getItems().clear();
        tbSeller.setItems(listSellers);
        listenerSelection();
    }

//    private void getSeller() {
//            listSellers.addAll(SellerController.getSeller);
//    }

    private void listenerSelection() {
            tbSeller.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) -> {
                selectSeller = newSelection;
                showSellerInformation(selectSeller);
            });
    }

    private void showSellerInformation(SellerDto selectSeller) {
            if (selectSeller !=null){
                txtName.setText(selectSeller.name());
                txtLastName.setText(selectSeller.lastName());
                txtidNumber.setText(selectSeller.idNumber());
                txtAddress.setText(selectSeller.address());
                txtUserName.setText(selectSeller.username());
                txtPassword.setText(selectSeller.password());
            }
    }

    private void initDataBinding() {
        tcName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().name()));
        tcLastName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().lastName()));
        tcIdNumber.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().idNumber()));
        tcAddress.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().address()));
        tcUserName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().username()));
        tcPassword.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().password()));
    }
}
