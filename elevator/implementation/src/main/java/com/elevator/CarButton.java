package com.elevator;

public class CarButton implements Button {
    private int targetFloor;
    private InternalDispatcher dispatcher;

    public CarButton(int targetFloor, InternalDispatcher dispatcher) {
        this.targetFloor = targetFloor;
        this.dispatcher = dispatcher;
    }

    @Override
    public void onClick() {
        System.out.println("Car button pressed for floor " + targetFloor);
        dispatcher.submitInternalRequest(targetFloor);
    }
}