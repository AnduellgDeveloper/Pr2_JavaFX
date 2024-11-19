package co.edu.uniquindio.marketplace_fx.marketplace_app.model;

import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.UserDto;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.builder.SellerBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Seller extends User {

    private List<Product> products;
    private List<Seller> sellerFriends;

    public Seller(String name,
                  String lastName,
                  String idNumber,
                  String address,
                  String username,
                  String password) {
        super(name, lastName, idNumber, address, username, password);
        this.products = new ArrayList<>();
        this.sellerFriends = new ArrayList<>();
    }

    public List<Product> getProducts() {
        return products;
    }
    public List<Seller> getSellerFriends() {
        return sellerFriends;
    }
    public static SellerBuilder builder(){
        return new SellerBuilder();
    }


    public void setProducts(List<Product> products) {
        this.products = products;
    }
    public void setSellerFriends(List<Seller> sellerFriends) {
        this.sellerFriends = sellerFriends;
    }


    public void addFriend(Seller friend) {
        if (!sellerFriends.contains(friend) && !friend.equals(this)) {
            sellerFriends.add(friend);
        }
    }

    public void removeFriend(Seller friend) {
        sellerFriends.remove(friend);
    }

    public boolean isFriendWith(Seller seller) {
        return sellerFriends.contains(seller);
    }


    public void addProduct(Product product) {
        if (!products.contains(product)) {
            products.add(product);
        }
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }


}
