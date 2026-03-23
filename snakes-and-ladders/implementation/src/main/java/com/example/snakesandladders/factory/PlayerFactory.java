package com.example.snakesandladders.factory;

import com.example.snakesandladders.entities.BoardPlayer;
import com.example.snakesandladders.entities.Player;
import java.util.ArrayList;
import java.util.List;

public class PlayerFactory {
    public static List<Player> create(int n) {
        List<Player> players = new ArrayList<>();
        String[] colors = {"Red", "Blue", "Green", "Yellow", "Purple", "Orange"};
        
        for (int i = 0; i < n; i++) {
            int id = i + 1;
            String name = "Player " + id;
            String color = colors[i % colors.length];
            BoardPlayer pawn = new BoardPlayer(color, 0); // Position 0 initially
            players.add(new Player(id, name, pawn));
        }
        
        return players;
    }
}

