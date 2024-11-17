package co.edu.uniquindio.marketplace_fx.marketplace_app.viewcontroller.observer;

import co.edu.uniquindio.marketplace_fx.marketplace_app.controller.ProductController;
import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.ProductDto;
import co.edu.uniquindio.marketplace_fx.marketplace_app.service.service_observer.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;


//  Clase Sujeto (Observable)
public class ProductManager {

    private String username;
    private ProductController productController;
    private static ProductManager instance;
    private final List<Observer> observers = new ArrayList<>();
    private ObservableList<ProductDto> productList = FXCollections.observableArrayList();

    private ProductManager(ProductController productController) {
        this.productController = productController;
        getProducts();
    }
    // Método que obtiene los productos del controlador y los añade a la lista
    private void getProducts() {
        productList.clear();
        List<ProductDto> userProducts = productController.getProducts(username);
        productList.addAll(userProducts);

    }

    public static synchronized ProductManager getInstance(ProductController productController ) {
        if (instance == null) {
            instance = new ProductManager(productController);
        }
        return instance;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public void addProduct(ProductDto product) {
        productList.add(product);
        notifyObservers();
    }
    public void deleteProduct(ProductDto product) {
        productList.remove(product);
        notifyObservers();
    }
    public void updateProduct(ProductDto product) {
        productList.replaceAll(p -> p.name().equals(product.name()) ? product : p);
        notifyObservers();
    }

    public List<ProductDto> getProductList() {
        return new ArrayList<>(productList);
    }
}
