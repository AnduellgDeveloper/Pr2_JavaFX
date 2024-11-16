package co.edu.uniquindio.marketplace_fx.marketplace_app.service;

import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.ProductDto;

public interface IObserver {
    void update(ProductDto product);
    void LikeAdd(ProductDto product);
    void CommentAdd(ProductDto product);
}
