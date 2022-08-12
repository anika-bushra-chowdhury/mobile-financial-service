package com.anika.mobilefinancialservice.enums;

import java.util.HashMap;
import java.util.Map;

public enum RateType {
    FIXED("FIXED"),
    RATE("RATE");

    private final String value;

    RateType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static final Map<String, RateType> MAP_BY_VALUE = new HashMap<>();

    static void populateMap() {
        for (RateType rateType : values()) {
            MAP_BY_VALUE.put(rateType.value, rateType);
        }
    }

    public static RateType getByValue(String value) {
        return MAP_BY_VALUE.get(value);
    }
}
