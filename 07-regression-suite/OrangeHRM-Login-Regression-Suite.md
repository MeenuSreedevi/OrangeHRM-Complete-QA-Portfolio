# OrangeHRM Login Module — Regression Test Suite

**Document ID:** ORG-REG-AUTH-001  
**Version:** 1.0  
**Status:** Designed — not executed  
**System under test:** OrangeHRM public demo (`https://opensource-demo.orangehrmlive.com/`)  
**References:** `../01-requirements/OrangeHRM-Login-Requirements.md`, `../02-test-scenarios/OrangeHRM-Login-Test-Scenarios.md`, `../03-test-cases/OrangeHRM-Login-Test-Cases.md`  
**Companion tracker:** `OrangeHRM-Login-Regression-Execution-Tracker.xlsx`

> **Important:** This is a planned regression suite, not evidence of execution. Some requirements in the portfolio are proposed, not product-confirmed. Treat exploratory behavior as observations until verified. Do not conduct brute-force or stress testing against the shared public demo.

## 1. Objective and scope

Verify that changes to the OrangeHRM login experience do not break authentication, validation, recovery navigation, logout, access protection, and essential usability. Reuse the **same 32 test case IDs** rather than duplicating detailed steps.

## 2. Regression tiers

| Tier | When to execute | Selection | Exit expectation |
|---|---|---|---|
| Smoke | After deployment or when environment is first available | 9 short, high-signal cases | All applicable smoke cases pass; blockers documented |
| Critical regression | After authentication, session or security changes | 17 risk-focused cases | No unresolved critical authentication regressions |
| Full regression | Before a substantial release or after cross-cutting changes | All 32 cases | All applicable cases executed, deviations triaged |

**Overlap is intentional:** smoke and critical suites are subsets of the 32-case full suite. A test can belong to both.

## 3. Smoke test suite

| Order | Test Case ID | Focus |
|---:|---|---|
| 1 | `ORG-TC-LOGIN-001` | Login page controls |
| 2 | `ORG-TC-LOGIN-002` | Valid login |
| 3 | `ORG-TC-LOGIN-006` | Empty username |
| 4 | `ORG-TC-LOGIN-007` | Empty password |
| 5 | `ORG-TC-LOGIN-008` | Both fields empty |
| 6 | `ORG-TC-LOGIN-011` | Post-login navigation |
| 7 | `ORG-TC-LOGIN-012` | Forgot Password link |
| 8 | `ORG-TC-LOGIN-013` | Forgot Password navigation |
| 9 | `ORG-TC-LOGIN-015` | Logout invalidates session |

## 4. Critical regression suite

| Order | Test Case ID | Risk / focus |
|---:|---|---|
| 1 | `ORG-TC-LOGIN-002` | Valid login |
| 2 | `ORG-TC-LOGIN-003` | Invalid username with valid password |
| 3 | `ORG-TC-LOGIN-004` | Valid username with invalid password |
| 4 | `ORG-TC-LOGIN-005` | Both credentials invalid |
| 5 | `ORG-TC-LOGIN-006` | Empty username |
| 6 | `ORG-TC-LOGIN-007` | Empty password |
| 7 | `ORG-TC-LOGIN-008` | Both fields empty |
| 8 | `ORG-TC-LOGIN-010` | Invalid credentials error |
| 9 | `ORG-TC-LOGIN-011` | Post-login navigation |
| 10 | `ORG-TC-LOGIN-014` | Protected URL without login |
| 11 | `ORG-TC-LOGIN-015` | Logout invalidates session |
| 12 | `ORG-TC-LOGIN-016` | Back navigation after logout |
| 13 | `ORG-TC-LOGIN-017` | Failed login does not establish session |
| 14 | `ORG-TC-LOGIN-021` | Password case sensitivity |
| 15 | `ORG-TC-LOGIN-027` | HTTPS authentication |
| 16 | `ORG-TC-LOGIN-028` | Username enumeration resistance |
| 17 | `ORG-TC-LOGIN-029` | Invalidated session reuse |

## 5. Full suite and traceability

