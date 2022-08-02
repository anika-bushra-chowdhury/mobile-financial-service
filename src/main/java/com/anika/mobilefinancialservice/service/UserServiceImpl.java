package com.anika.mobilefinancialservice.service;

import com.anika.mobilefinancialservice.dao.LastTxnDao;
import com.anika.mobilefinancialservice.dao.UserDao;
import com.anika.mobilefinancialservice.dto.User;
import com.anika.mobilefinancialservice.entity.LastTxnEntity;
import com.anika.mobilefinancialservice.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserHelperService userHelperService;
    private final LastTxnDao lastTxnDao;
    private final UserDao userDao;

    public UserServiceImpl(LastTxnDao lastTxnDao, UserHelperService userHelperService, UserDao userDao) {
        this.userHelperService = userHelperService;
        this.lastTxnDao = lastTxnDao;
        this.userDao = userDao;
    }

    @Override
    public User userRegistration(User registrationRequest) {

        UserEntity userEntity = userHelperService.prepareUserEntity(registrationRequest);
        userEntity = userDao.save(userEntity);

        LastTxnEntity lastTxnEntity = userHelperService.prepareLastTxnEntity(registrationRequest);
        lastTxnDao.save(lastTxnEntity);

        return userHelperService.prepareRegistrationResponse(userEntity);
    }

}
