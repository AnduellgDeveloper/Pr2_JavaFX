package co.edu.uniquindio.marketplace_fx.marketplace_app.model;

import co.edu.uniquindio.marketplace_fx.marketplace_app.service.ILogin;
import co.edu.uniquindio.marketplace_fx.marketplace_app.service.IProductCrud;
import co.edu.uniquindio.marketplace_fx.marketplace_app.service.ISellerCrud;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Marketplace implements ISellerCrud, IProductCrud, ILogin {
    private List<Product> listProducts = new ArrayList<>();
    private List<Seller> listSellers = new ArrayList<>();
    private List<User> listRegisterUser = new ArrayList<>();
    private List<User> listUsers = new ArrayList<>();
    private List<Administrator> listAdministrators = new ArrayList<>();
    private String name;

    public List<User> getListRegisterUser() {
        return listRegisterUser;
    }

    public void setListRegisterUser(List<User> listRegisterUser) {
        this.listRegisterUser = listRegisterUser;
    }

    public void setListUsers(List<User> listUsers) {
        this.listUsers = listUsers;
    }

    public void setListAdministrators(List<Administrator> listAdministrators) {
        this.listAdministrators = listAdministrators;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private User user;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setListSellers(List<Seller> listSellers) {
        this.listSellers = listSellers;
    }

    public Marketplace() {
    }
    // -------------------- Getters --------------------
    public List<Seller> getListSellers() {
        return listSellers;
    }
    public List<Product> getListProducts() {
        return listProducts;
    }
    public List<User> getListUsers() {
        return listUsers;
    }
    public List<Administrator> getListAdministrators() {
        return listAdministrators;
    }
    public List<User> listRegisterUser() {
        return listRegisterUser;
    }

    //seller builder
    private Seller getBuildSeller(String name,
                                  String lastName,
                                  String idNumber,
                                  String username,
                                  String password) {
        return Seller.builder()
                .name(name)
                .lastName(lastName)
                .idNumber(idNumber)
                .username(username)
                .password(password)
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
    public Seller getSeller(String name) {
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
    private Product getBuildProduct(String name,
                                    String image,
                                    String category,
                                    int price,
                                    String status,
                                    LocalDate publicationDate) {
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
    public  Product getProduct(String name) {
        for (Product product : getListProducts()) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }
    // Método para establecer la lista de productos
    public void setListProducts(List<Product> listProducts) {
        this.listProducts = listProducts;
    }


    public void addProductToSeller(Seller seller, Product product) {
        if (listSellers.contains(seller)) {
            addProduct(product);
            listProducts.add(product);
        } else {
            System.out.println("El vendedor no está registrado en el marketplace.");
        }
    }
    public void addProduct(Product product) {
        listProducts.add(product);
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
    // -------------------------- Login --------------------------

    // Validar el inicio de sesion
    @Override
    public boolean validateLogin(String username, String password) {
        for (User user : listRegisterUser) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                this.user = user;
                return true;
            }
        }
        return false;
    }

    //
    @Override
    public User authenticate(String username, String password) {
        for (User user : listRegisterUser) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public String getUserRole(User user) {
        if (user instanceof Seller) {
            return "seller";
        } else if (user instanceof Administrator) {
            return "administrator";
        }
        return "unknown";
    }

}
