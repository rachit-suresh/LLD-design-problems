package com.example.parkinglot.core;

import com.example.parkinglot.entities.Rate;
import com.example.parkinglot.entities.RateRegistry;
import com.example.parkinglot.entities.Slot;
import com.example.parkinglot.entities.Ticket;
import com.example.parkinglot.entities.VehicleDetails;
import com.example.parkinglot.enums.ActionType;
import com.example.parkinglot.enums.SlotType;
import com.example.parkinglot.enums.VehicleType;
import com.example.parkinglot.factory.LevelFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiLevelParkingLot {
    private List<Level> levels;
    private int numberOfLevels;
    private Map<String, Ticket> activeTickets;

    public MultiLevelParkingLot() {
        this.levels = new ArrayList<>();
        this.activeTickets = new HashMap<>();
    }

    public List<Level> getLevels() {
        return levels;
    }

    public void setLevels(List<Level> levels) {
        this.levels = levels;
    }

    public int getNumberOfLevels() {
        return numberOfLevels;
    }

    public void setNumberOfLevels(int numberOfLevels) {
        this.numberOfLevels = numberOfLevels;
    }

    public Map<String, Ticket> getActiveTickets() {
        return activeTickets;
    }

    public void setActiveTickets(Map<String, Ticket> activeTickets) {
        this.activeTickets = activeTickets;
    }

    public void create(int numberOfFloors, Map<String, Map<SlotType, Integer>> slotConfig, Map<String, Integer> gateConfig, Map<String, Map<String, Integer>> distances) {
        this.numberOfLevels = numberOfFloors;
        this.levels = new ArrayList<>();
        this.activeTickets = new HashMap<>();

        for (int i = 1; i <= numberOfFloors; i++) {
            String levelName = "Level-" + i;
            Level level = LevelFactory.createLevelComponents(
                levelName, 
                slotConfig.get(levelName), 
                gateConfig.get(levelName), 
                distances
            );
            this.levels.add(level);
        }
    }

    public Ticket park(String vehicleNumber, VehicleType vehicleType, String entryGateId) {
        SlotProviderUtil slotProvider = new SlotProviderUtil();
        Slot assignedSlot = slotProvider.getSlot(this.levels, vehicleType, entryGateId);
        
        if (assignedSlot == null) {
            System.out.println("Parking Full for Vehicle Type: " + vehicleType);
            return null;
        }

        // Delegate slot resolution and state updates cleanly to Level
        for (Level level : levels) {
            if (level.updateSlot(assignedSlot.getId(), ActionType.OCCUPY)) {
                break;
            }
        }

        VehicleDetails vehicleDetails = new VehicleDetails(vehicleNumber, vehicleType);
        Ticket ticket = new Ticket(vehicleDetails, new Date(), assignedSlot.getId());
        
        String ticketId = "TKT-" + System.currentTimeMillis();
        this.activeTickets.put(ticketId, ticket);
        
        return ticket;
    }

    public double exit(String ticketId) {
        if (!activeTickets.containsKey(ticketId)) {
            throw new IllegalArgumentException("Invalid Ticket ID");
        }

        Ticket ticket = activeTickets.get(ticketId);
        ticket.setExitTime(new Date());
        
        // Avoid deep nested loops; ask the level to update the slot directly
        for (Level level : levels) {
            if (level.updateSlot(ticket.getSlotId(), ActionType.FREE)) {
                break;
            }
        }

        RateRegistry registry = new RateRegistry();
        Rate rate = registry.getRateMap().get(ticket.getVehicle().getVehicleType());
        
        double finalPrice = rate.getTotal(ticket.getEntryTime(), ticket.getExitTime());
        activeTickets.remove(ticketId);
        
        return finalPrice;
    }

    public Map<String, Map<SlotType, Integer>> status() {
        Map<String, Map<SlotType, Integer>> statusMap = new HashMap<>();
        
        for (Level level : levels) {
            Map<SlotType, Integer> levelStatus = new HashMap<>();
            
            for (SlotType type : SlotType.values()) {
                int freeCount = level.getFreeSlotCount(type);
                if (freeCount > 0) {
                    levelStatus.put(type, freeCount);
                }
            }
            
            statusMap.put(level.getName(), levelStatus);
        }
        
        return statusMap;
    }
}
