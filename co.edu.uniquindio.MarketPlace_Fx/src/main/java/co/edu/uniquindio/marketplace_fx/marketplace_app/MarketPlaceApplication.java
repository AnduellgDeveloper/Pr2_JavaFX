package co.edu.uniquindio.marketplace_fx.marketplace_app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MarketPlaceApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MarketPlaceApplication.class.getResource("Product-app.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("MarketPlace");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    public static void main() {
        launch();
    }
}