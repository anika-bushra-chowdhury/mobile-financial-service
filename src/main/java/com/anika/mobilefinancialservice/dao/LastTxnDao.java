package com.anika.mobilefinancialservice.dao;

import com.anika.mobilefinancialservice.entity.LastTxnEntity;


public interface LastTxnDao {
    LastTxnEntity save(LastTxnEntity lastTxnEntity);
}
