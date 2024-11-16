package co.edu.uniquindio.marketplace_fx.marketplace_app.controller;

import co.edu.uniquindio.marketplace_fx.marketplace_app.factory.ModelFactory;
import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.UserDto;
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



}
