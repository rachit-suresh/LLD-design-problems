package com.elevator;

public class ExternalDispatcher {
    private ElevatorSystem system;

    public ExternalDispatcher(ElevatorSystem system) {
        this.system = system;
    }

    public void submitExternalRequest(int floor, Direction dir) {
        system.handleExternalRequest(floor, dir);
    }
}