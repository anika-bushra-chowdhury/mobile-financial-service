package com.anika.mobilefinancialservice.dao;

import com.anika.mobilefinancialservice.entity.UserEntity;
import com.anika.mobilefinancialservice.repositories.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    private final UserRepository userRepository;

    public UserDaoImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public UserEntity getByPhnNo(String phnNO) {
        return userRepository.findByAccountNumber(phnNO);
    }
}
