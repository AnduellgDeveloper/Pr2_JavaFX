package co.edu.uniquindio.marketplace_fx.marketplace_app.viewcontroller;

import co.edu.uniquindio.marketplace_fx.marketplace_app.controller.RegisterController;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.User;
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
    void onRegisterr(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("/co/edu/uniquindio/marketplace_fx/marketplace_app/WelcomeDunima-view.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = new Stage();
            stage.setTitle("Dunima MarketPlace - Hub");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

            Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onRegister(ActionEvent event) {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showMessage("Campos Vacíos", "Por favor complete todos los campos", "", Alert.AlertType.ERROR);
            return;
        }

        User newUser = new User(username, password);
        if (registerController.registerUser(newUser)) {
            showMessage("Registro Exitoso", "Usuario registrado correctamente", "", Alert.AlertType.INFORMATION);
        } else {
            showMessage("Error de Registro", "El nombre de usuario ya está en uso", "", Alert.AlertType.ERROR);
        }
    }

    private void showMessage(String title, String message, String header, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void navigateToHub(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/marketplace_fx/marketplace_app/WelcomeDunima-view.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("Dunima MarketPlace - Hub");
            stage.setScene(scene);
            stage.show();

            Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
