package com.anika.mobilefinancialservice.dto;

import com.anika.mobilefinancialservice.enums.TxnType;
import lombok.*;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TxnCommonResponse {

    private String toAccNo;

    private String responseMessage;

    private TxnType txnType;

    private String reference;

    private BigDecimal txnAmount;

    private BigDecimal fee;

    private BigDecimal commission;

    private BigDecimal balance;

    private String txnId;

}
