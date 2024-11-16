package co.edu.uniquindio.marketplace_fx.marketplace_app.service.service_components;

public interface IComponentFactory {
    IPostContainer createPostContainer();
    ISellerInfo createSellerInfo();
    IProductView createProductDisplay();
    IFeedBackPanel createInteractionPanel();
}
