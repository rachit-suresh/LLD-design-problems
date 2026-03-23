package com.example.snakesandladders.rules;

public abstract class RulesStrategy {
    public abstract boolean validateMove(int currentPos, int roll, int boardSize);
    
    public abstract boolean checkWin(int currentPos, int boardSize);
    
    public abstract boolean checkReroll(int roll);
}
