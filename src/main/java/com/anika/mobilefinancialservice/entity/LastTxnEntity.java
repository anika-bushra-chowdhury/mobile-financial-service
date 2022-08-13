package com.anika.mobilefinancialservice.entity;

import com.anika.mobilefinancialservice.converter.AccountNumberConverter;
import com.anika.mobilefinancialservice.enums.*;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "LAST_TXN")
public class LastTxnEntity extends BaseDomain {

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Long id;

    @Convert(converter = AccountNumberConverter.class)
    @Column(name = "MSISDN")
    private String accountNumber;

    @Column(name = "APPROVAL_DATE_TIME")
    private Date approvalDt;

    @Column(name = "APPROVAL_DATE")
    private Integer approvalDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "TXN_TYPE")
    private TxnType txnType;

    @Enumerated(EnumType.STRING)
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

    @Column(name = "AVAILABLE_BALANCE")
    private BigDecimal availableBalance;

    @Column(name = "BALANCE")
    private BigDecimal balance;

    @Column(name = "TXN_ID")
    private String txnId;

    @Column(name = "NR_NUMBER")
    private String nrNumber;

    @Column(name = "TXN_REFERENCE")
    private String reference;
}
