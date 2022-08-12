package com.anika.mobilefinancialservice.dto;

import com.anika.mobilefinancialservice.enums.SenderOrReceiver;
import com.anika.mobilefinancialservice.enums.TxnCategory;
import com.anika.mobilefinancialservice.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author atiQue
 * @since 12'Aug 2022 at 1:09 AM
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CalculatedFeeComm {

    private TxnCategory txnCategory;

    private BigDecimal amount;

    private UserType userType;

    private String name;
}
