package com.example.parkinglot.entities;

import com.example.parkinglot.enums.SlotType;
import java.util.Map;

public class Slot {
    private String position;
    private boolean free;
    private String id;
    private Map<Gate, Integer> dist;
    private SlotType slotType;

    public Slot(String position, boolean free, String id, Map<Gate, Integer> dist, SlotType slotType) {
        this.position = position;
        this.free = free;
        this.id = id;
        this.dist = dist;
        this.slotType = slotType;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<Gate, Integer> getDist() {
        return dist;
    }

    public void setDist(Map<Gate, Integer> dist) {
        this.dist = dist;
    }

    public SlotType getSlotType() {
        return slotType;
    }

    public void setSlotType(SlotType slotType) {
        this.slotType = slotType;
    }

    public boolean isEmpty() {
        return this.free;
    }

    public void occupy() {
        this.free = false;
    }

    public void free() {
        this.free = true;
    }
}
