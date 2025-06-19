package com.gracemann.recon.matchengine.repository;

import com.gracemann.recon.matchengine.domain.SchemeSettlementTransactionLedger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for SchemeSettlementTransactionLedger entities.
 */
@Repository
public interface SchemeSettlementTransactionLedgerRepository extends JpaRepository<SchemeSettlementTransactionLedger, Long> {
    // Optionally add custom queries here (e.g., findByTxnId)
}
