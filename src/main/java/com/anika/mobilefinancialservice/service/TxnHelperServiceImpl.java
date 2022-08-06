package com.anika.mobilefinancialservice.service;

import com.anika.mobilefinancialservice.dao.FeeCommDao;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Slf4j
@Service
public class TxnHelperServiceImpl implements TxnHelperService {
    private final FeeCommDao feeCommDao;
    private final UserService userService;
    private final LastTxnService lastTxnService;
    private final TxnLogDao txnLogDao;

    public TxnHelperServiceImpl(FeeCommDao feeCommDao, UserService userService, LastTxnService lastTxnService, TxnLogDao txnLogDao) {
        this.feeCommDao = feeCommDao;
        this.userService = userService;
        this.lastTxnService = lastTxnService;
        this.txnLogDao = txnLogDao;
    }

//    @Override
//    public List<User> getUserAccounts(TxnCommonRequest request) {
//        List<User> userAccounts = new ArrayList<>();
//
//        User sender = userService.getUserInfo(request.getFromAccNo());
//        userAccounts.add(sender);
//
//        User receiver = userService.getUserInfo(request.getToAccNo());
//        userAccounts.add(receiver);
//
//        return userAccounts;
//    }


    @Override
    @Transactional
    public List<LastTxnEntity> generateOrgTxn(TxnCommonRequest request) {
        LastTxnEntity senderLastTxn = lastTxnService.getLastTxn(request.getFromAccNo());
        LastTxnEntity receiverLastTxn = lastTxnService.getLastTxn(request.getToAccNo());

        String txnId = Util.generateNrNUmber();
        String nrNumber = Util.generateNrNUmber();

        prepareLastTxn(senderLastTxn, SenderOrReceiver.SENDER, txnId, nrNumber, request);
        prepareLastTxn(receiverLastTxn, SenderOrReceiver.RECEIVER, txnId, nrNumber, request);

        List<LastTxnEntity> lastTxnEntities = new ArrayList<>();
        lastTxnEntities.add(lastTxnService.updateLastTxnEntity(senderLastTxn));
        writeTxnLog(senderLastTxn, request);

        lastTxnEntities.add(lastTxnService.updateLastTxnEntity(receiverLastTxn));
        writeTxnLog(receiverLastTxn, request);

        return lastTxnEntities;
    }


    private void prepareLastTxn(LastTxnEntity lastTxn, SenderOrReceiver senderOrReceiver, String txnId, String nrNumber, TxnCommonRequest request) {

        switch (senderOrReceiver) {
            case SENDER -> {
                lastTxn.setBalance(lastTxn.getBalance().subtract(request.getTxnAmount()));
                lastTxn.setAvailableBalance(lastTxn.getAvailableBalance().subtract(request.getTxnAmount()));
                lastTxn.setDebitOrCredit(DebitOrCredit.DEBIT);
                lastTxn.setSenderOrReceiver(SenderOrReceiver.SENDER);
            }
            case RECEIVER -> {
                lastTxn.setBalance(lastTxn.getBalance().add(request.getTxnAmount()));
                lastTxn.setAvailableBalance(lastTxn.getAvailableBalance().add(request.getTxnAmount()));
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

    private void writeTxnLog(LastTxnEntity lastTxn, TxnCommonRequest request) {
        TxnLogEntity txnLog = TxnLogEntity.builder()
                .number(lastTxn.getAccountNumber())
                .approvalDate(Util.convertDateToDateInt(new Date(), Constants.DateFormats.ddMMyyyy))
                .approvalDt(new Date())
                .txnType(lastTxn.getTxnType())
                .userType(lastTxn.getUserType())
                .senderOrReceiver(lastTxn.getSenderOrReceiver())
                .debitOrCredit(lastTxn.getDebitOrCredit())
                .txnCategory(lastTxn.getTxnCategory())
                .amount(request.getTxnAmount())
                .preBalance(lastTxn.getAvailableBalance().subtract(request.getTxnAmount()))
                .currBalance(lastTxn.getAvailableBalance())
                .txnId(lastTxn.getTxnId())
                .nrNumber(lastTxn.getNrNumber())
                .build();

        txnLogDao.save(txnLog);
    }
}
