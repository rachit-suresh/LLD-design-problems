package com.example.parkinglot.factory;

import com.example.parkinglot.entities.Gate;
import java.util.HashMap;
import java.util.Map;

public class GateFactory {
    
    public static Gate create(String position, String id) {
        return new Gate(position, id);
    }

    public static Map<String, Gate> createGates(String levelName, Integer gatesCount) {
        Map<String, Gate> gates = new HashMap<>();
        
        if (gatesCount == null) {
            return gates;
        }

        for (int j = 1; j <= gatesCount; j++) {
            String gateId = levelName + "-Gate-" + j;
            gates.put(gateId, create(levelName, gateId));
        }
        
        return gates;
    }
}
