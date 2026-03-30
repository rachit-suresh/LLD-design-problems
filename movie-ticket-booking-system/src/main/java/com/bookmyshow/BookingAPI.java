package com.bookmyshow;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookingAPI {

    private MovieTicketBookingSystem system;
    private PaymentAPI paymentAPI;
    private static final long LOCK_TIMEOUT_MS = 300000; // 5 minutes

    public BookingAPI(MovieTicketBookingSystem system, PaymentAPI paymentAPI) {
        this.system = system;
        this.paymentAPI = paymentAPI;
    }

    public Ticket bookTickets(String showId, String userId) {
        Show show = system.getAllShows().get(showId);
        if (show == null) return null;

        List<Seat> userSeats = new ArrayList<>();
        int totalBasePrice = 0;

        synchronized (show) {
            for (Seat seat : show.getSeats()) {
                if (seat.getSeatState() == SeatState.PAYMENTPENDING && userId.equals(seat.getLockedByUserId())) {
                    // Check if lock expired
                    if (System.currentTimeMillis() - seat.getLockedAt() <= LOCK_TIMEOUT_MS) {
                        userSeats.add(seat);
                        totalBasePrice += show.getPrices().getOrDefault(seat.getSeatType(), seat.getBasePrice());
                    } else {
                        // Release lock if expired
                        seat.setSeatState(SeatState.FREE);
                        seat.setLockedByUserId(null);
                    }
                }
            }
        }

        if (userSeats.isEmpty()) return null;

        // Generate Receipt
        Receipt receipt = getReceipt(userSeats, totalBasePrice);

        // Make Payment
        PaymentInfo paymentInfo = paymentAPI.makePayment(receipt);

        if (paymentInfo != null && paymentInfo.getPayment_id() != null) {
            Booking booking = new Booking(BookingStatus.CONFIRMED, UUID.randomUUID().toString(), UUID.randomUUID().toString(), userId, userSeats);
            system.getAllBookings().put(booking.getBooking_id(), booking);
            
            synchronized (show) {
                for (Seat seat : userSeats) {
                    seat.setSeatState(SeatState.OCCUPIED);
                }
            }

            Ticket ticket = new Ticket(booking.getTicket_id(), show.getMovie().getMovieDetails(), show.getTheatre().getTheatreDetails(), show, paymentInfo);
            return ticket;
        }

        return null;
    }

    public boolean selectSeats(List<Seat> seats, String userId) {
        long currentTime = System.currentTimeMillis();
        synchronized (this) {
            // First pass: validate availability
            for (Seat seat : seats) {
                boolean isFree = seat.getSeatState() == SeatState.FREE;
                boolean isExpired = seat.getSeatState() == SeatState.PAYMENTPENDING && (currentTime - seat.getLockedAt() > LOCK_TIMEOUT_MS);
                
                if (!isFree && !isExpired) {
                    return false; // Concurrent booking guard
                }
            }
            // Second pass: lock seats
            for (Seat seat : seats) {
                seat.setSeatState(SeatState.PAYMENTPENDING);
                seat.setLockedAt(currentTime);
                seat.setLockedByUserId(userId);
            }
            return true;
        }
    }

    public Receipt getReceipt(List<Seat> seats, int totalBasePrice) {
        BasicAddOnStrategy addon = new BasicAddOnStrategy(18, 50.0); // 18% tax, 50 flat convenience fee
        return new Receipt(UUID.randomUUID().toString(), addon);
    }

    public Receipt getReceipt(List<Seat> seats) {
        // Fallback for single use
        return getReceipt(seats, 0); 
    }

    public Ticket getTicket(String ticketId) {
        return null; // would look up ticket if we stored tickets map
    }

    public boolean cancelBooking(String bookingId) {
        Booking booking = system.getAllBookings().get(bookingId);
        if (booking != null) {
            return booking.cancelBooking(bookingId);
        }
        return false;
    }
}
