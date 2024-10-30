package co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.mappers;

import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.ProductDto;
import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.SellerDto;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.Product;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.Seller;
import co.edu.uniquindio.marketplace_fx.marketplace_app.service.IMarketPlaceMapping;

import java.util.ArrayList;
import java.util.List;

public class MarketPlaceMappingImpl implements IMarketPlaceMapping {

    @Override
    public List<ProductDto> getProductsDto(List<Product> listProducts) {
        if (listProducts == null) {
            return new ArrayList<>();
        }

        List<ProductDto> listProductsDto = new ArrayList<>(listProducts.size());
        for (Product product : listProducts) {
            listProductsDto.add(productToProductDto(product));
        }
        return listProductsDto;
    }
    @Override
    public ProductDto productToProductDto(Product product) {
        if (product == null) {
            return null;
        }

        return new ProductDto(
                product.getName(),
                product.getImage(),
                product.getCategory(),
                product.getPrice(),
                product.getStatus(),
                product.getPublicationDate());
    }
    @Override
    public Product productDtoToProduct(ProductDto productDto) {
        if (productDto == null) {
            return null;
        }

        return Product.builder()
                .name(productDto.name())
                .image(productDto.image())
                .category(productDto.category())
                .price(productDto.price())
                .status(productDto.status())
                .publicationDate(productDto.publicationDate())
                .build();
    }
    @Override
    public Product toObjectProduct(ProductDto product) {
        return null;
    }

    @Override
    public List<SellerDto> getSellersDto(List<Seller> listSellers) {
        if (listSellers == null) {
            return new ArrayList<>();
        }

        List<SellerDto> listSellersDto = new ArrayList<>(listSellers.size());
        for (Seller seller : listSellers) {
            listSellersDto.add(sellertToSellerDto(seller));
        }
        return listSellersDto;
    }

    @Override
    public SellerDto sellertToSellerDto(Seller seller) {
        if (seller == null) {
            return null;
        }

        return new SellerDto(
                seller.getName(),
                seller.getLastName(),
                seller.getIdNumber(),
                seller.getAddress(),
                seller.getUsername(),
                seller.getPassword(),
                seller.getProducts());

    }

    @Override
    public Seller sellerDtoToSeller(SellerDto sellerDto) {
        if (sellerDto == null) {
            return null;
        }

        return Seller.builder()
                .name(sellerDto.name())
                .lastName(sellerDto.lastName())
                .idNumber(sellerDto.idNumber())
                .address(sellerDto.address())
                .username(sellerDto.username())
                .password(sellerDto.password())
                .build();
    }

    @Override
    public Seller toObjectSeller(SellerDto seller) {
        return null;
    }
}