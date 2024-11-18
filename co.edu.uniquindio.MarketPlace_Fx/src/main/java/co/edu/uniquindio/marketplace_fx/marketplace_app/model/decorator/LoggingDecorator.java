package co.edu.uniquindio.marketplace_fx.marketplace_app.model.decorator;

import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.ProductDto;
import co.edu.uniquindio.marketplace_fx.marketplace_app.service.IProductService;

public class LoggingDecorator extends ProductDecorator {

    public LoggingDecorator(IProductService productService) {
        super(productService);
    }

    @Override
    public boolean addProduct(ProductDto productDto) {
        System.out.println("Añadiendo producto: " + productDto.name());
        return super.addProduct(productDto);
    }

    @Override
    public void removeProduct(ProductDto product) {
        System.out.println("Eliminando producto: " + product.name());
        super.removeProduct(product);
    }
}