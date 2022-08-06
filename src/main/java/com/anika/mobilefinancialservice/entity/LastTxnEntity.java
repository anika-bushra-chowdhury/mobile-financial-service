package com.anika.mobilefinancialservice.entity;

import com.anika.mobilefinancialservice.enums.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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

    @Column(name = "MSISDN")
    @Pattern(regexp = "([0-9]*)", message = "Account number should be numbers only.")
    @Size(max = 11, min = 11, message = "Account number should be 11 digits.")
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
}
