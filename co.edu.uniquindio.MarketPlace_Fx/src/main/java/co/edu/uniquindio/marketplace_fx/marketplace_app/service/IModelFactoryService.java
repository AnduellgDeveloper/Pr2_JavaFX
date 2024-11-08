package co.edu.uniquindio.marketplace_fx.marketplace_app.service;


import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.AdministratorDto;
import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.ProductDto;
import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.SellerDto;
import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.UserDto;

import java.util.List;

public interface IModelFactoryService {
    List<ProductDto> getProducts();
//    boolean addProduct(ProductDto productDto);
    List<SellerDto> getSellers();
    List<UserDto> getUsers();
    List<AdministratorDto> getAdministrators();
}
