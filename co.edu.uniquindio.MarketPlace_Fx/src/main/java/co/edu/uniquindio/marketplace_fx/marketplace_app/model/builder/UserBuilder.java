package co.edu.uniquindio.marketplace_fx.marketplace_app.model.builder;

import co.edu.uniquindio.marketplace_fx.marketplace_app.model.User;

public abstract class UserBuilder<T extends UserBuilder<T>> {
    protected String name;
    protected String lastName;
    protected String idNumber;
    protected String address;
    protected String username;
    protected String password;

    public T name(String name) {
        this.name = name;
        return self();
    }

    public T lastName(String lastName) {
        this.lastName = lastName;
        return self();
    }

    public T idNumber(String idNumber) {
        this.idNumber = idNumber;
        return self();
    }

    public T address(String address) {
        this.address = address;
        return self();
    }

    public T username(String username) {
        this.username = username;
        return self();
    }

    public T password(String password) {
        this.password = password;
        return self();
    }

    protected abstract T self();
    public abstract User build();
}
