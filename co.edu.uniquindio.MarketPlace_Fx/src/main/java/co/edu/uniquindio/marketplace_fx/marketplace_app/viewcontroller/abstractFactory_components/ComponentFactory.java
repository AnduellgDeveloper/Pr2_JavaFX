package co.edu.uniquindio.marketplace_fx.marketplace_app.viewcontroller.abstractFactory_components;

import co.edu.uniquindio.marketplace_fx.marketplace_app.service.service_abstractFactory.*;

public class ComponentFactory implements IComponentFactory {
    @Override
    public IPostContainer createPostContainer() {
        return new JavaFxPostContainer();
    }
    @Override
    public IProductName createProductName() {
        return new JavaFxProductName();
    }
    @Override
    public IProductView createProductDisplay() {
        return new JavaFxProductView();
    }
    @Override
    public IFeedBackPanel createInteractionPanel() {
        return new JavaFxFeedBackPanel();
    }
    @Override
    public IProductDateTime createProductDateTime() {
        return new JavaFxProductDateTime();
    }
}
