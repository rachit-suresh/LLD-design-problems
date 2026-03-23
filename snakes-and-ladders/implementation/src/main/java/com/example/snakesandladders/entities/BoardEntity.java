package com.example.snakesandladders.entities;

public abstract class BoardEntity {
    private int startPosition;
    private int endPosition;

    public BoardEntity() {
    }

    public BoardEntity(int startPosition, int endPosition) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }

    public int getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
    }

    public int getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(int endPosition) {
        this.endPosition = endPosition;
    }

    public int executeJump() {
        return this.endPosition;
    }
}
