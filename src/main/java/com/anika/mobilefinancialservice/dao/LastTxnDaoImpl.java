package com.anika.mobilefinancialservice.dao;

import com.anika.mobilefinancialservice.entity.LastTxnEntity;
import com.anika.mobilefinancialservice.repositories.LastTxnRepository;
import org.springframework.stereotype.Repository;

@Repository
public class LastTxnDaoImpl implements LastTxnDao {

    private final LastTxnRepository lastTxnRepository;

    public LastTxnDaoImpl(LastTxnRepository lastTxnRepository) {
        this.lastTxnRepository = lastTxnRepository;
    }

    @Override
    public LastTxnEntity save(LastTxnEntity lastTxnEntity) {
        return lastTxnRepository.save(lastTxnEntity);
    }
}
