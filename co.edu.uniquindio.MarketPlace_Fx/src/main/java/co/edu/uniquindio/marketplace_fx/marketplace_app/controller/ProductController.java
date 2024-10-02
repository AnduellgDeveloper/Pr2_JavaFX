package co.edu.uniquindio.marketplace_fx.marketplace_app.controller;
import co.edu.uniquindio.marketplace_fx.marketplace_app.factory.ModelFactory;
public class ProductController {
    ModelFactory modelFactory;
    public ProductController{
        modelFactory = ModelFactory.getInstance();
    }

    public List<SellerDto> getSellers () {
        return modelFactory.getSellers();
    }


















}