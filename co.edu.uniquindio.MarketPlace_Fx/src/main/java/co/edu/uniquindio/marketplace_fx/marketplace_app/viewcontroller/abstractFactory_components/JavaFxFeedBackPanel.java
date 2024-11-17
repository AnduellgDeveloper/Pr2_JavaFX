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

public class JavaFxFeedBackPanel implements IFeedBackPanel {
    private HBox interactionBox;
    private Button likeButton;
    private Button commentButton;

    public JavaFxFeedBackPanel() {
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
    }

    @Override
    public void setLikeAction(EventHandler<ActionEvent> action) {
        likeButton.setOnAction(action);
    }

    @Override
    public void setCommentAction(EventHandler<ActionEvent> action) {
        commentButton.setOnAction(action);
    }

    @Override
    public Node getInteractionNode() {
        return interactionBox;
    }
}
