package com.elevator;

public class ScanAlgorithm implements MovementAlgorithm {
    
    @Override
    public int calculateNextStop(int currentFloor, Direction currentDir, boolean[] upStops, boolean[] downStops) {
        if (currentDir == Direction.UP) {
            for (int i = currentFloor; i < upStops.length; i++) {
                if (upStops[i] || downStops[i]) return i;
            }
            // If no stops above, check below
            for (int i = currentFloor - 1; i >= 0; i--) {
                if (upStops[i] || downStops[i]) return i;
            }
        } else if (currentDir == Direction.DOWN) {
            for (int i = currentFloor; i >= 0; i--) {
                if (upStops[i] || downStops[i]) return i;
            }
            // If no stops below, check above
            for (int i = currentFloor + 1; i < upStops.length; i++) {
                if (upStops[i] || downStops[i]) return i;
            }
        } else {
            // Idle direction
            for (int i = 0; i < upStops.length; i++) {
                if (upStops[i] || downStops[i]) return i;
            }
        }
        return -1; // No stops requested
    }
}