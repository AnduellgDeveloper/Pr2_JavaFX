package co.edu.uniquindio.marketplace_fx.marketplace_app.factory;

import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.ProductDto;
import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.mappers.MarketPlaceMappingImpl;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.ObjectProduct;
import co.edu.uniquindio.marketplace_fx.marketplace_app.service.IMarketPlaceMapping;
import co.edu.uniquindio.marketplace_fx.marketplace_app.service.IModelFactoryService;
import co.edu.uniquindio.marketplace_fx.marketplace_app.utils.DataUtil;

import java.util.List;

public class ModelFactory implements IModelFactoryService {
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
    public void addProduct(ProductDto product) {
        objectProduct.addProduct(mapper.toObjectProduct(product));
    }

    // Eliminar un producto de la lista
    public void removeProduct(ProductDto product) {
        objectProduct.removeProduct(mapper.toObjectProduct(product));
    }

    // Actualizar un producto existente
    public void updateProduct(ProductDto updatedProduct) {
        objectProduct.updateProduct(mapper.toObjectProduct(updatedProduct));
    }
}
