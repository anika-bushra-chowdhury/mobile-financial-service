package com.anika.mobilefinancialservice.service;

import com.anika.mobilefinancialservice.dao.TxnLogDao;
import com.anika.mobilefinancialservice.dto.FeeCommResource;
import com.anika.mobilefinancialservice.dto.TxnCommonRequest;
import com.anika.mobilefinancialservice.dto.TxnCommonResponse;
import com.anika.mobilefinancialservice.entity.LastTxnEntity;
import com.anika.mobilefinancialservice.entity.TxnLogEntity;
import com.anika.mobilefinancialservice.enums.DebitOrCredit;
import com.anika.mobilefinancialservice.enums.SenderOrReceiver;
import com.anika.mobilefinancialservice.enums.TxnCategory;
import com.anika.mobilefinancialservice.enums.TxnType;
import com.anika.mobilefinancialservice.utils.Constants;
import com.anika.mobilefinancialservice.utils.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Slf4j
@Service
public class TxnHelperServiceImpl implements TxnHelperService {
    private final LastTxnService lastTxnService;
    private final TxnLogDao txnLogDao;

    public TxnHelperServiceImpl(LastTxnService lastTxnService, TxnLogDao txnLogDao) {
        this.lastTxnService = lastTxnService;
        this.txnLogDao = txnLogDao;
    }

    @Override
    @Transactional
    public List<LastTxnEntity> generateOrgTxn(TxnCommonRequest request, BigDecimal totalAmount) {

        List<LastTxnEntity> lastTxnEntities = new ArrayList<>();

        if (request.getTxnType() != TxnType.B2B_AG && request.getTxnType() != TxnType.REDEEM_AG) {

            LastTxnEntity senderLastTxn = lastTxnService.getLastTxn(request.getFromAccNo());

            if (senderLastTxn.getBalance().compareTo(totalAmount) >= 0) {
                LastTxnEntity receiverLastTxn = lastTxnService.getLastTxn(request.getToAccNo());

                String nrNumber = Util.generateNrNUmber();

                prepareLastTxn(senderLastTxn, SenderOrReceiver.SENDER, nrNumber + "1", nrNumber, request);
                prepareLastTxn(receiverLastTxn, SenderOrReceiver.RECEIVER, nrNumber + "2", nrNumber, request);

                lastTxnEntities.add(lastTxnService.updateLastTxnEntity(senderLastTxn));
                writeTxnLog(senderLastTxn);

                lastTxnEntities.add(lastTxnService.updateLastTxnEntity(receiverLastTxn));
                writeTxnLog(receiverLastTxn);
            }

        } else if (request.getTxnType() == TxnType.B2B_AG) {

            LastTxnEntity receiverLastTxn = lastTxnService.getLastTxn(request.getToAccNo());

            String nrNumber = Util.generateNrNUmber();

            prepareLastTxn(receiverLastTxn, SenderOrReceiver.RECEIVER, nrNumber + "1", nrNumber, request);

            lastTxnEntities.add(lastTxnService.updateLastTxnEntity(receiverLastTxn));
            writeTxnLog(receiverLastTxn);

        } else if (request.getTxnType() == TxnType.REDEEM_AG) {

            LastTxnEntity senderLastTxn = lastTxnService.getLastTxn(request.getFromAccNo());

            String nrNumber = Util.generateNrNUmber();

            prepareLastTxn(senderLastTxn, SenderOrReceiver.SENDER, nrNumber + "1", nrNumber, request);

            lastTxnEntities.add(lastTxnService.updateLastTxnEntity(senderLastTxn));
            writeTxnLog(senderLastTxn);
        }

        return lastTxnEntities;
    }

    @Override
    public void generateFeeCommTxnLog(List<LastTxnEntity> orgTxnEntities, TxnCommonRequest txnRequest, BigDecimal fee, BigDecimal commission) {

        for (LastTxnEntity lastTxnEntity : orgTxnEntities) {
            if (lastTxnEntity.getSenderOrReceiver() == SenderOrReceiver.SENDER && fee.compareTo(BigDecimal.ZERO) > 0) {
                lastTxnEntity.setBalance(lastTxnEntity.getBalance().subtract(fee));
                lastTxnEntity.setDebitOrCredit(DebitOrCredit.DEBIT);
                lastTxnEntity.setTxnId(lastTxnEntity.getNrNumber() + "3");
                lastTxnEntity.setTxnCategory(TxnCategory.FEE);
                lastTxnEntity.setAmount(fee);

                lastTxnService.updateLastTxnEntity(lastTxnEntity);
                writeTxnLog(lastTxnEntity);

            } else if (lastTxnEntity.getSenderOrReceiver() == SenderOrReceiver.RECEIVER && commission.compareTo(BigDecimal.ZERO) > 0
                    && lastTxnEntity.getTxnType() != TxnType.CASH_IN) {
                lastTxnEntity.setBalance(lastTxnEntity.getBalance().add(commission));
                lastTxnEntity.setDebitOrCredit(DebitOrCredit.CREDIT);
                lastTxnEntity.setTxnId(lastTxnEntity.getNrNumber() + "4");
                lastTxnEntity.setTxnCategory(TxnCategory.COMMISSION);
                lastTxnEntity.setAmount(commission);

                lastTxnService.updateLastTxnEntity(lastTxnEntity);
                writeTxnLog(lastTxnEntity);

            } else if (lastTxnEntity.getSenderOrReceiver() == SenderOrReceiver.SENDER && commission.compareTo(BigDecimal.ZERO) > 0
                    && lastTxnEntity.getTxnType() == TxnType.CASH_IN) {
                lastTxnEntity.setBalance(lastTxnEntity.getBalance().add(commission));
                lastTxnEntity.setDebitOrCredit(DebitOrCredit.CREDIT);
                lastTxnEntity.setTxnId(lastTxnEntity.getNrNumber() + "4");
                lastTxnEntity.setTxnCategory(TxnCategory.COMMISSION);
                lastTxnEntity.setAmount(commission);

                lastTxnService.updateLastTxnEntity(lastTxnEntity);
                writeTxnLog(lastTxnEntity);
            }
        }
    }


