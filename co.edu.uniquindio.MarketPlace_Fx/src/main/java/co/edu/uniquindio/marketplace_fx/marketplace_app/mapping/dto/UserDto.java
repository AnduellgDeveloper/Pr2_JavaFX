package co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto;

public record UserDto(
        String name,
        String lastName,
        String idNumber,
        String address,
        String username,
        String password
) {
}