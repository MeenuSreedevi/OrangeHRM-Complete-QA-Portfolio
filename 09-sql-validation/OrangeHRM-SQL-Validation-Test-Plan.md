# OrangeHRM – SQL Validation Test Plan

**Project:** OrangeHRM Complete QA Portfolio  
**Module:** Login / Authentication  
**Document ID:** ORG-SQL-AUTH-001  
**Version:** 1.0  
**Status:** Prepared – application database tests not executed  
**Scope:** Read-only database verification and synthetic SQLite practice fixtures

## 1. Objective

Demonstrate SQL-based quality checks for account records, status, uniqueness, role/employee relations, session state, login attempts, and password-reset metadata. Link each check to existing Login Module test cases where meaningful. SQL queries complement, but **do not replace**, UI/API authentication tests.

## 2. Critical environment distinction

The OrangeHRM public demo does **not** grant you direct SQL/database access. All executable scripts in this folder use **SQLite and synthetic data**. Their tables (`users`, `roles`, `employees`, `user_sessions`, `login_attempts`, `password_reset_requests`) are a **teaching model**, not claimed to be the actual OrangeHRM schema or production implementation.

OrangeHRM Starter uses a MySQL-compatible database in its documented installation flow. Source versions may contain tables such as `ohrm_user`, `ohrm_user_role`, and `hs_hr_employee`, but column names, constraints, and session/audit mechanisms must be verified against the **specific installed version** before writing real queries. Never compare plaintext passwords or query real password hashes for portfolio evidence.

**Safety:** Use your own isolated local database for the fixture. On a real OrangeHRM installation, obtain authorization, use read-only database credentials, avoid production personal data, and redact identifiers/screenshots before publication.

## 3. Local sample data

| Table | Key fields | Synthetic records |
|---|---|---|
| `roles` | `role_id`, `role_name` | Admin, ESS |
| `employees` | `employee_id`, `full_name`, `employment_status` | Alex Morgan, Taylor Lee, Jordan Casey |
| `users` | `user_id`, `username`, `role_id`, `employee_id`, `account_status`, `deleted` | Admin, ess_user, disabled_user, deleted_user |
| `user_sessions` | `session_id`, `user_id`, `revoked_at` | one active, one revoked |
| `login_attempts` | `attempted_username`, `success` | one successful, two failed |
| `password_reset_requests` | `user_id`, `state` | one requested reset |

`credential_marker` values in the fixture are dummy markers, **not passwords or hashes**.

## 4. Test scenarios, expectations, and existing case mappings

All `SQL-TC-*` rows are synthetic practice tests; the listed mappings indicate which existing UI test they could *support*. They do **not** demonstrate that the UI case has passed.

| SQL ID | Validation scenario | Expected fixture result | Existing Login case | Requirement or relationship |
|---|---|---|---|---|
| SQL-TC-001 | Find active, nondeleted account | 1 Admin record | ORG-TC-LOGIN-002 | ORG-FR-LOGIN-002; supports account precondition |
| SQL-TC-002 | Invalid username absent | 0 records | ORG-TC-LOGIN-003 | ORG-FR-LOGIN-003; data setup only |
| SQL-TC-003 | Identify disabled account | 1 disabled user | ORG-TC-LOGIN-017 | ORG-FR-LOGIN-017; additional policy-dependent coverage |
| SQL-TC-004 | Identify soft-deleted account | 1 deleted user | ORG-TC-LOGIN-017 | ORG-FR-LOGIN-017; exploratory |
| SQL-TC-005 | Duplicate username detection | 0 duplicate groups | ORG-TC-LOGIN-002 | Data-integrity supplement; not an existing login case assertion |
| SQL-TC-006 | Null/blank username detection | 0 invalid records | ORG-TC-LOGIN-006 | ORG-FR-LOGIN-006; persisted data constraint supplement |
| SQL-TC-007 | User-role relationship | Admin mapped to Admin role | ORG-TC-LOGIN-011 | ORG-FR-LOGIN-011; authorization precondition |
| SQL-TC-008 | User-employee relationship | ess_user mapped to Taylor Lee | ORG-TC-LOGIN-011 | Supporting mapping only |
| SQL-TC-009 | Count failed Admin attempts | 1 | ORG-TC-LOGIN-004 | ORG-FR-LOGIN-004; illustrative audit linkage |
| SQL-TC-010 | Active session present | 1 active Admin session | ORG-TC-LOGIN-002 | ORG-FR-LOGIN-002; illustrative session linkage |
| SQL-TC-011 | Revoked session detected | 1 revoked ESS session | ORG-TC-LOGIN-015 | ORG-FR-LOGIN-015; illustrative logout linkage |
| SQL-TC-012 | Password reset state | REQUESTED | ORG-TC-LOGIN-013 | ORG-FR-LOGIN-013; illustrative recovery linkage |
| SQL-TC-013 | Orphaned role mappings | 0 | ORG-TC-LOGIN-011 | Data-integrity supplement |
| SQL-TC-014 | Count users assigned ESS | 3 (includes deleted user) | ORG-TC-LOGIN-011 | Exploratory role distribution; scope explicitly includes deleted |

