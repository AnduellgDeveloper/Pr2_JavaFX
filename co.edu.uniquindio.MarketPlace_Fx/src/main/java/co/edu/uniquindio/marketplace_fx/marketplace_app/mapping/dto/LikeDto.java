package co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto;

import co.edu.uniquindio.marketplace_fx.marketplace_app.model.Product;

public record LikeDto(
        Product product,
        int likes
) {
}
