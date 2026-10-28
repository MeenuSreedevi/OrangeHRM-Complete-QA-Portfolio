# OrangeHRM API Test Cases

**Status:** Not Executed  
**Date:** 2026-10-28

| ID | Description | Test Type | Preconditions | Action | Expected Result | Status |
|---|---|---|---|---|---|---|
| API-TC-001 | Directory listing with valid token | Read-only Positive | Valid token, directory permission | `GET /api/v2/directory/employees` | 200 + parseable JSON | Not Executed |
| API-TC-002 | Existing employee details | Read-only Positive | Valid token, authorized employee ID | `GET /api/v2/pim/employees/{empNumber}` | 200 + parseable JSON | Not Executed |
| API-TC-003 | Existing admin user details | Read-only Positive | Admin token and authorized user ID | `GET /api/v2/admin/users/{id}` | 200 + parseable JSON | Not Executed |
| API-TC-004 | Directory without token | Negative Auth | No token | `GET /api/v2/directory/employees` | 401 provisional; check contract | Not Executed |
| API-TC-005 | Unknown employee number | Negative Data | Authorized token + absent ID | `GET /api/v2/pim/employees/{absent empNumber}` | 404 per docs | Not Executed |
| API-TC-006 | Unknown admin user ID | Negative Data | Admin token + absent ID | `GET /api/v2/admin/users/{absent id}` | 404 per docs | Not Executed |
| API-TC-007 | Invalid bearer token | Negative Auth | Known-invalid token | `GET /api/v2/directory/employees` | 401 provisional; check contract | Not Executed |

## Execution record template

```text
Run ID:
API Test Case ID:
Environment and build:
Date/time:
Request URL and method:
Preconditions:
Test data (redacted):
Steps to reproduce:
Expected result:
Actual result:
HTTP status:
Status (Pass / Fail / Blocked / Not Applicable / Not Executed):
Evidence:
Linked defect ID:
```

## Notes

- Status-code expectations marked provisional are hypotheses pending environment and contract confirmation.
- Never include real tokens, cookies, credentials or private employee data in evidence.
- Positive tests require a valid OAuth token and rights.
- Logged-in browser session authentication and OAuth for the REST API are distinct workflows.
