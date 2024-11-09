package co.edu.uniquindio.marketplace_fx.marketplace_app.model.builder;

import co.edu.uniquindio.marketplace_fx.marketplace_app.model.User;

public abstract class UserBuilder<U extends UserBuilder<U>> {
    protected String name;
    protected String lastName;
    protected String idNumber;
    protected String address;
    protected String username;
    protected String password;

    public U name(String name) {
        this.name = name;
        return self();
    }

    public U lastName(String lastName) {
        this.lastName = lastName;
        return self();
    }

    public U idNumber(String idNumber) {
        this.idNumber = idNumber;
        return self();
    }

    public U address(String address) {
        this.address = address;
        return self();
    }

    public U username(String username) {
        this.username = username;
        return self();
    }

    public U password(String password) {
        this.password = password;
        return self();
    }

    protected abstract U self();
    public abstract User build();
}
