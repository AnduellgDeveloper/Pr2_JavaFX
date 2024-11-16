package co.edu.uniquindio.marketplace_fx.marketplace_app.service;

import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.ProductDto;

import java.util.List;

public interface IObserverProduct {
    void onProductsChanged(List<ProductDto> updatedProducts);
}