| Test Case ID | Scenario ID | Requirement mapping | Description | Priority | Suite membership | Initial status |
|---|---|---|---|---|---|---|
| ORG-TC-LOGIN-001 | ORG-TS-LOGIN-001 | ORG-FR-LOGIN-001 | Login page controls | High | Smoke + Full | Not Executed |
| ORG-TC-LOGIN-002 | ORG-TS-LOGIN-002 | ORG-FR-LOGIN-002 | Valid login | Critical | Smoke + Critical + Full | Not Executed |
| ORG-TC-LOGIN-003 | ORG-TS-LOGIN-003 | ORG-FR-LOGIN-003 | Invalid username with valid password | Critical | Critical + Full | Not Executed |
| ORG-TC-LOGIN-004 | ORG-TS-LOGIN-004 | ORG-FR-LOGIN-004 | Valid username with invalid password | Critical | Critical + Full | Not Executed |
| ORG-TC-LOGIN-005 | ORG-TS-LOGIN-005 | ORG-FR-LOGIN-005 | Both credentials invalid | Critical | Critical + Full | Not Executed |
| ORG-TC-LOGIN-006 | ORG-TS-LOGIN-006 | ORG-FR-LOGIN-006 | Empty username | Critical | Smoke + Critical + Full | Not Executed |
| ORG-TC-LOGIN-007 | ORG-TS-LOGIN-007 | ORG-FR-LOGIN-007 | Empty password | Critical | Smoke + Critical + Full | Not Executed |
| ORG-TC-LOGIN-008 | ORG-TS-LOGIN-008 | ORG-FR-LOGIN-008 | Both fields empty | Critical | Smoke + Critical + Full | Not Executed |
| ORG-TC-LOGIN-009 | ORG-TS-LOGIN-009 | ORG-FR-LOGIN-009 | Password masking | Medium | Full only | Not Executed |
| ORG-TC-LOGIN-010 | ORG-TS-LOGIN-010 | ORG-FR-LOGIN-010 | Invalid credentials error | Critical | Critical + Full | Not Executed |
| ORG-TC-LOGIN-011 | ORG-TS-LOGIN-011 | ORG-FR-LOGIN-011 | Post-login navigation | Critical | Smoke + Critical + Full | Not Executed |
| ORG-TC-LOGIN-012 | ORG-TS-LOGIN-012 | ORG-FR-LOGIN-012 | Forgot Password link | High | Smoke + Full | Not Executed |
| ORG-TC-LOGIN-013 | ORG-TS-LOGIN-013 | ORG-FR-LOGIN-013 | Forgot Password navigation | High | Smoke + Full | Not Executed |
| ORG-TC-LOGIN-014 | ORG-TS-LOGIN-014 | ORG-FR-LOGIN-014 | Protected URL without login | Critical | Critical + Full | Not Executed |
| ORG-TC-LOGIN-015 | ORG-TS-LOGIN-015 | ORG-FR-LOGIN-015 | Logout invalidates session | Critical | Smoke + Critical + Full | Not Executed |
| ORG-TC-LOGIN-016 | ORG-TS-LOGIN-016 | ORG-FR-LOGIN-016 | Back navigation after logout | Critical | Critical + Full | Not Executed |
| ORG-TC-LOGIN-017 | ORG-TS-LOGIN-017 | ORG-FR-LOGIN-017 | Failed login does not establish session | Critical | Critical + Full | Not Executed |
| ORG-TC-LOGIN-018 | ORG-TS-LOGIN-018 | Exploratory (confirm requirement) | Username surrounding spaces | Medium | Full only | Not Executed |
| ORG-TC-LOGIN-019 | ORG-TS-LOGIN-019 | Exploratory (confirm requirement) | Password surrounding spaces | Medium | Full only | Not Executed |
| ORG-TC-LOGIN-020 | ORG-TS-LOGIN-020 | Exploratory (confirm requirement) | Username case behavior | Medium | Full only | Not Executed |
| ORG-TC-LOGIN-021 | ORG-TS-LOGIN-021 | Exploratory (confirm requirement) | Password case sensitivity | Critical | Critical + Full | Not Executed |
| ORG-TC-LOGIN-022 | ORG-TS-LOGIN-022 | Exploratory (confirm requirement) | Enter-key submission | Medium | Full only | Not Executed |
| ORG-TC-LOGIN-023 | ORG-TS-LOGIN-023 | Exploratory (confirm requirement) | Responsive login UI | Medium | Full only | Not Executed |
| ORG-TC-LOGIN-024 | ORG-TS-LOGIN-024 | Exploratory (confirm requirement) | Refresh login page | Medium | Full only | Not Executed |
| ORG-TC-LOGIN-025 | ORG-TS-LOGIN-025 | Exploratory (confirm requirement) | Long username | Medium | Full only | Not Executed |
| ORG-TC-LOGIN-026 | ORG-TS-LOGIN-026 | Exploratory (confirm requirement) | Long password | Medium | Full only | Not Executed |
| ORG-TC-LOGIN-027 | ORG-TS-LOGIN-027 | ORG-NFR-LOGIN-001 | HTTPS authentication | Critical | Critical + Full | Not Executed |
| ORG-TC-LOGIN-028 | ORG-TS-LOGIN-028 | ORG-NFR-LOGIN-002 | Username enumeration resistance | Critical | Critical + Full | Not Executed |
| ORG-TC-LOGIN-029 | ORG-TS-LOGIN-029 | ORG-NFR-LOGIN-003 | Invalidated session reuse | Critical | Critical + Full | Not Executed |
| ORG-TC-LOGIN-030 | ORG-TS-LOGIN-030 | ORG-NFR-LOGIN-004 | Keyboard accessibility | Medium | Full only | Not Executed |
| ORG-TC-LOGIN-031 | ORG-TS-LOGIN-031 | ORG-NFR-LOGIN-005 | Browser compatibility | Medium | Full only | Not Executed |
| ORG-TC-LOGIN-032 | ORG-TS-LOGIN-032 | ORG-NFR-LOGIN-006 | Credential-guessing policy review | Medium | Full only | Not Executed |

