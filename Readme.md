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
