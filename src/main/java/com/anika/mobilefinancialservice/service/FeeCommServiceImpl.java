package com.anika.mobilefinancialservice.service;

import com.anika.mobilefinancialservice.dao.FeeCommDao;
import com.anika.mobilefinancialservice.dto.FeeCommResource;
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
    public List<FeeCommResource> getByTxnType(TxnType txnType) {
        List<FeeCommEntity> feeCommEntities = feeCommDao.findAllByTxnType(txnType);

        List<FeeCommResource> feeCommResources = new ArrayList<>();
        for (FeeCommEntity feeCommEntity : feeCommEntities) {
            FeeCommResource feeCommResource = prepareFeeResources(feeCommEntity);
            feeCommResources.add(feeCommResource);
        }
        return feeCommResources;
    }

    private FeeCommResource prepareFeeResources(FeeCommEntity entity) {
        return FeeCommResource.builder()
                .txnType(entity.getTxnType())
                .txnCategory(entity.getTxnCategory())
                .name(entity.getName())
                .rate(entity.getRate())
                .rateType(entity.getRateType())
                .userType(entity.getUserType())
                .senderOrReceiver(entity.getSenderOrReceiver())
                .maxAmount(entity.getMaxAmount())
                .minAmount(entity.getMinAmount())
                .build();
    }
}
