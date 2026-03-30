package com.example.parkinglot.factory;

import com.example.parkinglot.entities.Gate;
import com.example.parkinglot.entities.Slot;
import com.example.parkinglot.enums.SlotType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SlotFactory {
    
    public static Slot create(String position, String id, Map<Gate, Integer> dist, SlotType slotType) {
        return new Slot(position, true, id, dist, slotType); // true by default (isFree)
    }

    public static Map<SlotType, List<Slot>> createSlots(String levelName, Map<SlotType, Integer> typeCountMap, Map<String, Map<String, Integer>> distances) {
        Map<SlotType, List<Slot>> slots = new HashMap<>();
        
        if (typeCountMap == null) {
            return slots;
        }

        for (Map.Entry<SlotType, Integer> entry : typeCountMap.entrySet()) {
            SlotType type = entry.getKey();
            int count = entry.getValue();
            List<Slot> slotsList = new ArrayList<>();
            
            for (int s = 1; s <= count; s++) {
                String slotId = levelName + "-" + type.name() + "-" + s;
                Map<Gate, Integer> slotDistances = new HashMap<>();
                
                if (distances != null && distances.containsKey(slotId)) {
                    Map<String, Integer> gateDistMap = distances.get(slotId);
                    for (Map.Entry<String, Integer> distEntry : gateDistMap.entrySet()) {
                        // Dummy gate placeholder for distance mapping encapsulation
                        slotDistances.put(GateFactory.create("UNKNOWN", distEntry.getKey()), distEntry.getValue());
                    }
                }

                slotsList.add(create(levelName, slotId, slotDistances, type));
            }
            slots.put(type, slotsList);
        }
        
        return slots;
    }
}
