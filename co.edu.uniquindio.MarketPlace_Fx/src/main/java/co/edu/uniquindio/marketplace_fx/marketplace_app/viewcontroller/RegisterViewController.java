package co.edu.uniquindio.marketplace_fx.marketplace_app.viewcontroller;

import co.edu.uniquindio.marketplace_fx.marketplace_app.controller.RegisterController;
import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.UserDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static co.edu.uniquindio.marketplace_fx.marketplace_app.utils.RegisterConstants.*;

public class RegisterViewController {
    private RegisterController registerController;

    @FXML
    private Button btnRegister;

    @FXML
    private TextField txtAdress;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtIdNumber;

    @FXML
    private TextField txtLastName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    void initialize() {
        registerController = new RegisterController();
    }

    @FXML
    void navigateToHubView(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("/co/edu/uniquindio/marketplace_fx/marketplace_app/WelcomeDunima-view.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("Dunima MarketPlace - Hub");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onRegister(ActionEvent event) {
        String name = txtName.getText();
        String lastName = txtLastName.getText();
        String idnumber = txtIdNumber.getText();
        String address = txtAdress.getText();
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        if (username.isEmpty() || password.isEmpty()||name.isEmpty()
                ||lastName.isEmpty()||idnumber.isEmpty()||address.isEmpty()) {
            showMessage(TITULO_CAMPOS_VACIOS, BODY_CAMPOS_VACIOS, HEADER, Alert.AlertType.ERROR);
            return;
        }

        UserDto usernew = new UserDto(name,lastName,idnumber,address,username, password);
        if (registerController.registerNewUser(usernew)) {
            navigateToHubView(event);

            showMessage(TITULO_USUARIO_REGISTRADO, BODY_USUARIO_REGISTRADO, HEADER, Alert.AlertType.INFORMATION);
        } else {
            showMessage(TITULO_ERROR_REGISTRO, BODY_ERROR_REGISTRO, HEADER, Alert.AlertType.ERROR);
        }
    }

    private void showMessage(String title, String message, String header, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
