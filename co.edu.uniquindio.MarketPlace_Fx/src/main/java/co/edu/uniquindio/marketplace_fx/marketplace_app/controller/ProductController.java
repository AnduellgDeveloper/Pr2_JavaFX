package co.edu.uniquindio.marketplace_fx.marketplace_app.controller;
import co.edu.uniquindio.marketplace_fx.marketplace_app.factory.ModelFactory;
import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.ProductDto;

import java.util.List;

public class ProductController {
    ModelFactory modelFactory;
    public ProductController() {
        modelFactory = ModelFactory.getInstance();
    }
    public List<ProductDto> getProducts () {
        return modelFactory.getProducts();
    }

    }


















}