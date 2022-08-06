package com.anika.mobilefinancialservice.service;

import com.anika.mobilefinancialservice.dao.FeeCommDao;
import com.anika.mobilefinancialservice.dto.FeeResource;
import com.anika.mobilefinancialservice.dto.TxnCommonRequest;
import com.anika.mobilefinancialservice.dto.TxnCommonResponse;
import com.anika.mobilefinancialservice.entity.LastTxnEntity;
import com.anika.mobilefinancialservice.enums.FeeType;
import com.anika.mobilefinancialservice.enums.TxnCategory;
import com.anika.mobilefinancialservice.utils.Constants;
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

        BigDecimal fee = new BigDecimal(0);

        List<FeeResource> feeResources = feeCommService.getByTxnType(txnRequest.getTxnType());

        if (!feeResources.isEmpty()) {
            for (FeeResource feeResource : feeResources) {
                if (feeResource.getTxnCategory().equals(TxnCategory.FEE.getValue())) {
                    fee = calculateFee(feeResource, txnRequest.getTxnAmount());
                }
            }
        }

        BigDecimal totalAmount = txnRequest.getTxnAmount().add(fee);

        List<LastTxnEntity> orgTxnEntities = txnHelperService.generateOrgTxn(txnRequest, totalAmount);

        txnHelperService.generateFeeTxnLog(orgTxnEntities, txnRequest, fee);
        return null;
    }


    private BigDecimal calculateFee(FeeResource feeResource, BigDecimal txnAmount) {
        BigDecimal fee = new BigDecimal(0);

        if (feeResource.getFeeType().equals(FeeType.FIXED)) {
            fee = feeResource.getFeeRate();
        } else {
            fee = txnAmount.multiply(feeResource.getFeeRate()).divide(Constants.ONE_HUNDRED);
        }

        return fee;
    }


}
