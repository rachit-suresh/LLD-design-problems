package com.elevator;

public class HallwayButton implements Button {
    private int floor;
    private Direction direction;
    private ExternalDispatcher dispatcher;

    public HallwayButton(int floor, Direction direction, ExternalDispatcher dispatcher) {
        this.floor = floor;
        this.direction = direction;
        this.dispatcher = dispatcher;
    }

    @Override
    public void onClick() {
        System.out.println("Hallway button pressed at floor " + floor + " going " + direction);
        dispatcher.submitExternalRequest(floor, direction);
    }
}