package com.anika.mobilefinancialservice.repositories;

import com.anika.mobilefinancialservice.entity.LastTxnEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LastTxnRepository extends JpaRepository<LastTxnEntity, Integer> {

    LastTxnEntity findByAccountNumber(String phnNo);

}
