package com.anika.mobilefinancialservice.enums;

public enum DebitOrCredit {
    DEBIT("Dr"),
    CREDIT("Cr");

    private String value;

    DebitOrCredit(String value) {
        this.value = value;
    }
}
