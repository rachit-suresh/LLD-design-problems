package com.example.parkinglot.util;

public class ParsingUtil {

    // Parses something like "level1-mediumslot" and returns the separated parts
    public static String[] parseSlotConfigKey(String key) {
        if (key == null) return new String[0];
        return key.split("-");
    }

    // Parses something like "level_slot_id+level_gate_id" and returns the separated parts
    public static String[] parseDistanceKey(String key) {
        if (key == null) return new String[0];
        return key.split("\\+");
    }
}
