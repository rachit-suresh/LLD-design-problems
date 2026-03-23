package com.example.snakesandladders.rules;

public class HardRules extends RulesStrategy {
    @Override
    public boolean validateMove(int currentPos, int roll, int boardSize) {
        return (currentPos + roll) <= boardSize;
    }

    @Override
    public boolean checkWin(int currentPos, int boardSize) {
        return currentPos == boardSize;
    }

    @Override
    public boolean checkReroll(int roll) {
        return roll == 6; 
    }
}
