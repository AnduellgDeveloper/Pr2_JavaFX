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
        Seller seller = new Seller(name, lastName, idNumber, address, username, password);
        seller.setProducts(products);
        seller.setSellers(sellers);
        return seller;
    }

    public SellerBuilder name(String name) {this.name = name;return this;}
    public SellerBuilder lastName(String lastName) {this.lastName = lastName;return this;}
    public SellerBuilder idNumber(String idNumber) {this.idNumber = idNumber;return this;}
    public SellerBuilder address(String address) {this.address = address;return this;}
    public SellerBuilder username(String username) {this.username = username;return this;}
    public SellerBuilder password(String password) {this.password = password;return this;}

    public SellerBuilder addProduct(Product product) {
        this.products.add(product);
        return this;
    }
    public SellerBuilder addSeller(Seller seller) {
        this.sellers.add(seller);
        return this;
    }



}
