package com.elevator;

import java.util.ArrayList;
import java.util.List;

public abstract class ISensor {
    private List<IObserver> observers = new ArrayList<>();

    public void registerObserver(IObserver o) {
        observers.add(o);
    }

    public void removeObserver(IObserver o) {
        observers.remove(o);
    }

    protected void notifyObservers(SensorEvent event) {
        for (IObserver observer : observers) {
            observer.onSensorTriggered(event);
        }
    }
}