package com.bookmyshow;

import java.util.List;

public class Booking {
    private BookingStatus status;
    private String ticket_id;
    private String booking_id;
    private String user_id;
    private List<Seat> seats;

    public Booking(BookingStatus status, String ticket_id, String booking_id, String user_id, List<Seat> seats) {
        this.status = status;
        this.ticket_id = ticket_id;
        this.booking_id = booking_id;
        this.user_id = user_id;
        this.seats = seats;
    }

    public synchronized boolean cancelBooking(String bookingId) {
        if (this.booking_id.equals(bookingId) && this.status != BookingStatus.CANCELLED) {
            this.status = BookingStatus.CANCELLED;
            for (Seat seat : seats) {
                seat.setSeatState(SeatState.FREE);
                seat.setLockedByUserId(null);
            }
            return true;
        }
        return false;
    }

    public synchronized boolean updateBookingStatus(BookingStatus newStatus) {
        this.status = newStatus;
        return true;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public String getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(String ticket_id) {
        this.ticket_id = ticket_id;
    }

    public String getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(String booking_id) {
        this.booking_id = booking_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
}
