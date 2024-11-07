package co.edu.uniquindio.marketplace_fx.marketplace_app.viewcontroller;

import co.edu.uniquindio.marketplace_fx.marketplace_app.controller.SellerController;
import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.ProductDto;
import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.SellerDto;
import co.edu.uniquindio.marketplace_fx.marketplace_app.utils.ProductConstants;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.ResourceBundle;

import static co.edu.uniquindio.marketplace_fx.marketplace_app.utils.ProductConstants.*;
import static co.edu.uniquindio.marketplace_fx.marketplace_app.utils.ProductConstants.HEADER;
import static co.edu.uniquindio.marketplace_fx.marketplace_app.utils.SellerConstants.*;
import static co.edu.uniquindio.marketplace_fx.marketplace_app.utils.SellerConstants.TITULO_INCOMPLETO;

public class SellerViewController {
    private SellerController sellerController;
    private ObservableList<SellerDto>listSellers = FXCollections.observableArrayList();
    private SellerDto selectSeller;

        @FXML
        private ResourceBundle resources;

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
            addSeller();
        }
        @FXML
        void onClearFields(ActionEvent event) {
            clearFields();
        }
        @FXML
        void onRemoveSeller(ActionEvent event) {
            removeSeller();
        }
        @FXML
        void onUpdateSeller(ActionEvent event) {
            updateSeller();
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
        getSellers();
        tbSeller.getItems().clear();
        tbSeller.setItems(listSellers);
        listenerSelection();
    }
    private void getSellers() {
            listSellers.addAll(sellerController.getSellers());
    }
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
    // Método que agrega un nuevo vendedor a la lista y a la base de datos
    private void addSeller() {
        SellerDto sellertDto = createSellerDto();
        if (sellertDto != null && validDataSeller(sellertDto)) {
            if (isSellerDuplicate(sellertDto)) {
                showMessage(TITULO_VENDEDOR_DUPLICADO, BODY_VENDEDOR_DUPLICADO, HEADER, Alert.AlertType.WARNING);
            } else {
                if (sellerController.addSeller(sellertDto)) {
                    listSellers.add(sellertDto);
                    clearFields();
                    showMessage(TITULO_VENDEDOR_AGREGADO, BODY_VENDEDOR_AGREGADO, HEADER, Alert.AlertType.INFORMATION);
                } else {
                    showMessage(TITULO_VENDEDOR_NO_AGREGADO, BODY_VENDEDOR_NO_AGREGADO, HEADER, Alert.AlertType.ERROR);
                }
            }
        } else {
            showMessage(TITULO_INCOMPLETO, BODY_INCOMPLETO, HEADER, Alert.AlertType.WARNING);
        }
    }
    // Método que actualiza el vendedor seleccionado en la lista y en la base de datos
    private void updateSeller() {
        if (selectSeller != null) {
            SellerDto updatedSeller = createSellerDto();
            if (updatedSeller != null && validDataSeller(updatedSeller)) {
                sellerController.updateSeller(updatedSeller);
                listSellers.set(listSellers.indexOf(selectSeller), updatedSeller);
                showSellerInformation(updatedSeller);
                showMessage(TITULO_VENDEDOR_ACTUALIZADO, BODY_VENDEDOR_ACTUALIZADO, HEADER, Alert.AlertType.INFORMATION);
            } else {
                showMessage(ProductConstants.TITULO_INCOMPLETO, BODY_INCOMPLETO, HEADER, Alert.AlertType.WARNING);
            }
        } else {
            showMessage(TITULO_VENDEDOR_NO_SELECCIONADO, BODY_VENDEDOR_NO_SELECCIONADO, HEADER, Alert.AlertType.WARNING);
        }
    }
    private SellerDto createSellerDto() {
        String Name = txtName.getText();
        String lastName = txtLastName.getText();
        String idNumber = txtidNumber.getText();
        String address  = txtAddress.getText();
        String username = txtUserName.getText();
        String password = txtPassword.getText();
        if ( Name.isEmpty()|| lastName.isEmpty()||idNumber.isEmpty()||
        address.isEmpty()||username.isEmpty()||password.isEmpty()) {

        } else {
            showMessage(TITULO_VENDEDOR_AGREGADO,BODY_VENDEDOR_AGREGADO, HEADER, Alert.AlertType.ERROR);
            return new SellerDto(Name,lastName,idNumber,address,username,password);
        }

        return null;
    }
    // Método que elimina el vendedor seleccionado de la lista y de la base de datos
    private void removeSeller() {
        if (selectSeller != null) {
            sellerController.removeSeller(selectSeller);
            listSellers.remove(selectSeller);
            clearFields();
            showMessage(TITULO_VENDEDOR_REMOVIDO, BODY_VENDEDOR_REMOVIDO, HEADER, Alert.AlertType.INFORMATION);
        } else {
            showMessage(TITULO_VENDEDOR_NO_REMOVIDO, BODY_VENDEDOR_NO_REMOVIDO, HEADER, Alert.AlertType.WARNING);
    }
}
// Método que valida los datos del vendedor antes de añadirlo o actualizarlo
private boolean validDataSeller(SellerDto sellerDto) {
    return sellerDto.name() != null && !sellerDto.name().isEmpty() &&
            sellerDto.lastName() != null && !sellerDto.lastName().isEmpty() &&
            sellerDto.idNumber()  != null && ! sellerDto.idNumber().isEmpty() &&
            sellerDto.address() != null && ! sellerDto.address().isEmpty() &&
            sellerDto.username() != null &&! sellerDto.username().isEmpty() &&
            sellerDto.password() != null && ! sellerDto.password().isEmpty();
    }
    // Método que verifica si el producto ya existe en la lista
    private boolean isSellerDuplicate(SellerDto sellerDto) {
        return listSellers.stream().anyMatch(p -> p.name().equalsIgnoreCase(sellerDto.name()));
    }
    // Método que muestra un mensaje en un cuadro de diálogo
    private void showMessage(String title, String message, String header, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }
}