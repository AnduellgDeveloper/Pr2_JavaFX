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
        Seller seller6 = Seller.builder()
                .name("sofia")
                .lastName("Ortiz")
                .idNumber("901234")
                .address("Calarca-QUINDIO")
                .username("obsof")
                .password("1234y")
                .build();
        Seller seller7 = Seller.builder()
                .name("Ana")
                .lastName("lopez")
                .idNumber("12345678")
                .address("Berlin-Armenia")
                .username("anital")
                .password("156789")
                .build();
        Seller seller8 = Seller.builder()
                .name("Abigail")
                .lastName("Cardona")
                .idNumber("89012378")
                .address("Pereira-Risaralda")
                .username("BilCardona")
                .password("abigailc")
                .build();
        Seller seller9 = Seller.builder()
                .name("Ana")
                .lastName("Duque")
                .idNumber("2367891")
                .address("Supia-Caldas")
                .username("DuqueAna")
                .password("25214ana")
                .build();
        Seller seller10 = Seller.builder()
                .name("Mathias")
                .lastName("Cardona")
                .idNumber("908732")
                .address("Virginia-Risaralda")
                .username("matisc")
                .password("1234")
                .build();
//------------MEN----------------------------
        Product men1 = Product.builder()
                .name("Camisa Hombre")
                .image("CamisaHombre.png")
                .category("Ropa - Hombre")
                .price(1500)
                .status("Published")
                .publicationDate(LocalDate.of(2023, 10, 2))
                .build();

        Product men2 = Product.builder()
                .name("Camiseta Blanca")
                .image("CamisetaBlanca.png")
                .category("Ropa - Hombre")
                .price(500)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 9, 15))
                .build();

        Product men3 = Product.builder()
                .name("Chaqueta Azul")
                .image("ChaquetaAzul.png")
                .category("Ropa - Hombre")
                .price(1200)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 8, 22))
                .build();

        Product men4 = Product.builder()
                .name("Saco Verde a Cuadros")
                .image("SacoVerdeCuadros.png")
                .category("Ropa - Hombre")
                .price(800)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 9, 5))
                .build();
        Product men5 = Product.builder()
                .name("Camiseta Negra Oversize")
                .image("CamisetaNegra.jpg")
                .category("Ropa - Hombre")
                .price(1000)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 1, 9))
                .build();
        Product men6 = Product.builder()
                .name("Chaqueta Impermeable ")
                .image("ChaquetaAmarilla.jpg")
                .category("Ropa - Hombre")
                .price(450)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 4, 6))
                .build();

