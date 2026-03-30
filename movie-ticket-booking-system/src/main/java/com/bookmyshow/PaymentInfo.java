package com.bookmyshow;

public class PaymentInfo {
    private double amount;
    private String type;
    private String payment_id;

    public PaymentInfo(double amount, String type, String payment_id) {
        this.amount = amount;
        this.type = type;
        this.payment_id = payment_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
    }
}
