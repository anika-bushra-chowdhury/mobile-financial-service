package com.anika.mobilefinancialservice.service;

import com.anika.mobilefinancialservice.dto.User;
import com.anika.mobilefinancialservice.entity.LastTxnEntity;
import com.anika.mobilefinancialservice.entity.UserEntity;
import com.anika.mobilefinancialservice.enums.ProfileType;
import com.anika.mobilefinancialservice.enums.UserStatus;
import com.anika.mobilefinancialservice.utils.Constants;
import com.anika.mobilefinancialservice.utils.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;


@Slf4j
@Service
public class UserHelperServiceImpl implements UserHelperService {

    @Override
    public LastTxnEntity prepareLastTxnEntity(User request) {
        return LastTxnEntity.builder()
                .number(Util.decode(request.getPhoneNumber()))
                .approvalDt(new Date())
                .approvalDate(Util.convertDateToDateInt(new Date(), Constants.DateFormats.ddMMyyyy))
                .userType(request.getUserType())
                .amount(new BigDecimal(0))
                .availableBalance(new BigDecimal(0))
                .balance(new BigDecimal(0))
                .build();
    }

    @Override
    public UserEntity prepareUserEntity(User request) {
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
                .userStatus(UserStatus.ACTIVE)
                .build();
    }

    @Override
    public User prepareRegistrationResponse(UserEntity userEntity) {
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
