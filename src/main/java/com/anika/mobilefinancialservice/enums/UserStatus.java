package com.anika.mobilefinancialservice.enums;

import java.util.HashMap;
import java.util.Map;

public enum UserStatus {

    ACTIVE("ACTIVE"),
    SUSPENDED("SUSPENDED");

    private final String value;

    UserStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    private static final Map<String, UserStatus> MAP_BY_VALUE = new HashMap<>();

    static void populateMap() {
        for (UserStatus status : values()) {
            MAP_BY_VALUE.put(status.value, status);
        }
    }

    public static UserStatus getByValue(String value) {
        return MAP_BY_VALUE.get(value);
    }

}
