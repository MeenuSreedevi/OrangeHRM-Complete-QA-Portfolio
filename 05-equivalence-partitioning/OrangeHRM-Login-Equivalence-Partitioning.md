# OrangeHRM – Login Module Equivalence Partitioning

| Document metadata | Value |
|---|---|
| Project | OrangeHRM Complete QA Portfolio |
| Module | Authentication – Login |
| Document ID | ORG-EP-AUTH-001 |
| Version | 1.0 |
| Status | Draft – not executed; proposed expectations require verification |
| Environment | https://opensource-demo.orangehrmlive.com/ |
| Requirements reference | `../01-requirements/OrangeHRM-Login-Requirements.md` |
| Scenarios reference | `../02-test-scenarios/OrangeHRM-Login-Test-Scenarios.md` |
| Test cases reference | `../03-test-cases/OrangeHRM-Login-Test-Cases.md` |
| BVA reference | `../04-boundary-value-testing/OrangeHRM-Login-Boundary-Value-Analysis.md` |

## 1. Objective

Use **Equivalence Partitioning (EP)** to group login inputs or application states that are expected to receive equivalent handling. Select representative values from each class, link them to existing cases and identify coverage gaps. This complements Boundary Value Analysis (BVA), which concentrates on exact limits.

**Baseline:** The OrangeHRM public demo is the system under test. The known published demo login is often `Admin` / `admin123`; check the login page for the current values before execution. All tests must be conducted without disrupting the shared public demo. Do not commit private credentials, tokens or session cookies.

**Classification:** `Specified/assumed` means a common authentication expectation reflected by the portfolio requirements, **not** an OrangeHRM-approved specification. `Exploratory` means the observed behavior must be documented and assessed before calling a deviation a bug. Passwords are case-sensitive in the proposed security requirements; username case normalization and allowed lengths are not established.

## 2. Partition design and representative data

