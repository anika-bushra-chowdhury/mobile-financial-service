package com.anika.mobilefinancialservice.repositories;

import com.anika.mobilefinancialservice.entity.FeeCommEntity;
import com.anika.mobilefinancialservice.enums.TxnType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface FeeCommRepository extends JpaRepository<FeeCommEntity, Integer> {

    List<FeeCommEntity> findAllByTxnType(TxnType txnType);

}
