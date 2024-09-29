module co.edu.uniquindio.marketplace_fx.marketplace_app {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.marketplace_fx.marketplace_app to javafx.fxml;
    exports co.edu.uniquindio.marketplace_fx.marketplace_app;
    opens co.edu.uniquindio.marketplace_fx.marketplace_app.controller;
    exports co.edu.uniquindio.marketplace_fx.marketplace_app.controller;
}