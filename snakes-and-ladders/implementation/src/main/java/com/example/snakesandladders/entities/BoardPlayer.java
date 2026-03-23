package com.example.snakesandladders.entities;

public class BoardPlayer {
    private String colour;
    private int currPosition;

    public BoardPlayer(String colour, int currPosition) {
        this.colour = colour;
        this.currPosition = currPosition;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public int getCurrPosition() {
        return currPosition;
    }

    public void setCurrPosition(int currPosition) {
        this.currPosition = currPosition;
    }
}
