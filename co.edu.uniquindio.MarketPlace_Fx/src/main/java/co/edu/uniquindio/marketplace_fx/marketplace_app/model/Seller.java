package co.edu.uniquindio.marketplace_fx.marketplace_app.model;
import java.util.ArrayList;
import java.util.List;

public class Seller extends User {
    private List<Product> products;
    private List<Seller> sellers;

    public Seller(String name, String lastName, String idNumber, String address, String username, String password, List<Product> products, List<Seller> sellers) {
        super(name, lastName, idNumber, address, username, password);
        this.products = new ArrayList<>();
        this.sellers = new ArrayList<>();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Seller> getSellers() {
        return sellers;
    }

    public void setSellers(List<Seller> sellers) {
        this.sellers = sellers;
    }
}
