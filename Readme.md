# [UPDATE] : Ingestion Engine - Complete

The ingestion engine is now fully implemented with the following features:

* Consumes raw bank and scheme transaction messages from Kafka topics
* Normalizes and validates transaction data using JSR-380 annotations
* Implements deduplication logic to filter out duplicate transactions
* Forwards clean, validated, and unique transactions to the `ingested-txn-topic`
* Includes a test producer utility to simulate raw ISO and CSV messages
* Logs processing metrics and errors for observability

This completes the core ingestion pipeline, providing a reliable, production-ready data intake foundation for downstream matching and reconciliation.

Next steps: Integrate the match engine to consume from the ingested topic and perform reconciliation.
# Why we’re building this

- Every day the bank’s card-switch ledger produces millions of authorizations, while Visa and RuPay clear and settle those same transactions in their own daily files.
- Even a single mismatch is potential revenue leakage or a compliance breach.
- The new Two-Way Reconciliation Engine Two-Way Reconciliation Engine closes that gap by automatically ingesting both
  feeds, matching them with hash-plus-fuzzy logic, and surfacing any discrepancies for rapid resolution.
- In short, it turns reconciliation from a nightly fire-drill into an
  auditable, SLA-backed service that keeps money and trust intact

```
  recon-engine/               ← Git repo root
│  
├─ pom.xml                  ← Parent POM (“mother POM”)
│  
├─ ingestion-service/       ← Module 1
│   └─ pom.xml              ← Child POM
│  
├─ match-engine/            ← Module 2
│   └─ pom.xml
│  
├─ report-service/          ← Module 3
│   └─ pom.xml
│  
├─ exception-api/           ← Module 4
│   └─ pom.xml
│  
├─ scheduler/               ← Module 5
│   └─ pom.xml
│  
└─ monitor-metrics/         ← Module 6
    └─ pom.xml

```

| **Technology** | **What It’s For / Why Used** |
| --- | --- |
| **Java (JDK 17+)** | Main programming language for all backend logic and services |
| **Spring Boot** | Builds REST APIs, microservices, schedulers, security layers, etc. |
| **Apache Kafka** | Ingests (streams in) large volumes of transaction data (if/when needed) |
| **CSV File Input** | Rapid prototyping—read/write transaction and scheme files with zero setup |
| **Apache Cassandra** | Stores all transaction match results (ledger), optimized for speed & scale |
| **PostgreSQL** | Stores exceptions, manual resolutions, and admin/audit data (relational) |
| **MinIO** | Acts as S3-compatible storage for reports and files, runs locally on Windows |
| **JUnit** | Write and run unit tests for code correctness |
| **k6** | Load testing—simulate thousands/millions of transactions |
| **JMH** | Micro-benchmarking for critical sections (match logic, parsing, etc.) |
| **Micrometer** | App metrics collection for Prometheus |
| **Prometheus** | Time-series database to store and visualize app metrics |
| **Grafana** | Dashboard/visualization for system metrics and health |
| **OWASP ZAP** | Scans REST APIs for security vulnerabilities |
| **Spring Security** | Adds HTTPS, authentication, and authorization to your APIs |

---

**Every tool above is open-source, runs natively on Windows, and can be installed without Docker or the cloud.**
