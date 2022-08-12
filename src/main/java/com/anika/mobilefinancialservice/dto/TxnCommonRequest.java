package com.anika.mobilefinancialservice.dto;

import com.anika.mobilefinancialservice.enums.TxnType;
import com.anika.mobilefinancialservice.enums.UserType;
import lombok.*;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TxnCommonRequest {

    @Pattern(regexp = "(01[3-9]\\d{8})$", message = "Account number should be numbers only.")
    @Size(max = 11, min = 11, message = "Account number should be 11 digits.")
    @NotNull
    @NotEmpty
    private String toAccNo;

    private UserType toUserType;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "(01[3-9]\\d{8})$", message = "Account number should be numbers only.")
    @Size(max = 11, min = 11, message = "Account number should be 11 digits.")
    private String fromAccNo;

    private UserType fromUserType;

    @NotNull
    @NotEmpty
    private BigDecimal txnAmount;

    @NotNull
    private TxnType txnType;

    private String reference;

    @NotNull
    @NotEmpty
    @Size(max = 4, min = 4, message = "Pin number should be 4 digits.")
    private String pin;

}
