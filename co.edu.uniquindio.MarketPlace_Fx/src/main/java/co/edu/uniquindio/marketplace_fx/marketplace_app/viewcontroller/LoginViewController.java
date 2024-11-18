package co.edu.uniquindio.marketplace_fx.marketplace_app.viewcontroller;

import co.edu.uniquindio.marketplace_fx.marketplace_app.controller.LoginController;
import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.UserDto;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.session.Session;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.session.SessionManager;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.User;
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
import java.time.LocalDateTime;

import static co.edu.uniquindio.marketplace_fx.marketplace_app.utils.LoginConstants.*;
import static co.edu.uniquindio.marketplace_fx.marketplace_app.utils.ProductConstants.HEADER;

public class LoginViewController {
    private LoginController loginController;
    private SessionManager sessionManager = SessionManager.getInstance();

    @FXML
    private ProductViewController productViewController;
    @FXML
    private Button btnBackToHub, btnLogin;
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
            Session session = sessionManager.createSession(username);
            session.setSessionData("Ultimo inicio de sesion", LocalDateTime.now());
            String role = loginController.getUserRole(userDto);
            switch (role) {
                case "seller":
                    navigateToSellerView(event, username, session);
                    break;
                case "administrator":
                    navigateToAdminView(event, session);
                    break;
                default:
                    showMessage(TITULO_ROL_DESCONOCIDO, BODY_ROL_DESCONOCIDO, HEADER, Alert.AlertType.ERROR);
            }
        } else {
            showMessage(TITULO_DATOS_INCONRRECTOS, BODY_DATOS_INCORRECTOS, HEADER, Alert.AlertType.ERROR);
        }
    }

    private void navigateToSellerView(ActionEvent event, String username, Session session) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/marketplace_fx/marketplace_app/MarketPlace-app.fxml"));
            Parent root = loader.load();

            MarketPlaceAppController marketPlaceController = loader.getController();
            marketPlaceController.setProductUsername(username);
            marketPlaceController.initSession(session);


            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Dunima MarketPlace - Vendedor"+
                    "- Sesión de " + session.getUsername()+
                    "- ID:" + session.getSessionId());
            stage.setOnCloseRequest(eventClose -> {
                System.out.println("La ventana del vendedor se está cerrando...");
                sessionManager.closeSession(session.getSessionId());
            });
            stage.setScene(scene);
            stage.show();

//           closeCurrentStage(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void navigateToAdminView(ActionEvent event, Session session) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/marketplace_fx/marketplace_app/Administrator-view.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("Dunima MarketPlace - Administrador"+
                    "- Sesión de " + session.getUsername()+
                    "- ID:" + session.getSessionId());
            stage.setScene(scene);
            stage.show();
            showMessage(TITULO_INTERFAZ_ADMINISTRADOR, BODY_BIENVENIDO_ADMINISTRADOR, HEADER, Alert.AlertType.INFORMATION);
            closeCurrentStage(event);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    private void closeCurrentStage(ActionEvent event) {
        Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        currentStage.close();
    }

    @FXML
    void onBackToHub(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/marketplace_fx/marketplace_app/WelcomeDunima-view.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("Dunima MarketPlace - Hub");
            stage.setScene(scene);
            stage.show();

            closeCurrentStage(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to display a message in a dialog
    private void showMessage(String title, String message, String header, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
