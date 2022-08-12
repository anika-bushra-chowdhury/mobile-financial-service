package com.anika.mobilefinancialservice.enums;

import java.util.HashMap;
import java.util.Map;

public enum TxnType {

    P2P("400303"),
    CASH_OUT("530302"),
    CASH_IN("520203"),
    B2B_AG("609085"),
    REDEEM_AG("608590");

    private final String value;

    TxnType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static final Map<String, TxnType> MAP_BY_VALUE = new HashMap<>();

    static void populateMap() {
        for (TxnType txnType : values()) {
            MAP_BY_VALUE.put(txnType.value, txnType);
        }
    }

    public static TxnType getByValue(String value) {
        return MAP_BY_VALUE.get(value);
    }
}
