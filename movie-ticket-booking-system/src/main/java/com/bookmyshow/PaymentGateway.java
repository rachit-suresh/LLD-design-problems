package com.bookmyshow;

public class PaymentGateway {
    public PaymentInfo pay(int amount) {
        // Boilerplate logic
        return new PaymentInfo(amount, "CREDIT_CARD", "TXN-" + System.currentTimeMillis());
    }
}
