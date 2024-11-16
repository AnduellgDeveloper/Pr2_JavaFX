package co.edu.uniquindio.marketplace_fx.marketplace_app.service;

import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.ProductDto;

public interface IObserver {
    void update(ProductDto product);
    void likeAdd(ProductDto product);
    void commentAdd(ProductDto product);
}
