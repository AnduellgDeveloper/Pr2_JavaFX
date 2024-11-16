package co.edu.uniquindio.marketplace_fx.marketplace_app.model.builder;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.Administrator;

public class AdministratorBuilder extends UserBuilder<AdministratorBuilder> {
    @Override
    public Administrator build() {
        return new Administrator(name, lastName, idNumber, address, username, password);
    }
    @Override
    protected AdministratorBuilder self() {
        return this;
    }
}
