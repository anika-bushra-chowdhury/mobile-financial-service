package com.anika.mobilefinancialservice.service;

import com.anika.mobilefinancialservice.dao.LastTxnDao;
import com.anika.mobilefinancialservice.dto.LastTxn;
import com.anika.mobilefinancialservice.entity.LastTxnEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class LastTxnServiceImpl implements LastTxnService {
    private final LastTxnDao lastTxnDao;

    public LastTxnServiceImpl(LastTxnDao lastTxnDao) {
        this.lastTxnDao = lastTxnDao;
    }

    @Override
    public LastTxnEntity getLastTxn(String phnNo) {

        LastTxnEntity lastTxnEntity = new LastTxnEntity();

        try {
            lastTxnEntity = lastTxnDao.findByPhnNos(phnNo);
        } catch (Exception e) {
            log.error("Error while retrieving data of {}", phnNo, e);
        }

        return lastTxnEntity;
    }


    @Override
    public LastTxnEntity updateLastTxnEntity(LastTxnEntity lastTxn) {
        return lastTxnDao.save(lastTxn);
    }


    private LastTxn prepareLastTxn(LastTxnEntity entity) {
        return LastTxn.builder()
                .phoneNumber(entity.getAccountNumber())
                .txnType(entity.getTxnType())
                .userType(entity.getUserType())
                .senderOrReceiver(entity.getSenderOrReceiver())
                .txnCategory(entity.getTxnCategory())
                .amount(entity.getAmount())
                .availableBalance(entity.getAvailableBalance())
                .balance(entity.getBalance())
                .debitOrCredit(entity.getDebitOrCredit())
                .txnId(entity.getTxnId())
                .nrNumber(entity.getNrNumber())
                .build();
    }
}
