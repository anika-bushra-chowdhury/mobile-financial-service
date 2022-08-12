package com.anika.mobilefinancialservice.service;

import com.anika.mobilefinancialservice.dto.CalculatedFeeComm;
import com.anika.mobilefinancialservice.dto.FeeCommResource;
import com.anika.mobilefinancialservice.dto.UserTypeBaseCalculatedFeeComm;
import com.anika.mobilefinancialservice.enums.TxnType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * @author atiQue
 * @since 12'Aug 2022 at 1:13 AM
 */

@Service
@Slf4j
public class CalculateFeeCommServiceImpl implements CalculateFeeCommService {

    private final FeeCommService feeCommService;

    public CalculateFeeCommServiceImpl(FeeCommService feeCommService) {
        this.feeCommService = feeCommService;
    }

    @Override
    public UserTypeBaseCalculatedFeeComm calculateFeeComm(TxnType txnType, BigDecimal txnAmount) {

        UserTypeBaseCalculatedFeeComm result = initiateUserTypeBasedCalculatedFeeComm();

        List<FeeCommResource> feeCommResources = feeCommService.getByTxnType(txnType);

        if (feeCommResources.isEmpty()) {
            return result;
        }

        for (FeeCommResource resource : feeCommResources) {
            BigDecimal amount = BigDecimal.ZERO;

            switch (resource.getRateType()) {
                case RATE ->
                        amount = txnAmount.multiply(resource.getRate()).divide(new BigDecimal("100"), RoundingMode.DOWN);
                case FIXED -> amount = resource.getRate();
            }

            switch (resource.getSenderOrReceiver()) {
                case SENDER -> result.getSenderFeeComms().add(getCalculatedFeeComm(resource, amount));
                case RECEIVER -> result.getReceiverFeeComms().add(getCalculatedFeeComm(resource, amount));
            }
        }

        return result;
    }

    private UserTypeBaseCalculatedFeeComm initiateUserTypeBasedCalculatedFeeComm() {
        List<CalculatedFeeComm> senderFeeComms = new ArrayList<>();
        List<CalculatedFeeComm> receiverFeeComms = new ArrayList<>();

        return UserTypeBaseCalculatedFeeComm.builder()
                .senderFeeComms(senderFeeComms)
                .receiverFeeComms(receiverFeeComms).build();
    }

    private CalculatedFeeComm getCalculatedFeeComm(FeeCommResource resource, BigDecimal amount) {
        return CalculatedFeeComm.builder()
                .amount(amount)
                .txnCategory(resource.getTxnCategory())
                .name(resource.getName())
                .userType(resource.getUserType())
                .build();
    }
}
