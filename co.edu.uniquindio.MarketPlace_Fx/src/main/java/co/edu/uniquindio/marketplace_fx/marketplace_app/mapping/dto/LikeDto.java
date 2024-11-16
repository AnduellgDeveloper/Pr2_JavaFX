package co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto;

import co.edu.uniquindio.marketplace_fx.marketplace_app.model.Product;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.Seller;

public record LikeDto(
        Product product,
        int likes,
        Seller seller
) {
}
