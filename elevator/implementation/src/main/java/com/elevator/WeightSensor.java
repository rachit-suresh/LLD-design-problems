package com.elevator;

public class WeightSensor extends ISensor {
    private double currentWeightKg;
    private double maxCapacityKg;

    public WeightSensor(double maxCapacityKg) {
        this.maxCapacityKg = maxCapacityKg;
        this.currentWeightKg = 0.0;
    }

    public void setCurrentWeight(double weightKg) {
        this.currentWeightKg = weightKg;
        measureStrain();
    }

    public void measureStrain() {
        if (currentWeightKg > maxCapacityKg) {
            notifyObservers(new SensorEvent(SensorType.WEIGHT, currentWeightKg, System.currentTimeMillis()));
        }
    }

    public double getCurrentWeightKg() {
        return currentWeightKg;
    }

    public double getMaxCapacityKg() {
        return maxCapacityKg;
    }
}