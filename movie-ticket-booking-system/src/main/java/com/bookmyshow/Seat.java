package com.bookmyshow;

public class Seat {
    private SeatState seatState;
    private SeatType seatType;
    private int basePrice;
    private long lockedAt; // Added to support payment timeout/concurrency requirement
    private String lockedByUserId;

    public Seat(SeatState seatState, SeatType seatType, int basePrice) {
        this.seatState = seatState;
        this.seatType = seatType;
        this.basePrice = basePrice;
        this.lockedAt = 0;
        this.lockedByUserId = null;
    }

    public SeatState getSeatState() {
        return seatState;
    }

    public void setSeatState(SeatState seatState) {
        this.seatState = seatState;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    public long getLockedAt() {
        return lockedAt;
    }

    public void setLockedAt(long lockedAt) {
        this.lockedAt = lockedAt;
    }

    public String getLockedByUserId() {
        return lockedByUserId;
    }

    public void setLockedByUserId(String lockedByUserId) {
        this.lockedByUserId = lockedByUserId;
    }
}
