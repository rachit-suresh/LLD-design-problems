package com.example.snakesandladders.core;

import com.example.snakesandladders.entities.BoardEntity;
import com.example.snakesandladders.entities.BoardPlayer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private Map<Integer, BoardEntity> staticEntities;
    private Map<Integer, List<BoardPlayer>> dynamicEntities;

    public Board() {
        this.staticEntities = new HashMap<>();
        this.dynamicEntities = new HashMap<>();
    }

    public Map<Integer, BoardEntity> getStaticEntities() {
        return staticEntities;
    }

    public void setStaticEntities(Map<Integer, BoardEntity> staticEntities) {
        this.staticEntities = staticEntities;
    }

    public Map<Integer, List<BoardPlayer>> getDynamicEntities() {
        return dynamicEntities;
    }

    public void setDynamicEntities(Map<Integer, List<BoardPlayer>> dynamicEntities) {
        this.dynamicEntities = dynamicEntities;
    }

    public int move(int currentPosition, int steps) {
        int newPosition = currentPosition + steps;
        if (staticEntities.containsKey(newPosition)) {
            newPosition = staticEntities.get(newPosition).executeJump();
        }
        return newPosition;
    }
}
