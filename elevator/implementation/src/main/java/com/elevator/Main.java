package com.elevator;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("--- Starting Elevator Simulation System ---\n");

        int maxFloors = 10;
        MovementAlgorithm scanAlgo = new ScanAlgorithm();
        
        // 1. Initialize Elevator Cars & Controllers
        ElevatorCar car1 = new ElevatorCar(1, 800.0, 0); // Start at G
        ElevatorController ctrl1 = new ElevatorController(1, maxFloors, car1, scanAlgo);

        ElevatorCar car2 = new ElevatorCar(2, 800.0, 5); // Start at Floor 5
        ElevatorController ctrl2 = new ElevatorController(2, maxFloors, car2, scanAlgo);

        List<ElevatorController> fleet = new ArrayList<>();
        fleet.add(ctrl1);
        fleet.add(ctrl2);

        // 2. Initialize Core System and Dispatchers
        LiftAssignmentStrategy nearestCarStrategy = new NearestCarStrategy();
        ElevatorSystem system = new ElevatorSystem(fleet, nearestCarStrategy);
        
        ExternalDispatcher extDispatcher = new ExternalDispatcher(system);

        // 3. User requests Lift at Floor 3 to go UP
        System.out.println("USER ACTION: Pressing Hallway button at Floor 3 (Going UP)");
        HallwayButton hallwayBtn = new HallwayButton(3, Direction.UP, extDispatcher);
        hallwayBtn.onClick();

        // 4. Run Simulation Loop
        runSimulationTicks(system, 5);

        // 5. User enters Car 1 and presses Floor 7
        System.out.println("\nUSER ACTION: User boards Car 1 and presses Floor 7");
        InternalDispatcher intDispatcher = new InternalDispatcher(ctrl1);
        CarButton carBtn = new CarButton(7, intDispatcher);
        carBtn.onClick();

        // Run simulation loop
        runSimulationTicks(system, 6);

        // 6. Test Weight Sensor Override
        System.out.println("\nUSER ACTION: 10 heavy boxes loaded into Car 1");
        car1.addLoad(900.0); // Exceeds 800kg capacity
        
        runSimulationTicks(system, 2); // Notice it doesn't move anymore

        // Removing weight
        System.out.println("\nUSER ACTION: 2 heavy boxes removed from Car 1");
        car1.removeLoad(200.0);

    }

    private static void runSimulationTicks(ElevatorSystem system, int ticks) throws InterruptedException {
        for (int i = 0; i < ticks; i++) {
            System.out.println("\n--- TICK " + (i+1) + " ---");
            system.tick();
            Thread.sleep(300); // Small delay for readability in terminal
        }
    }
}