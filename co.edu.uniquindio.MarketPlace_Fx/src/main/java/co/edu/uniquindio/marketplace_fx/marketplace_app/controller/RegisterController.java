package co.edu.uniquindio.marketplace_fx.marketplace_app.controller;

import co.edu.uniquindio.marketplace_fx.marketplace_app.factory.ModelFactory;
import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.UserDto;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.User;

public class RegisterController {
    ModelFactory modelFactory;
    public RegisterController(){
        modelFactory = ModelFactory.getInstance();
    }

    public boolean registerNewUser(UserDto userDto) {
        return modelFactory.registerUser(userDto);
    }
}

