package co.edu.uniquindio.marketplace_fx.marketplace_app.factory;

import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.ProductDto;
import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.SellerDto;
import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.mappers.MarketPlaceMappingImpl;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.ObjectProduct;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.Product;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.Seller;
import co.edu.uniquindio.marketplace_fx.marketplace_app.service.IMarketPlaceMapping;
import co.edu.uniquindio.marketplace_fx.marketplace_app.service.IModelFactoryService;
import co.edu.uniquindio.marketplace_fx.marketplace_app.service.IProductCrud;
import co.edu.uniquindio.marketplace_fx.marketplace_app.utils.DataUtil;

import java.util.List;

public class ModelFactory implements IModelFactoryService, IProductCrud {
    private static ModelFactory modelFactory;
    private ObjectProduct objectProduct;
    private IMarketPlaceMapping mapper;

    // Método para obtener la instancia singleton
    public static ModelFactory getInstance() {
        if (modelFactory == null) {
            modelFactory = new ModelFactory();
        }
        return modelFactory;
    }

    private ModelFactory() {
        mapper = new MarketPlaceMappingImpl();
        objectProduct = DataUtil.initializeData();
    }

    // Obtener la lista de productos
    @Override
    public List<ProductDto> getProducts() {
        return mapper.getProductsDto(objectProduct.getListProducts());
    }

    // Añadir un nuevo producto a la lista
    public boolean addProduct(ProductDto productDto) {
        Product product = mapper.productDtoToProduct(productDto);
        return objectProduct.createProduct(product);
    }

    // Eliminar un producto de la lista
    public void removeProduct(ProductDto product) {
        objectProduct.removeProduct(mapper.toObjectProduct(product));
    }

    // Actualizar un producto existente
    public void updateProduct(ProductDto updatedProduct) {
        objectProduct.updateProduct(mapper.toObjectProduct(updatedProduct));
    }
    //Obtener lista vendedores
    public List<SellerDto> getSellers() {
        return mapper.getSellersDto(objectProduct.getListSellers());
    }
    // Añadir un nuevo vendedor a la lista
    public boolean addSeller(SellerDto sellerDto) {
        Seller seller = mapper.sellerDtoToSeller(sellerDto);
        return objectProduct.createSeller(seller);
    }
    //remover vendedor de la lista
    public void removeSeller(SellerDto seller){
        objectProduct.removeSeller(mapper.toObjectSeller(seller));
    }
    // Actualizar un vendedor existente
    public void updateSeller(SellerDto updatedSeller) {
        objectProduct.updateSeller(mapper.toObjectSeller(updatedSeller));
    }
}
