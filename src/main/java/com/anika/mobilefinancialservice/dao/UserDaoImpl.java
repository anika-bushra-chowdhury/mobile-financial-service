package com.anika.mobilefinancialservice.dao;

import com.anika.mobilefinancialservice.entity.UserEntity;
import com.anika.mobilefinancialservice.repositories.UserRepository;

public class UserDaoImpl implements UserDao {
    private final UserRepository userRepository;

    public UserDaoImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity createUserEntity(UserEntity user) {
        return userRepository.save(user);
    }
}
