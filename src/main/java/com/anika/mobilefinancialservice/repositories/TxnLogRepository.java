package com.anika.mobilefinancialservice.repositories;

import com.anika.mobilefinancialservice.entity.TxnLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TxnLogRepository extends JpaRepository<TxnLogEntity, Integer> {


}
