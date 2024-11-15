package co.edu.uniquindio.marketplace_fx.marketplace_app.controller;

import co.edu.uniquindio.marketplace_fx.marketplace_app.factory.ModelFactory;
import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.ProductDto;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.User;

import java.util.List;



public class ProductController {
    ModelFactory modelFactory;
    private User user;
    public ProductController(){
        modelFactory = ModelFactory.getInstance();
    }


    // Método para inicializar el usuario
    public void setUser(User user) {
        this.user = user;
    }


    public List<ProductDto> getProducts(String username) {
        return modelFactory.getProductsSeller(username);
    }
    public boolean addProduct(ProductDto product) {
        // Verifica si 'product' y 'user' son válidos
        if (product != null && user != null && user.getUsername() != null) {
            modelFactory.addProductToSeller(user.getUsername(), product);
            return true;
        }
        return false;
    }

    // Eliminar un producto de la lista
    public void removeProduct(ProductDto product) {
        modelFactory.removeProduct(product);
    }

    // Actualizar un producto existente
    public void updateProduct(ProductDto updatedProduct) {
        modelFactory.updateProduct(updatedProduct);
    }
}
