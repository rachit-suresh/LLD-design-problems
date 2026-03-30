package com.elevator;

public interface MovementAlgorithm {
    int calculateNextStop(int currentFloor, Direction currentDir, boolean[] upStops, boolean[] downStops);
}