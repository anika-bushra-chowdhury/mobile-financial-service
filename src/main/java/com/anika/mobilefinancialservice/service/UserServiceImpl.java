package com.anika.mobilefinancialservice.service;

import com.anika.mobilefinancialservice.dao.LastTxnDao;
import com.anika.mobilefinancialservice.dao.UserDao;
import com.anika.mobilefinancialservice.dto.User;
import com.anika.mobilefinancialservice.dto.UserBasicInfoRequest;
import com.anika.mobilefinancialservice.dto.UserBasicInfoResponse;
import com.anika.mobilefinancialservice.entity.LastTxnEntity;
import com.anika.mobilefinancialservice.entity.UserEntity;
import com.anika.mobilefinancialservice.utils.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;


@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserHelperService userHelperService;
    private final LastTxnDao lastTxnDao;
    private final UserDao userDao;
    private final LastTxnService lastTxnService;

    public UserServiceImpl(LastTxnDao lastTxnDao, UserHelperService userHelperService, UserDao userDao, LastTxnService lastTxnService) {
        this.userHelperService = userHelperService;
        this.lastTxnDao = lastTxnDao;
        this.userDao = userDao;
        this.lastTxnService = lastTxnService;
    }

    @Override
    @Transactional
    public User userRegistration(User registrationRequest) {

        UserEntity userEntity = userHelperService.prepareUserEntity(registrationRequest);
        userEntity = userDao.save(userEntity);

        LastTxnEntity lastTxnEntity = userHelperService.createLastTxnEntity(registrationRequest);
        lastTxnDao.save(lastTxnEntity);
//        LastTxnEntity lastTxnEntity = userHelperService.prepareLastTxnEntity(registrationRequest);
//        lastTxnDao.save(lastTxnEntity);

        return userHelperService.prepareUser(userEntity);
    }


    @Override
    public User getUserInfo(String phnNO) {

        UserEntity userEntity = userHelperService.getUserInfoByPhnNo(phnNO);

        return userHelperService.prepareUser(userEntity);
    }


    @Override
    public UserBasicInfoResponse logIn(UserBasicInfoRequest infoRequest) {

        UserEntity userEntity = userHelperService.getUserInfoByPhnNo(infoRequest.getPhoneNumber());

        UserBasicInfoResponse infoResponse = new UserBasicInfoResponse();
        if (userEntity != null && userEntity.getPin().equals(Util.encode(infoRequest.getPin()))) {
            infoResponse = userHelperService.prepareUserBasicInfo(userEntity);
        }

        return infoResponse;
    }

    @Override
    public BigDecimal getBalance(String phnNo) {
        LastTxnEntity lastTxn = lastTxnService.getLastTxn(phnNo);
        return lastTxn.getAvailableBalance();
    }
}
