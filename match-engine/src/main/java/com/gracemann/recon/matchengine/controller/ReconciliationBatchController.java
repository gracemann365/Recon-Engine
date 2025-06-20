package com.gracemann.recon.matchengine.controller;

import com.gracemann.recon.matchengine.domain.ReconciliationBatchControl;
import com.gracemann.recon.matchengine.service.ReconciliationBatchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * REST controller for triggering reconciliation batch execution.
 * <p>
 * Exposes an endpoint to start a new reconciliation batch.
 * This triggers the central batch orchestration service, which:
 * <ul>
 * <li>Creates a batch control record</li>
 * <li>Fetches transactions for the configured window</li>
 * <li>Runs reconciliation/matching logic</li>
 * <li>Persists match results and updates batch status/counters</li>
 * </ul>
 * <p>
 * Intended for manual or automated invocation (from UI, scheduler, or CLI).
 * </p>
 *
 * @author [Your Name]
 * @since 1.0
 */
@RestController
@RequestMapping("/api/batches")
public class ReconciliationBatchController {

    private final ReconciliationBatchService batchService;

    public ReconciliationBatchController(ReconciliationBatchService batchService) {
        this.batchService = batchService;
    }

    /**
     * Starts a new reconciliation batch.
     *
     * @param request JSON payload containing optional operator and config/rule
     *                snapshot.
     * @return Metadata of the triggered batch, including batch UUID and start
     *         timestamp.
     */
    @PostMapping("/start")
    public ResponseEntity<Map<String, Object>> startBatch(@RequestBody(required = false) StartBatchRequest request) {
        String operator = (request != null && request.operator() != null && !request.operator().isBlank())
                ? request.operator()
                : "SYSTEM";
        String config = (request != null) ? request.configSnapshot() : null;

        // Call service to create & start new batch
        ReconciliationBatchControl batch = batchService.createNewBatch(operator, config);

        // Build simple response
        Map<String, Object> response = new HashMap<>();
        response.put("batchExecutionId", batch.getBatchExecutionId());
        response.put("status", batch.getBatchExecutionStatus());
        response.put("batchStartTimestamp", batch.getBatchStartTimestamp());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Simple DTO for start batch request.
     */
    public record StartBatchRequest(
            String operator,
            String configSnapshot) {
    }
}
