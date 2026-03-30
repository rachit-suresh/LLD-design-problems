package com.example.parkinglot.entities;

import java.util.Date;

public class Ticket {
    private VehicleDetails vehicle;
    private Date entryTime;
    private Date exitTime;
    private String slotId;

    public Ticket(VehicleDetails vehicle, Date entryTime, String slotId) {
        this.vehicle = vehicle;
        this.entryTime = entryTime;
        this.slotId = slotId;
    }

    public VehicleDetails getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleDetails vehicle) {
        this.vehicle = vehicle;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public Date getExitTime() {
        return exitTime;
    }

    public void setExitTime(Date exitTime) {
        this.exitTime = exitTime;
    }

    public String getSlotId() {
        return slotId;
    }

    public void setSlotId(String slotId) {
        this.slotId = slotId;
    }
}
