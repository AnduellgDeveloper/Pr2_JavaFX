package co.edu.uniquindio.marketplace_fx.marketplace_app.viewcontroller;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.session.Session;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.session.SessionManager;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MarketPlaceAppController {
    private MarketPlaceAppController marketPlaceAppController;
    private ProductViewController productViewController;
    private PostWallViewController postWallViewController;
    private SessionManager sessionManager = SessionManager.getInstance();
    private Session currentSession;
    @FXML
    private ListView<String> activeUsersListView;
    @FXML
    private TabPane tabPane;
    @FXML
    private TextArea sharedNotesArea;
    @FXML
    private Tab tabProductView;
    @FXML
    private Tab tabPostWall;
    @FXML
    private AnchorPane tabMessages;
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
        setupNotifications();

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
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public void initSession(Session session) {
        this.currentSession = session;
        tabProductView.setText("Productos de " + session.getUsername());

        String sharedNotes = (String) sessionManager.getSharedData().get("sharedNotes");
        if (sharedNotes != null) {
            sharedNotesArea.setText(sharedNotes);
        }
        sharedNotesArea.textProperty().addListener((obs, oldText, newText) -> {
            sessionManager.getSharedData().put("sharedNotes", newText);
        });
        updateActiveUsersList();
    }
    private void updateActiveUsersList() {
        Platform.runLater(() -> {
            activeUsersListView.getItems().clear();
            sessionManager.getActiveSessions().forEach(session ->
                    activeUsersListView.getItems().add(session.getUsername())
            );
        });
    }
    @FXML
    private void testNotification() {
        showNotification("¡Notificación de prueba!");
    }
    @FXML
    private void debugSharedFile() {
        String sharedFile = (String) sessionManager.getSharedData().get("sharedFile");
        System.out.println("Archivo compartido: " + sharedFile);
    }


    private void setupNotifications() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            List<String> notifications = (List<String>) sessionManager.getSharedData()
                    .getOrDefault("notifications", new ArrayList<String>());

            for (String notification : notifications) {
                showNotification(notification);
            }

            // Limpiar notificaciones después de mostrarlas
            notifications.clear();
            sessionManager.getSharedData().put("notifications", notifications);
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private Stage getStage() {
        return (Stage) tabMessages.getScene().getWindow();
    }

    private void showNotification(String message) {
        Platform.runLater(() -> {
            Tooltip tooltip = new Tooltip(message);
            tooltip.show(getStage());

            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(e -> tooltip.hide());
            delay.play();
        });
    }
    @FXML
    private void shareFile() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            sessionManager.getSharedData().put("sharedFile", file.getName());
            notifyFileshare();
        }
    }

    private void notifyFileshare() {
        String notification = currentSession.getUsername() + " ha compartido un archivo";
        List<String> notifications = (List<String>) sessionManager.getSharedData()
                .getOrDefault("notifications", new ArrayList<String>());
        notifications.add(notification);
        sessionManager.getSharedData().put("notifications", notifications);
    }
}
