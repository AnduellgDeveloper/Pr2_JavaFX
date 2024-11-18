package co.edu.uniquindio.marketplace_fx.marketplace_app.viewcontroller;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.Session;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.SessionManager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.io.IOException;

public class MarketPlaceAppController {
    private MarketPlaceAppController marketPlaceAppController;
    private ProductViewController productViewController;
    private PostWallViewController postWallViewController;
    private SessionManager sessionManager = SessionManager.getInstance();
    private Session currentSession;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tabProductView;
    @FXML
    private Tab tabPostWall;

    @FXML
    public void initialize() {
        FXMLLoader loaderProduct = new FXMLLoader(getClass()
                .getResource("/co/edu/uniquindio/marketplace_fx/marketplace_app/Product-view.fxml"));
        try {
            Parent productContent = loaderProduct.load();
            productViewController = loaderProduct.getController();
            tabProductView.setContent(productContent);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al cargar Product-view.fxml");
        }

        FXMLLoader loaderPostWall = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/marketplace_fx/marketplace_app/PostWall-view.fxml"));
        try {
            Parent postWallContent = loaderPostWall.load();
            postWallViewController = loaderPostWall.getController();
            tabPostWall.setContent(postWallContent);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void setProductUsername(String username) {
        if (productViewController != null && postWallViewController != null) {
            productViewController.setUsername(username);
            postWallViewController.setUsername(username);
            showMessage(username,"Username recibido en MarketPlaceAppController: " + username,"Notificacion", Alert.AlertType.INFORMATION);
        }
    }

    // Método que muestra un mensaje en un cuadro de diálogo
    private void showMessage(String title, String message, String header, Alert.AlertType alertType) {

    }

    public void initializeWithSession(Session session) {
        this.currentSession = session;
//        setupNotificationListener();
        updateUI();
    }

    private void updateUI() {
    }

//    private void setupNotificationListener() {
//        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
//            List<String> notifications = sessionManager.getSharedData().getNotifications();
//        }));
//        timeline.setCycleCount(Timeline.INDEFINITE);
//        timeline.play();
//    }
    @FXML
    private void shareDataWithOtherSessions() {
        // Ejemplo de compartir datos
        sessionManager.shareData("message", "Hola desde sesión " + currentSession.getSessionId());
    }
}
