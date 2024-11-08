package co.edu.uniquindio.marketplace_fx.marketplace_app.service;

import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.AdministratorDto;
import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.ProductDto;
import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.SellerDto;
import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.UserDto;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.Administrator;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.Product;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.Seller;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.User;

import java.util.List;

public interface IMarketPlaceMapping {
    // ------------------------ Products -----------------------
    List<ProductDto> getProductsDto(List<Product> listProducts);
    ProductDto productToProductDto(Product product);
    Product productDtoToProduct(ProductDto productDto);

    Product toObjectProduct(ProductDto product);
    // ------------------------ Sellers ------------------------
    List<SellerDto> getSellersDto(List<Seller> listSellers);
    SellerDto sellertToSellerDto(Seller seller);
    Seller sellerDtoToSeller(SellerDto sellerDto);

    Seller  toObjectSeller(SellerDto seller);
    // ------------------------ Users -----------------------
    List<UserDto> getUsersDto(List<User> listUsers);
    UserDto userToUserDto(User user);
    User userDtoToUser(UserDto userDto);

    User  toObjectUser(UserDto user);

    // ------------------------ Administrator -----------------------
    List<AdministratorDto> getAdministratorsDto(List<Administrator> listAdministrators);
    AdministratorDto administratorToAdministratorDto(Administrator administrator);
    Administrator administratorDtoToAdministrator(AdministratorDto administratorDto);

    Administrator  toObjectAdministrator(AdministratorDto administrator);


}