    private void prepareLastTxn(LastTxnEntity lastTxn, SenderOrReceiver senderOrReceiver, String txnId, String nrNumber, TxnCommonRequest request) {
        switch (senderOrReceiver) {
            case SENDER -> {
                lastTxn.setBalance(lastTxn.getBalance().subtract(request.getTxnAmount()));
                lastTxn.setDebitOrCredit(DebitOrCredit.DEBIT);
                lastTxn.setSenderOrReceiver(SenderOrReceiver.SENDER);
            }
            case RECEIVER -> {
                lastTxn.setBalance(lastTxn.getBalance().add(request.getTxnAmount()));
                lastTxn.setDebitOrCredit(DebitOrCredit.CREDIT);
                lastTxn.setSenderOrReceiver(SenderOrReceiver.RECEIVER);
            }
        }
        lastTxn.setTxnId(txnId);
        lastTxn.setNrNumber(nrNumber);
        lastTxn.setTxnType(request.getTxnType());
        lastTxn.setTxnCategory(TxnCategory.ORIGINAL);
        lastTxn.setAmount(request.getTxnAmount());
        lastTxn.setReference(request.getReference());
    }


    private void writeTxnLog(LastTxnEntity lastTxn) {
        TxnLogEntity txnLog = TxnLogEntity.builder()
                .accountNumber(lastTxn.getAccountNumber())
                .approvalDate(Util.convertDateToDateInt(new Date(), Constants.DateFormats.ddMMyyyy))
                .approvalDt(new Date())
                .txnType(lastTxn.getTxnType())
                .userType(lastTxn.getUserType())
                .senderOrReceiver(lastTxn.getSenderOrReceiver())
                .debitOrCredit(lastTxn.getDebitOrCredit())
                .txnCategory(lastTxn.getTxnCategory())
                .amount(lastTxn.getAmount())
                .preBalance(lastTxn.getBalance().subtract(lastTxn.getAmount()))
                .newBalance(lastTxn.getBalance())
                .txnId(lastTxn.getTxnId())
                .nrNumber(lastTxn.getNrNumber())
                .reference(lastTxn.getReference())
                .build();

        txnLogDao.save(txnLog);
    }

    @Override
    public BigDecimal calculateFeeComm(FeeCommResource feeCommResource, BigDecimal txnAmount) {
        BigDecimal feeComm = BigDecimal.ZERO;

        switch (feeCommResource.getRateType()) {
            case FIXED -> {
                feeComm = feeCommResource.getRate();
            }
            case RATE -> {
                feeComm = txnAmount.multiply(feeCommResource.getRate()).divide(Constants.ONE_HUNDRED);
            }
        }
        return feeComm;
    }


    @Override
    public TxnCommonResponse prepareTxnResponse(List<LastTxnEntity> orgTxnEntities, BigDecimal fee, BigDecimal commission, TxnType txnType) {

        LastTxnEntity lastTxn = new LastTxnEntity();

        if (TxnType.B2B_AG == txnType) {
            for (LastTxnEntity lastTxnEntity : orgTxnEntities) {
                if (lastTxnEntity.getSenderOrReceiver() == SenderOrReceiver.RECEIVER) {
                    lastTxn = lastTxnEntity;
                }
            }
        } else {
            for (LastTxnEntity lastTxnEntity : orgTxnEntities) {
                if (lastTxnEntity.getSenderOrReceiver() == SenderOrReceiver.SENDER) {
                    lastTxn = lastTxnEntity;
                }
            }
        }

        if (commission.compareTo(BigDecimal.ZERO) > 0 && txnType == TxnType.CASH_OUT) {
            commission = BigDecimal.ZERO;
        }

        return TxnCommonResponse.builder()
                .txnType(lastTxn.getTxnType())
                .txnAmount(lastTxn.getAmount())
                .fee(fee)
                .commission(commission)
                .txnId(lastTxn.getTxnId())
                .balance(lastTxn.getBalance())
                .reference(lastTxn.getReference())
                .build();
    }
}
