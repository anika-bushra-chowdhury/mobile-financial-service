package com.anika.mobilefinancialservice.enums;

public enum UserType {
    SYSTEM("01"),
    AGENT("02"),
    CUSTOMER("03");

    private String value;

    UserType(String value) {
        this.value = value;
    }

}
