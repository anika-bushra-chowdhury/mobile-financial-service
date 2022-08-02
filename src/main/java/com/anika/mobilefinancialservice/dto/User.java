package com.anika.mobilefinancialservice.dto;

import com.anika.mobilefinancialservice.enums.ProfileType;
import com.anika.mobilefinancialservice.enums.Status;
import com.anika.mobilefinancialservice.enums.UserType;
import com.sun.istack.NotNull;
import lombok.*;

import java.util.Date;

/**
 * @author atiQue
 * @since 31'Jul 2022 at 9:26 PM
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @NotNull
    private String phoneNumber;

    @NotNull
    private String userName;

    private String nid;

    @NotNull
    private Date dob;

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

    private Status status;

    private ProfileType profileType;

}
