package co.edu.uniquindio.marketplace_fx.marketplace_app.service;

import co.edu.uniquindio.marketplace_fx.marketplace_app.model.Product;

public interface IProductCrud {
    boolean createProduct(Product newProduct);
    Product getProduct(String name);
    void addProduct(Product product);
    void removeProduct(Product product);
    void updateProduct(Product updatedProduct);
}
