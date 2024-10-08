package co.edu.uniquindio.marketplace_fx.marketplace_app.controller;

import co.edu.uniquindio.marketplace_fx.marketplace_app.factory.ModelFactory;

public class MarketPlaceAppController {
    ModelFactory modelFactory;
    public MarketPlaceAppController(){
        modelFactory = ModelFactory.getInstance();
    }
}
