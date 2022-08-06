
package com.anika.mobilefinancialservice.service;

import com.anika.mobilefinancialservice.dto.TxnCommonRequest;
import com.anika.mobilefinancialservice.dto.TxnCommonResponse;

public interface TxnService {

    TxnCommonResponse executeTxn(TxnCommonRequest txnRequest);

}
