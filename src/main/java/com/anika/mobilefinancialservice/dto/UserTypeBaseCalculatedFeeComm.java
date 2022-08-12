package com.anika.mobilefinancialservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author atiQue
 * @since 12'Aug 2022 at 1:47 AM
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserTypeBaseCalculatedFeeComm {

    private List<CalculatedFeeComm> senderFeeComms;

    private List<CalculatedFeeComm> receiverFeeComms;
}
