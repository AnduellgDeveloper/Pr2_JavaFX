package co.edu.uniquindio.marketplace_fx.marketplace_app.viewcontroller;

import co.edu.uniquindio.marketplace_fx.marketplace_app.factory.ModelFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginViewController {
    ModelFactory modelFactory;
    void initialize() {
        modelFactory = ModelFactory.getInstance();
    }
    @FXML
    private Button buttonLogin;

    @FXML
    private Button buttonRegister;

    @FXML
    private PasswordField textPassword;

    @FXML
    private TextField textUser;


}
