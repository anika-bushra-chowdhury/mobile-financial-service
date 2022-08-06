package com.anika.mobilefinancialservice.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TxnCommonResponse {

    private String responseMessage;

    private BigDecimal txnAmount;

    private BigDecimal availableBalance;

    private String txnId;

    Map<String, String> otherAmounts;
}
