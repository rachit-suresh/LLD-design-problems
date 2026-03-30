package com.example.parkinglot.core;

import com.example.parkinglot.entities.Gate;
import com.example.parkinglot.entities.Slot;
import com.example.parkinglot.enums.SlotType;
import com.example.parkinglot.enums.VehicleType;
import java.util.List;
import java.util.Map;

public class SlotProviderUtil {
    
    public Slot getSlot(List<Level> levels, VehicleType vehicleType, String entryGateId) {
        SlotType requiredSlotType = getSlotTypeForVehicle(vehicleType);
        
        Slot bestSlot = null;
        int minDistance = Integer.MAX_VALUE;

        for (Level level : levels) {
            Map<SlotType, List<Slot>> slotsMap = level.getSlots();
            if (slotsMap.containsKey(requiredSlotType)) {
                List<Slot> slots = slotsMap.get(requiredSlotType);
                for (Slot slot : slots) {
                    if (slot.isEmpty()) {
                        int distance = getDistance(slot, entryGateId);
                        if (distance < minDistance) {
                            minDistance = distance;
                            bestSlot = slot;
                        }
                    }
                }
            }
        }
        return bestSlot;
    }

    private SlotType getSlotTypeForVehicle(VehicleType vehicleType) {
        switch (vehicleType) {
            case BIKE:
            case SCOOTER:
                return SlotType.SMALL;
            case SEDAN:
                return SlotType.MEDIUM;
            case SUV:
                return SlotType.LARGE;
            default:
                throw new IllegalArgumentException("Unknown vehicle type: " + vehicleType);
        }
    }

    private int getDistance(Slot slot, String gateId) {
        for (Map.Entry<Gate, Integer> entry : slot.getDist().entrySet()) {
            if (entry.getKey().getId().equals(gateId)) {
                return entry.getValue();
            }
        }
        // If distance not mapped, return a default large distance to deprioritize
        return 10000;
    }
}

