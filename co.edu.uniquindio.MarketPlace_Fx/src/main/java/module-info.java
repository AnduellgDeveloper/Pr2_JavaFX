module co.edu.uniquindio.marketplace_fx.marketplace_app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens co.edu.uniquindio.marketplace_fx.marketplace_app to javafx.fxml;
    exports co.edu.uniquindio.marketplace_fx.marketplace_app;
    opens co.edu.uniquindio.marketplace_fx.marketplace_app.viewcontroller;
    exports co.edu.uniquindio.marketplace_fx.marketplace_app.viewcontroller;
    opens co.edu.uniquindio.marketplace_fx.marketplace_app.controller;
    exports co.edu.uniquindio.marketplace_fx.marketplace_app.controller;
    opens co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.mappers;
    exports co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.mappers;
}