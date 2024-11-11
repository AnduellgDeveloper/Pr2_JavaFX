package co.edu.uniquindio.marketplace_fx.marketplace_app.controller;

import co.edu.uniquindio.marketplace_fx.marketplace_app.factory.ModelFactory;
import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.UserDto;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.User;


public class LoginController {
    ModelFactory modelFactory;
    public LoginController(){
        modelFactory = ModelFactory.getInstance();
    }
    // Método para autenticar al usuario
    public UserDto authenticateUser(String username, String password) {
        return modelFactory.authenticate(username, password);
    }
    // Método para obtener el rol del usuario autenticado
    public String getUserRole(UserDto userDto) {
        return modelFactory.getUserRole(userDto);
    }

    public boolean registerUser(String username, String password, String name, String lastName, String idNumber, String address) {
        User newUser = User.builder()
                .username(username)
                .password(password)
                .name(name)
                .lastName(lastName)
                .idNumber(idNumber)
                .address(address)
                .build();
        return modelFactory.registerUser(newUser);
    }

    public boolean login(String username, String password) {
        return modelFactory.validateLogin(username, password);
    }

}
