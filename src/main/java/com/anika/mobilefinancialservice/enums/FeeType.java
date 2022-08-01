package com.anika.mobilefinancialservice.enums;

public enum FeeType {
    FIXED("FIXED"),
    RATE("RATE");

    private String value;

    FeeType(String value) {
        this.value = value;
    }
}
