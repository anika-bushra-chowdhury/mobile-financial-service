package com.anika.mobilefinancialservice.entity;

import com.anika.mobilefinancialservice.enums.*;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author atiQue
 * @since 31'Jul 2022 at 9:27 PM
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "TXN_LOG")
public class TxnLog extends BaseDomain {

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "MSISDN")
    private String number;

    @Column(name = "APPROVAL_DATE_TIME")
    private Date approvalDt;

    @Column(name = "APPROVAL_DATE")
    private Number approvalDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "TXN_TYPE")
    private TxnType txnType;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "USER_TYPE")
    private UserType userType;

    @Enumerated(EnumType.STRING)
    @Column(name = "SENDER_OR_RECEIVER")
    private SenderOrReceiver senderOrReceiver;

    @Enumerated(EnumType.STRING)
    @Column(name = "DEBIT_OR_CREDIT")
    private DebitOrCredit debitOrCredit;

    @Enumerated(EnumType.STRING)
    @Column(name = "TXN_CATEGORY")
    private TxnCategory txnCategory;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "PRE_BALANCE")
    private BigDecimal preBalance;

    @Column(name = "CURRENT_BALANCE")
    private BigDecimal currBalance;

    @Column(name = "TXN_ID")
    private String txnId;

    @Column(name = "NR_NUMBER")
    private String nrNumber;
}
