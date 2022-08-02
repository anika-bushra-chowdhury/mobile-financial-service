package com.anika.mobilefinancialservice.service;


import com.anika.mobilefinancialservice.dto.User;
import com.anika.mobilefinancialservice.entity.LastTxnEntity;
import com.anika.mobilefinancialservice.entity.UserEntity;

public interface UserHelperService {

    LastTxnEntity prepareLastTxnEntity(User request);

    UserEntity prepareUserEntity(User request);

    User prepareRegistrationResponse(UserEntity userEntity);
}
