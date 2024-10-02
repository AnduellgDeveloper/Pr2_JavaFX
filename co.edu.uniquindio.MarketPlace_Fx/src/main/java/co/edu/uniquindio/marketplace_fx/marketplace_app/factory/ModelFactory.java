package co.edu.uniquindio.marketplace_fx.marketplace_app.factory;


import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.ProductDto;
import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.mappers.MarketPlaceMappingImpl;
import co.edu.uniquindio.marketplace_fx.marketplace_app.service.IModelFactoryService;
import co.edu.uniquindio.marketplace_fx.marketplace_app.utils.DataUtil;

import java.util.List;

public class ModelFactory implements IModelFactoryService {
    private static ModelFactory modelFactory;

    public static ModelFactory getInstance() {
        if(modelFactory == null) {
            modelFactory = new ModelFactory();
        }
        return modelFactory;
    }
    private ModelFactory(){
        mapper = new MarketPlaceMappingImpl();
        objectProduct = DataUtil.initializeData();
    }
    @Override
    public List<ProductDto> getProducts() {
        return mapper.getProductsDto(objectProduct.getListProducts());
    }
}
