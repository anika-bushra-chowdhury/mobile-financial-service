package com.anika.mobilefinancialservice.enums;

import java.util.HashMap;
import java.util.Map;

public enum UserType {
    SYSTEM("01"),
    AGENT("02"),
    CUSTOMER("03");

    private String value;

    UserType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static final Map<String, UserType> MAP_BY_VALUE = new HashMap<>();

    static void populateMap() {
        for (UserType userType : values()) {
            MAP_BY_VALUE.put(userType.value, userType);
        }
    }

    public static UserType getByValue(String value) {
        return MAP_BY_VALUE.get(value);
    }
}
