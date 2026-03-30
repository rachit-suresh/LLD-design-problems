package com.bookmyshow;

public class BasicAddOnStrategy implements IAddOnPriceStrategy {
    private int taxRate;
    private double convenienceFees;

    public BasicAddOnStrategy(int taxRate, double convenienceFees) {
        this.taxRate = taxRate;
        this.convenienceFees = convenienceFees;
    }

    @Override
    public double calcExtraCosts(int basePrice) {
        return (basePrice * (taxRate / 100.0)) + convenienceFees;
    }

    public int getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(int taxRate) {
        this.taxRate = taxRate;
    }

    public double getConvenienceFees() {
        return convenienceFees;
    }

    public void setConvenienceFees(double convenienceFees) {
        this.convenienceFees = convenienceFees;
    }
}
