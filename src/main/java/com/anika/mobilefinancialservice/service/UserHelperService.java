package com.anika.mobilefinancialservice.service;


import com.anika.mobilefinancialservice.dto.User;
import com.anika.mobilefinancialservice.dto.UserBasicInfoResponse;
import com.anika.mobilefinancialservice.entity.LastTxnEntity;
import com.anika.mobilefinancialservice.entity.UserEntity;

public interface UserHelperService {

    LastTxnEntity createLastTxnEntity(User request);

    UserEntity prepareUserEntity(User request);

    User prepareUser(UserEntity userEntity);

    UserBasicInfoResponse prepareUserBasicInfo(UserEntity userEntity);
}
