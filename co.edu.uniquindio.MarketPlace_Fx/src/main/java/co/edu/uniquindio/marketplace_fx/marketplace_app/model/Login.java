package co.edu.uniquindio.marketplace_fx.marketplace_app.model;

public class Login {
    public boolean authenticate(User user, String username, String password) {
        return user.getUsername().equals(username) && user.getPassword().equals(password);
    }

}
