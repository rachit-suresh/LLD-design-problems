# Movie Ticket Booking System (BookMyShow LLD)

This project contains a Low Level Design (LLD) implementation of a Movie Ticket Booking System like BookMyShow in Java.

## Key Features Implemented:

1. **Role-Based Users:** Distinction between `Customers` and `Admins`.
2. **Admin Capabilities:** Add Movies, Theatres, Screens, and Shows securely.
3. **Customer Capabilities:** Search shows, lock seats, book tickets, and cancel bookings.
4. **Concurrency & Thread Safety:** Utilizes synchronized blocks and a `lockedAt` TTL (time-to-live) mechanism with lazy evaluation so a seat locks during payment (`PAYMENTPENDING`) and safely expires after 5 minutes if the user abandons checkout.
5. **Strategy Design Pattern:** Pluggable interfaces for `IPricingStrategy` (seat-based dynamic pricing) and `IAddOnPriceStrategy` (tax & convenience fees).
6. **In-Memory Data Store:** Uses efficient HashMaps linked across the `MovieTicketBookingSystem` class as a single source of truth without external database bounds.

## Project Structure

- `com.bookmyshow.*`: All models, controllers (APIs), factories, enums, and interfaces mappings matching the provided UML diagram logic.
- `com.bookmyshow.Main`: Demo execution script containing a mock workflow.

---

## Prerequisites

- Java Development Kit (JDK 8 or higher) installed on your machine.

---

## How to Compile and Run

Open your terminal or command prompt and navigate to the project directory:

```bash
cd movie-ticket-booking-system
```

### 1. Compile the Java Files

Compile all the Java files using the `javac` command. We will emit the compiled `.class` files into a `bin` directory for cleanliness.

```bash
# Windows
mkdir bin
javac -d bin src\main\java\com\bookmyshow\*.java

# Mac / Linux
mkdir -p bin
javac -d bin src/main/java/com/bookmyshow/*.java
```

### 2. Run the Main Application

Run the compiled `Main` class using the `java` command, specifying the classpath (`-cp`):

```bash
java -cp bin com.bookmyshow.Main
```

### Expected Output

When you run the above program, the output should mimic a full workflow showing the concurrent seating functionality:

```text
--- Starting BookMyShow / Movie Ticket Booking System ---
1. Setting up Theatre and Movie...
   Show created with ID: S171...

2. Customer querying shows...
   Found 1 show(s) for movie: Inception

3. Customer locking and booking seats...
   Seats successfully locked for user!
   Ticket booked successfully! Ticket ID: ...
   Amount Paid: 100.0

4. Another user trying to book the same seat...
   Concurrency working! Second user failed to lock the seat because it's occupied.
```

---

## Modifying Logic

To adjust how seats time out, you can look up `LOCK_TIMEOUT_MS` under `src/main/java/com/bookmyshow/BookingAPI.java`.

To add a new pricing strategy, implement the `IPricingStrategy` and pass it to your `Show` initialization.
