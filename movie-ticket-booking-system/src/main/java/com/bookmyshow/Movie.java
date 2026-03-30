package com.bookmyshow;

import java.util.List;
import java.util.Map;

public class Movie {
    private List<Theatre> availableTheatres;
    private MovieDetails movieDetails;
    private String movieId;
    private Map<Theatre, List<Show>> shows;

    public Movie(List<Theatre> availableTheatres, MovieDetails movieDetails, String movieId, Map<Theatre, List<Show>> shows) {
        this.availableTheatres = availableTheatres;
        this.movieDetails = movieDetails;
        this.movieId = movieId;
        this.shows = shows;
    }

    public List<Show> getShowsGivenTheatre(Theatre theatre) {
        return shows.get(theatre);
    }

    public List<Theatre> getAvailableTheatres() {
        return availableTheatres;
    }

    public void setAvailableTheatres(List<Theatre> availableTheatres) {
        this.availableTheatres = availableTheatres;
    }

    public MovieDetails getMovieDetails() {
        return movieDetails;
    }

    public void setMovieDetails(MovieDetails movieDetails) {
        this.movieDetails = movieDetails;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public Map<Theatre, List<Show>> getShows() {
        return shows;
    }

    public void setShows(Map<Theatre, List<Show>> shows) {
        this.shows = shows;
    }
}
