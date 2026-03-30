package com.bookmyshow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdminAPI {

    private MovieTicketBookingSystem system;

    public AdminAPI(MovieTicketBookingSystem system) {
        this.system = system;
    }

    public Movie addMovie(User user, String cityId, MovieDetails movieDetails) {
        if (user.getType() != UserType.ADMIN) {
            throw new SecurityException("Only Admins can add movies");
        }
        Movie movie = new Movie(new ArrayList<>(), movieDetails, "M" + System.currentTimeMillis(), new HashMap<>());
        City city = system.getCities().get(cityId);
        if (city != null) {
            if (city.getMovies() == null) city.setMovies(new ArrayList<>());
            city.getMovies().add(movie);
        }
        return movie;
    }

    public Theatre addTheatre(User user, String cityId, TheatreDetails theatreDetails) {
        if (user.getType() != UserType.ADMIN) {
            throw new SecurityException("Only Admins can add theatres");
        }
        Theatre theatre = new Theatre(new ArrayList<>(), new ArrayList<>(), new HashMap<>(), "T" + System.currentTimeMillis(), theatreDetails);
        City city = system.getCities().get(cityId);
        if (city != null) {
            if (city.getTheatres() == null) city.setTheatres(new ArrayList<>());
            city.getTheatres().add(theatre);
        }
        return theatre;
    }

    public Show addShow(User user, Theatre theatre, Movie movie, Timings timings, Screen screen, List<Seat> seats) {
        if (user.getType() != UserType.ADMIN) {
            throw new SecurityException("Only Admins can add shows");
        }
        Show show = new Show(theatre, movie, timings, screen, seats, new BasicPricingStrategy(), new HashMap<>(), "S" + System.currentTimeMillis());
        show.setPrices(show.getPricingStrategy().calcPrices(show));
        
        // Add to Theatre
        theatre.getShows().computeIfAbsent(movie, k -> new ArrayList<>()).add(show);
        if (!theatre.getAvailableMovies().contains(movie)) {
            theatre.getAvailableMovies().add(movie);
        }

        // Add to Movie
        movie.getShows().computeIfAbsent(theatre, k -> new ArrayList<>()).add(show);
        if (!movie.getAvailableTheatres().contains(theatre)) {
            movie.getAvailableTheatres().add(theatre);
        }

        system.getAllShows().put(show.getShowId(), show);

        return show;
    }

    public Screen addScreen(User user, Theatre theatre, ScreenType screenType, int basePrice) {
        if (user.getType() != UserType.ADMIN) {
            throw new SecurityException("Only Admins can add screens");
        }
        Screen screen = new Screen(theatre.getTheatreId(), screenType, basePrice);
        if (theatre.getScreens() == null) theatre.setScreens(new ArrayList<>());
        theatre.getScreens().add(screen);
        return screen;
    }
}
