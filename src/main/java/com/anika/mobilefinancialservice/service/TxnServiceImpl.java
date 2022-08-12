package com.anika.mobilefinancialservice.service;

import com.anika.mobilefinancialservice.dao.FeeCommDao;
import com.anika.mobilefinancialservice.dto.FeeCommResource;
import com.anika.mobilefinancialservice.dto.TxnCommonRequest;
import com.anika.mobilefinancialservice.dto.TxnCommonResponse;
import com.anika.mobilefinancialservice.entity.LastTxnEntity;
import com.anika.mobilefinancialservice.entity.TxnLogEntity;
import com.anika.mobilefinancialservice.enums.TxnCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;


@Slf4j
@Service
public class TxnServiceImpl implements TxnService {
    private final FeeCommDao feeCommDao;
    private final TxnHelperService txnHelperService;
    private final FeeCommService feeCommService;


    public TxnServiceImpl(FeeCommDao feeCommDao, TxnHelperService txnHelperService, FeeCommService feeCommService) {
        this.feeCommDao = feeCommDao;
        this.txnHelperService = txnHelperService;
        this.feeCommService = feeCommService;
    }

    @Override
    @Transactional
    public TxnCommonResponse executeTxn(TxnCommonRequest txnRequest) {

        BigDecimal fee = BigDecimal.ZERO;
        BigDecimal commission = BigDecimal.ZERO;

        List<FeeCommResource> feeCommResources = feeCommService.getByTxnType(txnRequest.getTxnType());

        if (!feeCommResources.isEmpty()) {
            for (FeeCommResource feeCommResource : feeCommResources) {
                if (feeCommResource.getTxnCategory().equals(TxnCategory.FEE)) {
                    fee = txnHelperService.calculateFeeComm(feeCommResource, txnRequest.getTxnAmount());
                } else if (feeCommResource.getTxnCategory().equals(TxnCategory.COMMISSION)) {
                    commission = txnHelperService.calculateFeeComm(feeCommResource, txnRequest.getTxnAmount());
                }
            }
        }

        BigDecimal totalAmount = txnRequest.getTxnAmount().add(fee);
        List<LastTxnEntity> orgTxnEntities = txnHelperService.generateOrgTxn(txnRequest, totalAmount);

        TxnCommonResponse txnCommonResponse = txnHelperService.prepareTxnResponse(orgTxnEntities, fee, commission);

        txnHelperService.generateFeeCommTxnLog(orgTxnEntities, txnRequest, fee, commission);

        return txnCommonResponse;
    }

    @Override
    public List<TxnLogEntity> getTxnHistory(String phnNO) {
        return null;
    }


}
