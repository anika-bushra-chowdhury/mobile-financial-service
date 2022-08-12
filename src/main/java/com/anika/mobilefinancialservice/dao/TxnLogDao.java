package com.anika.mobilefinancialservice.dao;

import com.anika.mobilefinancialservice.entity.TxnLogEntity;

import java.util.List;


public interface TxnLogDao {

    TxnLogEntity save(TxnLogEntity txnLogEntity);

    List<TxnLogEntity> save(List<TxnLogEntity> entities);
}
