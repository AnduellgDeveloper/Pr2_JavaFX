package co.edu.uniquindio.marketplace_fx.marketplace_app.model;

import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.UserDto;
import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.mappers.MarketPlaceMappingImpl;
import co.edu.uniquindio.marketplace_fx.marketplace_app.service.ILogin;
import co.edu.uniquindio.marketplace_fx.marketplace_app.service.IProductCrud;
import co.edu.uniquindio.marketplace_fx.marketplace_app.service.IRegister;
import co.edu.uniquindio.marketplace_fx.marketplace_app.service.ISellerCrud;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Marketplace implements ISellerCrud, IProductCrud, ILogin, IRegister {
    private User user;
    private final List<Product> products = new ArrayList<>();
    private final Map<String, List<Product>> sellerProductMap = new HashMap<>();
    private final Map<String, List<Seller>> sellerFriendsMap = new HashMap<>();
    private final List<Seller> listSellers = new ArrayList<>();
    private final List<User> listRegisterUser = new ArrayList<>();
    private final List<User> listUsers = new ArrayList<>();
    private final List<Administrator> listAdministrators = new ArrayList<>();
    private List<Seller> sellerFriends = new ArrayList<>();
    private String name;
    private String username;
    MarketPlaceMappingImpl mapper;

    public Marketplace() {
        this.mapper = new MarketPlaceMappingImpl();
    }
    public List<User> getListRegisterUser() {
        return listRegisterUser;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // -------------------- Getters --------------------
    public List<Seller> getListSellers() {
        return listSellers;
    }
    public List<Product> getProducts() {
        return products;
    }
    public void printSellerProductMap() {
        sellerProductMap.forEach((username, products) -> {
            System.out.printf("Vendedor: %s, Productos: %s%n", username,
                    products.stream().map(Product::getName).collect(Collectors.joining(", ")));
        });
    }
    public List<Product> addProductToSeller(String username, Product product) {
        String cleanedProductName = product.getName().trim();
        product.setName(cleanedProductName);

        List<Product> products = sellerProductMap.computeIfAbsent(username, _ -> new ArrayList<>());
        if (products.stream().noneMatch(p -> p.getName().equalsIgnoreCase(cleanedProductName))) {
            products.add(product);
            System.out.printf("Producto agregado: %s para el vendedor: %s%n", cleanedProductName, username);
            return products;
        } else {
            System.out.printf("Producto duplicado: %s para el vendedor: %s%n", cleanedProductName, username);
        }
        return products;
    }
    public String getSellerForProduct(String productName) {
        String cleanedProductName = productName.trim();
        for (Map.Entry<String, List<Product>> entry : sellerProductMap.entrySet()) {
            for (Product product : entry.getValue()) {
                if (product.getName().trim().equalsIgnoreCase(cleanedProductName)) {
                    System.out.printf("Vendedor encontrado: %s para el producto: %s%n", entry.getKey(), cleanedProductName);
                    return entry.getKey();
                }
            }
        }
        return "Desconocido";
    }



    public List<Seller> getSellerFriends() {
        return sellerFriends;
    }
    public void addFriend(Seller friend) {
        if (!sellerFriends.contains(friend) && !friend.equals(this)) {
            sellerFriends.add(friend);
        }
    }
    public List<Seller> addFriendToSeller(String username, Seller seller) {
        List<Seller> friends = sellerFriendsMap.computeIfAbsent(username, _ -> new ArrayList<>());
        if (friends.stream().noneMatch(p -> p.getUsername().equalsIgnoreCase(seller.getUsername()))) {
            friends.add(seller);
            return friends;
        } else {
            System.out.printf("Amigo duplicado: %s%n ", seller.getUsername());
        }
        return new ArrayList<>();
    }
    // Método para obtener los productos de un vendedor específico
    public List<Seller> getFriendsSeller(String username) {
        List<Seller> friends = sellerFriendsMap.getOrDefault(username, new ArrayList<>());
        return friends;
    }
    // Método para obtener los productos de un vendedor específico
    public List<Product> getProductsSeller(String username) {
        List<Product> products = sellerProductMap.getOrDefault(username, new ArrayList<>());
        return products;
    }
    public void addProduct(Product product) {
        products.add(product);
    }
    public List<User> getListUsers() {
        return listUsers;
    }
    public List<Administrator> getListAdministrators() {
        return listAdministrators;
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
    private final Map<String, List<Product>> sellerProducts = new HashMap<>();




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
    public boolean addFriend(Seller seller, String username) {
        if (seller != null && !sellerFriends.contains(seller)) {
            sellerFriends.add(seller);
            return true;
        }
        return false;
    }


    public void removeFriend(Seller friend) {
        sellerFriends.remove(friend);
    }
    private Product getBuildProduct(String name,
                                    String image,
                                    String category,
                                    int price,
                                    String status,
                                    LocalDateTime publicationDate) {
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
            getProducts().add(newProduct);
            return true;
        }else{
            return  false;
        }
    }

    // Método para buscar un producto por nombre
    public  Product getProduct(String name) {
        for (Product product : getProducts()) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }
    // Método para eliminar un producto de la lista
    public void removeProduct(Product product) {
        if (product != null) {
            products.remove(product);
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
    // Método para buscar un administrator por nombre
    public Administrator getAdministrator(String name) {
        for (Administrator administrator : getListAdministrators()) {
            if (administrator.getName().equalsIgnoreCase(name)) {
                return administrator;
            }
        }
        return null;
    }
    @Override
    public User authenticate(String username, String password) {
        for (User user : listRegisterUser) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                System.out.println(user);
                return user;
            }
        }
        return null;
    }

    @Override
    public String getUserRole(UserDto userDto) {
        User user = mapper.userDtoToUserType(userDto);
        if (user instanceof Seller) {
            System.out.printf("Vendedor detectado: %s\n" ,user.getUsername());
            return "seller";
        } else if (user instanceof Administrator) {
            System.out.printf("Administrador detectado: %s\n", user.getUsername());
            return "administrator";
        }
        System.out.printf("Usuario desconocido: %s\n", user.getUsername());
        return "unknown";
    }

   //------------------------REGISTER----------------------------

    public boolean registerUser(User user) {
        for (User existingUser : listRegisterUser) {
            if (existingUser.getUsername().equals(user.getUsername())) {
                return false;
            }
        }
        listRegisterUser.add(user);
        return true;
    }
}
