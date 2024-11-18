package co.edu.uniquindio.marketplace_fx.marketplace_app.model.builder;

import co.edu.uniquindio.marketplace_fx.marketplace_app.model.Product;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.Seller;

import java.util.ArrayList;
import java.util.List;

public class SellerBuilder  extends UserBuilder<SellerBuilder> {
    private List<Product> products = new ArrayList<>();
    private List<Seller> sellers = new ArrayList<>();

    public SellerBuilder addProduct(Product product) {
        this.products.add(product);
        return this;
    }
    public SellerBuilder addSeller(Seller seller) {
        this.sellers.add(seller);
        return this;
    }
    @Override
    public Seller build() {
        Seller seller = new Seller(name, lastName, idNumber, address, username, password);
        seller.setProducts(products);
        seller.setSellerFriends(sellers);
        return seller;
    }
    @Override
    protected SellerBuilder self() {
        return this;
    }
}
