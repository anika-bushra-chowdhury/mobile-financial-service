package com.anika.mobilefinancialservice.enums;

import java.util.HashMap;
import java.util.Map;

public enum ProfileType {

    FULL("F");

    private String value;

    ProfileType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static final Map<String, ProfileType> MAP_BY_VALUE = new HashMap<>();

    static void populateMap() {
        for (ProfileType ProfileType : values()) {
            MAP_BY_VALUE.put(ProfileType.value, ProfileType);
        }
    }

    public static ProfileType getByValue(String value) {
        return MAP_BY_VALUE.get(value);
    }
}
