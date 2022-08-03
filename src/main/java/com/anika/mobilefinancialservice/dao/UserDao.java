package com.anika.mobilefinancialservice.dao;

import com.anika.mobilefinancialservice.entity.UserEntity;

/**
 * @author atiQue
 * @since 31'Jul 2022 at 9:26 PM
 */

public interface UserDao {
    UserEntity save(UserEntity user);

    UserEntity getByPhnNo(String phnNO);

}