## 6. Preconditions and test data

- The public demo URL is reachable and allowed for non-disruptive manual testing.
- A browser and its version are recorded at run time.
- Current published demo credentials are verified on the live login page (historically `Admin` / `admin123`; they may change).
- The tester can log out, start a fresh private window and capture screenshots.
- An approved isolated environment is required for security control tests beyond normal, low-volume behavior.
- Test data for invalid credentials and blank fields comes from the original 32 test cases; do not store private passwords in committed files.

## 7. How to execute a regression run

1. Open the companion Excel tracker. Record a unique run ID such as `REG-2026-10-28-01`, build, environment, browser/version and tester.
2. Select the tier: **Smoke**, **Critical**, or **Full**. Filter the `Cases` sheet by its membership column.
3. Open the linked detailed test case in `../03-test-cases/OrangeHRM-Login-Test-Cases.md` and follow its steps.
4. In the `Cases` sheet update `Status` (`Pass`, `Fail`, `Blocked`, `Not Applicable`, `Not Executed`), `Actual Result`, `Evidence`, and `Bug ID`.
5. For an unexpected result, reproduce safely, compare against a confirmed requirement, and create a bug in `../06-bug-reports/` if warranted.
6. Review the Dashboard; decide release readiness using the documented exit criteria and outstanding risk.
7. For subsequent runs, copy the `Cases` sheet or create a new workbook named by run ID to preserve previous run evidence. Do **not** overwrite a historical run.

## 8. Exit criteria

**Smoke:** All applicable smoke tests pass; any blocked test is evaluated before proceeding.  
**Critical regression:** Zero unresolved critical-severity confirmed regression defects in authentication/session paths; security-policy checks may be marked Blocked if the public demo cannot safely support them.  
**Full regression:** All applicable tests have an executed disposition (Pass / Fail / Blocked / Not Applicable), failures linked to defects, and outstanding risks summarized.  

**Suggested metrics:** `Execution % = (Pass + Fail + Blocked + Not Applicable) / Selected`; `Pass % = Pass / (Pass + Fail)` when at least one case is executed with a pass/fail result. Do not count Blocked or Not Applicable as passes.

## 9. Defect and evidence convention

- Example evidence: `../06-bug-reports/evidence/REG-2026-10-28-01_ORG-TC-LOGIN-006.png`.
- Bug IDs: `ORG-BUG-LOGIN-001`, etc.; real defects only.
- Every bug report should include **Bug Description**, **Steps to Reproduce**, **Actual Result**, **Expected Result**, and **Severity**, as well as priority, environment, evidence and linked test case.
- Never upload credentials, session tokens, cookies, or personal information in screenshots and logs.

## 10. Suggested execution report template

```text
Run ID:
Date:
Build/version:
Environment:
Browser/version:
Tester:
Suite tier:
Selected / Pass / Fail / Blocked / N/A / Not Executed:
Confirmed bug IDs:
Known limitations:
Release recommendation: Ready / Ready with risk / Not ready / Inconclusive
```

## 11. Maintenance

Update suite membership when login requirements, supported browsers, authentication flows or risk levels change. Maintain one canonical detailed test case per ID and keep regression execution history separately.