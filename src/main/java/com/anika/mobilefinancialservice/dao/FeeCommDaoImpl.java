package com.anika.mobilefinancialservice.dao;

import com.anika.mobilefinancialservice.entity.FeeCommEntity;
import com.anika.mobilefinancialservice.enums.TxnType;
import com.anika.mobilefinancialservice.repositories.FeeCommRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FeeCommDaoImpl implements FeeCommDao {

    private final FeeCommRepository feeCommRepository;

    public FeeCommDaoImpl(FeeCommRepository feeCommRepository) {
        this.feeCommRepository = feeCommRepository;
    }

    @Override
    public List<FeeCommEntity> findAllByTxnType(TxnType txnType) {
        return feeCommRepository.findAllByTxnType(txnType);
    }
}
