package com.elevator;

import java.util.List;

public class ElevatorSystem {
    private List<ElevatorController> controllers;
    private LiftAssignmentStrategy assignmentStrategy;

    public ElevatorSystem(List<ElevatorController> controllers, LiftAssignmentStrategy assignmentStrategy) {
        this.controllers = controllers;
        this.assignmentStrategy = assignmentStrategy;
    }

    public void handleExternalRequest(int floor, Direction dir) {
        ElevatorController bestCar = assignmentStrategy.assignOptimalCar(controllers, floor, dir);
        if (bestCar != null) {
            bestCar.acceptNewRequest(floor, dir);
        } else {
            System.out.println("No elevator available to handle request.");
        }
    }

    public void tick() {
        for (ElevatorController controller : controllers) {
            controller.tick();
        }
    }
}