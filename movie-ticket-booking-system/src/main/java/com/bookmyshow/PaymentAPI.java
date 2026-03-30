package com.bookmyshow;

public class PaymentAPI {
    
    private MovieTicketBookingSystem system;

    public PaymentAPI(MovieTicketBookingSystem system) {
        this.system = system;
    }

    public PaymentInfo makePayment(Receipt receipt) {
        // Boilerplate passing off to PaymentGateway
        PaymentGateway gateway = new PaymentGateway();
        return gateway.pay(100); // Stubbed pricing
    }

}
