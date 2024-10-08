package co.edu.uniquindio.marketplace_fx.marketplace_app.utils;

import co.edu.uniquindio.marketplace_fx.marketplace_app.model.ObjectProduct;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.Product;

import java.time.LocalDate;

public class DataUtil {
    public static ObjectProduct initializeData() {
        ObjectProduct objectProduct = new ObjectProduct();

        Product product1 = Product.builder()
                .name("Producto 1")
                .image("/images/CamisaHombre.png")
                .category("Electrónica")
                .price(1500)
                .status("Published")
                .publicationDate(LocalDate.of(2023, 10, 2))
                .build();

        Product product2 = Product.builder()
                .name("Producto 2")
                .image("/images/CamisetaBlanca.png")
                .category("Hogar")
                .price(500)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 9, 15))
                .build();

        Product product3 = Product.builder()
                .name("Producto 3")
                .image("/images/ChaquetaAzul.png")
                .category("Deportes")
                .price(1200)
                .status("Published")
                .publicationDate(LocalDate.of(2023, 8, 22))
                .build();

        Product product4 = Product.builder()
                .name("Producto 4")
                .image("/images/SacoVerdeCuadros.png")
                .category("Moda")
                .price(800)
                .status("Published")
                .publicationDate(LocalDate.of(2023, 7, 5))
                .build();

        Product product5 = Product.builder()
                .name("Producto 5")
                .image("/images/SacoVerdeCuadros.png")
                .category("Juguetes")
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
