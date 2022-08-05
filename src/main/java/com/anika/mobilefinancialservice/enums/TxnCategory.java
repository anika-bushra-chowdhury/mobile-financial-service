package com.anika.mobilefinancialservice.enums;

import java.util.HashMap;
import java.util.Map;

public enum TxnCategory {
    ORIGINAL("O"),
    FEE("F"),
    COMMISSION("C");

    private String value;

    TxnCategory(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static final Map<String, TxnCategory> MAP_BY_VALUE = new HashMap<>();

    static void populateMap() {
        for (TxnCategory txnCategory : values()) {
            MAP_BY_VALUE.put(txnCategory.value, txnCategory);
        }
    }

    public static TxnCategory getByValue(String value) {
        return MAP_BY_VALUE.get(value);
    }
}
