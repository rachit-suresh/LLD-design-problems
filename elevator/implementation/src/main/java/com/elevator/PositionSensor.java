package com.elevator;

public class PositionSensor extends ISensor {
    private int currentFloor;

    public PositionSensor(int initialFloor) {
        this.currentFloor = initialFloor;
    }

    public void setCurrentFloor(int floor) {
        this.currentFloor = floor;
        detectFloorProximity();
    }

    public void detectFloorProximity() {
        notifyObservers(new SensorEvent(SensorType.POSITION, currentFloor, System.currentTimeMillis()));
    }

    public int getCurrentFloor() {
        return currentFloor;
    }
}