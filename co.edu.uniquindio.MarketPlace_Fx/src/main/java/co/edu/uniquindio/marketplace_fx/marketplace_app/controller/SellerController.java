package co.edu.uniquindio.marketplace_fx.marketplace_app.controller;

import co.edu.uniquindio.marketplace_fx.marketplace_app.factory.ModelFactory;
import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.ProductDto;
import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.SellerDto;

import java.util.List;

public class SellerController {
    ModelFactory modelFactory;
    public SellerController(){
        modelFactory = ModelFactory.getInstance();
    }
    // Retorna una lista de todos los vendedores
    public List<SellerDto> getProducts() {
        return modelFactory.getSellers();
    }

    // Añadir un nuevo vendedor a la lista
    public boolean addSeller(SellerDto seller) {
        modelFactory.addSeller(seller);
        return true;
    }

    // Eliminar un vendedor de la lista
    public void removeSeller(SellerDto seller) {
        modelFactory.removeSeller(seller);
    }

    // Actualizar un vendedor existente
    public void updateSeller(SellerDto updatedSeller) {
        modelFactory.updateSeller(updatedSeller);
    }
}