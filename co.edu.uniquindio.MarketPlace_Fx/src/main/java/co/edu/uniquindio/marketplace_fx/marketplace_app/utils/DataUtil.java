package co.edu.uniquindio.marketplace_fx.marketplace_app.utils;

import co.edu.uniquindio.marketplace_fx.marketplace_app.model.Product;

import java.time.LocalDateTime;

public class DataUtil {
    public static ObjectProduct initializeData() {
        ObjectProduct objectProduct = new ObjectProduct();

        Product product1 = Product.builder()
                .name("Producto 1")
                .image("image1.jpg")
                .category("Electrónica")
                .price(1500)
                .status("Disponible")
                .publicationDate(LocalDateTime.of(2023, 10, 2, 14, 30))
                .build();
        Product product2 = Product.builder()
                .name("Producto 2")
                .image("image2.jpg")
                .category("Hogar")
                .price(500)
                .status("Disponible")
                .publicationDate(LocalDateTime.of(2023, 9, 15, 10, 45))
                .build();
        Product product3 = Product.builder()
                .name("Producto 3")
                .image("image3.jpg")
                .category("Deportes")
                .price(1200)
                .status("Agotado")
                .publicationDate(LocalDateTime.of(2023, 8, 22, 16, 30))
                .build();
        Product product4 = Product.builder()
                .name("Producto 4")
                .image("image4.jpg")
                .category("Moda")
                .price(800)
                .status("Disponible")
                .publicationDate(LocalDateTime.of(2023, 7, 5, 12, 0))
                .build();
        Product product5 = Product.builder()
                .name("Producto 5")
                .image("image5.jpg")
                .category("Juguetes")
                .price(300)
                .status("Disponible")
                .publicationDate(LocalDateTime.of(2023, 6, 25, 9, 15))
                .build();


        ObjectProduct.getListaProductos().add(product1);
        ObjectProduct.getListaProductos().add(product2);
        ObjectProduct.getListaProductos().add(product3);

        return prestamoObjeto;
    }

}
