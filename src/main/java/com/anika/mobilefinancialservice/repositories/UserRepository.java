package com.anika.mobilefinancialservice.repositories;

import com.anika.mobilefinancialservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author atiQue
 * @since 31'Jul 2022 at 9:28 PM
 */

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findByAccountNumber(String phnNo);

}
