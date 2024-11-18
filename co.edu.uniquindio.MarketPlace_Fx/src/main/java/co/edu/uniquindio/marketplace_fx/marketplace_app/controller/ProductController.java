package co.edu.uniquindio.marketplace_fx.marketplace_app.controller;

import co.edu.uniquindio.marketplace_fx.marketplace_app.factory.ModelFactory;
import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.ProductDto;
import co.edu.uniquindio.marketplace_fx.marketplace_app.service.service_observer.Observable;
import co.edu.uniquindio.marketplace_fx.marketplace_app.service.service_observer.Observer;
import co.edu.uniquindio.marketplace_fx.marketplace_app.viewcontroller.PostWallViewController;

import java.util.ArrayList;
import java.util.List;
public class ProductController implements Observable, Observer {
    ModelFactory modelFactory;
    private final List<Observer> observers;
    private List<ProductDto> products = new ArrayList<>();


    public ProductController() {
        this.products = new ArrayList<>();
        modelFactory = ModelFactory.getInstance();
        observers = new ArrayList<>();
    }
    @Override
    public void addObserver(Observer observer) {
        observers.add(observer); // Agregar un observador a la lista
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer); // Eliminar un observador de la lista
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(); // Notificar a todos los observadores
        }
    }

    public List<ProductDto> getProducts(String username) {
        return modelFactory.getProductsSeller(username);
    }
    // Eliminar un producto de la lista
    public void removeProduct(ProductDto product) {
        modelFactory.removeProduct(product);
        notifyObservers();
    }
    // Actualizar un producto existente
    public void updateProduct(ProductDto updatedProduct) {
        modelFactory.updateProduct(updatedProduct);
        notifyObservers();
    }
    // Agregar un producto nuevo a la lista
    public boolean addProduct(ProductDto productDto) {
        modelFactory.addProduct(productDto);
        notifyObservers();
        return true;
    }


    @Override
    public void update() {
        // Aquí actualizarías lo que necesites, por ejemplo, recargar los productos.
        System.out.println("ProductController ha sido notificado de un cambio.");
    }
}
