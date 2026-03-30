package com.elevator;

public class FloorDisplay implements IObserver {
    private int elevatorId;
    private int currentFloor;
    private Direction currentDirection;

    public FloorDisplay(int elevatorId, int initialFloor) {
        this.elevatorId = elevatorId;
        this.currentFloor = initialFloor;
        this.currentDirection = Direction.NONE;
    }

    public void updateDirection(Direction dir) {
        this.currentDirection = dir;
        render();
    }

    @Override
    public void onSensorTriggered(SensorEvent event) {
        if (event.getType() == SensorType.POSITION) {
            this.currentFloor = (int) event.getPayload();
            render();
        }
    }

    private void render() {
        String dirIndicator = "";
        if (currentDirection == Direction.UP) dirIndicator = "^";
        else if (currentDirection == Direction.DOWN) dirIndicator = "v";
        else dirIndicator = "-";

        System.out.println("[Display Car " + elevatorId + "] Floor: " + currentFloor + " " + dirIndicator);
    }
}