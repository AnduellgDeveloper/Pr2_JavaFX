package co.edu.uniquindio.marketplace_fx.marketplace_app.model.builder;

import co.edu.uniquindio.marketplace_fx.marketplace_app.model.Product;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.Seller;

import java.util.ArrayList;
import java.util.List;

public class SellerBuilder {
    protected String name;
    protected String lastName;
    protected String idNumber;
    protected String address;
    protected String username;
    protected String password;
    protected List<Product> products = new ArrayList<>() ;
    protected  List<Seller> sellers = new ArrayList<>();

    public Seller build() {
        return new Seller(name,lastName,idNumber,address,username,password, new ArrayList<Product>(), new ArrayList<Seller>());
    }



    public SellerBuilder name(String name) {this.name = name;return this;}
    public SellerBuilder lastName(String lastName) {this.lastName = lastName;return this;}
    public SellerBuilder idNumber(String idNumber) {this.idNumber = idNumber;return this;}
    public SellerBuilder address(String address) {this.address = address;return this;}
    public SellerBuilder username(String username) {this.username = username;return this;}
    public SellerBuilder password(String password) {this.password = password;return this;}
    public SellerBuilder sellers(List<Seller> sellers) {this.sellers = sellers;return this;}
    public SellerBuilder products(List<Product> products) {this.products = products;return this;}


}
