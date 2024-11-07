package co.edu.uniquindio.marketplace_fx.marketplace_app.viewcontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterViewController {

    @FXML
    private Button btnRegister;

    @FXML
    private TextField txtAdress;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtIdNumber;

    @FXML
    private TextField txtLastName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    void onRegister(ActionEvent event) {
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

}
