package com.anika.mobilefinancialservice.dto;

import com.anika.mobilefinancialservice.enums.TxnType;
import com.anika.mobilefinancialservice.enums.UserType;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TxnCommonRequest {

    @NotNull
    @NotEmpty
    @Pattern(regexp = "([0-9]*)", message = "Account number should be numbers only.")
    @Size(max = 11, min = 11, message = "Account number should be 11 digits.")
    private String toAccNo;

    private UserType toUserType;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "([0-9]*)", message = "Account number should be numbers only.")
    private String fromAccNo;

    private UserType fromUserType;

    private BigDecimal txnAmount;

    private TxnType txnType;

    @Pattern(regexp = "([0-9]*)", message = "Pin number should be numbers only.")
    @Size(max = 4, min = 4, message = "Pin number should be 4 digits.")
    private String pin;

}
