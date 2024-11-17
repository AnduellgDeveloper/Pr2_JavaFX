package co.edu.uniquindio.marketplace_fx.marketplace_app.viewcontroller.observer;

import co.edu.uniquindio.marketplace_fx.marketplace_app.service.service_observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class Observable {
    private final List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    protected void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}

