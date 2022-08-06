package com.anika.mobilefinancialservice.dao;

import com.anika.mobilefinancialservice.entity.TxnLogEntity;
import com.anika.mobilefinancialservice.repositories.TxnLogRepository;
import org.springframework.stereotype.Repository;

@Repository
public class TxnLogDaoImpl implements TxnLogDao {

    private final TxnLogRepository txnLogRepository;

    public TxnLogDaoImpl(TxnLogRepository txnLogRepository) {
        this.txnLogRepository = txnLogRepository;
    }

    @Override
    public TxnLogEntity save(TxnLogEntity txnLogEntity) {
        return txnLogRepository.save(txnLogEntity);
    }
}
