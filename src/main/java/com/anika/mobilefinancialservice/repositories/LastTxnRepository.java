package com.anika.mobilefinancialservice.repositories;

import com.anika.mobilefinancialservice.entity.LastTxnEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface LastTxnRepository extends JpaRepository<LastTxnEntity, Integer> {


}
