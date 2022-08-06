package com.anika.mobilefinancialservice.dto;

import com.anika.mobilefinancialservice.enums.*;
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
public class LastTxn {

    @NotNull
    @NotEmpty
    @Pattern(regexp = "([0-9]*)", message = "Account number should be numbers only.")
    @Size(max = 11, min = 11, message = "Account number should be 11 digits.")
    private String phoneNumber;

    private TxnType txnType;

    private UserType userType;

    private SenderOrReceiver senderOrReceiver;

    private DebitOrCredit debitOrCredit;

    private TxnCategory txnCategory;

    private BigDecimal amount;

    private BigDecimal availableBalance;

    private BigDecimal balance;

    private String txnId;

    private String nrNumber;
}
