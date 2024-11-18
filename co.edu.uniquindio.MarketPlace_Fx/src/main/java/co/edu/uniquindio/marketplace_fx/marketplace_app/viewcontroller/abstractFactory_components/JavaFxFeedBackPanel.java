package co.edu.uniquindio.marketplace_fx.marketplace_app.viewcontroller.abstractFactory_components;

import co.edu.uniquindio.marketplace_fx.marketplace_app.service.service_abstractFactory.IFeedBackPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class JavaFxFeedBackPanel implements IFeedBackPanel {
    private HBox interactionBox;
    private Button likeButton;
    private Button commentButton;
    private List<String> likesList; // Lista para almacenar los likes
    private int likeCount = 0; // Contador de likes
    private String currentUser = "User"; // Nombre del usuario que da like (esto puede variar dependiendo de tu lógica)

    public JavaFxFeedBackPanel() {
        likesList = new ArrayList<>(); // Inicializamos la lista de likes

        interactionBox = new HBox(10);

        likeButton = new Button();
        commentButton = new Button();

        ImageView likeImageView = new ImageView(new Image(getClass().getResource("/co/edu/uniquindio/marketplace_fx/marketplace_app/images/LikeNegro.png").toExternalForm()));
        ImageView commentImageView = new ImageView(new Image(getClass().getResource("/co/edu/uniquindio/marketplace_fx/marketplace_app/images/Comentario.png").toExternalForm()));

        likeImageView.setFitHeight(20);
        likeImageView.setFitWidth(20);

        commentImageView.setFitHeight(20);
        commentImageView.setFitWidth(20);

        likeButton.setGraphic(likeImageView);
        commentButton.setGraphic(commentImageView);

        likeButton.getStyleClass().add("button-like");
        commentButton.getStyleClass().add("button-comment");

        interactionBox.getChildren().addAll(likeButton, commentButton);
        interactionBox.setAlignment(Pos.CENTER);

        likeButton.getStylesheets().add(getClass().getResource("/co/edu/uniquindio/marketplace_fx/marketplace_app/Css/ButtonStylePostWall.css").toExternalForm());
        commentButton.getStylesheets().add(getClass().getResource("/co/edu/uniquindio/marketplace_fx/marketplace_app/Css/ButtonStylePostWall.css").toExternalForm());


        updateLikeButtonText();
    }

    @Override
    public void setLikeAction(EventHandler<ActionEvent> action) {
        likeButton.setOnAction(event -> {
            // Si el usuario no ha dado like antes, lo añadimos
            if (!likesList.contains(currentUser)) {

                likeCount++; // Incrementar contador de likes
            }
            updateLikeButtonText();
            action.handle(event);
        });
    }

    @Override
    public void setCommentAction(EventHandler<ActionEvent> action) {
        commentButton.setOnAction(action);
    }

    @Override
    public Node getInteractionNode() {
        return interactionBox;
    }

    // Método para actualizar el texto del botón de likes
    private void updateLikeButtonText() {
        likeButton.setText("Likes: " + likeCount);
    }

    // Método para obtener la lista de likes
    public List<String> getLikesList() {
        return likesList;
    }

    // Método para actualizar el usuario actual que da like
    public void setCurrentUser(String user) {
        this.currentUser = user;
    }
}


