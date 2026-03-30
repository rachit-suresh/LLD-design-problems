package com.example.parkinglot.factory;

import com.example.parkinglot.core.Level;
import com.example.parkinglot.entities.Gate;
import com.example.parkinglot.entities.Slot;
import com.example.parkinglot.enums.SlotType;
import java.util.List;
import java.util.Map;

public class LevelFactory {
    
    public static Level create(String name, Map<SlotType, List<Slot>> slots, Map<String, Gate> gates) {
        return new Level(name, slots, gates);
    }

    public static Level createLevelComponents(String levelName, Map<SlotType, Integer> typeCountMap, Integer gatesCount, Map<String, Map<String, Integer>> distances) {
        Map<String, Gate> levelGates = GateFactory.createGates(levelName, gatesCount);
        Map<SlotType, List<Slot>> levelSlots = SlotFactory.createSlots(levelName, typeCountMap, distances);
        
        return create(levelName, levelSlots, levelGates);
    }
}
