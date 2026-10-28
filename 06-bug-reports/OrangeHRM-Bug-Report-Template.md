# OrangeHRM – Login Module Bug Report Template

**Project:** OrangeHRM Complete QA Portfolio  
**Module:** Login / Authentication  
**Environment:** https://opensource-demo.orangehrmlive.com/  
**Report classification:** Template – not a confirmed defect  

> Only create a real bug report when a reproducible deviation from a confirmed requirement is observed. Exploratory expectations are not automatically defects. Never include real passwords, session cookies, or sensitive identifiers in screenshots or logs.

## Bug information

| Field | Value |
|---|---|
| Bug ID | `ORG-BUG-LOGIN-001` (replace with next available ID) |
| Bug Description / Title | [Clear, specific observed problem] |
| Linked Test Case ID | `ORG-TC-LOGIN-___` |
| Requirement ID | `ORG-FR-LOGIN-___` or `ORG-NFR-LOGIN-___` |
| Severity | Critical / High / Medium / Low |
| Priority | P0 / P1 / P2 / P3 |
| Status | New |
| Environment / URL | [Demo/staging URL] |
| Browser / OS | [Version and OS] |
| Build / Version | [If available] |
| Reported By | [Tester] |
| Reported Date | YYYY-MM-DD |
| Reproducibility | Always / Intermittent / Once |
| Evidence | `evidence/ORG-BUG-LOGIN-001.png` |

## Preconditions

1. [Relevant account / permissions and signed-in state]
2. [Specific test environment and data state]

## Test data

- **Username:** [synthetic identifier or official demo username]
- **Password:** [valid / invalid category only; do not record secrets]

## Steps to reproduce

1. Open the approved application URL.
2. [Enter the exact necessary actions in order.]
3. [Submit the form / perform the trigger.]
4. [Observe the outcome.]

## Actual result

[Record exactly what happened: message, page state, URL, screenshot observation. Never invent an observation.]

## Expected result

[Record the confirmed requirement or accepted behavior, and cite its requirement ID.]

## Impact and classification

- **User/business impact:** [Who is affected and how]
- **Severity rationale:** [Why Critical / High / Medium / Low]
- **Priority rationale:** [Why P0 / P1 / P2 / P3]
- **Regression risk:** [Related functionality that may be affected]

## Evidence

- Screenshot: `evidence/ORG-BUG-LOGIN-001.png`
- Video / logs: [safe path, if available]
- Evidence capture date: YYYY-MM-DD

## Retest / closure

- **Fix build:** [TBD]
- **Retest date:** [TBD]
- **Retest result:** Not Retested / Pass / Fail / Blocked
- **Final status:** New / Open / In Progress / Fixed / Retest / Reopened / Closed / Rejected / Deferred
- **Comments:** [TBD]

---

## Educational example — SIMULATED, NOT AN OBSERVED ORANGEHRM DEFECT

**Bug ID:** `DEMO-BUG-001`  
**Description:** Empty username unexpectedly allows login (hypothetical example only)  
**Linked test case:** `ORG-TC-LOGIN-006`  
**Requirement:** `ORG-FR-LOGIN-006` (proposed; verify before treating as accepted)  
**Indicative severity:** Critical if verified as an authentication bypass  
**Indicative priority:** P0 if confirmed in production  

**Steps to reproduce:**

1. Open the login page.
2. Leave Username empty.
3. Enter the authorized demo password.
4. Click Login.

**Actual result (SIMULATED):** The application opens the dashboard without a username.

**Expected result:** Authentication is not granted when required credentials are missing, with suitable validation feedback.

**Evidence:** None; this is a hypothetical illustration and must not be entered into the confirmed defect tracker.

---

## Severity guide

| Severity | Meaning | Example impact (not a real OrangeHRM finding) |
|---|---|---|
| Critical | Authentication bypass, sensitive data exposure, or total core function failure | Unauthenticated access to protected data |
| High | Major login function fails with no reasonable workaround | Valid users cannot log in |
| Medium | Feature incorrect but workaround exists | Misleading validation or broken secondary navigation |
| Low | Cosmetic/minor usability issue | Alignment or spacing inconsistency |

**Priority** is business fix urgency and should be agreed with the team. It is not necessarily identical to severity.

## Traceability examples

| Related test case | Requirement | Bug investigation area |
|---|---|---|
| ORG-TC-LOGIN-002 | ORG-FR-LOGIN-002 | Successful login |
| ORG-TC-LOGIN-006 | ORG-FR-LOGIN-006 | Empty username validation |
| ORG-TC-LOGIN-009 | ORG-FR-LOGIN-009 | Password masking |
| ORG-TC-LOGIN-013 | ORG-FR-LOGIN-013 | Forgot Password navigation |
| ORG-TC-LOGIN-015 | ORG-FR-LOGIN-015 | Logout/session termination |
| ORG-TC-LOGIN-016 | ORG-FR-LOGIN-016 | Back navigation after logout |

**Note:** These are investigation areas and are not claims of actual product defects.
