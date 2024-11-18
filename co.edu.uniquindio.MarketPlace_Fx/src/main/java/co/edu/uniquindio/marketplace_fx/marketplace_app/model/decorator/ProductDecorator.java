package co.edu.uniquindio.marketplace_fx.marketplace_app.model.decorator;

import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.ProductDto;
import co.edu.uniquindio.marketplace_fx.marketplace_app.service.IProductService;

import java.util.List;

public class ProductDecorator implements IProductService {
    protected IProductService productService;

    public ProductDecorator(IProductService productService) {
        this.productService = productService;
    }

    @Override
    public List<ProductDto> getProducts(String username) {
        return productService.getProducts(username);
    }

    @Override
    public void removeProduct(ProductDto product) {
        productService.removeProduct(product);
    }

    @Override
    public void updateProduct(ProductDto updatedProduct) {
        productService.updateProduct(updatedProduct);
    }

    @Override
    public boolean addProduct(ProductDto productDto) {
        return productService.addProduct(productDto);
    }
}
