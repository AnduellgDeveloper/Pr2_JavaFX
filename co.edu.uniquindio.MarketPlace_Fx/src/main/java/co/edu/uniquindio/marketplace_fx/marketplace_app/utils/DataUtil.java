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
                .name("Ana Pepa")
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
                .password("1234")
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
                .image("chaquetaAmarilla.jpg")
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
                .image("JeanCamuflado.jpg")
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
                .publicationDate(LocalDate.of(2024, 6, 20))
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
                .image("LapizLabios.jpg")
                .category("Maquillaje")
                .price(430)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 10, 2))
                .build();
        Product makeup3 = Product.builder()
                .name("Base Dior Tono Claro")
                .image("Base.jpg")
                .category("Maquillaje")
                .price(130)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 3, 2))
                .build();
        Product makeup4 = Product.builder()
                .name("Bronzer trendy")
                .image("Bronzer.jpg")
                .category("Maquillaje")
                .price(130)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 5, 2))
                .build();
        Product makeup5 = Product.builder()
                .name("Iluminador Dior")
                .image("IluminadorDior.jpg")
                .category("Maquillaje")
                .price(130)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 10, 8))
                .build();
        Product makeup6 = Product.builder()
                .name("Paleta Sombras Dior")
                .image("PaletaSombras.jpg")
                .category("Maquillaje")
                .price(130)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 3, 2))
                .build();
        //_____-------------------------------Technology------------------------
        Product tech1 = Product.builder()
                .name("Televisor Samsung Pantalla Plana")
                .image("Televisor1.jpg")
                .category("Tecnologia")
                .price(5000)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 10, 2))
                .build();
        Product tech2 = Product.builder()
                .name("Televisor OLED ")
                .image("Televisor2.jpg")
                .category("Maquillaje")
                .price(130)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 3, 2))
                .build();
        Product tech3 = Product.builder()
                .name("Computador Gamer")
                .image("Computador.jpg")
                .category("Tecnologia")
                .price(4500)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 12, 3))
                .build();
        Product tech4 = Product.builder()
                .name("Computador hp Intel Celeron")
                .image("Computador2.jpg")
                .category("Tecnologia")
                .price(2300)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 4, 14))
                .build();
        Product tech5 = Product.builder()
                .name("Laptob Mac Chip M3")
                .image("Mac.jpg")
                .category("Tecnologia")
                .price(7800)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 2, 19))
                .build();
        Product tech6 = Product.builder()
                .name("Torre y Teclado Dell")
                .image("Dell.jpg")
                .category("Tecnologia")
                .price(5600)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 1, 23))
                .build();

//--------------------MOBILE DEVICE-------------------------------
        Product mobile1 = Product.builder()
                .name("Iphone 15 Pro Max Titanium")
                .image("15ProMax.jpg")
                .category("Smartphones")
                .price(10000)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 8, 21))
                .build();
        Product mobile2 = Product.builder()
                .name("Iphone 15 Pro Max Azul")
                .image("15ProMaxA.jpg")
                .category("Smartphones")
                .price(10000)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 9, 15))
                .build();
        Product mobile3 = Product.builder()
                .name("Samsung S24 Ultra")
                .image("S24.jpg")
                .category("Smartphones")
                .price(6000)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 1, 12))
                .build();
        Product mobile4 = Product.builder()
                .name("Xiaomi 14 ")
                .image("Xiaomi.jpg")
                .category("Smartphones")
                .price(4000)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 9, 19))
                .build();
        Product mobile5 = Product.builder()
                .name("Ipad Chip M4")
                .image("Ipad.jpg")
                .category("Smartphones")
                .price(9000)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 11, 14))
                .build();
        Product mobile6 = Product.builder()
                .name("Lenovo Tab")
                .image("Lenovo.jpg")
                .category("Smartphones")
                .price(2000)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 9, 23))
                .build();
