package com.elevator;

public class ElevatorCar {
    private int id;
    private int currentFloor;
    private ElevatorState state;

    private double capacityKg;
    private double currentLoadKg;

    private WeightSensor weightSensor;
    private PositionSensor positionSensor;
    private FloorDisplay display;

    public ElevatorCar(int id, double capacityKg, int initialFloor) {
        this.id = id;
        this.capacityKg = capacityKg;
        this.currentFloor = initialFloor;
        this.currentLoadKg = 0.0;
        this.state = ElevatorState.IDLE;

        this.weightSensor = new WeightSensor(capacityKg);
        this.positionSensor = new PositionSensor(initialFloor);
        this.display = new FloorDisplay(id, initialFloor);

        // Position sensor notifies display directly
        this.positionSensor.registerObserver(this.display);
    }

    public void gotoFloor(int targetFloor) {
        if (state == ElevatorState.OVERLOADED || state == ElevatorState.MAINTENANCE) {
            return; // Cannot move
        }

        if (targetFloor > currentFloor) {
            state = ElevatorState.MOVING_UP;
            display.updateDirection(Direction.UP);
            currentFloor++; // Simulate 1 tick move
        } else if (targetFloor < currentFloor) {
            state = ElevatorState.MOVING_DOWN;
            display.updateDirection(Direction.DOWN);
            currentFloor--; // Simulate 1 tick move
        }
        
        positionSensor.setCurrentFloor(currentFloor);
    }

    public void stop() {
        state = ElevatorState.IDLE;
        display.updateDirection(Direction.NONE);
    }

    public void openDoors() {
        if (state == ElevatorState.IDLE) {
            state = ElevatorState.DOORS_OPEN;
            System.out.println("Car " + id + ": Doors Opened.");
        }
    }

    public void closeDoors() {
        if (state == ElevatorState.DOORS_OPEN) {
            state = ElevatorState.IDLE;
            System.out.println("Car " + id + ": Doors Closed.");
        }
    }

    public void addLoad(double weightKg) {
        this.currentLoadKg += weightKg;
        this.weightSensor.setCurrentWeight(currentLoadKg);
        if (currentLoadKg > capacityKg) {
            this.state = ElevatorState.OVERLOADED;
            System.out.println("Car " + id + ": OVERLOAD BELL RINGS! Capacity exceeded.");
        }
    }

    public void removeLoad(double weightKg) {
        this.currentLoadKg -= weightKg;
        if (this.currentLoadKg < 0) this.currentLoadKg = 0;
        this.weightSensor.setCurrentWeight(currentLoadKg);
        if (currentLoadKg <= capacityKg && state == ElevatorState.OVERLOADED) {
            this.state = ElevatorState.IDLE;
            System.out.println("Car " + id + ": Weight normalized.");
        }
    }

    public int getId() { return id; }
    public int getCurrentFloor() { return currentFloor; }
    public ElevatorState getState() { return state; }
    public WeightSensor getWeightSensor() { return weightSensor; }
    public PositionSensor getPositionSensor() { return positionSensor; }
    public double getCurrentLoadKg() { return currentLoadKg; }
}