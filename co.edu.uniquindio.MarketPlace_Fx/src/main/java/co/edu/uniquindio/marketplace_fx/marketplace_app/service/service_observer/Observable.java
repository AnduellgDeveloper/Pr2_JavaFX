package co.edu.uniquindio.marketplace_fx.marketplace_app.service.service_observer;

public interface Observable {
    void addObserver(Observer o);
    void deleteObserver(Observer o);
    void notifyObservers();
}

