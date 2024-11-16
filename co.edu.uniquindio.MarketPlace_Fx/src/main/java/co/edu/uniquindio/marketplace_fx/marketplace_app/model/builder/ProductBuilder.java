package co.edu.uniquindio.marketplace_fx.marketplace_app.model.builder;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.Product;

import java.time.LocalDate;

public class ProductBuilder {
    private String name;
    private String image;
    private String category;
    private int price;
    private String status;
    private LocalDate publicationDate;

    public ProductBuilder name(String name) {
        this.name = name;
        return this;
    }
    public ProductBuilder image(String image) {
        this.image = image;
        return this;
    }
    public ProductBuilder category(String category) {
        this.category = category;
        return this;
    }
    public ProductBuilder price(int price) {
        this.price = price;
        return this;
    }
    public ProductBuilder status(String status) {
        this.status = status;
        return this;
    }
    public ProductBuilder publicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
        return this;
    }
    public Product build() {
        return new Product(name, image, category, price, status, publicationDate);
    }
}
