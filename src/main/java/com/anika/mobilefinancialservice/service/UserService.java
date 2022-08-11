package com.anika.mobilefinancialservice.service;

import com.anika.mobilefinancialservice.dto.Balance;
import com.anika.mobilefinancialservice.dto.User;
import com.anika.mobilefinancialservice.dto.UserBasicInfoRequest;
import com.anika.mobilefinancialservice.dto.UserBasicInfoResponse;

import java.math.BigDecimal;

/**
 * @author atiQue
 * @since 31'Jul 2022 at 9:28 PM
 */

public interface UserService {

    User userRegistration(User registrationRequest);

    User getUserInfo(String phnNO);

    UserBasicInfoResponse logIn(UserBasicInfoRequest infoRequest);

    Balance getBalance(String phnNo);
}
