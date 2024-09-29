package co.edu.uniquindio.marketplace_fx.marketplace_app.model;
import java.time.LocalDateTime;

public class Product {
    private String name;
    private String image;
    private String category;
    private int price;
    private String estatus;
    private LocalDateTime publicationDate;

    public Product(String name, String image, String category, int price, String estatus, LocalDateTime publicationDate) {
        this.name = name;
        this.image = image;
        this.category = category;
        this.price = price;
        this.estatus = "Published";
        this.publicationDate = LocalDateTime.now();
    }
// ----------------------------- Getters and Setters -----------------------------
    public String getEstatus() {return estatus;}
    public void setEstatus(String estatus) {this.estatus = estatus;}
    public void setPublicationDate(LocalDateTime publicationDate) {this.publicationDate = publicationDate;}
    public void SoldProduct() {this.estatus = "Sold";}
    public void cancelProduct() {this.estatus = "Cancelled";}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getImage() {return image;}
    public void setImage(String image) {this.image = image;}
    public String getCategory() {return category;}
    public void setCategory(String category) {this.category = category;}
    public int getPrice() {return price;}
    public void setPrice(int price) {this.price = price;}
    public LocalDateTime getPublicationDate(){return publicationDate;}
}
