package com.anika.mobilefinancialservice.controller;

import com.anika.mobilefinancialservice.dto.TxnCommonRequest;
import com.anika.mobilefinancialservice.dto.TxnCommonResponse;
import com.anika.mobilefinancialservice.entity.TxnLogEntity;
import com.anika.mobilefinancialservice.service.TxnService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/v1/txn")
public class TxnController {

    private final TxnService txnService;

    public TxnController(TxnService txnService) {
        this.txnService = txnService;
    }

    @PostMapping(value = "/doTxn")
    public TxnCommonResponse doTransaction(@RequestBody TxnCommonRequest txnRequest) {
        return txnService.executeTxn(txnRequest);
    }

    @GetMapping(value = "/get-user-txn/{phnNO}")
    public List<TxnLogEntity> getUserTransactions(@PathVariable String phnNO) {
        return txnService.getTxnHistory(phnNO);
    }
}
