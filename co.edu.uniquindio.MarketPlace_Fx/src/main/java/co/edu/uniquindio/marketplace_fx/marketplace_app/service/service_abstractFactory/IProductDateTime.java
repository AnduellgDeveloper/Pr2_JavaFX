package co.edu.uniquindio.marketplace_fx.marketplace_app.service.service_abstractFactory;

import javafx.scene.Node;

import java.time.LocalDateTime;

public interface IProductDateTime {
    void setProductDateTime(LocalDateTime productDate);
    Node getProductDateTimeNode();
}
