package co.edu.uniquindio.marketplace_fx.marketplace_app.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ObjectProduct {
    private List<Product> listProducts = new ArrayList<>();
    private List<Seller> listSellers = new ArrayList<>();
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

    // Método privado para construir un producto
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

    // Método para buscar un producto por nombre
    private Product getProduct(String name) {
        for (Product product : getListProducts()) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null; // Retorna null si no se encuentra el producto
    }

    // Método para obtener la lista de productos
    public List<Product> getListProducts() {
        return listProducts;
    }

    // Método para establecer la lista de productos
    public void setListProducts(List<Product> listProducts) {
        this.listProducts = listProducts;
    }

    // Método para obtener la lista de vendedores
    public List<Seller> getListSellers() {
        return listSellers;
    }

    // Método para agregar un producto a la lista
    public void addProduct(Product product) {
        if (product != null) {
            listProducts.add(product);
        }
    }

    // Método para eliminar un producto de la lista
    public void removeProduct(Product product) {
        if (product != null) {
            listProducts.remove(product);
        }
    }

    // Método para actualizar un producto existente
    public void updateProduct(Product updatedProduct) {
        if (updatedProduct != null) {
            Product existingProduct = getProduct(updatedProduct.getName());
            if (existingProduct != null) {
                // Actualiza los atributos del producto existente
                existingProduct.setImage(updatedProduct.getImage());
                existingProduct.setCategory(updatedProduct.getCategory());
                existingProduct.setPrice(updatedProduct.getPrice());
                existingProduct.setStatus(updatedProduct.getStatus());
                existingProduct.setPublicationDate(updatedProduct.getPublicationDate());
            }
        }
    }
}
