package com.example.snakesandladders.dice;

public class Dice {
    private int id;

    public Dice() {
        this.id = 1;
    }

    public Dice(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int roll() {
        return (int) (Math.random() * 6) + 1;
    }
}
