package com.anika.mobilefinancialservice.dto;

import com.anika.mobilefinancialservice.enums.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author atiQue
 * @since 13'Aug 2022 at 6:44 PM
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TxnLogDto {

    private String accountNumber;

    private String approvalDt;

    private Integer approvalDate;

    private TxnType txnType;

    private UserType userType;

    private SenderOrReceiver senderOrReceiver;

    private DebitOrCredit debitOrCredit;

    private TxnCategory txnCategory;

    private BigDecimal amount;

    private BigDecimal preBalance;

    private BigDecimal newBalance;

    private BigDecimal preAvailableBalance;

    private BigDecimal newAvailableBalance;

    private String txnId;

    private String nrNumber;

    private TxnStatus txnStatus;

    private String reference;

}
