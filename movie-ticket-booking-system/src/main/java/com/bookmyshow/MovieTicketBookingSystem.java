package com.bookmyshow;

import java.util.HashMap;
import java.util.Map;

public class MovieTicketBookingSystem {
    private Map<String, City> cities;
    private Map<String, Show> allShows;
    private Map<String, Booking> allBookings;

    public MovieTicketBookingSystem() {
        this.cities = new HashMap<>();
        this.allShows = new HashMap<>();
        this.allBookings = new HashMap<>();
    }

    public Map<String, City> getCities() {
        return cities;
    }

    public void setCities(Map<String, City> cities) {
        this.cities = cities;
    }

    public Map<String, Show> getAllShows() {
        return allShows;
    }

    public Map<String, Booking> getAllBookings() {
        return allBookings;
    }
}
