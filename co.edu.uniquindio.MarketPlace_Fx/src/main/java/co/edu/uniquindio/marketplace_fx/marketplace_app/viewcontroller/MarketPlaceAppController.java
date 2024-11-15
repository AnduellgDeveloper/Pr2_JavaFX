package co.edu.uniquindio.marketplace_fx.marketplace_app.viewcontroller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.io.IOException;

public class MarketPlaceAppController {
    private MarketPlaceAppController marketPlaceAppController;
    private ProductViewController productViewController;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tabProductView;

    @FXML
    public void initialize() {
        marketPlaceAppController = new MarketPlaceAppController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/marketplace_fx/marketplace_app/Product-view.fxml"));
        try {
            Parent productContent = loader.load();
            productViewController = loader.getController();
            tabProductView.setContent(productContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setProductUsername(String username) {
        if (productViewController != null) {
            productViewController.setUsername(username);
        }
    }
}
