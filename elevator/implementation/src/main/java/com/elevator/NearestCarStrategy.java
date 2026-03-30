package com.elevator;

import java.util.List;

public class NearestCarStrategy implements LiftAssignmentStrategy {
    @Override
    public ElevatorController assignOptimalCar(List<ElevatorController> controllers, int reqFloor, Direction reqDir) {
        ElevatorController bestController = null;
        int minDistance = Integer.MAX_VALUE;

        for (ElevatorController ctrl : controllers) {
            ElevatorCar car = ctrl.getCar();
            int distance = Math.abs(car.getCurrentFloor() - reqFloor);

            // Simple optimization: penalize cars moving away
            if (ctrl.getDirection() == Direction.UP && reqFloor < car.getCurrentFloor()) {
                distance += 10; 
            } else if (ctrl.getDirection() == Direction.DOWN && reqFloor > car.getCurrentFloor()) {
                distance += 10;
            }

            if (distance < minDistance) {
                minDistance = distance;
                bestController = ctrl;
            }
        }

        return bestController;
    }
}