package co.edu.uniquindio.marketplace_fx.marketplace_app.model.decorator;

import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.ProductDto;
import co.edu.uniquindio.marketplace_fx.marketplace_app.service.service_decorator.IProductService;

public class ValidationDecorator extends ProductDecorator {
    public ValidationDecorator(IProductService productService) {
        super(productService);
    }
    @Override
    public boolean addProduct(ProductDto productDto) {
        if (productDto.price() <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        }
        return super.addProduct(productDto);
    }
    @Override
    public void updateProduct(ProductDto updatedProduct) {
        if (updatedProduct.name().isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        super.updateProduct(updatedProduct);
    }
}
