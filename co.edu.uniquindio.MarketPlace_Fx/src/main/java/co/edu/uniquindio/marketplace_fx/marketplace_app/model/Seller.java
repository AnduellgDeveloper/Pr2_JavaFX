package co.edu.uniquindio.marketplace_fx.marketplace_app.model;
import java.util.ArrayList;
import java.util.List;

public class Seller {
    private String name;
    private String lastName;
    private String idNumber;
    private String address;
    private String username;
    private String password;
    private List<Product> products; // Se usa para la asociacion con distintos productos (Association with Product)

    public Seller(String name, String lastName, String idNumber, String address, String username, String password, List<Product> products) {
        this.name = name;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.address = address;
        this.username = username;
        this.password = password;
        this.products = new ArrayList<>();
    }
// ----------------------------- Getters and Setters -----------------------------
    public void setProducts(List<Product> products) {this.products = products;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public String getIdNumber() {return idNumber;}
    public void setIdNumber(String idNumber) {this.idNumber = idNumber;}
    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}
    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    public void addProduct(Product product) {products.add(product);}
    public List<Product> getProducts() {return products;}
}
