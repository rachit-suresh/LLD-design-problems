package com.bookmyshow;

import java.util.List;
import java.util.Map;

public class Show {
    private Theatre theatre;
    private Movie movie;
    private Timings timings;
    private Screen screen;
    private List<Seat> seats;
    private IPricingStrategy pricingStrategy;
    private Map<SeatType, Integer> prices;
    private String showId;

    public Show(Theatre theatre, Movie movie, Timings timings, Screen screen, List<Seat> seats, IPricingStrategy pricingStrategy, Map<SeatType, Integer> prices, String showId) {
        this.theatre = theatre;
        this.movie = movie;
        this.timings = timings;
        this.screen = screen;
        this.seats = seats;
        this.pricingStrategy = pricingStrategy;
        this.prices = prices;
        this.showId = showId;
    }

    public synchronized boolean changeSeatState(Seat seat, SeatState seatState) {
        // Simple logic for state change for boilerplate
        if (seat.getSeatState() == SeatState.FREE || seatState == SeatState.FREE) {
             seat.setSeatState(seatState);
             return true;
        }
        return false;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Timings getTimings() {
        return timings;
    }

    public void setTimings(Timings timings) {
        this.timings = timings;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public IPricingStrategy getPricingStrategy() {
        return pricingStrategy;
    }

    public void setPricingStrategy(IPricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    public Map<SeatType, Integer> getPrices() {
        return prices;
    }

    public void setPrices(Map<SeatType, Integer> prices) {
        this.prices = prices;
    }

    public String getShowId() {
        return showId;
    }

    public void setShowId(String showId) {
        this.showId = showId;
    }
}
