package com.anika.mobilefinancialservice.service;

import com.anika.mobilefinancialservice.dto.TxnCommonRequest;
import com.anika.mobilefinancialservice.dto.UserTypeBaseCalculatedFeeComm;

/**
 * @author atiQue
 * @since 12'Aug 2022 at 12:42 AM
 */

public interface TransactionValidator {

    void validateAccounts(TxnCommonRequest request);

    void validateDebitCredit(UserTypeBaseCalculatedFeeComm feeComm, TxnCommonRequest request);
}
