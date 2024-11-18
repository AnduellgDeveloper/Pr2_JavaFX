package co.edu.uniquindio.marketplace_fx.marketplace_app.service.service_decorator;

import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.ProductDto;

import java.util.List;

public interface IProductService {
    List<ProductDto> getProducts(String username);
    void removeProduct(ProductDto product);
    void updateProduct(ProductDto updatedProduct);
    boolean addProduct(ProductDto productDto);
}
