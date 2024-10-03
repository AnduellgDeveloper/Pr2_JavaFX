package co.edu.uniquindio.marketplace_fx.marketplace_app.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ObjectProduct {
    List<Product> listProducts = new ArrayList<>();
    List<Seller> listSellers = new ArrayList<>();
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    private Product getProduct(String name) {
        Product product = null;
        for (Product product1: getListProducts()) {
            if(product1.getName().equalsIgnoreCase(name)){
                product = product1;
                break;
            }
        }

        return product;
    }


    public List<Product> getListProducts() {
        return listProducts;
    }
    public void setListProducts(List<Product> listProducts) {
        this.listProducts = listProducts;
    }
    public List<Seller> getListSellers() {
        return listSellers;
    }
}
