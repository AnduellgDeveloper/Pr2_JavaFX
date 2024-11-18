package co.edu.uniquindio.marketplace_fx.marketplace_app.model.decorator;

import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.ProductDto;
import co.edu.uniquindio.marketplace_fx.marketplace_app.service.service_decorator.IProductService;

public class LoginDecorator extends ProductDecorator {

    public LoginDecorator(IProductService productService) {
        super(productService);
    }
    @Override
    public boolean addProduct(ProductDto productDto) {
        return super.addProduct(productDto);
    }
    @Override
    public void removeProduct(ProductDto product) {;
        super.removeProduct(product);
    }
}
