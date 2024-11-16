package co.edu.uniquindio.marketplace_fx.marketplace_app.service;

import co.edu.uniquindio.marketplace_fx.marketplace_app.model.Seller;

public interface ISellerCrud {
    boolean createSeller(Seller newSeller);
    Seller getSeller(String name);
    void addSeller(Seller seller);
    void removeSeller(Seller seller);
    void updateSeller(Seller updatedSeller);





}
