package com.elevator;

public class ElevatorController implements IObserver {
    private int id;
    private ElevatorCar car;
    private Direction direction;
    private int maxFloors;

    private boolean[] upStops;
    private boolean[] downStops;
    private MovementAlgorithm algorithm;

    public ElevatorController(int id, int maxFloors, ElevatorCar car, MovementAlgorithm algorithm) {
        this.id = id;
        this.maxFloors = maxFloors;
        this.car = car;
        this.algorithm = algorithm;
        this.direction = Direction.NONE;
        
        this.upStops = new boolean[maxFloors];
        this.downStops = new boolean[maxFloors];

        // Register controller for load and emergency sensors
        this.car.getWeightSensor().registerObserver(this);
    }

    public void acceptNewRequest(int floor, Direction dir) {
        if (floor < 0 || floor >= maxFloors) return;
        
        System.out.println("Controller " + id + " received request targeting floor " + floor);
        if (dir == Direction.UP || dir == Direction.NONE) {
            upStops[floor] = true;
        }
        if (dir == Direction.DOWN || dir == Direction.NONE) {
            downStops[floor] = true;
        }

        evaluateNextState();
    }

    public void tick() {
        if (car.getState() == ElevatorState.MAINTENANCE || car.getState() == ElevatorState.OVERLOADED) {
            return; // Safety halting
        }

        if (car.getState() == ElevatorState.DOORS_OPEN) {
            car.closeDoors(); // Tick auto close
            evaluateNextState(); // Resume
            return;
        }

        int currFloor = car.getCurrentFloor();

        // Arrived at a destination?
        if (upStops[currFloor] || downStops[currFloor]) {
            handleFloorArrival(currFloor);
            return;
        }

        evaluateNextState();
        int nextStop = algorithm.calculateNextStop(currFloor, direction, upStops, downStops);

        if (nextStop != -1 && nextStop != currFloor) {
            car.gotoFloor(nextStop);
        } else {
            car.stop();
            this.direction = Direction.NONE;
        }
    }

    private void handleFloorArrival(int floor) {
        System.out.println("Controller " + id + ": Arrived at destination " + floor);
        car.stop();
        car.openDoors();
        
        // Clear stops depending on direction
        if (direction == Direction.UP || direction == Direction.NONE) {
            upStops[floor] = false;
        }
        if (direction == Direction.DOWN || direction == Direction.NONE) {
            downStops[floor] = false;
        }
        upStops[floor] = false; // Safe clear
        downStops[floor] = false; 
    }

    private void evaluateNextState() {
        int nextStop = algorithm.calculateNextStop(car.getCurrentFloor(), direction, upStops, downStops);
        if (nextStop > car.getCurrentFloor()) {
            this.direction = Direction.UP;
        } else if (nextStop < car.getCurrentFloor() && nextStop != -1) {
            this.direction = Direction.DOWN;
        } else if (nextStop == -1) {
            this.direction = Direction.NONE;
        }
    }

    @Override
    public void onSensorTriggered(SensorEvent event) {
        if (event.getType() == SensorType.WEIGHT) {
            System.out.println("Controller " + id + " halting due to weight limit.");
            car.stop();
        }
    }

    public ElevatorCar getCar() { return car; }
    public Direction getDirection() { return direction; }
}