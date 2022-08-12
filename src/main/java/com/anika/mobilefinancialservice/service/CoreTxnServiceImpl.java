package com.anika.mobilefinancialservice.service;

import com.anika.mobilefinancialservice.dao.LastTxnDao;
import com.anika.mobilefinancialservice.dao.TxnLogDao;
import com.anika.mobilefinancialservice.dto.CoreTransaction;
import com.anika.mobilefinancialservice.entity.LastTxnEntity;
import com.anika.mobilefinancialservice.entity.TxnLogEntity;
import com.anika.mobilefinancialservice.enums.TxnStatus;
import com.anika.mobilefinancialservice.utils.Constants;
import com.anika.mobilefinancialservice.utils.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author atiQue
 * @since 11'Aug 2022 at 9:50 PM
 */

@Service
@Slf4j
public class CoreTxnServiceImpl implements CoreTxnService {

    private final LastTxnDao lastTxnDao;
    private final TxnLogDao txnLogDao;

    public CoreTxnServiceImpl(LastTxnDao lastTxnDao, TxnLogDao txnLogDao) {
        this.lastTxnDao = lastTxnDao;
        this.txnLogDao = txnLogDao;
    }

    @Transactional
    @Override
    public void doTransaction(List<CoreTransaction> coreTransactions) {

        Map<String, LastTxnEntity> LAST_TXN_MAP_BY_ACC_NO = getLastTxnMapByAccountNo(coreTransactions);

        List<TxnLogEntity> txnLogsToBeCreated = new ArrayList<>();

        for (CoreTransaction coreTxn : coreTransactions) {

            Date approvalDateTime = new Date();
            Integer approvalDate = Util.convertDateToDateInt(approvalDateTime, Constants.DateFormats.ddMMyyyy);

            LastTxnEntity lastTxn = LAST_TXN_MAP_BY_ACC_NO.get(coreTxn.getAccountNumber());

            resolveCoreTxnBalance(coreTxn, lastTxn);

            updateLastTxn(coreTxn, lastTxn, approvalDateTime, approvalDate);

            txnLogsToBeCreated.add(prepareTxnLog(coreTxn, approvalDateTime, approvalDate));
        }

        lastTxnDao.save((List<LastTxnEntity>) LAST_TXN_MAP_BY_ACC_NO.values());
        txnLogDao.save(txnLogsToBeCreated);
    }

    private Map<String, LastTxnEntity> getLastTxnMapByAccountNo(List<CoreTransaction> coreTransactions) {

        List<String> accountNos =
                coreTransactions
                        .stream()
                        .map(e -> Util.encode(e.getAccountNumber())).toList();

        List<LastTxnEntity> lastTxnEntities = lastTxnDao.findByPhnNos(accountNos);

        return lastTxnEntities
                .stream()
                .collect(Collectors.toMap(LastTxnEntity::getAccountNumber, Function.identity()));
    }

    private void resolveCoreTxnBalance(CoreTransaction coreTxn, LastTxnEntity lastTxn) {

        BigDecimal preBalance = lastTxn.getBalance();
        BigDecimal preAvailableBalance = lastTxn.getAvailableBalance();
        BigDecimal newBalance = preBalance;
        BigDecimal newAvailableBalance = preAvailableBalance;

        switch (coreTxn.getDebitOrCredit()) {
            case CREDIT -> {
                newBalance = lastTxn.getBalance().add(coreTxn.getAmount());
                newAvailableBalance = lastTxn.getAvailableBalance().add(coreTxn.getAmount());
            }
            case DEBIT -> {
                newBalance = lastTxn.getBalance().subtract(coreTxn.getAmount());
                newAvailableBalance = lastTxn.getAvailableBalance().subtract(coreTxn.getAmount());
            }
            default -> log.error(coreTxn.getDebitOrCredit() + "not supported");
        }

        coreTxn.setPreBalance(preBalance);
        coreTxn.setNewBalance(newBalance);
        coreTxn.setPreAvailableBalance(preAvailableBalance);
        coreTxn.setNewAvailableBalance(newAvailableBalance);

    }

    private TxnLogEntity prepareTxnLog(CoreTransaction coreTxn, Date approvalDateTime, Integer approvalDate) {
        return TxnLogEntity.builder()
                .accountNumber(coreTxn.getAccountNumber())
                .approvalDt(approvalDateTime)
                .approvalDate(approvalDate)
                .txnType(coreTxn.getTxnType())
                .userType(coreTxn.getUserType())
                .senderOrReceiver(coreTxn.getSenderOrReceiver())
                .debitOrCredit(coreTxn.getDebitOrCredit())
                .txnCategory(coreTxn.getTxnCategory())
                .amount(coreTxn.getAmount())
                .preBalance(coreTxn.getPreBalance())
                .newBalance(coreTxn.getNewBalance())
                .preAvailableBalance(coreTxn.getPreAvailableBalance())
                .newAvailableBalance(coreTxn.getNewAvailableBalance())
                .txnId(coreTxn.getTxnId())
                .nrNumber(coreTxn.getNrNumber())
                .txnStatus(TxnStatus.PROCESSED)
                .build();
    }

    private void updateLastTxn(CoreTransaction coreTxn, LastTxnEntity lastTxn, Date approvalDateTime, Integer approvalDate) {
        lastTxn.setApprovalDt(approvalDateTime);
        lastTxn.setApprovalDate(approvalDate);
        lastTxn.setTxnType(coreTxn.getTxnType());
        lastTxn.setSenderOrReceiver(coreTxn.getSenderOrReceiver());
        lastTxn.setDebitOrCredit(coreTxn.getDebitOrCredit());
        lastTxn.setTxnCategory(coreTxn.getTxnCategory());
        lastTxn.setAmount(coreTxn.getAmount());
        lastTxn.setAvailableBalance(coreTxn.getNewAvailableBalance());
        lastTxn.setBalance(coreTxn.getNewBalance());
        lastTxn.setTxnId(coreTxn.getTxnId());
        lastTxn.setNrNumber(coreTxn.getNrNumber());
    }
}
