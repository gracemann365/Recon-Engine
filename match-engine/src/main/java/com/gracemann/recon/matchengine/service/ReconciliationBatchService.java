package com.gracemann.recon.matchengine.service;

import com.gracemann.recon.matchengine.domain.ReconciliationBatchControl;
import com.gracemann.recon.matchengine.repository.ReconciliationBatchControlRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service class for managing the lifecycle and orchestration of reconciliation
 * batches.
 * <p>
 * This class encapsulates business logic for:
 * <ul>
 * <li>Creating new reconciliation batches with configuration metadata</li>
 * <li>Updating batch execution status, counters, and timings</li>
 * <li>Querying for active, latest, or historical batch executions</li>
 * <li>Providing orchestration entrypoints for batch-triggering controllers or
 * schedulers</li>
 * </ul>
 *
 * <b>Usage:</b> Call from REST controllers or scheduled jobs to manage batch
 * execution flow.
 *
 * @author David Grace
 * @since 1.0
 */
@Service
public class ReconciliationBatchService {

    private final ReconciliationBatchControlRepository batchRepo;

    public ReconciliationBatchService(ReconciliationBatchControlRepository batchRepo) {
        this.batchRepo = batchRepo;
    }

    /**
     * Creates and persists a new reconciliation batch, marking status as
     * PROCESSING.
     *
     * @param createdBy      Operator or process name that triggered this batch.
     * @param configSnapshot Batch config/rule snapshot as JSON string.
     * @return The persisted ReconciliationBatchControl entity.
     */
    @Transactional
    public ReconciliationBatchControl createNewBatch(String createdBy, String configSnapshot) {
        ReconciliationBatchControl batch = new ReconciliationBatchControl();
        batch.setBatchStartTimestamp(Timestamp.from(Instant.now()));
        batch.setBatchExecutionStatus("PROCESSING");
        batch.setBatchCreatedBy(createdBy);
        // Always provide a valid JSON object (even if empty)!!!
        batch.setBatchConfigurationSnapshot(
                (configSnapshot == null || configSnapshot.isBlank()) ? "{}" : configSnapshot);

        // batchExecutionId is auto-generated (DB or JPA)
        return batchRepo.save(batch);
    }

    /**
     * Marks a batch as completed, updates all relevant counters and timestamps.
     *
     * @param batchId  UUID of the batch to mark as completed.
     * @param endTime  Completion time.
     * @param counters Updated batch counters (total processed, matched, unmatched,
     *                 etc).
     * @return Updated batch entity, or Optional.empty() if not found.
     */
    @Transactional
    public Optional<ReconciliationBatchControl> completeBatch(UUID batchId, Timestamp endTime, BatchCounters counters) {
        Optional<ReconciliationBatchControl> opt = batchRepo.findById(batchId);
        opt.ifPresent(batch -> {
            batch.setBatchEndTimestamp(endTime);
            batch.setBatchExecutionStatus("COMPLETED");
            batch.setTotalTransactionsProcessed(counters.totalProcessed());
            batch.setExactMatchesFound(counters.exactMatches());
            batch.setFuzzyMatchesFound(counters.fuzzyMatches());
            batch.setUnmatchedBankTransactions(counters.unmatchedBank());
            batch.setUnmatchedSchemeTransactions(counters.unmatchedScheme());
            batch.setExceptionCasesGenerated(counters.exceptions());
            batchRepo.save(batch);
        });
        return opt;
    }

    /**
     * Marks a batch as failed with end timestamp and optional error message.
     *
     * @param batchId Batch UUID.
     * @param endTime Failure timestamp.
     * @param error   Optional error info (expand entity if you wish).
     * @return Updated batch, or empty if not found.
     */
    @Transactional
    public Optional<ReconciliationBatchControl> failBatch(UUID batchId, Timestamp endTime, String error) {
        Optional<ReconciliationBatchControl> opt = batchRepo.findById(batchId);
        opt.ifPresent(batch -> {
            batch.setBatchEndTimestamp(endTime);
            batch.setBatchExecutionStatus("FAILED");
            // (optional) batch.setErrorMessage(error);
            batchRepo.save(batch);
        });
        return opt;
    }

    /**
     * Fetches the latest batch by start timestamp.
     *
     * @return Latest batch, or null if none exist.
     */
    @Transactional(readOnly = true)
    public ReconciliationBatchControl getLatestBatch() {
        return batchRepo.findFirstByOrderByBatchStartTimestampDesc();
    }

    /**
     * Fetches all batches by execution status (e.g., PROCESSING, COMPLETED).
     *
     * @param status Execution status filter.
     * @return List of matching batches, latest first.
     */
    @Transactional(readOnly = true)
    public List<ReconciliationBatchControl> getBatchesByStatus(String status) {
        return batchRepo.findByBatchExecutionStatusOrderByBatchStartTimestampDesc(status);
    }

    /**
     * Fetches a batch by its UUID.
     *
     * @param batchId Batch UUID.
     * @return Optional containing batch or empty if not found.
     */
    @Transactional(readOnly = true)
    public Optional<ReconciliationBatchControl> getBatchById(UUID batchId) {
        return batchRepo.findById(batchId);
    }

    // -- Additional orchestration methods as needed for your flow --

    /**
     * DTO for passing batch result counters to the completeBatch method.
     */
    public record BatchCounters(
            int totalProcessed,
            int exactMatches,
            int fuzzyMatches,
            int unmatchedBank,
            int unmatchedScheme,
            int exceptions) {
    }

}
