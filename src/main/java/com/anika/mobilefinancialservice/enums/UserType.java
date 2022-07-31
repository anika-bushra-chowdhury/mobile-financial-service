package com.anika.mobilefinancialservice.enums;

public enum UserType {
    SYSTEM(01),
    AGENT(02),
    CUSTOMER(03);

    private int value;

    UserType(int value) {
        this.value = value;
    }
}
