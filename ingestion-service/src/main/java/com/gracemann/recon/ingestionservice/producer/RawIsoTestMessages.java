package com.gracemann.recon.ingestionservice.producer;

/**
 * ----------------------------------------------------------------
 * INTERNAL BANK LEDGER RAW ISO 8583 MESSAGE PRODUCER SAMPLES
 * ----------------------------------------------------------------
 *
 * Raw ISO8583-like test message constants for bank transaction ingestion
 * testing.
 *
 * <p>
 * This class provides static constants containing pipe-delimited raw message
 * strings
 * that simulate bank transaction data in ISO8583-like format. These messages
 * are designed
 * to be pristine and well-formed for testing the ingestion pipeline's happy
 * path scenarios.
 * </p>
 *
 * <p>
 * Each message contains all required fields for bank transactions including:
 * MTI, txnId, cardNumber, amount, txnTimestamp, currency, merchantId,
 * terminalId,
 * responseCode, channel, and authCode.
 * </p>
 *
 * <p>
 * These test constants can be modified as needed to support various ingestion
 * test scenarios.
 * </p>
 *
 * @author Generated for ingestion testing
 * @version 1.0
 */
public class RawIsoTestMessages {

    /**
     * Sample POS transaction message - successful purchase
     */
    public static final String POS_PURCHASE_SUCCESS = "MTI=0200|txnId=BANK-TXN-001|cardNumber=4532123456789012|amount=2500.00|"
            +
            "txnTimestamp=2024-06-20T14:30:15|currency=INR|merchantId=MERCH-001|" +
            "terminalId=TERM-001|responseCode=00|channel=POS|authCode=AUTH123456";

    /**
     * Sample ATM withdrawal message - successful transaction
     */
    public static final String ATM_WITHDRAWAL_SUCCESS = "MTI=0200|txnId=BANK-TXN-002|cardNumber=5234567890123456|amount=5000.00|"
            +
            "txnTimestamp=2024-06-20T15:45:30|currency=INR|merchantId=MERCH-002|" +
            "terminalId=TERM-002|responseCode=00|channel=ATM|authCode=AUTH789012";

    /**
     * Sample online transaction message - successful e-commerce purchase
     */
    public static final String ONLINE_PURCHASE_SUCCESS = "MTI=0200|txnId=BANK-TXN-003|cardNumber=4111111111111111|amount=1750.50|"
            +
            "txnTimestamp=2024-06-20T16:20:45|currency=INR|merchantId=MERCH-003|" +
            "terminalId=TERM-003|responseCode=00|channel=ONLINE|authCode=AUTH345678";

    /**
     * Sample mobile banking transaction message - successful transfer
     */
    public static final String MOBILE_TRANSFER_SUCCESS = "MTI=0200|txnId=BANK-TXN-004|cardNumber=6011123456789012|amount=3200.75|"
            +
            "txnTimestamp=2024-06-20T17:10:12|currency=INR|merchantId=MERCH-004|" +
            "terminalId=TERM-004|responseCode=00|channel=MOBILE|authCode=AUTH901234";

    /**
     * Sample declined transaction message - insufficient funds
     */
    public static final String POS_PURCHASE_DECLINED = "MTI=0210|txnId=BANK-TXN-005|cardNumber=4532987654321098|amount=10000.00|"
            +
            "txnTimestamp=2024-06-20T18:05:33|currency=INR|merchantId=MERCH-005|" +
            "terminalId=TERM-005|responseCode=51|channel=POS|authCode=";

    private RawIsoTestMessages() {
        // Utility class - prevent instantiation
    }

    public static final String[] ALL_MESSAGES = {
            POS_PURCHASE_SUCCESS,
            ATM_WITHDRAWAL_SUCCESS,
            ONLINE_PURCHASE_SUCCESS,
            MOBILE_TRANSFER_SUCCESS,
            POS_PURCHASE_DECLINED
    };

}