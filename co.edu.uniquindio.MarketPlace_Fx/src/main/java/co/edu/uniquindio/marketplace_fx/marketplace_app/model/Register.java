package co.edu.uniquindio.marketplace_fx.marketplace_app.model;

import java.util.ArrayList;
import java.util.List;

public class Register {
    private List<User> listUsers = new ArrayList<>();

    public void registerUser (User user){
        listUsers.add(user);
    }

    public boolean usernameExists(String username){
        return listUsers.stream()
                .anyMatch(u -> u
                .getUsername()
                .equals(username));
    }

    public List<User> getUsers(){
        return listUsers;
    }
}
