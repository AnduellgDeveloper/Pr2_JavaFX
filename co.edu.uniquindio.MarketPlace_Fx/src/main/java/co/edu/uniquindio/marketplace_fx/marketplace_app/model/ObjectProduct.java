package co.edu.uniquindio.marketplace_fx.marketplace_app.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ObjectProduct {
    private List<Product> listProducts = new ArrayList<>();
    private List<Seller> listSellers = new ArrayList<>();
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setListSellers(List<Seller> listSellers) {
        this.listSellers = listSellers;
    }

    public ObjectProduct() {
    }

    private Product getBuildProduct(String name, String image, String category, int price, String status, LocalDate publicationDate) {
        return Product.builder()
                .name(name)
                .image(image)
                .category(category)
                .price(price)
                .status(status)
                .publicationDate(publicationDate)
                .build();
    }
    public boolean createProduct(Product newProduct){
        Product productFound = getProduct(newProduct.getName());
        if(productFound == null){
            getListProducts().add(newProduct);
            return true;
        }else{
            return  false;
        }
    }

    // Método para buscar un producto por nombre
    private Product getProduct(String name) {
        for (Product product : getListProducts()) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }
    // Método para obtener la lista de productos
    public List<Product> getListProducts() {
        return listProducts;
    }

    // Método para establecer la lista de productos
    public void setListProducts(List<Product> listProducts) {
        this.listProducts = listProducts;
    }

    // Método para obtener la lista de vendedores
    public List<Seller> getListSellers() {
        return listSellers;
    }

    // Método para agregar un producto a la lista
    public void addProduct(Product product) {
        if (product != null) {
            listProducts.add(product);
        }
    }

    // Método para eliminar un producto de la lista
    public void removeProduct(Product product) {
        if (product != null) {
            listProducts.remove(product);
        }
    }


    // Método para actualizar un producto existente
    public void updateProduct(Product updatedProduct) {
        if (updatedProduct != null) {
            Product existingProduct = getProduct(updatedProduct.getName());
            if (existingProduct != null) {
                existingProduct.setImage(updatedProduct.getImage());
                existingProduct.setCategory(updatedProduct.getCategory());
                existingProduct.setPrice(updatedProduct.getPrice());
                existingProduct.setStatus(updatedProduct.getStatus());
                existingProduct.setPublicationDate(updatedProduct.getPublicationDate());
            }
        }
    }

    //seller builder
    private Seller getBuildSeller(String name,String lastName,String idNumber , String username, String password) {

        return Seller.builder()
                .name(name)
                .lastName(lastName)
                .idNumber(idNumber)
                .username(username)
                .password(password)
                .products(new ArrayList<>())
                .sellers(new ArrayList<>())
                .build();
    }
    //crear vendedores
    public boolean createSeller(Seller newSeller){
        Seller sellerFound = getSeller(newSeller.getName());
        if(sellerFound == null){
            getListSellers().add(newSeller);
            return true;
        }else{
            return  false;
        }
    }
    // Método para buscar un vendedor por nombre
    private Seller getSeller(String name) {
        for (Seller seller : getListSellers()) {
            if (seller.getName().equalsIgnoreCase(name)) {
                return seller;
            }
        }
        return null;
    }
    // Método para agregar un vendedpr a la lista
    public void addSeller(Seller seller) {
        if (seller != null) {
            listSellers.add(seller);
        }
    }
    // Método para eliminar un vendedor de la lista
    public void removeSeller(Seller seller) {
        if (seller != null) {
            listSellers.remove(seller);
        }
    }
    // Método para actualizar un vendedor existente
    public void updateSeller(Seller updatedSeller) {
        if (updatedSeller != null) {
            Seller existingSeller = getSeller(updatedSeller.getName());
            if (existingSeller != null) {
                existingSeller.setName(updatedSeller.getName());
                existingSeller.setLastName(updatedSeller.getLastName());
                existingSeller.setIdNumber(updatedSeller.getIdNumber());
                existingSeller.setAddress(updatedSeller.getAddress());
                existingSeller.setUsername(updatedSeller.getUsername());
                existingSeller.setPassword(updatedSeller.getPassword());
            }
        }
    }
}


