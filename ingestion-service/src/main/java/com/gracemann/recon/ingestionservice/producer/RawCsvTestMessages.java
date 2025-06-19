package com.gracemann.recon.ingestionservice.producer;

/**
 *
 * ----------------------------------------------------------------
 * EXTERNAL VISA/RUPAY SCHMES CLEARING FILES
 * ----------------------------------------------------------------
 * Raw CSV test message constants for scheme transaction ingestion testing.
 *
 * <p>
 * This class provides static constants containing raw CSV line strings that
 * simulate
 * scheme transaction data files. These messages intentionally include some
 * "dirty" data
 * with missing fields, empty values, and malformed entries to test the
 * ingestion pipeline's
 * error handling and data validation capabilities.
 * </p>
 *
 * <p>
 * CSV format follows the order: date, schemeName, txnId, cardNumber, amount,
 * currency,
 * merchantId, terminalId, responseCode, batchId
 * </p>
 *
 * <p>
 * These test constants can be modified as needed to support various ingestion
 * test scenarios,
 * including both clean data and edge cases with data quality issues.
 * </p>
 *
 * @author Generated for ingestion testing
 * @version 1.0
 */
public class RawCsvTestMessages {

    /**
     * Clean scheme transaction - matches BANK-TXN-001 from ISO messages
     */
    public static final String VISA_CLEAN_TRANSACTION = "2024-06-20,VISA,BANK-TXN-001,4532123456789012,2500.00,INR,MERCH-101,TERM-101,00,BATCH-001";

    /**
     * Mastercard transaction with missing terminal ID - matches BANK-TXN-002
     */
    public static final String MASTERCARD_MISSING_TERMINAL = "2024-06-20,MASTERCARD,BANK-TXN-002,5234567890123456,5000.00,INR,MERCH-102,,00,BATCH-002";

    /**
     * RuPay transaction with empty amount field (malformed) - matches BANK-TXN-003
     */
    public static final String RUPAY_EMPTY_AMOUNT = "2024-06-20,RUPAY,BANK-TXN-003,4111111111111111,,INR,MERCH-103,TERM-103,00,BATCH-003";

    /**
     * AMEX transaction with invalid date format - matches BANK-TXN-004
     */
    public static final String AMEX_INVALID_DATE = "20/06/2024,AMEX,BANK-TXN-004,6011123456789012,3200.75,USD,MERCH-104,TERM-104,00,BATCH-004";

    /**
     * Visa transaction with missing batch ID and response code - matches
     * BANK-TXN-005
     */
    public static final String VISA_MISSING_FIELDS = "2024-06-20,VISA,BANK-TXN-005,4532987654321098,10000.00,INR,MERCH-105,TERM-105,,";

    /**
     * Malformed CSV with too few fields (missing last 3 columns) - additional test
     * case
     */
    public static final String MALFORMED_TOO_FEW_FIELDS = "2024-06-20,MASTERCARD,BANK-TXN-006,5555555555554444,1000.00,INR,MERCH-106";

    /**
     * Transaction with special characters in merchant ID - additional test case
     */
    public static final String SPECIAL_CHARS_MERCHANT = "2024-06-20,VISA,BANK-TXN-007,4532987654321098,750.75,INR,MERCH-107@#$,TERM-107,00,BATCH-007";

    /**
     * Transaction with negative amount (edge case) - additional test case
     */
    public static final String NEGATIVE_AMOUNT = "2024-06-20,RUPAY,BANK-TXN-008,6011987654321098,-500.00,INR,MERCH-108,TERM-108,00,BATCH-008";

    /**
     * Empty CSV line for testing empty record handling
     */
    public static final String EMPTY_LINE = "";

    /**
     * CSV header line (should be skipped during processing)
     */
    public static final String CSV_HEADER = "date,schemeName,txnId,cardNumber,amount,currency,merchantId,terminalId,responseCode,batchId";

    private RawCsvTestMessages() {
        // Utility class - prevent instantiation
    }

    public static final String[] ALL_MESSAGES = {
            VISA_CLEAN_TRANSACTION,
            MASTERCARD_MISSING_TERMINAL,
            RUPAY_EMPTY_AMOUNT,
            AMEX_INVALID_DATE,
            VISA_MISSING_FIELDS,
            MALFORMED_TOO_FEW_FIELDS,
            SPECIAL_CHARS_MERCHANT,
            NEGATIVE_AMOUNT,
            EMPTY_LINE,
            CSV_HEADER
    };

}