package com.anika.mobilefinancialservice.dto;

import com.anika.mobilefinancialservice.enums.*;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Column;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LastTxn {

    @NotNull
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
