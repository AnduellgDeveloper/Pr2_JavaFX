package co.edu.uniquindio.marketplace_fx.marketplace_app.model;

import co.edu.uniquindio.marketplace_fx.marketplace_app.model.builder.AdministratorBuilder;


public class Administrator extends User {
    public Administrator(String name,
                         String lastName,
                         String idNumber,
                         String address,
                         String username,
                         String password) {
        super(name, lastName, idNumber, address, username, password);
    }
    public static AdministratorBuilder builder(){
        return new AdministratorBuilder();
    }
}