//----------------------MOTORCYCLE-----------------------------------

        Product motorcycle1 = Product.builder()
                .name("Nmax 2024")
                .image("Nmax.jpg")
                .category("Motocicletas")
                .price(12000)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 12, 23))
                .build();
        Product motorcycle2 = Product.builder()
                .name("Mt09 2024")
                .image("Mt09.jpg")
                .category("Motocicletas")
                .price(20000)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 3, 4))
                .build();
        Product motorcycle3 = Product.builder()
                .name("Cuatrimoto Raptor")
                .image("4.jpg")
                .category("Motocicletas")
                .price(18000)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 11, 10))
                .build();
        Product motorcycle4 = Product.builder()
                .name("R1 Moto")
                .image("R1.jpg")
                .category("Motocicletas")
                .price(19000)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 5, 12))
                .build();
        Product motorcycle5 = Product.builder()
                .name("Fz F1 150")
                .image("Fz.jpg")
                .category("Motocicletas")
                .price(10000)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 8, 20))
                .build();
        Product motorcycle6 = Product.builder()
                .name("Yz Moto")
                .image("Yz.jpg")
                .category("Motocicletas")
                .price(17000)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 1, 6))
                .build();
//-----------------------CARS-----------------------------
        Product car1 = Product.builder()
                .name("CyberTruck Tesla")
                .image("Cyber.jpg")
                .category("Carros")
                .price(1894000)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 3, 1))
                .build();
        Product car2 = Product.builder()
                .name("Toyota Camioneta")
                .image("Toyota.jpg")
                .category("Carros")
                .price(56781200)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 7, 1))
                .build();
        Product car3 = Product.builder()
                .name("Toyota Tuner")
                .image("ToyotaT.jpg")
                .category("Carros")
                .price(2340000)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 12, 7))
                .build();
        Product car4 = Product.builder()
                .name("Exclusivo Carro Toreto")
                .image("Toreto.jpg")
                .category("Carros")
                .price(90000000)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 4, 23))
                .build();
        Product car5 = Product.builder()
                .name("Fiat Camioneta")
                .image("F.jpg")
                .category("Carros")
                .price(1230000)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 12, 25))
                .build();
        Product car6 = Product.builder()
                .name(" Mclaren ")
                .image("Mc.jpg")
                .category("Carros")
                .price(8900000)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 7, 23))
                .build();
//-----------------------------HOME----------------------------------
        Product home1 = Product.builder()
                .name(" Organizador Morado ")
                .image("Organizador.jpg")
                .category("Hogar")
                .price(120)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 8, 12))
                .build();
        Product home2 = Product.builder()
                .name(" Toalla Para Pelo Panda ")
                .image("Toalla.jpg")
                .category("Hogar")
                .price(150)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 2, 15))
                .build();
        Product home3 = Product.builder()
                .name(" Toalla Stich ")
                .image("Stich.jpg")
                .category("Hogar")
                .price(200)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 9, 12))
                .build();
        Product home4 = Product.builder()
                .name(" Lampara Escritorio ")
                .image("Lampara.jpg")
                .category("Hogar")
                .price(600)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 2, 13))
                .build();
        Product home5 = Product.builder()
                .name(" Reloj Escritorio")
                .image("Reloj.jpg")
                .category("Hogar")
                .price(100)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 12, 23))
                .build();
        Product home6 = Product.builder()
                .name(" Cubiertos Navideños ")
                .image("Cubiertos.jpg")
                .category("Hogar")
                .price(230)
                .status("Published")
                .publicationDate(LocalDate.of(2024, 9, 9))
                .build();

        //----------------------------------------- Usuarios Registrados -----------------------------------------
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
//----------------------------------------- MEN -----------------------------------------
        marketplace.addProductToSeller("AnduellSeller", men1);
        marketplace.addProductToSeller("AnduellSeller", men2);
        marketplace.addProductToSeller("AnduellSeller", men3);
        marketplace.addProductToSeller("AnduellSeller", men4);
        marketplace.addProductToSeller("AnduellSeller", men5);
        marketplace.addProductToSeller("AnduellSeller", men6);

