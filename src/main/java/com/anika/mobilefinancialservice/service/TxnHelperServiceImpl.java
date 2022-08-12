package com.anika.mobilefinancialservice.service;

import com.anika.mobilefinancialservice.dao.TxnLogDao;
import com.anika.mobilefinancialservice.dto.TxnCommonRequest;
import com.anika.mobilefinancialservice.entity.LastTxnEntity;
import com.anika.mobilefinancialservice.entity.TxnLogEntity;
import com.anika.mobilefinancialservice.enums.DebitOrCredit;
import com.anika.mobilefinancialservice.enums.SenderOrReceiver;
import com.anika.mobilefinancialservice.enums.TxnCategory;
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

        LastTxnEntity senderLastTxn = lastTxnService.getLastTxn(request.getFromAccNo());

        if (senderLastTxn.getAvailableBalance().compareTo(totalAmount) >= 0) {
            LastTxnEntity receiverLastTxn = lastTxnService.getLastTxn(request.getToAccNo());

            String txnId = Util.generateNrNUmber();
            String nrNumber = Util.generateNrNUmber();

            prepareLastTxn(senderLastTxn, SenderOrReceiver.SENDER, txnId + "1", nrNumber, request);
            prepareLastTxn(receiverLastTxn, SenderOrReceiver.RECEIVER, txnId + "2", nrNumber, request);

            lastTxnEntities.add(lastTxnService.updateLastTxnEntity(senderLastTxn));
            writeTxnLog(senderLastTxn);

            lastTxnEntities.add(lastTxnService.updateLastTxnEntity(receiverLastTxn));
            writeTxnLog(receiverLastTxn);
        }

        return lastTxnEntities;
    }

    @Override
    public void generateFeeTxnLog(List<LastTxnEntity> orgTxnEntities, TxnCommonRequest txnRequest, BigDecimal fee) {

        for (LastTxnEntity lastTxnEntity : orgTxnEntities) {
            if (lastTxnEntity.getSenderOrReceiver() == SenderOrReceiver.SENDER) {
                lastTxnEntity.setBalance(lastTxnEntity.getBalance().subtract(fee));
                lastTxnEntity.setDebitOrCredit(DebitOrCredit.DEBIT);
                lastTxnEntity.setTxnId(lastTxnEntity.getTxnId() + "3");
                lastTxnEntity.setTxnCategory(TxnCategory.FEE);
                lastTxnEntity.setAmount(fee);

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
                .build();

        txnLogDao.save(txnLog);
    }
}
