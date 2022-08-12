package com.anika.mobilefinancialservice.service;

import com.anika.mobilefinancialservice.dto.TxnCommonRequest;

/**
 * @author atiQue
 * @since 12'Aug 2022 at 12:39 AM
 */

public interface TxnServiceV2 {

    void processTransaction(TxnCommonRequest request);

}
