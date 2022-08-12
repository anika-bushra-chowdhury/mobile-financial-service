package com.anika.mobilefinancialservice.dao;

import com.anika.mobilefinancialservice.entity.LastTxnEntity;

import java.util.List;


public interface LastTxnDao {

    LastTxnEntity save(LastTxnEntity lastTxnEntity);

    List<LastTxnEntity> save(List<LastTxnEntity> entities);

    LastTxnEntity findByPhnNos(String phnNo);

    List<LastTxnEntity> findByPhnNos(List<String> phnNos);
}
