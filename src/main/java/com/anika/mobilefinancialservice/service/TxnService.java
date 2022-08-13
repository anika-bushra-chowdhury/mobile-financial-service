
package com.anika.mobilefinancialservice.service;

import com.anika.mobilefinancialservice.dto.TxnCommonRequest;
import com.anika.mobilefinancialservice.dto.TxnCommonResponse;
import com.anika.mobilefinancialservice.dto.TxnHistoryRequest;
import com.anika.mobilefinancialservice.entity.TxnLogEntity;
import org.springframework.data.domain.Page;

public interface TxnService {

    TxnCommonResponse executeTxn(TxnCommonRequest txnRequest);

    Page<TxnLogEntity> getTxnHistory(TxnHistoryRequest request);
}
