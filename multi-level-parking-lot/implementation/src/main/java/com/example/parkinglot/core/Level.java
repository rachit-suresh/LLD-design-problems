package com.example.parkinglot.core;

import com.example.parkinglot.entities.Gate;
import com.example.parkinglot.entities.Slot;
import com.example.parkinglot.enums.ActionType;
import com.example.parkinglot.enums.SlotType;
import java.util.List;
import java.util.Map;

public class Level {
    private Map<SlotType, List<Slot>> slots;
    private Map<String, Gate> gates;
    private String name;

    public Level(String name, Map<SlotType, List<Slot>> slots, Map<String, Gate> gates) {
        this.name = name;
        this.slots = slots;
        this.gates = gates;
    }

    public Map<SlotType, List<Slot>> getSlots() {
        return slots;
    }

    public void setSlots(Map<SlotType, List<Slot>> slots) {
        this.slots = slots;
    }

    public Map<String, Gate> getGates() {
        return gates;
    }

    public void setGates(Map<String, Gate> gates) {
        this.gates = gates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFreeSlotCount(SlotType type) {
        if (!slots.containsKey(type)) return 0;
        int freeCount = 0;
        for (Slot slot : slots.get(type)) {
            if (slot.isEmpty()) {
                freeCount++;
            }
        }
        return freeCount;
    }

    public boolean updateSlot(String slotId, ActionType actionType) {
        for (List<Slot> slotList : slots.values()) {
            for (Slot slot : slotList) {
                if (slot.getId().equals(slotId)) {
                    if (actionType == ActionType.OCCUPY) {
                        slot.occupy();
                    } else if (actionType == ActionType.FREE) {
                        slot.free();
                    }
                    return true;
                }
            }
        }
        return false;
    }
}
