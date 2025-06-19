package com.gracemann.recon.matchengine.repository;

import com.gracemann.recon.matchengine.domain.BankSwitchTransactionLedger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for BankSwitchTransactionLedger entities.
 */
@Repository
public interface BankSwitchTransactionLedgerRepository extends JpaRepository<BankSwitchTransactionLedger, Long> {
    // Optionally add custom queries here (e.g., findByTxnId)
}
