package com.anika.mobilefinancialservice.dao;

import com.anika.mobilefinancialservice.entity.TxnLogEntity;
import com.anika.mobilefinancialservice.repositories.TxnLogRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

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

    @Override
    public List<TxnLogEntity> save(List<TxnLogEntity> entities) {
        return (List<TxnLogEntity>) txnLogRepository.saveAll(entities);
    }

    @Override
    public Page<TxnLogEntity> getAll(String accNo, Date fromDate, Date toDate, Pageable paging) {

        if (fromDate == null && toDate == null) {
            return txnLogRepository.findAllByAccountNumber(accNo, paging);
        } else if (fromDate != null && toDate != null) {
            return txnLogRepository.findAllByAccountNumberAndApprovalDtGreaterThanEqualAndApprovalDtLessThanEqual(accNo, fromDate, toDate, paging);
        } else if (fromDate != null) {
            return txnLogRepository.findAllByAccountNumberAndApprovalDtGreaterThanEqual(accNo, fromDate, paging);
        } else {
            return txnLogRepository.findAllByAccountNumberAndApprovalDtLessThanEqual(accNo, toDate, paging);
        }
    }
}
