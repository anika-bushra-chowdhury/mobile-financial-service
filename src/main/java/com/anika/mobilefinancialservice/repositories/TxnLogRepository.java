package com.anika.mobilefinancialservice.repositories;

import com.anika.mobilefinancialservice.entity.TxnLogEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;


public interface TxnLogRepository extends PagingAndSortingRepository<TxnLogEntity, Long> {

    Page<TxnLogEntity> findAllByAccountNumber(String accNo, Pageable paging);

    Page<TxnLogEntity> findAllByAccountNumberAndApprovalDtGreaterThanEqual(String accNo, Date fromDate, Pageable paging);

    Page<TxnLogEntity> findAllByAccountNumberAndApprovalDtLessThanEqual(String accNo, Date toDate, Pageable paging);

    Page<TxnLogEntity> findAllByAccountNumberAndApprovalDtGreaterThanEqualAndApprovalDtLessThanEqual(String accNo, Date fromDate, Date toDate, Pageable paging);
}
