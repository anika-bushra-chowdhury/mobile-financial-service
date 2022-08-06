
package com.anika.mobilefinancialservice.service;

import com.anika.mobilefinancialservice.dto.FeeResource;
import com.anika.mobilefinancialservice.enums.TxnType;

import java.util.List;

public interface FeeCommService {

    List<FeeResource> getByTxnType(TxnType txnType);
}
