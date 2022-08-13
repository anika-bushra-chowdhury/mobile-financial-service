package com.anika.mobilefinancialservice.controller;

import com.anika.mobilefinancialservice.dto.TxnCommonRequest;
import com.anika.mobilefinancialservice.dto.TxnCommonResponse;
import com.anika.mobilefinancialservice.entity.TxnLogEntity;
import com.anika.mobilefinancialservice.service.TxnService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/v1/txn")
@Slf4j
public class TxnController {

    private final TxnService txnService;

    public TxnController(TxnService txnService) {
        this.txnService = txnService;
    }

    @PostMapping(value = "/doTxn")
    public TxnCommonResponse doTransaction(@RequestBody TxnCommonRequest txnRequest) {

        log.info("Transaction request: {}", txnRequest.toString());

        TxnCommonResponse response = txnService.executeTxn(txnRequest);
        log.info("Transaction response: {}", response.toString());

        return response;
    }

    @GetMapping(value = "/get-user-txn/{phnNO}")
    public List<TxnLogEntity> getUserTransactions(@PathVariable String phnNO) {

        log.info("Txn history inquiry request by: {}", phnNO);

        return txnService.getTxnHistory(phnNO);
    }
}
