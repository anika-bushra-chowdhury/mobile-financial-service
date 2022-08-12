
package com.anika.mobilefinancialservice.service;

import com.anika.mobilefinancialservice.dto.FeeCommResource;
import com.anika.mobilefinancialservice.dto.TxnCommonRequest;
import com.anika.mobilefinancialservice.dto.TxnCommonResponse;
import com.anika.mobilefinancialservice.entity.LastTxnEntity;
import com.anika.mobilefinancialservice.enums.TxnType;

import java.math.BigDecimal;
import java.util.List;

public interface TxnHelperService {

    List<LastTxnEntity> generateOrgTxn(TxnCommonRequest request, BigDecimal totalAmount);

    void generateFeeCommTxnLog(List<LastTxnEntity> orgTxnEntities, TxnCommonRequest txnRequest, BigDecimal fee, BigDecimal commission);

    BigDecimal calculateFeeComm(FeeCommResource feeCommResource, BigDecimal txnAmount);

    TxnCommonResponse prepareTxnResponse(List<LastTxnEntity> orgTxnEntities, BigDecimal fee, BigDecimal commission, TxnType txnType);
}
