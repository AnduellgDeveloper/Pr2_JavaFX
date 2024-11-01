package co.edu.uniquindio.marketplace_fx.marketplace_app.model;

import java.util.ArrayList;
import java.util.List;

public class ObjectSeller {
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

    public ObjectSeller() {
    }
    // Método para obtener la lista de vendedores
    public List<Seller> getListSellers() {
        return listSellers;
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
