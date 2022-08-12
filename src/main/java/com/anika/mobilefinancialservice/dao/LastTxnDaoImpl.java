package com.anika.mobilefinancialservice.dao;

import com.anika.mobilefinancialservice.entity.LastTxnEntity;
import com.anika.mobilefinancialservice.repositories.LastTxnRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public List<LastTxnEntity> save(List<LastTxnEntity> entities) {
        return lastTxnRepository.saveAll(entities);
    }

    @Override
    public LastTxnEntity findByPhnNos(String phnNo) {
        return lastTxnRepository.findByAccountNumber(phnNo);
    }

    @Override
    public List<LastTxnEntity> findByPhnNos(List<String> phnNos) {
        return lastTxnRepository.findByAccountNumberIn(phnNos);
    }
}
