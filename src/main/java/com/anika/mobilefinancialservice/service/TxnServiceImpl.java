package com.anika.mobilefinancialservice.service;

import com.anika.mobilefinancialservice.dao.FeeCommDao;
import com.anika.mobilefinancialservice.dto.TxnCommonRequest;
import com.anika.mobilefinancialservice.dto.TxnCommonResponse;
import com.anika.mobilefinancialservice.entity.FeeCommEntity;
import com.anika.mobilefinancialservice.entity.LastTxnEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Service
public class TxnServiceImpl implements TxnService {
    private final FeeCommDao feeCommDao;
    private final TxnHelperService txnHelperService;



    public TxnServiceImpl(FeeCommDao feeCommDao, TxnHelperService txnHelperService) {
        this.feeCommDao = feeCommDao;
        this.txnHelperService = txnHelperService;
    }

    @Override
    @Transactional
    public TxnCommonResponse executeTxn(TxnCommonRequest txnRequest) {
        List<LastTxnEntity> orgTxnEntities = txnHelperService.generateOrgTxn(txnRequest);

        List<FeeCommEntity> feeResources = feeCommDao.findAllByTxnType(txnRequest.getTxnType());

        if(!feeResources.isEmpty()){

        }

        return null;
    }


}
