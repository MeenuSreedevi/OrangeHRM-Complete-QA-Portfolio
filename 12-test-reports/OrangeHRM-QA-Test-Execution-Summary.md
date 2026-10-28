# OrangeHRM QA — Test Execution Summary

**Report ID:** ORG-QA-EXEC-001  
**Report date:** 2026-10-28  
**Status:** Draft baseline (no verified application test execution recorded)  
**Run ID / Build / Browser / Tester:** To be completed after execution

## Executive summary
The portfolio includes test design and automation source code. Prepared scripts and test plans are **not** evidence that tests passed. Execution outcomes remain Not Executed until recorded with proof.

## Execution summary
| Stream | Planned | Pass | Fail | Blocked | N/A | Not Executed |
|---|---:|---:|---:|---:|---:|---:|
| Manual login | 32 | 0 | 0 | 0 | 0 | 32 |
| Selenium Java | 12 | 0 | 0 | 0 | 0 | 12 |
| Playwright TypeScript | 12 | 0 | 0 | 0 | 0 | 12 |
| Postman API | 7 | 0 | 0 | 0 | 0 | 7 |
| Live SQL (requires authorized access) | 14 | 0 | 0 | 0 | 0 | 14 |
| **Total** | **77** | **0** | **0** | **0** | **0** | **77** |

**Execution coverage:** 0%.  
**Pass rate:** N/A — no decided executions.  
**Confirmed defects recorded:** 0 (not evidence that the application is defect-free).

## Manual testing and regression
**Source:** `../03-test-cases/OrangeHRM-Login-Test-Cases.md`; regression subsets in `../07-regression-suite/`.  
**Evidence:** Pending; smoke subset 9, critical subset 17, full suite 32. Suites overlap.

## Selenium Java
**Source:** `../10-selenium-java/`  
**Command:** `mvn clean test`  
**Planned:** 12 mapped automated tests.  
**Actual:** Not Executed.  
**Expected output:** `target/surefire-reports/` and failure screenshots after running. Record test count, failures/skips, platform and run ID.

## Playwright TypeScript
**Source:** `../11-playwright-agentic-ai/`  
**Command:** `npx playwright test`  
**Planned:** 12 mapped automated tests.  
**Actual:** Not Executed.  
**Expected output:** `playwright-report/` and `test-results/` after running. Record browser version, run ID and sanitized trace links.

## Postman API
**Source:** `../08-postman-api-tests/`  
**Planned:** 7 read-only requests, excluding manual discovery placeholder.  
**Actual:** Not Executed.  
**Risk:** API token / authorized environment might be unavailable. Use **Blocked**, not Pass or Fail, when prerequisites are missing.

## SQL validation
**Source:** `../09-sql-validation/`  
**Live OrangeHRM database:** No authorized access evidenced; 14 candidates Not Executed.  
**Synthetic SQLite fixture:** A separate portfolio exercise, previously described as 14/14 sample checks passed; **not included in live application pass totals**. Record a new run and its evidence separately.

## Defect summary
| Severity | Confirmed defects |
|---|---:|
| Critical | 0 |
| High | 0 |
| Medium | 0 |
| Low | 0 |
| **Recorded total** | **0** |

## Release / readiness judgment
**Not Assessed.** No verified application execution evidence is recorded. The portfolio does not constitute release certification.

## Next update
Run smoke cases, then Selenium and Playwright, record actual outcomes in Excel. Review API prerequisites and mark blocked cases honestly. Keep practice SQL outputs distinct from authorized live database verification.