**Test design caution:** UI login success depends on credential verification and application logic, not merely existence of a user row. The simulated `user_sessions` and `login_attempts` tables cannot establish which real OrangeHRM session or logging implementation is used.

## 5. Execution steps for each SQL test

**Preconditions:** Python 3 and access to an isolated local SQLite database (the Python script uses an in-memory database automatically). No external packages required.

1. In VS Code, open the portfolio root and terminal.
2. Run `python 09-sql-validation/03_execute_checks.py` (or `py 09-sql-validation/03_execute_checks.py` on Windows).
3. Review the 14 individual expected/actual values.
4. Open `02_validation_queries.sql` to review the corresponding `SELECT` statements.
5. If using DB Browser for SQLite, import `01_schema_and_sample_data.sql` into a **new local** database, then run the queries one at a time.
6. Record the environment, run date, actual result, status, evidence, and any bug ID in a separate execution log.

**Expected fixture summary:** `14/14 PASS`. This result describes **only the local synthetic fixture**, not the OrangeHRM public demo.

## 6. Example detailed test case

**Test Case ID:** SQL-TC-005  
**Description:** Verify duplicate usernames are not present in synthetic user records.  
**Linked UI Test:** ORG-TC-LOGIN-002 (supporting data quality)  
**Precondition:** Local fixture loaded successfully.  
**Test Data:** Usernames Admin, ess_user, disabled_user, deleted_user.  
**Steps:**

1. Run the duplicate-username query from `02_validation_queries.sql`.
2. Inspect rows returned by `GROUP BY username HAVING COUNT(*) > 1`.
3. Record output.

**Expected Result:** Zero rows.  
**Actual Result (local fixture):** Zero rows (validated by execution script).  
**Status:** Pass – Synthetic Fixture Only.  
**Severity if a confirmed application defect:** Determine based on whether duplicate usernames cause real authentication or account-management impact; do not assign severity from fixture behavior alone.

## 7. Real OrangeHRM database validation: adaptation checklist

Before attempting real-database validation:

- Establish authorization and obtain a **read-only** connection to an isolated OrangeHRM instance.
- Identify deployment/version and inspect actual schema (`SHOW TABLES`, `DESCRIBE ...` in MySQL/MariaDB), after approval.
- Confirm precise meaning of `status`, `deleted`, role associations, and employee relationships for that release.
- Do not assume session or login attempts are stored in any named table.
- Use approved synthetic test accounts; do not export employee personal details, passwords, hashes, cookies, or tokens.
- Map checks to UI/API results using the same approved account and test window.
- Record any differences as observations until a requirement is confirmed.

## 8. Evidence and reporting

Suggested evidence folder: `09-sql-validation/evidence/` (redacted outputs only). Suggested evidence names: `SQL-TC-005-local-fixture.txt`, `SQL-TC-011-local-fixture.png`. In any confirmed defect report include **Bug Description, Steps to Reproduce, Actual Result, Expected Result, Severity**, plus Priority, Environment, Evidence, and Linked Test Case ID.

For portfolio reporting, mark these 14 tests **Passed (Synthetic Fixture)** and the corresponding **real OrangeHRM DB tests Not Executed / Access Not Available**. Do not mix their pass counts.

## 9. Next phase

Create `10-selenium-java/` automated UI tests using existing `ORG-TC-LOGIN-*` IDs. Automation reports may reference this SQL plan but should not claim DB validation without access.
