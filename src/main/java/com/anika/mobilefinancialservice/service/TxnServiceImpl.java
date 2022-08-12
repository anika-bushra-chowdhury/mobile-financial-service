package com.anika.mobilefinancialservice.service;

import com.anika.mobilefinancialservice.dao.FeeCommDao;
import com.anika.mobilefinancialservice.dto.FeeCommResource;
import com.anika.mobilefinancialservice.dto.TxnCommonRequest;
import com.anika.mobilefinancialservice.dto.TxnCommonResponse;
import com.anika.mobilefinancialservice.entity.LastTxnEntity;
import com.anika.mobilefinancialservice.enums.SenderOrReceiver;
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

        TxnCommonResponse txnCommonResponse = prepareTxnResponse(orgTxnEntities, fee, commission);

        txnHelperService.generateFeeCommTxnLog(orgTxnEntities, txnRequest, fee, commission);

        return txnCommonResponse;
    }


    private TxnCommonResponse prepareTxnResponse(List<LastTxnEntity> orgTxnEntities, BigDecimal fee, BigDecimal commission) {
        LastTxnEntity senderLastTxn = new LastTxnEntity();
        for (LastTxnEntity lastTxnEntity : orgTxnEntities) {
            if (lastTxnEntity.getSenderOrReceiver() == SenderOrReceiver.SENDER) {
                senderLastTxn = lastTxnEntity;
            }
        }
        return TxnCommonResponse.builder()
                .txnType(senderLastTxn.getTxnType())
                .txnAmount(senderLastTxn.getAmount())
                .fee(fee)
                .commission(commission)
                .txnId(senderLastTxn.getTxnId())
                .balance(senderLastTxn.getBalance())
                .build();
    }

}