//----------------WOMEN--------------------------------------------------
        Product women1 = Product.builder()
                .name("Vestido Mujer")
                .image("VestidoMujer.png")
                .category("Ropa - Mujer")
                .price(300)
                .status("Published")
                .publicationDate(LocalDate.of(2023, 6, 12))
                .build();
        Product women2 = Product.builder()
                .name("Jean Cargo")
                .image("Jean Camuflado.jpg")
                .category("Ropa - Mujer")
                .price(250)
                .status("Published")
                .publicationDate(LocalDate.of(2023, 6, 30))
                .build();
        Product women3 = Product.builder()
                .name("Pantalon Bota Campana")
                .image("PantalonBotaCampana.jpg")
                .category("Ropa - Mujer")
                .price(450)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 10, 25))
                .build();
        Product women4 = Product.builder()
                .name("Pantalon tendencia estilo Periodico")
                .image("PantalonPeriodico.jpg")
                .category("Ropa - Mujer")
                .price(150)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 4, 2))
                .build();
        Product women5 = Product.builder()
                .name("Jean Cargo Azul Oscuro")
                .image("JeanCargoOscuro.jpg")
                .category("Ropa - Mujer")
                .price(200)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 11, 1))
                .build();
        Product women6 = Product.builder()
                .name("Jean Cargo Azul Claro")
                .image("JeanCargoClaro.jpg")
                .category("Ropa - Mujer")
                .price(220)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 7, 21))
                .build();
        //---------------------------GIRLS------------------
        Product girl1 = Product.builder()
                .name("Jean Niña Muñecos Disney")
                .image("Jean Niña.jpg")
                .category("Ropa - Niña")
                .price(350)
                .status("Published")
                .publicationDate(LocalDate.of(2023, 6, 20))
                .build();
        Product girl2 = Product.builder()
                .name("Blusa Blanca Niña")
                .image("BlusaBlanca.jpg")
                .category("Ropa - Niña")
                .price(100)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 11, 12))
                .build();
        Product girl3 = Product.builder()
                .name("Blusa Niña Moño")
                .image("BlusaMoño.jpg")
                .category("Ropa - Niña")
                .price(110)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 7, 25))
                .build();
        Product girl4 = Product.builder()
                .name("Blusa Niña Estampada")
                .image("BlusaFlores.jpg")
                .category("Ropa - Niña")
                .price(200)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 8, 25))
                .build();
        Product girl5 = Product.builder()
                .name("Camisa Cafe Niña")
                .image("CamisaCafe.jpg")
                .category("Ropa - Niña")
                .price(123)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 6, 25))
                .build();
        Product girl6 = Product.builder()
                .name("Vestido Niña Rosa")
                .image("VestidoRosa.jpg")
                .category("Ropa - Mujer")
                .price(500)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 1, 25))
                .build();
        //----------------BOYS---------------------------
        Product boy1 = Product.builder()
                .name("Camiseta Superhoreo Niño")
                .image("CamisetaHeroe.jpg")
                .category("Ropa - Niñp")
                .price(120)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 2, 2))
                .build();
        Product boy2 = Product.builder()
                .name("Camiseta Niño Nasa")
                .image("CamisetaNasa.jpg")
                .category("Ropa - Niño")
                .price(128)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 4, 12))
                .build();
        Product boy3 = Product.builder()
                .name("Camiseta Niño Dinosaurio")
                .image("CamisetaDino.jpg")
                .category("Ropa - Niño")
                .price(130)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 6, 31))
                .build();
        Product boy4 = Product.builder()
                .name("Buzo Marvel Niño")
                .image("BuzoMarvel.jpg")
                .category("Ropa - Niño")
                .price(300)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 9, 9))
                .build();
        Product boy5 = Product.builder()
                .name("Conjunto Dinosaurio Niño")
                .image("ConjuntoDino.jpg")
                .category("Ropa - Niño")
                .price(500)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 1, 25))
                .build();
        Product boy6 = Product.builder()
                .name("Jean Niño")
                .image("JeanNiño.jpg")
                .category("Ropa - Niño")
                .price(130)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 3, 2))
                .build();
        //---------------Makeup---------------------------------------------
        Product makeup1 = Product.builder()
                .name("Iluminador ")
                .image("Iluminador.jpg")
                .category("Maquillaje")
                .price(100)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 4, 2))
                .build();
        Product makeup2 = Product.builder()
                .name("Lapiz Labios Kylie Cosmetics")
                .image("LaízLabios.jpg")
                .category("Ropa - Niño")
                .price(430)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 6, 12))
                .build();
        Product makeup3 = Product.builder()
                .name("Jean Niño")
                .image("JeanNiño.jpg")
                .category("Ropa - Niño")
                .price(130)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 3, 2))
                .build();
        Product makeup4 = Product.builder()
                .name("Jean Niño")
                .image("JeanNiño.jpg")
                .category("Ropa - Niño")
                .price(130)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 3, 2))
                .build();
        Product makeup5 = Product.builder()
                .name("Jean Niño")
                .image("JeanNiño.jpg")
                .category("Ropa - Niño")
                .price(130)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 3, 2))
                .build();
        Product makeup6 = Product.builder()
                .name("Jean Niño")
                .image("JeanNiño.jpg")
                .category("Ropa - Niño")
                .price(130)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 3, 2))
                .build();




        marketplace.addSeller(seller1);
        marketplace.addSeller(seller2);
        marketplace.addSeller(seller3);
        marketplace.addSeller(seller4);
        marketplace.addSeller(seller5);
        marketplace.addSeller(seller6);
        marketplace.addSeller(seller7);
        marketplace.addSeller(seller8);
        marketplace.addSeller(seller9);
        marketplace.addSeller(seller10);

        marketplace.addProductToSeller(seller1, men1);
        marketplace.addProductToSeller(seller1, men2);
        marketplace.addProductToSeller(seller1, men3);
        marketplace.addProductToSeller(seller1, men4);
        marketplace.addProductToSeller(seller1, men5);
        marketplace.addProductToSeller(seller1, men6);
        marketplace.addProductToSeller(seller2,women1 );
        marketplace.addProductToSeller(seller2,women2 );
        marketplace.addProductToSeller(seller2,women3 );
        marketplace.addProductToSeller(seller2,women4 );
        marketplace.addProductToSeller(seller2,women5);
        marketplace.addProductToSeller(seller2,women6 );




        marketplace.getListAdministrators().add(admin1);

        marketplace.getListSellers().add(seller1);
        marketplace.getListSellers().add(seller2);
        marketplace.getListSellers().add(seller3);
        marketplace.getListSellers().add(seller4);
        marketplace.getListSellers().add(seller5);
        marketplace.getListSellers().add(seller6);
        marketplace.getListSellers().add(seller7);
        marketplace.getListSellers().add(seller8);
        marketplace.getListSellers().add(seller9);
        marketplace.getListSellers().add(seller10);

        // Usuarios Registrados
        marketplace.getListRegisterUser().add(admin1);

        marketplace.getListRegisterUser().add(seller1);
        marketplace.getListRegisterUser().add(seller2);
        marketplace.getListRegisterUser().add(seller3);
        marketplace.getListRegisterUser().add(seller4);
        marketplace.getListRegisterUser().add(seller5);
        marketplace.getListRegisterUser().add(seller6);
        marketplace.getListRegisterUser().add(seller7);
        marketplace.getListRegisterUser().add(seller8);
        marketplace.getListRegisterUser().add(seller9);
        marketplace.getListRegisterUser().add(seller10);

        return marketplace;
    }
}