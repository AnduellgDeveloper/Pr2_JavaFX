package co.edu.uniquindio.marketplace_fx.marketplace_app.service;

import co.edu.uniquindio.marketplace_fx.marketplace_app.model.User;


public interface ILogin {
    boolean validateLogin(String username, String password);
    User authenticate(String username, String password);
    String getUserRole(User user);

}
