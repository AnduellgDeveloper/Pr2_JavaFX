package co.edu.uniquindio.marketplace_fx.marketplace_app.mediator;

import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.ProductDto;

import java.util.*;

public class MarketPlaceMediator {

    private Map<String, List<ProductDto>> sellerProducts = new HashMap<>();
    private Map<String, List<String>> sellerConnections = new HashMap<>();

    // Registrar un vendedor
    public void registerSeller(String username) {
        sellerProducts.putIfAbsent(username, new ArrayList<>());
        sellerConnections.putIfAbsent(username, new ArrayList<>());
    }

    // Agregar producto a un vendedor
    public void addProductToSeller(String username, ProductDto product) {
        sellerProducts.get(username).add(product);
    }

    // Conectar dos vendedores
    public void connectSellers(String sellerA, String sellerB) {
        sellerConnections.get(sellerA).add(sellerB);
        sellerConnections.get(sellerB).add(sellerA);
    }

    // Obtener productos para el muro de un vendedor (incluyendo conexiones)
    public List<ProductDto> getWallProducts(String username) {
        Set<ProductDto> wallProducts = new HashSet<>(sellerProducts.get(username));
        for (String connectedSeller : sellerConnections.get(username)) {
            wallProducts.addAll(sellerProducts.get(connectedSeller));
        }
        return new ArrayList<>(wallProducts);
    }
}

