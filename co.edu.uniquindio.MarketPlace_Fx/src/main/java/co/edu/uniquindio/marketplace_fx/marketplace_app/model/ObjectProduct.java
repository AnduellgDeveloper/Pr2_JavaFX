package co.edu.uniquindio.marketplace_fx.marketplace_app.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ObjectProduct {
    List<Product> listProducts = new ArrayList<>();
    List<Seller> listSellers = new ArrayList<>();

    public void setListProducts(List<Product> listProducts) {
        this.listProducts = listProducts;
    }

    public void setListSellers(List<Seller> listSellers) {
        this.listSellers = listSellers;
    }

    public ObjectProduct() {
    }
    private Product getBuildProduct(String name, String image, String category, int price, String status, LocalDateTime publicationDate) {
        return Product.builder()
                .name(name)
                .image(image)
                .category(category)
                .price(price)
                .status(status)
                .publicationDate(publicationDate)
                .build();
    }


    public List<Product> getListProducts() {
        return listProducts;
    }
    public List<Seller> getListSellers() {
        return listSellers;
    }
}
