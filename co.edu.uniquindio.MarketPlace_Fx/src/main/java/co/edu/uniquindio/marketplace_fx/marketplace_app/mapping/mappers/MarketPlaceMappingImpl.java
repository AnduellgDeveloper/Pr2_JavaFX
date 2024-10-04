package co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.mappers;

import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.ProductDto;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.Product;
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
}
