package com.example.parkinglot.entities;

import com.example.parkinglot.enums.VehicleType;
import java.util.HashMap;
import java.util.Map;

public class RateRegistry {
    private Map<VehicleType, Rate> rateMap;

    public RateRegistry() {
        rateMap = new HashMap<>();
        // Default initialized rates as per instructions
        rateMap.put(VehicleType.BIKE, new Rate(10, 5));
        rateMap.put(VehicleType.SCOOTER, new Rate(10, 5));
        rateMap.put(VehicleType.SEDAN, new Rate(20, 10));
        rateMap.put(VehicleType.SUV, new Rate(30, 15));
    }

    public Map<VehicleType, Rate> getRateMap() {
        return rateMap;
    }

    public void setRateMap(Map<VehicleType, Rate> rateMap) {
        this.rateMap = rateMap;
    }
}
