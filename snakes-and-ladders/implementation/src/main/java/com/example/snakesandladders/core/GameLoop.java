package com.example.snakesandladders.core;

public class GameLoop {
    private final Game game;

    public GameLoop(Game game) {
        this.game = game;
    }

    public void playGame() {
        game.start();
        while (game.isGameActive()) {
            game.makeTurn();
        }
    }
}