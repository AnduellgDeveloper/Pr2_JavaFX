package co.edu.uniquindio.marketplace_fx.marketplace_app.model;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.builder.ProductBuilder;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.builder.SellerBuilder;

import java.util.ArrayList;
import java.util.List;

public class Seller extends User {


    public Seller(String name, String lastName, String idNumber, String address, String username, String password) {
        super(name, lastName, idNumber, address, username, password);

    }





    public static SellerBuilder builder(){
        return new SellerBuilder();
    }






}
