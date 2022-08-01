package com.anika.mobilefinancialservice.enums;

public enum FeeType {
    FIXED("F"),
    RATE("R");

    private String value;

    FeeType(String value) {
        this.value = value;
    }
}