| Partition ID | Input / state | Partition class | Representative data or setup | Expected outcome / oracle | Mapping to existing case(s) | Basis |
|---|---|---|---|---|---|---|
| EP-LOGIN-001 | Credential combination | Valid: both credentials correct | `Admin` / `admin123` (confirm on demo) | Authenticated; authorized landing page | `ORG-TC-LOGIN-002`, `011` | Proposed FR-002, FR-011 |
| EP-LOGIN-002 | Credential combination | Invalid: unknown username, correct password | `InvalidUser123` / `admin123` | Authentication denied, generic error | `ORG-TC-LOGIN-003`, `010` | Proposed FR-003, FR-010 |
| EP-LOGIN-003 | Credential combination | Invalid: correct username, wrong password | `Admin` / `WrongPassword123` | Authentication denied, generic error | `ORG-TC-LOGIN-004`, `010` | Proposed FR-004, FR-010 |
| EP-LOGIN-004 | Credential combination | Invalid: both incorrect | `InvalidUser123` / `WrongPassword123` | Authentication denied | `ORG-TC-LOGIN-005`, `017` | Proposed FR-005, FR-017 |
| EP-LOGIN-005 | Username | Invalid: empty value | `""` / `admin123` | No authentication; username required feedback | `ORG-TC-LOGIN-006` | Proposed FR-006 |
| EP-LOGIN-006 | Password | Invalid: empty value | `Admin` / `""` | No authentication; password required feedback | `ORG-TC-LOGIN-007` | Proposed FR-007 |
| EP-LOGIN-007 | Both fields | Invalid: both empty | `""` / `""` | No authentication; relevant required feedback | `ORG-TC-LOGIN-008` | Proposed FR-008 |
| EP-LOGIN-008 | Username | Exploratory: whitespace only | `"   "` / `admin123` | Record validation or rejection behavior; compare with approved trimming policy | `ORG-TC-LOGIN-018` (extend data) | Exploratory; no approved trimming rule |
| EP-LOGIN-009 | Username | Exploratory: leading and trailing whitespace | `" Admin "` / `admin123` | Record whether spaces are trimmed, preserved or rejected | `ORG-TC-LOGIN-018` | Exploratory |
| EP-LOGIN-010 | Password | Exploratory: surrounding spaces | `Admin` / `" admin123 "` | Document behavior; no silent password mutation unless specified | `ORG-TC-LOGIN-019` | Exploratory |
| EP-LOGIN-011 | Username | Exploratory: case-variant of known username | `admin` / `admin123` | Record case-normalization behavior; verify before defect | `ORG-TC-LOGIN-020` | Exploratory |
| EP-LOGIN-012 | Password | Invalid: password case changed | `Admin` / `ADMIN123` | Authentication denied when password differs by case | `ORG-TC-LOGIN-021` | Proposed security expectation |
| EP-LOGIN-013 | Username | Exploratory: nonempty, very long input | 256 ASCII `A` characters / `admin123` | Handled safely, with controlled error/validation; exact limit unconfirmed | `ORG-TC-LOGIN-025` | Exploratory; also BVA |
| EP-LOGIN-014 | Password | Exploratory: nonempty, very long input | `Admin` / 256 ASCII `a` characters | Handled safely without crash or disclosure; exact limit unconfirmed | `ORG-TC-LOGIN-026` | Exploratory; also BVA |
| EP-LOGIN-015 | Password rendering | Valid UI state: text entered into masked field | `SamplePassword123` | Characters masked by default | `ORG-TC-LOGIN-009` | Proposed FR-009 |
| EP-LOGIN-016 | Session | Invalid access state: not signed in | Open recorded protected URL in private window | Protected data unavailable; redirect/deny access | `ORG-TC-LOGIN-014` | Proposed FR-014 |
| EP-LOGIN-017 | Session | Valid state transition: signed in then logout | Login → Logout | Session terminated; protected pages require new authentication | `ORG-TC-LOGIN-015`, `016`, `029` | Proposed FR-015, FR-016, NFR-003 |
| EP-LOGIN-018 | Session | Invalid state: unsuccessful login | Failed login → protected URL | No authenticated session created | `ORG-TC-LOGIN-017` | Proposed FR-017 |
| EP-LOGIN-019 | Password recovery | Valid navigation action | Click Forgot Password | Recovery interface displayed | `ORG-TC-LOGIN-012`, `013` | Proposed FR-012, FR-013 |
| EP-LOGIN-020 | Authentication error disclosure | Invalid username vs incorrect password | Compare EP-002 and EP-003 messages | No username enumeration via user-facing message | `ORG-TC-LOGIN-028` | Proposed NFR-002 |

> **Note:** EP-LOGIN-008 adds a new representative value to existing test case 018. Its original steps can be reused, but the test data should be extended and recorded separately. The exact minimum/maximum field length is **not established**; do not label 255/256 characters as acceptance boundaries without evidence.

## 3. Additional partitions / coverage gaps

These partitions are useful, but not yet mapped to a fully independent executable test case. Add them only after confirming product specifications, environment permissions and test needs.

| Gap ID | Candidate partition | Example | Action |
|---|---|---|---|
| EP-GAP-001 | Username containing special characters | `test!@#` | Add a data row to TC-025 or create a separate exploratory case |
| EP-GAP-002 | Username using non-ASCII letters | `TéstUser` | Verify expected support before assigning a Pass/Fail oracle |
| EP-GAP-003 | Control-character input | Tab/newline pasted in username | Check sanitization with isolated, non-destructive testing |
| EP-GAP-004 | Username with only spaces | `"   "` | Explicitly include this variant in TC-018 |
| EP-GAP-005 | Confirmed min/max username and password length | `min−1`, `min`, `min+1`, `max−1`, `max`, `max+1` | Add once limits are verified; see BVA document |

## 4. Detailed execution examples

### EP-LOGIN-001 – Valid credential partition

- **Requirement IDs:** `ORG-FR-LOGIN-002`, `ORG-FR-LOGIN-011`
- **Test case IDs:** `ORG-TC-LOGIN-002`, `ORG-TC-LOGIN-011`
- **Precondition:** User is logged out; demo site and current demo credentials are available.
- **Test data:** Username `Admin`; password `admin123`, after checking demo page.
- **Steps:**
  1. Open the OrangeHRM demo login page.
  2. Enter the valid username and password.
  3. Click **Login**.
  4. Check page navigation and signed-in content.
