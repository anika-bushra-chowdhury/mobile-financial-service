package com.anika.mobilefinancialservice.enums;

import java.util.HashMap;
import java.util.Map;

public enum FeeType {
    FIXED("FIXED"),
    RATE("RATE");

    private String value;

    FeeType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static final Map<String, FeeType> MAP_BY_VALUE = new HashMap<>();

    static void populateMap() {
        for (FeeType feeType : values()) {
            MAP_BY_VALUE.put(feeType.value, feeType);
        }
    }

    public static FeeType getByValue(String value) {
        return MAP_BY_VALUE.get(value);
    }
}
