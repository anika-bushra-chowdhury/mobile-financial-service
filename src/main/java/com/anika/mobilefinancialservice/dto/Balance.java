package com.anika.mobilefinancialservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author atiQue
 * @since 11'Aug 2022 at 8:47 PM
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Balance {

    private String balance;
    private String availableBalance;
}
