package com.anika.mobilefinancialservice.dto;

import com.anika.mobilefinancialservice.enums.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author atiQue
 * @since 11'Aug 2022 at 9:46 PM
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoreTransaction {

    private String accountNumber;

    private UserType userType;

    private BigDecimal amount;

    private DebitOrCredit debitOrCredit;

    private TxnType txnType;

    private TxnCategory txnCategory;

    private String txnId;

    private String nrNumber;

    private SenderOrReceiver senderOrReceiver;

    private BigDecimal preBalance;

    private BigDecimal newBalance;

    private BigDecimal preAvailableBalance;

    private BigDecimal newAvailableBalance;

    private Integer order;
}
