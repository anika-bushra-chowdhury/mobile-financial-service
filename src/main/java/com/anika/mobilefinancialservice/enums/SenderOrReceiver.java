package com.anika.mobilefinancialservice.enums;

public enum SenderOrReceiver {
    SENDER("S"),
    RECEIVER("R");

    private String value;

    SenderOrReceiver(String value) {
        this.value = value;
    }
}
