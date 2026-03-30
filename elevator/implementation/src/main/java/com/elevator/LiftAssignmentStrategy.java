package com.elevator;

import java.util.List;

public interface LiftAssignmentStrategy {
    ElevatorController assignOptimalCar(List<ElevatorController> controllers, int reqFloor, Direction reqDir);
}