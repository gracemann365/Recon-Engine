package com.gracemann.recon.matchengine.kafka;

import com.gracemann.recon.matchengine.dto.MatchEngineTxnDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class IngestedNormalizedTxnKafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(IngestedNormalizedTxnKafkaConsumer.class);

    @KafkaListener(topics = "ingested-txn-topic", groupId = "match-engine-consumer-group")
    public void consume(MatchEngineTxnDTO dto) {
        log.info("Received normalized ledger entry from ingestion engine: {}", dto);
        // TODO: persist to ledger, trigger match logic, etc.
    }
}
