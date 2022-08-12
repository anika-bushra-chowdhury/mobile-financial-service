package com.anika.mobilefinancialservice.service;

import com.anika.mobilefinancialservice.config.BalanceLimitConfig;
import com.anika.mobilefinancialservice.dao.UserDao;
import com.anika.mobilefinancialservice.dto.Balance;
import com.anika.mobilefinancialservice.dto.CalculatedFeeComm;
import com.anika.mobilefinancialservice.dto.TxnCommonRequest;
import com.anika.mobilefinancialservice.dto.UserTypeBaseCalculatedFeeComm;
import com.anika.mobilefinancialservice.entity.UserEntity;
import com.anika.mobilefinancialservice.config.TransactionConfig;
import com.anika.mobilefinancialservice.enums.SenderOrReceiver;
import com.anika.mobilefinancialservice.enums.TxnCategory;
import com.anika.mobilefinancialservice.enums.UserType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author atiQue
 * @since 12'Aug 2022 at 12:43 AM
 */

@Service
@Slf4j
public class TransactionValidatorImpl implements TransactionValidator {

    private final UserDao userDao;
    private final UserService userService;

    public TransactionValidatorImpl(UserDao userDao, UserService userService) {
        this.userDao = userDao;
        this.userService = userService;
    }

    @Override
    public void validateAccounts(TxnCommonRequest request) {

        TransactionConfig txnConfig = TransactionConfig.getByTxnType(request.getTxnType());

        if (txnConfig.getSenderUserType() != null) {
            verifyAccount(request, SenderOrReceiver.SENDER);
        } else if (txnConfig.getReceiverUserType() != null) {
            verifyAccount(request, SenderOrReceiver.RECEIVER);
        }
    }

    private void verifyAccount(TxnCommonRequest request, SenderOrReceiver senderOrReceiver) {

        TransactionConfig txnConfig = TransactionConfig.getByTxnType(request.getTxnType());
        String accountNo = null;
        UserType requiredUserType = null;

        switch (senderOrReceiver) {
            case SENDER -> {
                accountNo = request.getFromAccNo();
                requiredUserType = txnConfig.getSenderUserType();
            }
            case RECEIVER -> {
                accountNo = request.getToAccNo();
                requiredUserType = txnConfig.getReceiverUserType();
            }
        }


        if (!StringUtils.hasLength(accountNo)) {
            log.error("{} Account No. is null", senderOrReceiver);
            throw new IllegalArgumentException(senderOrReceiver + " Account No. is null");
        }

        UserEntity userEntity = userDao.getByPhnNo(accountNo);
        if (userEntity == null) {
            log.error("{} Account does not exist.", senderOrReceiver);
            throw new IllegalArgumentException(senderOrReceiver + " Account does not exist.");
        }

        if (userEntity.getUserType() != requiredUserType) {
            log.error("{} Account userType not allowed for {}.", senderOrReceiver, request.getTxnType());
            throw new IllegalArgumentException(senderOrReceiver + " Account userType not allowed for " + request.getTxnType());
        }
    }

    @Override
    public void validateDebitCredit(UserTypeBaseCalculatedFeeComm feeComm, TxnCommonRequest request) {

        TransactionConfig txnConfig = TransactionConfig.getByTxnType(request.getTxnType());

        if (txnConfig.getSenderUserType() != null) {
            validateDCUtil(feeComm, request, SenderOrReceiver.SENDER);
        } else if (txnConfig.getReceiverUserType() != null) {
            validateDCUtil(feeComm, request, SenderOrReceiver.RECEIVER);
        }
    }

    private void validateDCUtil(UserTypeBaseCalculatedFeeComm feeComm, TxnCommonRequest request, SenderOrReceiver senderOrReceiver) {

        TransactionConfig txnConfig = TransactionConfig.getByTxnType(request.getTxnType());
        List<CalculatedFeeComm> feeComms = null;
        Balance balance = null;
        BalanceLimitConfig balanceLimit = null;

        switch (senderOrReceiver) {
            case SENDER -> {
                balanceLimit = BalanceLimitConfig.getByUserType(txnConfig.getSenderUserType());
                feeComms = feeComm.getSenderFeeComms();
                balance = userService.getBalance(request.getFromAccNo());
            }
            case RECEIVER -> {
                balanceLimit = BalanceLimitConfig.getByUserType(txnConfig.getReceiverUserType());
                feeComms = feeComm.getReceiverFeeComms();
                balance = userService.getBalance(request.getToAccNo());
            }
        }

        BigDecimal totalFee = feeComms.stream().filter(e -> e.getTxnCategory() == TxnCategory.FEE).map(CalculatedFeeComm::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalComm = feeComms.stream().filter(e -> e.getTxnCategory() == TxnCategory.COMMISSION).map(CalculatedFeeComm::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal newAvailableBalance = balance.getAvailableBalance().subtract(request.getTxnAmount()).subtract(totalFee).add(totalComm);

        if (balanceLimit.getMinBalance() != null && newAvailableBalance.compareTo(balanceLimit.getMinBalance()) < 0) {
            log.error("Minimum balance violated for {} userType: {}.", senderOrReceiver, txnConfig.getSenderUserType());
            throw new IllegalArgumentException("Minimum balance violated for " + senderOrReceiver + " userType: " + txnConfig.getSenderUserType());
        }

        if (balanceLimit.getMaxBalance() != null && newAvailableBalance.compareTo(balanceLimit.getMaxBalance()) > 0) {
            log.error("Maximum balance violated for {} userType: {}.", senderOrReceiver, txnConfig.getSenderUserType());
            throw new IllegalArgumentException("Maximum balance violated for " + senderOrReceiver + " userType: " + txnConfig.getSenderUserType());
        }
    }
}
