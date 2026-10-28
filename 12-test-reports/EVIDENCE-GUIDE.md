# Execution Evidence Guide

Save evidence only after actual execution; sanitize secrets, cookies, tokens and personal data before committing.

Suggested paths:
- `evidence/manual/RUN-YYYYMMDD-01-ORG-TC-LOGIN-002.png`
- `evidence/selenium/RUN-YYYYMMDD-02-surefire-summary.txt`
- `evidence/playwright/RUN-YYYYMMDD-03-test-summary.md`
- `evidence/postman/RUN-YYYYMMDD-04-postman-results.md`
- `evidence/sql/RUN-YYYYMMDD-05-synthetic-fixture-output.txt`

Store large reports and unsanitized traces in private CI artifacts rather than committing them. In the workbook record the relative evidence path and Run ID for each test.
