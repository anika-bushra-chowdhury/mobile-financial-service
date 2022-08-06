
package com.anika.mobilefinancialservice.service;

import com.anika.mobilefinancialservice.dto.TxnCommonRequest;
import com.anika.mobilefinancialservice.entity.LastTxnEntity;

import java.util.List;

public interface TxnHelperService {

//    List<User> getUserAccounts(TxnCommonRequest request);

//    LastTxn getUserLastTxn(String phoneNo);

    List<LastTxnEntity> generateOrgTxn(TxnCommonRequest request);
}
