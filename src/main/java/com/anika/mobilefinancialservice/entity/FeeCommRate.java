package com.anika.mobilefinancialservice.entity;

import com.anika.mobilefinancialservice.enums.SenderOrReceiver;
import com.anika.mobilefinancialservice.enums.TxnCategory;
import com.anika.mobilefinancialservice.enums.TxnType;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

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
@Table(name = "FEE_COMM_RATE")
public class FeeCommRate extends BaseDomain {

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "TXN_TYPE")
    private TxnType txnType;

    @Enumerated(EnumType.STRING)
    @Column(name = "SENDER_OR_RECEIVER")
    private SenderOrReceiver senderOrReceiver;

    @Enumerated(EnumType.STRING)
    @Column(name = "TXN_CATEGORY")
    private TxnCategory txnCategory;

    @Column(name = "RATE")
    private BigDecimal feeRate;

    @Column(name = "AMOUNT_MIN")
    private BigDecimal minAmount;

    @Column(name = "AMOUNT_MAX")
    private BigDecimal maxAmount;

}