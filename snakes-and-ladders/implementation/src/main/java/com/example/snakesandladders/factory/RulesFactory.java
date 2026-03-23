package com.example.snakesandladders.factory;

import com.example.snakesandladders.rules.EasyRules;
import com.example.snakesandladders.rules.HardRules;
import com.example.snakesandladders.rules.RulesStrategy;

public class RulesFactory {
    public static RulesStrategy create(String mode) {
        if ("HARD".equalsIgnoreCase(mode)) {
            return new HardRules();
        }
        return new EasyRules();
    }
}

