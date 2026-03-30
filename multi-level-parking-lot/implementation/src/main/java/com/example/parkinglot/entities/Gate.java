package com.example.parkinglot.entities;

public class Gate {
    private String position;
    private String id;

    public Gate(String position, String id) {
        this.position = position;
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
