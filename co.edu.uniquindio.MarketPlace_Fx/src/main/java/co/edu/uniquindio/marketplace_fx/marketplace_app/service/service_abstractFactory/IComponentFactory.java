package co.edu.uniquindio.marketplace_fx.marketplace_app.service.service_abstractFactory;

public interface IComponentFactory {
    IPostContainer createPostContainer();
    IProductName createProductName();
    IProductView createProductDisplay();
    IFeedBackPanel createInteractionPanel();
    IProductDateTime createProductDateTime();
}
