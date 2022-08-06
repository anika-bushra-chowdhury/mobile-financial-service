package com.anika.mobilefinancialservice.dao;

import com.anika.mobilefinancialservice.entity.TxnLogEntity;


public interface TxnLogDao {
    TxnLogEntity save(TxnLogEntity txnLogEntity);
}
