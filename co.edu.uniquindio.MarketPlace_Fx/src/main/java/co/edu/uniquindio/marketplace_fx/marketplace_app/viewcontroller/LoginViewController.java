package co.edu.uniquindio.marketplace_fx.marketplace_app.viewcontroller;

import co.edu.uniquindio.marketplace_fx.marketplace_app.controller.LoginController;
import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.UserDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static co.edu.uniquindio.marketplace_fx.marketplace_app.utils.LoginConstants.*;
import static co.edu.uniquindio.marketplace_fx.marketplace_app.utils.ProductConstants.HEADER;


public class LoginViewController {
    private LoginController loginController;

    @FXML
    private Button btnBackToHub;

    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUser;

    @FXML
    void initialize() {
        loginController = new LoginController();
    }

    @FXML
    void onLogin(ActionEvent event) {
        String username = txtUser.getText();
        String password = txtPassword.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showMessage(TITULO_CAMPOS_VACIOS, BODY_CAMPOS_VACIOS, HEADER, Alert.AlertType.ERROR);
            return;
        }
        UserDto userDto = loginController.authenticateUser(username, password);
        if (userDto != null) {
            String role = loginController.getUserRole(userDto);
            switch (role) {
                case "seller":
                    navigateToSellerView(event, username);
                    break;
                case "administrator":
                    navigateToAdminView(event);
                    break;
                default:
                    showMessage(TITULO_ROL_DESCONOCIDO, BODY_ROL_DESCONOCIDO, HEADER, Alert.AlertType.ERROR);
            }
        } else {
            showMessage(TITULO_DATOS_INCONRRECTOS, BODY_DATOS_INCORRECTOS, HEADER, Alert.AlertType.ERROR);
        }
    }


    private void navigateToSellerView(ActionEvent event, String username) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/marketplace_fx/marketplace_app/MarketPlace-app.fxml"));
            Parent root = loader.load();

            // Configurar la escena y mostrarla en una nueva ventana
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Dunima MarketPlace - Vendedor");
            stage.setScene(scene);
            stage.show();

            // closeCurrentStage(event);
        } catch (IOException e) {
            e.printStackTrace();
        }

        showMessage(TITULO_INTERFAZ_VENDEDOR, BODY_BIENVENIDO_VENDEDOR, HEADER, Alert.AlertType.INFORMATION);
    }

    private void navigateToAdminView(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("/co/edu/uniquindio/marketplace_fx/marketplace_app/Administrator-view.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("Dunima MarketPlace - Administrador");
            stage.setScene(scene);
            stage.show();

//            closeCurrentStage(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
        showMessage(TITULO_INTERFAZ_ADMINISTRADOR, BODY_BIENVENIDO_ADMINISTRADOR, HEADER, Alert.AlertType.INFORMATION);
    }

    private void closeCurrentStage(ActionEvent event) {
        Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        currentStage.close();
    }


    @FXML
    void onBackToHub(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("/co/edu/uniquindio/marketplace_fx/marketplace_app/WelcomeDunima-view.fxml"));
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
    // Método que muestra un mensaje en un cuadro de diálogo
    private void showMessage(String title, String message, String header, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