//----------------------------------------- WOMEN -----------------------------------------
        marketplace.addProductToSeller("andresf989",women1 );
        marketplace.addProductToSeller("andresf989",women2 );
        marketplace.addProductToSeller("andresf989",women3 );
        marketplace.addProductToSeller("andresf989",women4 );
        marketplace.addProductToSeller("andresf989",women5);
        marketplace.addProductToSeller("andresf989",women6 );
//----------------------------------------- GIRLS -----------------------------------------
        marketplace.addProductToSeller("cjknijdi78",girl1);
        marketplace.addProductToSeller("cjknijdi78",girl2);
        marketplace.addProductToSeller("cjknijdi78",girl3);
        marketplace.addProductToSeller("cjknijdi78",girl4);
        marketplace.addProductToSeller("cjknijdi78",girl5);
        marketplace.addProductToSeller("cjknijdi78",girl6);
//----------------------------------------- BOYS -----------------------------------------
        marketplace.addProductToSeller("fer228",boy1);
        marketplace.addProductToSeller("fer228",boy2);
        marketplace.addProductToSeller("fer228",boy3);
        marketplace.addProductToSeller("fer228",boy4);
        marketplace.addProductToSeller("fer228",boy5);
        marketplace.addProductToSeller("fer228",boy6);
//----------------------------------------- MAKEUP -----------------------------------------
        marketplace.addProductToSeller("jarinos",makeup1);
        marketplace.addProductToSeller("jarinos",makeup2);
        marketplace.addProductToSeller("jarinos",makeup3);
        marketplace.addProductToSeller("jarinos",makeup4);
        marketplace.addProductToSeller("jarinos",makeup5);
        marketplace.addProductToSeller("jarinos",makeup6);
//----------------------------------------- TECHNOLOGY -----------------------------------------

        marketplace.addProductToSeller("obsof",tech1);
        marketplace.addProductToSeller("obsof",tech2);
        marketplace.addProductToSeller("obsof",tech3);
        marketplace.addProductToSeller("obsof",tech4);
        marketplace.addProductToSeller("obsof",tech5);
        marketplace.addProductToSeller("obsof",tech6);
//----------------------------------------- MOBILE DEVICE -----------------------------------------
        marketplace.addProductToSeller("anital",mobile1);
        marketplace.addProductToSeller("anital",mobile2);
        marketplace.addProductToSeller("anital",mobile3);
        marketplace.addProductToSeller("anital",mobile4);
        marketplace.addProductToSeller("anital",mobile5);
        marketplace.addProductToSeller("anital",mobile6);
//----------------------------------------- MOTORCYCLE -----------------------------------------

        marketplace.addProductToSeller("BilCardona",motorcycle1);
        marketplace.addProductToSeller("BilCardona",motorcycle2);
        marketplace.addProductToSeller("BilCardona",motorcycle3);
        marketplace.addProductToSeller("BilCardona",motorcycle4);
        marketplace.addProductToSeller("BilCardona",motorcycle5);
        marketplace.addProductToSeller("BilCardona",motorcycle6);
//----------------------------------------- CAR -----------------------------------------
        marketplace.addProductToSeller("DuqueAna",car1);
        marketplace.addProductToSeller("DuqueAna",car2);
        marketplace.addProductToSeller("DuqueAna",car3);
        marketplace.addProductToSeller("DuqueAna",car4);
        marketplace.addProductToSeller("DuqueAna",car5);
        marketplace.addProductToSeller("DuqueAna",car6);
//----------------------------------------- HOME -----------------------------------------
        marketplace.addProductToSeller("matisc",home1);
        marketplace.addProductToSeller("matisc",home2);
        marketplace.addProductToSeller("matisc",home3);
        marketplace.addProductToSeller("matisc",home4);
        marketplace.addProductToSeller("matisc",home5);
        marketplace.addProductToSeller("matisc",home6);



        return marketplace;
    }
}