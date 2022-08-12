package com.anika.mobilefinancialservice.service;

import com.anika.mobilefinancialservice.dto.UserTypeBaseCalculatedFeeComm;
import com.anika.mobilefinancialservice.enums.TxnType;

import java.math.BigDecimal;

/**
 * @author atiQue
 * @since 12'Aug 2022 at 1:12 AM
 */

public interface CalculateFeeCommService {

    UserTypeBaseCalculatedFeeComm calculateFeeComm(TxnType txnType, BigDecimal txnAmount);
}
