package co.edu.uniquindio.marketplace_fx.marketplace_app.model;

import co.edu.uniquindio.marketplace_fx.marketplace_app.model.builder.SellerBuilder;

import java.util.ArrayList;
import java.util.List;

public class Seller extends User {
    private List<Product> products;
    private List<Seller> sellers = new ArrayList<>();

    public Seller(String name,
                  String lastName,
                  String idNumber,
                  String address,
                  String username,
                  String password) {
        super(name, lastName, idNumber, address, username, password);
        this.products = new ArrayList<>();
        this.sellers = new ArrayList<>();
    }

    public List<Product> getProducts() {
        return products;
    }
    public void addProduct(Product product) {
        this.products.add(product);
    }
    public static SellerBuilder builder(){
        return new SellerBuilder();
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setSellers(List<Seller> sellers) {
        this.sellers = sellers;
    }
}
