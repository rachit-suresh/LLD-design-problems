package com.example.snakesandladders.entities;

public class Player {
    private int id;
    private String name;
    private BoardPlayer pawn;
    private boolean isWinner;
    private boolean hasTheirGameEnded;

    public Player(int id, String name, BoardPlayer pawn) {
        this.id = id;
        this.name = name;
        this.pawn = pawn;
        this.isWinner = false;
        this.hasTheirGameEnded = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BoardPlayer getPawn() {
        return pawn;
    }

    public void setPawn(BoardPlayer pawn) {
        this.pawn = pawn;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public void setWinner(boolean winner) {
        isWinner = winner;
    }

    public boolean isTheirGameEnded() {
        return hasTheirGameEnded;
    }

    public void setTheirGameEnded(boolean theirGameEnded) {
        hasTheirGameEnded = theirGameEnded;
    }
}
