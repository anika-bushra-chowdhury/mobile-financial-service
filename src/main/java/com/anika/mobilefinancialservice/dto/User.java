package com.anika.mobilefinancialservice.dto;

import com.anika.mobilefinancialservice.enums.ProfileType;
import com.anika.mobilefinancialservice.enums.UserStatus;
import com.anika.mobilefinancialservice.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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
    @NotEmpty
//    @Pattern(regexp = "([0-9]*)", message = "Account number should be numbers only.")
//    @Size(max = 11, min = 11, message = "Account number should be 11 digits.")
    private String phoneNumber;

    @NotNull
    @NotEmpty
    private String userName;

    @NotNull
    @NotEmpty
    private String nid;

    @NotNull
    private Date dob;

    @NotNull
    @NotEmpty
    private String presAddress;

    private String prmntAddress;

    private String fatherName;

    private String motherName;

    private String nidFront;

    private String nidBack;

    private String photo;

    @NotNull
    @NotEmpty
//    @Pattern(regexp = "([0-9]*)", message = "Pin number should be numbers only.")
//    @Size(max = 4, min = 4, message = "Pin number should be 4 digits.")
    private String pin;

    @NotNull
    private UserType userType;

    private UserStatus userStatus;

    private ProfileType profileType;
}
