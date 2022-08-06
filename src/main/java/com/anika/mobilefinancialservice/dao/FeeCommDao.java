package com.anika.mobilefinancialservice.dao;

import com.anika.mobilefinancialservice.entity.FeeCommEntity;
import com.anika.mobilefinancialservice.enums.TxnType;

import java.util.List;


public interface FeeCommDao {
    List<FeeCommEntity> findAllByTxnType(TxnType txnType);
}
