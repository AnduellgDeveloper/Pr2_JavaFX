package co.edu.uniquindio.marketplace_fx.marketplace_app.viewcontroller.abstractFactory_components;

import co.edu.uniquindio.marketplace_fx.marketplace_app.service.service_components.IFeedBackPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class JavaFxFeedBackPanel implements IFeedBackPanel {
    private HBox interactionBox;
    private Button likeButton;
    private Button commentButton;

    public JavaFxFeedBackPanel() {
        interactionBox = new HBox(10);
        likeButton = new Button("Like");
        commentButton = new Button("Comment");

        likeButton.getStyleClass().add("interaction-button");
        commentButton.getStyleClass().add("interaction-button");

        interactionBox.getChildren().addAll(likeButton, commentButton);
        interactionBox.setAlignment(Pos.CENTER);
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
