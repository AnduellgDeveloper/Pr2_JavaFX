package co.edu.uniquindio.marketplace_fx.marketplace_app.utils;

import co.edu.uniquindio.marketplace_fx.marketplace_app.model.*;

import java.time.LocalDate;

public class DataUtil {
    public static Marketplace initializeData() {
        Marketplace marketplace = new Marketplace();

        Administrator admin1 = Administrator.builder()
                .name("Admin")
                .lastName("Principal")
                .idNumber("01")
                .address("Central Office")
                .username("AnduellAdmin")
                .password("123")
                .build();
        Seller seller1 = Seller.builder()
                .name("Duvan Felipe")
                .lastName("Palomares Cerquera")
                .idNumber("1077722000")
                .address("El prado")
                .username("AnduellSeller")
                .password("123")
                .build();
        Seller seller2 = Seller.builder()
                .name("Andres felipe")
                .lastName("soto")
                .idNumber("89011278")
                .address("circasi-Quindio")
                .username("andresf989")
                .password("nnnhdui213")
                .build();
        Seller seller3 = Seller.builder()
                .name("Ana Maria")
                .lastName("Gomez")
                .idNumber("8901134")
                .address("Armenia-Quindio")
                .username("cjknijdi78")
                .password("8008731n")
                .build();
        Seller seller4 = Seller.builder()
                .name("Fernanda")
                .lastName("Moreno")
                .idNumber("1890264")
                .address("calarca-Quindio")
                .username("fer228")
                .password("90jos234")
                .build();
        Seller seller5 = Seller.builder()
                .name("Jair")
                .lastName("Andica")
                .idNumber("0097123")
                .address("Madrid-Cundinamarca")
                .username("jarinos")
                .password("nmnojub78")
                .build();

        Product product1 = Product.builder()
                .name("Camisa Hombre")
                .image("CamisaHombre.png")
                .category("Ropa - Hombre")
                .price(1500)
                .status("Published")
                .publicationDate(LocalDate.of(2023, 10, 2))
                .build();

        Product product2 = Product.builder()
                .name("Camiseta Blanca")
                .image("CamisetaBlanca.png")
                .category("Ropa - Hombre")
                .price(500)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 9, 15))
                .build();

        Product product3 = Product.builder()
                .name("Chaqueta Azul")
                .image("ChaquetaAzul.png")
                .category("Ropa - Hombre")
                .price(1200)
                .status("Published")
                .publicationDate(LocalDate.of(2023, 8, 22))
                .build();

        Product product4 = Product.builder()
                .name("Saco Verde a Cuadros")
                .image("SacoVerdeCuadros.png")
                .category("Ropa - Hombre")
                .price(800)
                .status("Published")
                .publicationDate(LocalDate.of(2023, 7, 5))
                .build();

        Product product5 = Product.builder()
                .name("Vestido Mujer")
                .image("VestidoMujer.png")
                .category("Ropa - Mujer")
                .price(300)
                .status("Published")
                .publicationDate(LocalDate.of(2023, 6, 25))
                .build();

        marketplace.addSeller(seller1);
        marketplace.addSeller(seller2);
        marketplace.addSeller(seller3);
        marketplace.addSeller(seller4);
        marketplace.addSeller(seller5);

        marketplace.addProductToSeller(seller1, product1);
        marketplace.addProductToSeller(seller1, product2);
        marketplace.addProductToSeller(seller1, product3);
        marketplace.addProductToSeller(seller1, product4);
        marketplace.addProductToSeller(seller1, product5);

        marketplace.getListAdministrators().add(admin1);

        marketplace.getListSellers().add(seller1);
        marketplace.getListSellers().add(seller2);
        marketplace.getListSellers().add(seller3);
        marketplace.getListSellers().add(seller4);
        marketplace.getListSellers().add(seller5);
        // Usuarios Registrados
        marketplace.getListRegisterUser().add(admin1);

        marketplace.getListRegisterUser().add(seller1);
        marketplace.getListRegisterUser().add(seller2);
        marketplace.getListRegisterUser().add(seller3);
        marketplace.getListRegisterUser().add(seller4);
        marketplace.getListRegisterUser().add(seller5);

        return marketplace;
    }
}