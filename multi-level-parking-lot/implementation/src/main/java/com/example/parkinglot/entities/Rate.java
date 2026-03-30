package com.example.parkinglot.entities;

import java.util.Date;

public class Rate {
    private int baseRate;
    private int hourlyRate;

    public Rate(int baseRate, int hourlyRate) {
        this.baseRate = baseRate;
        this.hourlyRate = hourlyRate;
    }

    public int getBaseRate() {
        return baseRate;
    }

    public void setBaseRate(int baseRate) {
        this.baseRate = baseRate;
    }

    public int getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(int hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public double getTotal(Date entryTime, Date exitTime) {
        if (exitTime.before(entryTime)) {
            throw new IllegalArgumentException("Exit time cannot be before entry time");
        }
        
        long diffInMillies = exitTime.getTime() - entryTime.getTime();
        double hours = (double) diffInMillies / (1000 * 60 * 60);
        
        int totalHoursToCharge = (int) Math.ceil(hours);
        
        // Base rate covers the 1st hour, subsequent hours use hourly rate
        if (totalHoursToCharge <= 1) {
            return baseRate;
        } else {
            return baseRate + (hourlyRate * (totalHoursToCharge - 1));
        }
    }
}
