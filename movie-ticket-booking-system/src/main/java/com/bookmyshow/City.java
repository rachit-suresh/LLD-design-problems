package com.bookmyshow;

import java.util.List;

public class City {
    private List<Movie> movies;
    private List<Theatre> theatres;

    public City(List<Movie> movies, List<Theatre> theatres) {
        this.movies = movies;
        this.theatres = theatres;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Theatre> getTheatres() {
        return theatres;
    }

    public void setTheatres(List<Theatre> theatres) {
        this.theatres = theatres;
    }
}
