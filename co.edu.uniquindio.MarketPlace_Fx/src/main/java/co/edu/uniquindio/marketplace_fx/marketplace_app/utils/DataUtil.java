package co.edu.uniquindio.marketplace_fx.marketplace_app.utils;

import co.edu.uniquindio.marketplace_fx.marketplace_app.model.ObjectProduct;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.Product;

import java.time.LocalDate;

public class DataUtil {
    public static ObjectProduct initializeData() {
        ObjectProduct objectProduct = new ObjectProduct();

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

        return objectProduct;
    }
}
