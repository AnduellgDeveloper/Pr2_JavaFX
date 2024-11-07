package co.edu.uniquindio.marketplace_fx.marketplace_app.viewcontroller;

import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.SellerDto;
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

import static co.edu.uniquindio.marketplace_fx.marketplace_app.utils.ProductConstants.*;

public class LoginViewController {
    private final AuthService authService = new AuthService();

    @FXML
    private Button btnBackToHub;

    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUser;

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

    @FXML
    void onLogin(ActionEvent event) {
        String username = txtUser.getText();
        String password = txtPassword.getText();
        if () {
            showMessage(TITULO_ERROR_FECHA, BODY_FECHA_INVALIDA, HEADER, Alert.AlertType.ERROR);
            try {
                FXMLLoader loader = new FXMLLoader(getClass()
                        .getResource("/co/edu/uniquindio/marketplace_fx/marketplace_app/MarketPlace-app.fxml"));
                Scene scene = new Scene(loader.load());
                Stage stage = new Stage();
                stage.setTitle("Dunima MarketPlace");
                stage.setScene(scene);
                stage.show();

                Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                currentStage.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showMessage(TITULO_ERROR_FECHA, BODY_FECHA_INVALIDA, HEADER, Alert.AlertType.ERROR);
        }

    }

    @FXML
    void initialize() {
        assert txtPassword != null : "fx:id=\"txtPassword\" was not injected: check your FXML file 'Login-view.fxml'.";
        assert btnLogin != null : "fx:id=\"btnLogin\" was not injected: check your FXML file 'Login-view.fxml'.";
        assert txtUser != null : "fx:id=\"txtUser\" was not injected: check your FXML file 'Login-view.fxml'.";
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
