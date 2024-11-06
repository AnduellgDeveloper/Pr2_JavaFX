package co.edu.uniquindio.marketplace_fx.marketplace_app.service;


import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.ProductDto;
import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.SellerDto;

import java.util.List;

public interface IModelFactoryService {
    List<ProductDto> getProducts();
//    boolean addProduct(ProductDto productDto);
    List<SellerDto> getSellers();
}
