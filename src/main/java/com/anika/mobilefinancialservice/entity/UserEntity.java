package com.anika.mobilefinancialservice.entity;

import com.anika.mobilefinancialservice.enums.ProfileType;
import com.anika.mobilefinancialservice.enums.UserStatus;
import com.anika.mobilefinancialservice.enums.UserType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author atiQue
 * @since 31'Jul 2022 at 9:27 PM
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "WALLET_USER")
public class UserEntity extends BaseDomain {

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "MSISDN")
    @Pattern(regexp = "([0-9]*)", message = "Account number should be numbers only.")
    @Size(max = 11, min = 11, message = "Account number should be 11 digits.")
    private String accountNumber;

    @Column(name = "NAME")
    private String userName;

    @Column(name = "NID")
    @Pattern(regexp = "([0-9]*)", message = "NID should be numbers only.")
    private String nid;

    @Column(name = "DOB")
    private Date dateOfBirth;

    @Column(name = "PRESENT_ADDRESS")
    private String presentAddress;

    @Column(name = "PERMANENT_ADDRESS")
    private String permanentAddress;

    @Column(name = "FATHER_NAME")
    private String fatherName;

    @Column(name = "MOTHER_NAME")
    private String motherName;

    @Column(name = "PHOTO")
    private String photo;

    @Column(name = "NID_FRONT")
    private String nidFront;

    @Column(name = "NID_BACK")
    private String nidBack;

    @Column(name = "PIN")
    @Pattern(regexp = "([0-9]*)", message = "Pin number should be numbers only.")
    @Size(max = 4, min = 4, message = "Pin number should be 4 digits.")
    private String pin;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private UserStatus userStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "PROFILE")
    private ProfileType profile;

    @Enumerated(EnumType.STRING)
    @Column(name = "USER_TYPE")
    private UserType userType;
}
