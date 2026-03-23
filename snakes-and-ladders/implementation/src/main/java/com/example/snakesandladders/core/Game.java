package com.example.snakesandladders.core;

import com.example.snakesandladders.dice.Dice;
import com.example.snakesandladders.entities.Player;
import com.example.snakesandladders.factory.PlayerFactory;
import com.example.snakesandladders.factory.RulesFactory;
import com.example.snakesandladders.rules.RulesStrategy;
import com.example.snakesandladders.util.BoardFormatterUtil;
import com.example.snakesandladders.util.OutputUtil;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Game {
    private final Board board;
    private List<Player> players;
    private final RulesStrategy rules;
    private Dice dice;
    private int n; // Represents board dimensions n*n, and count of n snakes / n ladders
    private int size;
    private Queue<Player> activePlayers;
    private boolean isGameActive;
    private boolean hasWinner;

    public Game(int n, int playersCount, String mode) {
        this.n = n;
        this.size = n * n;
        this.rules = RulesFactory.create(mode);
        this.players = PlayerFactory.create(playersCount);
        this.board = new Board();
        this.dice = new Dice();
        this.activePlayers = new LinkedList<>(this.players);
        this.isGameActive = false;
        this.hasWinner = false;
    }

    public void start() {
        if (this.players == null || this.players.size() < 2) {
            throw new IllegalStateException("Not enough players to start the game.");
        }
        
        // Randomly set up 'n' snakes and 'n' ladders on the board using a utility
        BoardFormatterUtil.setup(this.board, this.n);
        
        this.isGameActive = true;
        OutputUtil.print("Game started: " + this.players.size() + " players on a " + this.n + "x" + this.n + " board with " 
                + this.n + " snakes and " + this.n + " ladders.");
    }

    public void endPlayer(Player player) {
        if (!this.hasWinner) {
            player.setWinner(true);
            this.hasWinner = true;
            OutputUtil.print("Player " + player.getName() + " is the WINNER!");
        } else {
            OutputUtil.print("Player " + player.getName() + " has completed the game!");
        }
        player.setTheirGameEnded(true);
        // Note: the player is not added back to the 'activePlayers' queue.
    }

    public void endGame() {
        this.isGameActive = false;
        OutputUtil.print("Game Over!");
    }

    public void makeTurn() {
        if (!isGameActive || activePlayers.isEmpty()) return;

        Player currentPlayer = activePlayers.poll();
        int currentPosition = currentPlayer.getPawn().getCurrPosition();
        
        int roll = dice.roll();
        OutputUtil.print("Player " + currentPlayer.getName() + " rolled a " + roll);

        if (rules.validateMove(currentPosition, roll, this.size)) {
            int newPosition = board.move(currentPosition, roll);
            currentPlayer.getPawn().setCurrPosition(newPosition);
            OutputUtil.print("Player " + currentPlayer.getName() + " moved to " + newPosition);

            if (rules.checkWin(newPosition, this.size)) {
                endPlayer(currentPlayer);
                return;
            }
        } else {
            OutputUtil.print("Invalid move for Player " + currentPlayer.getName());
        }

        if (rules.checkReroll(roll)) {
            OutputUtil.print("Player " + currentPlayer.getName() + " gets a reroll!");
            ((LinkedList<Player>) activePlayers).addFirst(currentPlayer); // Put back to front for immediate turn
        } else {
            activePlayers.offer(currentPlayer); // Move to back of the queue
        }
    }
    
    public boolean isGameActive() {
        return isGameActive && activePlayers.size() > 1;
    }
}



