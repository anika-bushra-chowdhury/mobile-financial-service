package com.anika.mobilefinancialservice.dto;

import com.anika.mobilefinancialservice.enums.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeeResource {

    private TxnType txnType;

    private UserType userType;

    private SenderOrReceiver senderOrReceiver;

    private TxnCategory txnCategory;

    private FeeType feeType;

    private String feeName;

    private BigDecimal feeRate;

    private BigDecimal minAmount;

    private BigDecimal maxAmount;
}
