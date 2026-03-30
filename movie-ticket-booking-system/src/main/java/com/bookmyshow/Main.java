package com.bookmyshow;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Starting BookMyShow / Movie Ticket Booking System ---");

        // 1. Initialize the central datastore and APIs
        MovieTicketBookingSystem system = new MovieTicketBookingSystem();
        system.getCities().put("BLR", new City(new ArrayList<>(), new ArrayList<>())); // Initialize Bangalore
        
        AdminAPI adminAPI = new AdminAPI(system);
        PaymentAPI paymentAPI = new PaymentAPI(system);
        BookingAPI bookingAPI = new BookingAPI(system, paymentAPI);
        SearchAPI searchAPI = new SearchAPI(system);

        // 2. Setup Users
        User admin = new User(UserType.ADMIN, "admin_user");
        User customer = new User(UserType.USER, "john_doe");

        System.out.println("1. Setting up Theatre and Movie...");
        // 3. Admin creates Theatre and Movie
        TheatreDetails theatreDetails = new TheatreDetails("PVR Koramangala", "Bengaluru", "IMAX and 4DX screens");
        Theatre theatre = adminAPI.addTheatre(admin, "BLR", theatreDetails);

        MovieDetails movieDetails = new MovieDetails("Inception", "PG-13", "Dream within a dream", "English", "148 mins");
        Movie movie = adminAPI.addMovie(admin, "BLR", movieDetails);

        // 4. Admin creates a Screen and a Show
        Screen screen = adminAPI.addScreen(admin, theatre, ScreenType.IMAX, 300);
        
        // Setup 3 dummy seats for the show
        List<Seat> seats = new ArrayList<>();
        seats.add(new Seat(SeatState.FREE, SeatType.SILVER, 150));
        seats.add(new Seat(SeatState.FREE, SeatType.GOLD, 200));
        seats.add(new Seat(SeatState.FREE, SeatType.RECLINER, 300));

        Timings timings = new Timings(new Date(), new Date(System.currentTimeMillis() + 7200000)); // 2 hours from now
        Show show = adminAPI.addShow(admin, theatre, movie, timings, screen, seats);
        System.out.println("   Show created with ID: " + show.getShowId());

        System.out.println("\n2. Customer querying shows...");
        // 5. Customer Searches for Shows
        List<Show> availableShows = searchAPI.getShowsForMovie(movie);
        System.out.println("   Found " + availableShows.size() + " show(s) for movie: " + movie.getMovieDetails().getName());

        System.out.println("\n3. Customer locking and booking seats...");
        // 6. Customer Books a Ticket
        Show targetShow = availableShows.get(0);
        List<Seat> seatsToBook = new ArrayList<>();
        seatsToBook.add(targetShow.getSeats().get(0)); // Selecting the first seat

        // Seat selection/locking for concurrency
        boolean locked = bookingAPI.selectSeats(seatsToBook, customer.getUsername());
        if (locked) {
            System.out.println("   Seats successfully locked for user!");
            
            // Proceed to Payment and Booking
            Ticket ticket = bookingAPI.bookTickets(targetShow.getShowId(), customer.getUsername());
            if (ticket != null) {
                System.out.println("   Ticket booked successfully! Ticket ID: " + ticket.getTicket_id());
                System.out.println("   Amount Paid: " + ticket.getPaymentInfo().getAmount());
            } else {
                System.out.println("   Failed to book tickets. Payment or processing error.");
            }
        } else {
            System.out.println("   Failed to lock seats. They might be already occupied or pending payment.");
        }

        // 7. Verify Concurrency (Another user tries to book the booked seat)
        System.out.println("\n4. Another user trying to book the same seat...");
        User customer2 = new User(UserType.USER, "jane_smith");
        boolean locked2 = bookingAPI.selectSeats(seatsToBook, customer2.getUsername());
        if (!locked2) {
            System.out.println("   Concurrency working! Second user failed to lock the seat because it's occupied.");
        }
    }
}
