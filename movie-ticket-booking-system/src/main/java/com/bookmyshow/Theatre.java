package com.bookmyshow;

import java.util.List;
import java.util.Map;

public class Theatre {
    private List<Movie> availableMovies;
    private List<Screen> screens;
    private Map<Movie, List<Show>> shows;
    private String theatreId;
    private TheatreDetails theatreDetails;

    public Theatre(List<Movie> availableMovies, List<Screen> screens, Map<Movie, List<Show>> shows, String theatreId, TheatreDetails theatreDetails) {
        this.availableMovies = availableMovies;
        this.screens = screens;
        this.shows = shows;
        this.theatreId = theatreId;
        this.theatreDetails = theatreDetails;
    }

    public List<Show> getShowsGivenMovie(Movie movie) {
        return shows.get(movie);
    }

    public List<Movie> getAvailableMovies() {
        return availableMovies;
    }

    public void setAvailableMovies(List<Movie> availableMovies) {
        this.availableMovies = availableMovies;
    }

    public List<Screen> getScreens() {
        return screens;
    }

    public void setScreens(List<Screen> screens) {
        this.screens = screens;
    }

    public Map<Movie, List<Show>> getShows() {
        return shows;
    }

    public void setShows(Map<Movie, List<Show>> shows) {
        this.shows = shows;
    }

    public String getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(String theatreId) {
        this.theatreId = theatreId;
    }

    public TheatreDetails getTheatreDetails() {
        return theatreDetails;
    }

    public void setTheatreDetails(TheatreDetails theatreDetails) {
        this.theatreDetails = theatreDetails;
    }
}
