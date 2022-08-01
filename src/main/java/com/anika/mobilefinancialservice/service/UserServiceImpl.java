package com.anika.mobilefinancialservice.service;

import com.anika.mobilefinancialservice.Util;
import com.anika.mobilefinancialservice.dao.UserDao;
import com.anika.mobilefinancialservice.dto.User;
import com.anika.mobilefinancialservice.entity.UserEntity;
import com.anika.mobilefinancialservice.enums.ProfileType;
import com.anika.mobilefinancialservice.enums.Status;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final Util util;

    public UserServiceImpl(UserDao userDao, Util util) {
        this.userDao = userDao;
        this.util = util;
    }

    @Override
    public User userRegistration(User registrationRequest) {

        UserEntity userEntity = prepareUserEntity(registrationRequest);

        userEntity = userDao.createUserEntity(userEntity);

        //todo: create entity in LAST_TXN

        return prepareRegistrationResponse(userEntity);
    }


    private UserEntity prepareUserEntity(User request) {
        return UserEntity.builder()
                .userName(request.getUserName())
                .number(util.encode(request.getPhoneNumber()))
                .nid(request.getNid())
                .dateOfBirth(util.convertStringToDate(request.getDob()))
                .fatherName(request.getFatherName())
                .motherName(request.getMotherName())
                .presentAddress(request.getPresAddress())
                .permanentAddress(request.getPrmntAddress())
                .nidFront(request.getNidFront())
                .nidBack(request.getNidBack())
                .pin(util.encode(request.getPin()))
                .userType(request.getUserType())
                .profile(ProfileType.FULL)
                .status(Status.ACTIVE)
                .build();
    }


    private User prepareRegistrationResponse(UserEntity userEntity) {
        return User.builder()
                .userName(userEntity.getUserName())
                .phoneNumber(util.decode(userEntity.getNumber()))
                .nid(userEntity.getNid())
                .dob(util.convertDateToString(userEntity.getDateOfBirth()))
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
