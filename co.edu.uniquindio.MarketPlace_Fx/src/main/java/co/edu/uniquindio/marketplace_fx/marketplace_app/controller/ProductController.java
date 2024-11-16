package co.edu.uniquindio.marketplace_fx.marketplace_app.controller;

import co.edu.uniquindio.marketplace_fx.marketplace_app.factory.ModelFactory;
import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.ProductDto;
import co.edu.uniquindio.marketplace_fx.marketplace_app.service.IObserver;

import java.util.ArrayList;
import java.util.List;
public class ProductController {
    ModelFactory modelFactory;
    private List<IObserver> observers = new ArrayList<>();

    public ProductController(){
        modelFactory = ModelFactory.getInstance();
    }
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    // Notificar a todos los observadores cuando se actualiza un producto
    private void notifyProductUpdated(ProductDto product) {
        for (IObserver observer : observers) {
            observer.update(product);
        }
    }

    // Notificar a todos los observadores cuando se agrega un like
    private void notifyLikeAdded(ProductDto product) {
        for (IObserver observer : observers) {
            observer.LikeAdd(product);
        }
    }

    // Notificar a todos los observadores cuando se agrega un comentario
    private void notifyCommentAdded(ProductDto product) {
        for (IObserver observer : observers) {
            observer.CommentAdd(product);
        }
    }

    public List<ProductDto> getProducts(String username) {
        return modelFactory.getProductsSeller(username);
    }
    // Eliminar un producto de la lista
    public void removeProduct(ProductDto product) {
        modelFactory.removeProduct(product);
    }
    // Actualizar un producto existente
    public void updateProduct(ProductDto updatedProduct) {
        modelFactory.updateProduct(updatedProduct);
        notifyProductUpdated(updatedProduct);
    }
    // Agregar un producto nuevo a la lista
    public boolean addProduct(ProductDto productDto) {
        modelFactory.addProduct(productDto);
        notifyProductUpdated(productDto);
        return true;
    }
    public void addLike(ProductDto product) {
        notifyLikeAdded(product);
    }
    public void addComment(ProductDto product, String comment) {
        notifyCommentAdded(product); 
    }

}
