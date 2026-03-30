package com.bookmyshow;

public class MovieDetails {
    private String name;
    private String rating;
    private String description;
    private String language;
    private String duration;

    public MovieDetails(String name, String rating, String description, String language, String duration) {
        this.name = name;
        this.rating = rating;
        this.description = description;
        this.language = language;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
