package com.anika.mobilefinancialservice.service;

import com.anika.mobilefinancialservice.dto.CalculatedFeeComm;
import com.anika.mobilefinancialservice.dto.CoreTransaction;
import com.anika.mobilefinancialservice.dto.TxnCommonRequest;
import com.anika.mobilefinancialservice.dto.UserTypeBaseCalculatedFeeComm;
import com.anika.mobilefinancialservice.enums.*;
import com.anika.mobilefinancialservice.utils.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author atiQue
 * @since 12'Aug 2022 at 12:41 AM
 */

public class TxnServiceV2Impl implements TxnServiceV2 {

    private final TransactionValidator txnValidator;
    private final CalculateFeeCommService calculateFeeCommService;
    private final CoreTxnService coreTxnService;

    public TxnServiceV2Impl(TransactionValidator txnValidator, CalculateFeeCommService calculateFeeCommService, CoreTxnService coreTxnService) {
        this.txnValidator = txnValidator;
        this.calculateFeeCommService = calculateFeeCommService;
        this.coreTxnService = coreTxnService;
    }

    @Override
    public void processTransaction(TxnCommonRequest request) {

        //validate Accounts
        txnValidator.validateAccounts(request);

        //get calculated fee comm
        UserTypeBaseCalculatedFeeComm userTypeBaseCalculatedFeeComm =
                calculateFeeCommService.calculateFeeComm(request.getTxnType(), request.getTxnAmount());

        //validate debit & credit
        txnValidator.validateDebitCredit(userTypeBaseCalculatedFeeComm, request);

        List<CoreTransaction> coreTransactions = new ArrayList<>();

        Integer order = 1;

        CoreTransaction orgDebit = prepareDebitCoreTxn();
        String nRNumber = Util.generateNrNUmber();
        List<CalculatedFeeComm> senderFees = userTypeBaseCalculatedFeeComm.getSenderFeeComms()
                .stream()
                .filter(e -> e.getTxnCategory() == TxnCategory.FEE).toList();

        for (CalculatedFeeComm feeComm : senderFees) {

        }

        coreTxnService.doTransaction(coreTransactions);
    }

    private CoreTransaction prepareDebitCoreTxn() {

        return CoreTransaction.builder()
                .accountNumber("ad")
                .userType(UserType.AGENT)
                .amount(BigDecimal.ZERO)
                .debitOrCredit(DebitOrCredit.DEBIT)
                .txnType(TxnType.B2B_AG)
                .txnCategory(TxnCategory.COMMISSION)
                .txnId("sdf")
                .nrNumber("daf")
                .senderOrReceiver(SenderOrReceiver.SENDER)
                .order(1)
                .build();
    }
}
