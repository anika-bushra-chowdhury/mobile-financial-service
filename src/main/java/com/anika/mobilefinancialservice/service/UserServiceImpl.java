package com.anika.mobilefinancialservice.service;

import com.anika.mobilefinancialservice.utils.Util;
import com.anika.mobilefinancialservice.dao.LastTxnDao;
import com.anika.mobilefinancialservice.dao.UserDao;
import com.anika.mobilefinancialservice.dto.User;
import com.anika.mobilefinancialservice.entity.LastTxnEntity;
import com.anika.mobilefinancialservice.entity.UserEntity;
import com.anika.mobilefinancialservice.enums.ProfileType;
import com.anika.mobilefinancialservice.enums.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final LastTxnDao lastTxnDao;
    private final UserDao userDao;

    public UserServiceImpl(LastTxnDao lastTxnDao, UserDao userDao) {
        this.lastTxnDao = lastTxnDao;
        this.userDao = userDao;
    }

    @Override
    public User userRegistration(User registrationRequest) {

        UserEntity userEntity = prepareUserEntity(registrationRequest);
        userEntity = userDao.save(userEntity);

//        LastTxnEntity lastTxnEntity = prepareLastTxnEntity(registrationRequest);
//        lastTxnDao.createLastTxnEntity(lastTxnEntity);

        return prepareRegistrationResponse(userEntity);
    }

    private LastTxnEntity prepareLastTxnEntity(User request) {
        LastTxnEntity lastTxnEntity = LastTxnEntity.builder()
                .number(request.getPhoneNumber())
                .userType(request.getUserType())
                .amount(new BigDecimal(0))
                .availableBalance(new BigDecimal(0))
                .balance(new BigDecimal(0))
                .build();
        return null;
    }

    private UserEntity prepareUserEntity(User request) {
        return UserEntity.builder()
                .userName(request.getUserName())
                .number(Util.encode(request.getPhoneNumber()))
                .nid(request.getNid())
                .dateOfBirth(request.getDob())
                .fatherName(request.getFatherName())
                .motherName(request.getMotherName())
                .presentAddress(request.getPresAddress())
                .permanentAddress(request.getPrmntAddress())
                .nidFront(request.getNidFront())
                .nidBack(request.getNidBack())
                .pin(Util.encode(request.getPin()))
                .userType(request.getUserType())
                .profile(ProfileType.FULL)
                .status(Status.ACTIVE)
                .build();
    }

    private User prepareRegistrationResponse(UserEntity userEntity) {
        return User.builder()
                .userName(userEntity.getUserName())
                .phoneNumber(Util.decode(userEntity.getNumber()))
                .nid(userEntity.getNid())
                .dob(userEntity.getDateOfBirth())
                .fatherName(userEntity.getFatherName())
                .motherName(userEntity.getMotherName())
                .presAddress(userEntity.getPresentAddress())
                .prmntAddress(userEntity.getPermanentAddress())
                .nidFront(userEntity.getNidFront())
                .nidBack(userEntity.getNidBack())
                .userType(userEntity.getUserType())
                .build();
    }
}
