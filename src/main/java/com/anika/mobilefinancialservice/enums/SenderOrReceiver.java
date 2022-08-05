package com.anika.mobilefinancialservice.enums;

import java.util.HashMap;
import java.util.Map;

public enum SenderOrReceiver {
    SENDER("S"),
    RECEIVER("R");

    private String value;

    SenderOrReceiver(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static final Map<String, SenderOrReceiver> MAP_BY_VALUE = new HashMap<>();

    static void populateMap() {
        for (SenderOrReceiver senderOrReceiver : values()) {
            MAP_BY_VALUE.put(senderOrReceiver.value, senderOrReceiver);
        }
    }

    public static SenderOrReceiver getByValue(String value) {
        return MAP_BY_VALUE.get(value);
    }
}
