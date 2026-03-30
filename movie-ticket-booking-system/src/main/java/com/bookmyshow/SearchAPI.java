package com.bookmyshow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchAPI {

    private MovieTicketBookingSystem system;

    public SearchAPI(MovieTicketBookingSystem system) {
        this.system = system;
    }

    public List<City> getCities(String query) {
        return new ArrayList<>(system.getCities().values());
    }

    public List<Theatre> getTheatre(String cityId) {
        City city = system.getCities().get(cityId);
        return city != null ? city.getTheatres() : new ArrayList<>();
    }

    public List<Movie> getMovies(String cityId) {
        City city = system.getCities().get(cityId);
        return city != null ? city.getMovies() : new ArrayList<>();
    }

    public List<Movie> getMovieGivenTheatre(Theatre theatre) {
        return theatre.getAvailableMovies();
    }

    public List<Theatre> getTheatreGivenMovie(Movie movie) {
        return movie.getAvailableTheatres();
    }

    public List<Show> getShowsForMovie(Movie movie) {
        // Collect all shows for a movie across all theatres
        List<Show> allShows = new ArrayList<>();
        if (movie.getShows() != null) {
            for (List<Show> shows : movie.getShows().values()) {
                allShows.addAll(shows);
            }
        }
        return allShows;
    }

    public List<Seat> getSeatsForShow(Show show) {
        return show.getSeats();
    }

    public Map<SeatType, Integer> getSeatPricesForShow(Show show) {
        return show.getPrices();
    }
}
