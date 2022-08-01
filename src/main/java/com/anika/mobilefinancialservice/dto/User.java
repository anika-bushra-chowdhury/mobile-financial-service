package com.anika.mobilefinancialservice.dto;

import com.anika.mobilefinancialservice.enums.UserType;
import com.sun.istack.NotNull;

/**
 * @author atiQue
 * @since 31'Jul 2022 at 9:26 PM
 */

public class User {

    @NotNull
    private String phoneNumber;

    @NotNull
    private String userName;

    private String nid;

    @NotNull
    private String dob;

    private String presAddress;

    private String prmntAddress;

    private String fatherName;

    private String motherName;

    private String nidFront;

    private String nidBack;

    private String photo;

    private String pin;

    @NotNull
    private UserType userType;
}
