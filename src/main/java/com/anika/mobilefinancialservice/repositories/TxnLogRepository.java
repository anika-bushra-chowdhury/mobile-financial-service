package com.anika.mobilefinancialservice.repositories;

import com.anika.mobilefinancialservice.entity.TxnLogEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface TxnLogRepository extends PagingAndSortingRepository<TxnLogEntity, Long> {

    Page<TxnLogEntity> findAllByAccountNumber(String accNo, Pageable paging);
}
