package com.anika.mobilefinancialservice.entity;

import com.anika.mobilefinancialservice.enums.UserType;
import lombok.*;

import javax.persistence.*;
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
public class UserEntity extends BaseDomain{

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "MSISDN")
    private String number;

    @Column(name = "NAME")
    private String userName;

    @Column(name = "NID")
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

    @Column(name = "NID_FRONT")
    private String nidFront;

    @Column(name = "NID_BACK")
    private String nidBack;

    @Column(name = "PIN")
    private String pin;

    @Column(name = "STATUS")
    private Integer status;

    @Column(name = "PROFILE")
    private Integer profile;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "USER_TYPE")
    private UserType userType;
}
