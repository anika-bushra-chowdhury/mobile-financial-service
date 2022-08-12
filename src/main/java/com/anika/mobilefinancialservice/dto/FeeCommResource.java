package com.anika.mobilefinancialservice.dto;

import com.anika.mobilefinancialservice.enums.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeeCommResource {

    private TxnType txnType;

    private UserType userType;

    private SenderOrReceiver senderOrReceiver;

    private TxnCategory txnCategory;

    private RateType rateType;

    private String name;

    private BigDecimal rate;

    private BigDecimal minAmount;

    private BigDecimal maxAmount;
}
