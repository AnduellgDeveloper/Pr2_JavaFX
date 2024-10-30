package co.edu.uniquindio.marketplace_fx.marketplace_app.service;

import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.ProductDto;
import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.SellerDto;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.Product;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.Seller;

import java.util.List;

public interface IMarketPlaceMapping {
    List<ProductDto> getProductsDto(List<Product> listProducts);
    ProductDto productToProductDto(Product product);
    Product productDtoToProduct(ProductDto productDto);

    Product toObjectProduct(ProductDto product);

    List<SellerDto> getSellersDto(List<Seller> listSellers);
    SellerDto sellertToSellerDto(Seller seller);
    Seller sellerDtoToSeller(SellerDto sellerDto);

    Seller  toObjectSeller(SellerDto seller);
}
