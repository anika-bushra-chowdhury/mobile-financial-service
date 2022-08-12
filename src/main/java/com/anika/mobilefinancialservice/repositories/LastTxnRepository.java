package com.anika.mobilefinancialservice.repositories;

import com.anika.mobilefinancialservice.entity.LastTxnEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface LastTxnRepository extends JpaRepository<LastTxnEntity, Long> {

    LastTxnEntity findByAccountNumber(String phnNo);

    List<LastTxnEntity> findByAccountNumberIn(List<String> phnNos);
}
