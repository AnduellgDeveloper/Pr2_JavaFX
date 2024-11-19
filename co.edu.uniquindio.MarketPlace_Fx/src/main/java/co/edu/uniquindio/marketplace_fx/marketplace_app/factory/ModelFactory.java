package co.edu.uniquindio.marketplace_fx.marketplace_app.factory;

import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.AdministratorDto;
import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.ProductDto;
import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.SellerDto;
import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.UserDto;
import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.mappers.MarketPlaceMappingImpl;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.*;
import co.edu.uniquindio.marketplace_fx.marketplace_app.service.IMarketPlaceMapping;
import co.edu.uniquindio.marketplace_fx.marketplace_app.service.IModelFactoryService;
import co.edu.uniquindio.marketplace_fx.marketplace_app.utils.DataUtil;

import java.util.List;

public class ModelFactory implements IModelFactoryService {
    private static ModelFactory modelFactory;
    private Marketplace marketplace;
    private IMarketPlaceMapping mapper;

    // Método para obtener la instancia singleton
    public static ModelFactory getInstance() {
        if (modelFactory == null) {
            modelFactory = new ModelFactory();
        }
        return modelFactory;
    }
    private ModelFactory() {
        marketplace = DataUtil.initializeData();
        mapper = new MarketPlaceMappingImpl();

    }
    // -------------------------- Getters --------------------------

    //Obtener lista de Administradores
    @Override
    public List<AdministratorDto> getAdministrators() {
        return mapper.getAdministratorsDto(marketplace.getListAdministrators());
    }

    @Override
    public SellerDto getSellerName(String username) {
        return mapper.sellertToSellerDto(marketplace.getSeller(username));
    }

    // Obtener todos los productos
    @Override
    public List<ProductDto> getProducts() {
        return mapper.productsToProductsDto(marketplace.getProducts());
    }

    // Obtener productos de un vendedor específico
    public List<ProductDto> getProductsSeller(String username) {
        return mapper.productsToProductsDto(marketplace.getProductsSeller(username));
    }
    // Obtener productos de un vendedor específico
    public List<SellerDto> getFriendsSeller(String username) {
        return mapper.sellersToSellersDto(marketplace.getFriendsSeller(username));
    }
    //Obtener lista vendedores
    public List<SellerDto> getSellers() {
        return mapper.getSellersDto(marketplace.getListSellers());
    }
    //Obtener lista de usuarios
    @Override
    public List<UserDto> getUsers() {
        return mapper.getUsersDto(marketplace.getListUsers());
    }
    // -------------------------- Product --------------------------

    public void addProductToSeller(String username, ProductDto productDto) {
        Product product = mapper.productDtoToProduct(productDto);
        marketplace.addProductToSeller(username, product);
    }


    // Añadir un nuevo producto a la lista
    public boolean addProduct(ProductDto productDto) {
        Product product = mapper.productDtoToProduct(productDto);
        return marketplace.createProduct(product);
    }
    // Eliminar un producto de la lista
    public void removeProduct(ProductDto product) {
        marketplace.removeProduct(mapper.toObjectProduct(product));
    }
    // Actualizar un producto existente
    public void updateProduct(ProductDto updatedProduct) {
        marketplace.updateProduct(mapper.toObjectProduct(updatedProduct));
    }

    // -------------------------- Seller --------------------------

    // Añadir un nuevo vendedor a la lista
    public boolean addSeller(SellerDto sellerDto) {
        Seller seller = mapper.sellerDtoToSeller(sellerDto);
        return marketplace.createSeller(seller);
    }
    //remover vendedor de la lista
    public void removeSeller(SellerDto seller){
        marketplace.removeSeller(mapper.toObjectSeller(seller));
    }
    // Actualizar un vendedor existente
    public void updateSeller(SellerDto updatedSeller) {
        marketplace.updateSeller(mapper.toObjectSeller(updatedSeller));
    }

    // -------------------------- Login --------------------------

    // Método para autenticar al usuario basado en username y password
    public UserDto authenticate(String username, String password) {
        User user = marketplace.authenticate(username, password);
        if (user != null) {
            return mapper.userToUserDto(user);
        }
        return null;
    }

    // Método para obtener el rol del usuario autenticado
    public String getUserRole(UserDto userDto) {
        User user = mapper.userDtoToUserType(userDto);
        return marketplace.getUserRole(userDto);
    }
    //----------------------------------REGISTER----------------------------------------------

    public boolean registerUser(UserDto userDto) {
        User user = mapper.userDtoToUserType(userDto);
        return marketplace.registerUser(user);
    }

}
