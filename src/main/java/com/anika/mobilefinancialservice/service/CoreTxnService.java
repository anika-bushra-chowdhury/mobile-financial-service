package com.anika.mobilefinancialservice.service;

import com.anika.mobilefinancialservice.dto.CoreTransaction;

import javax.transaction.NotSupportedException;
import java.util.List;

/**
 * @author atiQue
 * @since 11'Aug 2022 at 9:42 PM
 */

public interface CoreTxnService {
    void doTransaction(List<CoreTransaction> coreTransactions);
}
