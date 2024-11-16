package co.edu.uniquindio.marketplace_fx.marketplace_app.model;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.builder.ProductBuilder;

import java.time.LocalDate;
public class Product {
    private String name;
    private String image;
    private String category;
    private int price;
    private String status;
    private LocalDate publicationDate;

    public Product(String name, String image, String category, int price, String status, LocalDate publicationDate) {
        this.name = name;
        this.image = image;
        this.category = category;
        this.price = price;
        this.status = status;
        this.publicationDate = publicationDate;
    }
    public static ProductBuilder builder(){
        return new ProductBuilder();
    }
// ----------------------------- Getters and Setters -----------------------------
    public String getStatus() {return status;}
    public void setStatus(String estatus) {this.status = estatus;}
    public void setPublicationDate(LocalDate publicationDate) {this.publicationDate = publicationDate;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getImage() {return image;}
    public void setImage(String image) {this.image = image;}
    public String getCategory() {return category;}
    public void setCategory(String category) {this.category = category;}
    public int getPrice() {return price;}
    public void setPrice(int price) {this.price = price;}
    public LocalDate getPublicationDate(){return publicationDate;}

}
