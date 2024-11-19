package co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto;

import co.edu.uniquindio.marketplace_fx.marketplace_app.model.Seller;

import java.util.List;


public record SellerDto(
        String name,
        String lastName,
        String idNumber,
        String address,
        String username,
        String password,
        List<Seller> friends
){}
