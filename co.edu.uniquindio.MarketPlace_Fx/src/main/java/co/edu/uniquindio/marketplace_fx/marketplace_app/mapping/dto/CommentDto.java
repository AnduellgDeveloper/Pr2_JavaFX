package co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto;

import co.edu.uniquindio.marketplace_fx.marketplace_app.model.Product;

import java.util.List;

public record CommentDto(
        Product product,
        List<String>comments
) {
}
