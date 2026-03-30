package com.elevator;

public class SensorEvent {
    private SensorType type;
    private Object payload;
    private long timestamp;

    public SensorEvent(SensorType type, Object payload, long timestamp) {
        this.type = type;
        this.payload = payload;
        this.timestamp = timestamp;
    }

    public SensorType getType() {
        return type;
    }

    public Object getPayload() {
        return payload;
    }

    public long getTimestamp() {
        return timestamp;
    }
}