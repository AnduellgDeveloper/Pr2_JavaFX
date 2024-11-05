package co.edu.uniquindio.marketplace_fx.marketplace_app.utils;

import co.edu.uniquindio.marketplace_fx.marketplace_app.model.ObjectProduct;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.ObjectSeller;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.Product;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.Seller;

import java.time.LocalDate;

public class DataUtil {
    public static ObjectProduct initializeData() {
        ObjectProduct objectProduct = new ObjectProduct();


        Seller seller1 = Seller.builder()
                .name("Maria camila")
                .lastName("orozco sanchez")
                .idNumber("24567891")
                .address("parla-madrid")
                .username("camii98")
                .password("ninsoo234")
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

        objectProduct.getListProducts().add(product1);
        objectProduct.getListProducts().add(product2);
        objectProduct.getListProducts().add(product3);
        objectProduct.getListProducts().add(product4);
        objectProduct.getListProducts().add(product5);
        objectProduct.getListSellers().add(seller1);
        objectProduct.getListSellers().add(seller2);
        objectProduct.getListSellers().add(seller3);
        objectProduct.getListSellers().add(seller4);
        objectProduct.getListSellers().add(seller5);

        return objectProduct;
    }
}
