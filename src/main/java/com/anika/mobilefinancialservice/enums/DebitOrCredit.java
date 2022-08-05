package com.anika.mobilefinancialservice.enums;

import java.util.HashMap;
import java.util.Map;

public enum DebitOrCredit {
    DEBIT("Dr"),
    CREDIT("Cr");

    private String value;

    DebitOrCredit(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static final Map<String, DebitOrCredit> MAP_BY_VALUE = new HashMap<>();

    static void populateMap() {
        for (DebitOrCredit debitOrCredit : values()) {
            MAP_BY_VALUE.put(debitOrCredit.value, debitOrCredit);
        }
    }

    public static DebitOrCredit getByValue(String value) {
        return MAP_BY_VALUE.get(value);
    }
}
