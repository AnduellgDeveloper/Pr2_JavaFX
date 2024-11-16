package co.edu.uniquindio.marketplace_fx.marketplace_app.controller;

import co.edu.uniquindio.marketplace_fx.marketplace_app.factory.ModelFactory;
import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.ProductDto;

import java.util.List;
public class ProductController {
    ModelFactory modelFactory;
    public ProductController(){
        modelFactory = ModelFactory.getInstance();
    }
    public List<ProductDto> getProducts(String username) {
        return modelFactory.getProductsSeller(username);
    }
    // Eliminar un producto de la lista
    public void removeProduct(ProductDto product) {
        modelFactory.removeProduct(product);
    }
    // Actualizar un producto existente
    public void updateProduct(ProductDto updatedProduct) {
        modelFactory.updateProduct(updatedProduct);
    }
    // Agregar un producto nuevo a la lista
    public boolean addProduct(ProductDto productDto) {
        modelFactory.addProduct(productDto);
        return true;
    }

}
