package co.edu.uniquindio.marketplace_fx.marketplace_app.controller;

import co.edu.uniquindio.marketplace_fx.marketplace_app.factory.ModelFactory;
import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.ProductDto;

import java.util.List;

public class ProductController {

    private final ModelFactory modelFactory;

    public ProductController() {
        // Obtenemos la instancia de ModelFactory
        this.modelFactory = ModelFactory.getInstance();
    }

    // Retorna una lista de todos los productos
    public List<ProductDto> getProducts() {
        return modelFactory.getProducts();
    }

    // Añadir un nuevo producto a la lista
    public void addProduct(ProductDto product) {
        modelFactory.addProduct(product);
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
