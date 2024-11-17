package co.edu.uniquindio.marketplace_fx.marketplace_app.service.service_abstractFactory;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;

public interface IFeedBackPanel {
    void setLikeAction(EventHandler<ActionEvent> action);
    void setCommentAction(EventHandler<ActionEvent> action);
    Node getInteractionNode();
}
