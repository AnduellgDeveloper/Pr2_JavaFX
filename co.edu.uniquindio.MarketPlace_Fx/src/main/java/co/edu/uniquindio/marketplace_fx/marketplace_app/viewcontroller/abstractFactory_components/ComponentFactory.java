package co.edu.uniquindio.marketplace_fx.marketplace_app.viewcontroller.abstractFactory_components;

import co.edu.uniquindio.marketplace_fx.marketplace_app.service.service_abstractFactory.*;

public class ComponentFactory implements IComponentFactory {
    @Override
    public IPostContainer createPostContainer() {
        return new JavaFxPostContainer();
    }
    @Override
    public IProductInfo createProductInfo() {
        return new JavaFxProductInfo();
    }
    @Override
    public IProductView createProductDisplay() {
        return new JavaFxProductView();
    }
    @Override
    public IFeedBackPanel createInteractionPanel() {
        return new JavaFxFeedBackPanel();
    }
}
