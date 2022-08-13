package com.anika.mobilefinancialservice.controller;

import com.anika.mobilefinancialservice.dto.TxnCommonRequest;
import com.anika.mobilefinancialservice.dto.TxnCommonResponse;
import com.anika.mobilefinancialservice.dto.TxnHistoryRequest;
import com.anika.mobilefinancialservice.entity.TxnLogEntity;
import com.anika.mobilefinancialservice.service.TxnService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
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

    @PostMapping(value = "/get-user-txn")
    public Page<TxnLogEntity> getUserTransactions(@RequestBody TxnHistoryRequest request) {

        log.info("Txn History request : {}", request.toString());

        if (request.getPageNo() == null) {
            request.setPageNo(0);
        }

        if (request.getPageSize() == null) {
            request.setPageSize(5);
        }

        return txnService.getTxnHistory(request);
    }
}
