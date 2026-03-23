package com.example.snakesandladders.util;

import com.example.snakesandladders.core.Board;
import com.example.snakesandladders.entities.Ladder;
import com.example.snakesandladders.entities.Snake;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class BoardFormatterUtil {
    public static void setup(Board board, int n) {
        int boardSize = n * n;
        Random random = new Random();
        Set<Integer> usedPositions = new HashSet<>();
        
        // Cannot use position 0, 1, or boardSize for start/end points
        usedPositions.add(0);
        usedPositions.add(1);
        usedPositions.add(boardSize);

        // Place n snakes
        int snakesPlaced = 0;
        while (snakesPlaced < n) {
            int head = random.nextInt(boardSize - 2) + 2; // 2 to boardSize-1
            int tail = random.nextInt(head - 1) + 1;      // 1 to head-1
            
            if (!usedPositions.contains(head) && !usedPositions.contains(tail)) {
                board.getStaticEntities().put(head, new Snake(head, tail));
                usedPositions.add(head);
                usedPositions.add(tail);
                snakesPlaced++;
            }
        }

        // Place n ladders
        int laddersPlaced = 0;
        while (laddersPlaced < n) {
            int start = random.nextInt(boardSize - 2) + 2; // 2 to boardSize-1
            int target = random.nextInt(boardSize - start) + start + 1; // start+1 to boardSize
            
            if (target > boardSize) target = boardSize;

            if (!usedPositions.contains(start) && !usedPositions.contains(target)) {
                board.getStaticEntities().put(start, new Ladder(start, target));
                usedPositions.add(start);
                usedPositions.add(target);
                laddersPlaced++;
            }
        }
    }
}

