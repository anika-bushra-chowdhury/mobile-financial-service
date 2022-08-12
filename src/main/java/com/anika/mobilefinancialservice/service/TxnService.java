
package com.anika.mobilefinancialservice.service;

import com.anika.mobilefinancialservice.dto.TxnCommonRequest;
import com.anika.mobilefinancialservice.dto.TxnCommonResponse;
import com.anika.mobilefinancialservice.dto.User;
import com.anika.mobilefinancialservice.entity.TxnLogEntity;

import java.util.List;

public interface TxnService {

    TxnCommonResponse executeTxn(TxnCommonRequest txnRequest);

    List<TxnLogEntity> getTxnHistory(String phnNO);
}
