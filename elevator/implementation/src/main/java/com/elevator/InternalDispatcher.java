package com.elevator;

public class InternalDispatcher {
    private ElevatorController controller;

    public InternalDispatcher(ElevatorController controller) {
        this.controller = controller;
    }

    public void submitInternalRequest(int floor) {
        controller.acceptNewRequest(floor, Direction.NONE);
    }
}