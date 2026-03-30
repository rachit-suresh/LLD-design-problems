package com.bookmyshow;

public class Ticket {
    private String ticket_id;
    private MovieDetails movieDetails;
    private TheatreDetails theatreDetails;
    private Show showDetails;
    private PaymentInfo paymentInfo;

    public Ticket(String ticket_id, MovieDetails movieDetails, TheatreDetails theatreDetails, Show showDetails, PaymentInfo paymentInfo) {
        this.ticket_id = ticket_id;
        this.movieDetails = movieDetails;
        this.theatreDetails = theatreDetails;
        this.showDetails = showDetails;
        this.paymentInfo = paymentInfo;
    }

    public String getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(String ticket_id) {
        this.ticket_id = ticket_id;
    }

    public MovieDetails getMovieDetails() {
        return movieDetails;
    }

    public void setMovieDetails(MovieDetails movieDetails) {
        this.movieDetails = movieDetails;
    }

    public TheatreDetails getTheatreDetails() {
        return theatreDetails;
    }

    public void setTheatreDetails(TheatreDetails theatreDetails) {
        this.theatreDetails = theatreDetails;
    }

    public Show getShowDetails() {
        return showDetails;
    }

    public void setShowDetails(Show showDetails) {
        this.showDetails = showDetails;
    }

    public PaymentInfo getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(PaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
    }
}
