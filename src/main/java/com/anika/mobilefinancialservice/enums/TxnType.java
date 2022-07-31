package com.anika.mobilefinancialservice.enums;

public enum TxnType {
    P2P("400303"),
    CASH_OUT("530302"),
    CASH_IN("520203");

    private String value;

    TxnType(String value) {
        this.value = value;
    }
}
