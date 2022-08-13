package com.anika.mobilefinancialservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author atiQue
 * @since 13'Aug 2022 at 5:19 PM
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TxnHistoryRequest {
    private String accountNo;
    private Date fromDate;
    private Date toDate;
    private Integer pageSize;
    private Integer pageNo;
}
