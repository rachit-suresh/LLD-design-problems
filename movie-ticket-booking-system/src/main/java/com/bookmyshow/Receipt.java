package com.bookmyshow;

public class Receipt {
    private String receiptId;
    private IAddOnPriceStrategy addOnPriceStrategy;

    public Receipt(String receiptId, IAddOnPriceStrategy addOnPriceStrategy) {
        this.receiptId = receiptId;
        this.addOnPriceStrategy = addOnPriceStrategy;
    }

    public double getTotalGivenSeatPrice(int seatPrice) {
        return seatPrice + addOnPriceStrategy.calcExtraCosts(seatPrice);
    }

    public String getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(String receiptId) {
        this.receiptId = receiptId;
    }

    public IAddOnPriceStrategy getAddOnPriceStrategy() {
        return addOnPriceStrategy;
    }

    public void setAddOnPriceStrategy(IAddOnPriceStrategy addOnPriceStrategy) {
        this.addOnPriceStrategy = addOnPriceStrategy;
    }
}
