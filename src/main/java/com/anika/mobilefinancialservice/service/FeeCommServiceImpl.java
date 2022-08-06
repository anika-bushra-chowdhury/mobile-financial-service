package com.anika.mobilefinancialservice.service;

import com.anika.mobilefinancialservice.dao.FeeCommDao;
import com.anika.mobilefinancialservice.dto.FeeResource;
import com.anika.mobilefinancialservice.entity.FeeCommEntity;
import com.anika.mobilefinancialservice.enums.TxnType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class FeeCommServiceImpl implements FeeCommService {
    private final FeeCommDao feeCommDao;

    public FeeCommServiceImpl(FeeCommDao feeCommDao) {
        this.feeCommDao = feeCommDao;
    }

    @Override
    public List<FeeResource> getByTxnType(TxnType txnType) {
        List<FeeCommEntity> feeCommEntities = feeCommDao.findAllByTxnType(txnType);

        List<FeeResource> feeResources = new ArrayList<>();
        for (FeeCommEntity feeCommEntity : feeCommEntities) {
            FeeResource feeResource = prepareFeeResources(feeCommEntity);
            feeResources.add(feeResource);
        }
        return feeResources;
    }

    private FeeResource prepareFeeResources(FeeCommEntity entity) {
        return FeeResource.builder()
                .txnType(entity.getTxnType())
                .txnCategory(entity.getTxnCategory())
                .feeName(entity.getFeeName())
                .feeRate(entity.getFeeRate())
                .feeType(entity.getFeeType())
                .userType(entity.getUserType())
                .senderOrReceiver(entity.getSenderOrReceiver())
                .maxAmount(entity.getMaxAmount())
                .minAmount(entity.getMinAmount())
                .build();
    }
}
