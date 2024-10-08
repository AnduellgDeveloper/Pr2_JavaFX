package co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ProductDto(
        String name,
        String image,
        String category,
        int price,
        String status,
        LocalDate publicationDate
) {
}
