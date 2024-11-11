package co.edu.uniquindio.marketplace_fx.marketplace_app.controller;

import co.edu.uniquindio.marketplace_fx.marketplace_app.factory.ModelFactory;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.User;

public class RegisterController {
    ModelFactory modelFactory;
    public RegisterController(){
        modelFactory = ModelFactory.getInstance();
    }

    // Método para registrar un nuevo usuario
    public boolean registerUser(User newUser) {
        return modelFactory.registerUser(newUser);
    }


}

