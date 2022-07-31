package com.anika.mobilefinancialservice.enums;

public enum TxnCategory {
    ORIGINAL("O"),
    FEE("F"),
    COMMISSION("C");

    private String value;

    TxnCategory(String value) {
        this.value = value;
    }
}
