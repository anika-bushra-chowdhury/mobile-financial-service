
package com.anika.mobilefinancialservice.service;

import com.anika.mobilefinancialservice.dto.LastTxn;
import com.anika.mobilefinancialservice.entity.LastTxnEntity;

public interface LastTxnService {

    LastTxnEntity getLastTxn(String phnNO);

    LastTxnEntity updateLastTxnEntity(LastTxnEntity lastTxn);
}
