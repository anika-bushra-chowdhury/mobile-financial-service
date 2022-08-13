package com.anika.mobilefinancialservice.dao;

import com.anika.mobilefinancialservice.entity.TxnLogEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;


public interface TxnLogDao {

    TxnLogEntity save(TxnLogEntity txnLogEntity);

    List<TxnLogEntity> save(List<TxnLogEntity> entities);

    Page<TxnLogEntity> getAll(String accNo, Date fromDate, Date toDate, Pageable paging);
}
