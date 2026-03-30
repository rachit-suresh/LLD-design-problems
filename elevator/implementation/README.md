# Elevator System LLD

This project contains a Low Level Design implementation of an Elevator System using Java, following standard OO principles and design patterns like Strategy and Observer. It adheres to the design specified in `/design.mmd`.

## Features

- **Simulation Environment:** Runs on discrete `tick()` cycles instead of instantly teleporting elevators, rendering movement realistically over time.
- **Strategy Pattern (Assignment):** The `NearestCarStrategy` distributes hallway button requests optimally to the closest elevator car.
- **Strategy Pattern (Movement):** Leverages a bidirectional `ScanAlgorithm` (Elevator Algorithm) internally resolving which queued floors to stop at next without unnecessary direction reversing.
- **Observer Pattern:** Implements an interrupt-based `SensorSystem` (`ISensor`, `IObserver`) to halt overloaded elevators, and updates real-time `FloorDisplay` objects whenever position changes.

## Compiling & Running

Navigate to `class-diagrams/elevator/implementation`:

```bash
cd class-diagrams/elevator/implementation
```

Compile all the java classes into a `bin` folder:

```bash
# Windows
mkdir bin
javac -d bin src\main\java\com\elevator\*.java

# Mac / Linux
mkdir -p bin
javac -d bin src/main/java/com/elevator/*.java
```

Execute the simulation script:

```bash
java -cp bin com.elevator.Main
```
