package com.anika.mobilefinancialservice.config;

import com.anika.mobilefinancialservice.enums.TxnType;
import com.anika.mobilefinancialservice.enums.UserType;

import java.util.HashMap;
import java.util.Map;

/**
 * @author atiQue
 * @since 12'Aug 2022 at 12:45 AM
 */

public enum TransactionConfig {

    P2P_CONFIG(TxnType.P2P, UserType.CUSTOMER, UserType.CUSTOMER),
    CASH_IN_CONFIG(TxnType.CASH_IN, UserType.AGENT, UserType.CUSTOMER),
    CASH_OUT_CONFIG(TxnType.CASH_OUT, UserType.CUSTOMER, UserType.AGENT),
    B2B_AG_CONFIG(TxnType.B2B_AG, null, UserType.AGENT),
    REDEEM_AG_CONFIG(TxnType.REDEEM_AG, UserType.AGENT, null),
    ;

    private final TxnType txnType;
    private final UserType senderUserType;
    private final UserType receiverUserType;

    TransactionConfig(TxnType txnType, UserType senderUserType, UserType receiverUserType) {
        this.txnType = txnType;
        this.senderUserType = senderUserType;
        this.receiverUserType = receiverUserType;
    }

    public TxnType getTxnType() {
        return txnType;
    }

    public UserType getSenderUserType() {
        return senderUserType;
    }

    public UserType getReceiverUserType() {
        return receiverUserType;
    }

    private static final Map<TxnType, TransactionConfig> MAP_BY_TXN_TYPE = new HashMap<>();

    static void populateMap() {
        for (TransactionConfig config : values()) {
            MAP_BY_TXN_TYPE.put(config.txnType, config);
        }
    }


    public static TransactionConfig getByTxnType(TxnType txnType) {
        return MAP_BY_TXN_TYPE.get(txnType);
    }
}
