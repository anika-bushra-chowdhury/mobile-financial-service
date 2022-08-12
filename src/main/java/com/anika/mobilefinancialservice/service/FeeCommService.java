
package com.anika.mobilefinancialservice.service;

import com.anika.mobilefinancialservice.dto.FeeCommResource;
import com.anika.mobilefinancialservice.enums.TxnType;

import java.util.List;

public interface FeeCommService {

    List<FeeCommResource> getByTxnType(TxnType txnType);
}