- **Expected result:** Valid account authenticates and opens an authorized landing page.
- **Actual result:** Not recorded.
- **Status:** Not Executed.

### EP-LOGIN-003 – Incorrect password partition

- **Requirement IDs:** `ORG-FR-LOGIN-004`, `ORG-FR-LOGIN-010`
- **Test case IDs:** `ORG-TC-LOGIN-004`, `ORG-TC-LOGIN-010`
- **Precondition:** User is logged out.
- **Test data:** Username `Admin`; password `WrongPassword123`.
- **Steps:**
  1. Open the login page.
  2. Enter the known username and an incorrect password.
  3. Click **Login**.
  4. Observe the error message and destination page.
- **Expected result:** Access denied; understandable non-sensitive error message; no authenticated session.
- **Actual result:** Not recorded.
- **Status:** Not Executed.

### EP-LOGIN-008 – Whitespace-only username partition

- **Requirement mapping:** Exploratory; no verified trimming rule.
- **Test case ID:** `ORG-TC-LOGIN-018` (additional test data row).
- **Precondition:** User is logged out.
- **Test data:** Username `"   "` (three spaces); password `admin123`.
- **Steps:**
  1. Open the login page.
  2. Enter three spaces into Username.
  3. Enter the valid demo password.
  4. Click **Login**.
  5. Record whether validation is displayed or input is normalized.
- **Expected result:** User is not authenticated to the demo account. Exact validation presentation requires confirmation.
- **Actual result:** Not recorded.
- **Status:** Not Executed.

## 5. Traceability summary

| Requirement or scope | EP IDs | Existing test cases |
|---|---|---|
| Valid/invalid credential combinations (`ORG-FR-LOGIN-002`–`005`) | EP-001–004 | TC-002–005, TC-010, TC-011, TC-017 |
| Required fields (`ORG-FR-LOGIN-006`–`008`) | EP-005–007 | TC-006–008 |
| Password masking (`ORG-FR-LOGIN-009`) | EP-015 | TC-009 |
| Authentication errors (`ORG-FR-LOGIN-010`) | EP-002, 003, 020 | TC-010, TC-028 |
| Password recovery (`ORG-FR-LOGIN-012`–`013`) | EP-019 | TC-012–013 |
| Session protection (`ORG-FR-LOGIN-014`–`017`) | EP-016–018 | TC-014–017, TC-029 |
| Exploratory username/password classes | EP-008–014 | TC-018–021, TC-025–026 |
| Security error equivalence (`ORG-NFR-LOGIN-002`) | EP-020 | TC-028 |

**Scope distinction:** TC-001 (page controls), TC-022 (Enter key), TC-023 (responsive layout), TC-024 (refresh), TC-027 (HTTPS), TC-030 (keyboard accessibility), TC-031 (browser compatibility) and TC-032 (rate-limit policy) are not fully covered by this *input equivalence* matrix. They remain covered by the broader test suite and should not be falsely marked as EP-covered.

## 6. Execution tracking

| Metric | Count |
|---|---:|
| Defined EP partitions | 20 |
| Exploratory coverage gaps | 5 |
| Executed | 0 |
| Pass | 0 |
| Fail | 0 |
| Blocked | 0 |
| Not Executed | 20 |

**Evidence naming:** `EP-LOGIN-001.png`, `EP-LOGIN-002.png`, etc. Store screenshots in an appropriately named evidence folder under `12-test-reports/`, avoiding screenshots that expose secrets or session tokens.

## 7. Defect reporting rules

Raise bugs only when actual behavior violates a **confirmed** requirement or an established security expectation. For each confirmed defect, record **Bug Description, Steps to Reproduce, Actual Result, Expected Result, and Severity**, together with environment, test data, evidence, EP partition ID and associated test case ID.

**Status:** Draft – ready for guided execution and traceability review.
