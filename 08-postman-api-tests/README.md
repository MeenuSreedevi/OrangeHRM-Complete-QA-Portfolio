# OrangeHRM API Testing Using Postman

**Project:** OrangeHRM Complete QA Portfolio  
**Module:** Login and API authentication / read-only API exploration  
**Document ID:** ORG-API-001  
**Status:** Prepared, not executed  
**Date:** 2026-10-28

## 1. Goal and boundaries

Demonstrate API discovery, secure authentication setup, positive and negative assertions, execution recording, and traceability. The public UI login endpoint is **not** asserted to be a JSON REST login API. Do not automate repeated login failures against the public shared demo. This collection contains only GET requests. No customer/employee data should be committed.

Official OrangeHRM Starter 5.x API docs describe OAuth 2.0 Authorization Code + PKCE for external API requests. Browser UI sign-in may use different session cookies and CSRF flow. A live demo username/password alone does not imply Postman bearer access.

## 2. File layout

```text
08-postman-api-tests/
├── README.md
├── OrangeHRM-API-Tests.postman_collection.json
├── OrangeHRM-QA.postman_environment.json
├── API-Test-Cases.md
└── evidence/
    └── .gitkeep
```

## 3. Import into Postman

1. Open Postman → **Import** → select the `postman_collection.json` file.
2. Import `OrangeHRM-QA.postman_environment.json` as an Environment.
3. Select the imported environment from the environment picker.
4. Confirm `baseUrl` for **your actual deployment**. Other values are placeholders until verified.
5. For OAuth testing, register and authorize an approved client in your own instance, following the linked official docs; store `accessToken` only as a Postman **local/current** secret value, not in shared/exported files.
6. Run a single GET request first; check response status, Content-Type, and JSON structure.
7. Open the Postman **Scripts > Post-response** tab to inspect/edit JavaScript assertions.
8. Save sanitized result snapshots/screenshots under `evidence/`, with no headers, names, passwords, IDs, tokens or cookies visible.

**Do not publish real bearer tokens, credential values, session cookies, OAuth client secrets, or personal employee data.** `accessToken` is empty in the environment template.

## 4. Discover the login network flow

1. Open the OrangeHRM login page in Chrome.
2. Open DevTools (`F12`) → **Network** → **Fetch/XHR** and enable **Preserve log**.
3. Submit a normal authorized login once, using published demo credentials (if allowed and available).
4. Inspect the requests: method, URL, request type, status, redirect chain, content type, and whether it uses forms, CSRF or sessions.
5. Record only nonsecret metadata in a local note. **Never** paste a live password, token, `Cookie` or CSRF value into a GitHub issue or repository.
6. The collection's `Login request — capture in browser first` is a **GET page navigation**, not an invented login POST endpoint.
7. If session/cookie/CSRF logic is required, document a manual workflow or reproduce only in an authorized isolated environment.

## 5. Endpoint catalog

All paths below are relative to `{{baseUrl}}`, which already includes `/web/index.php`.

| Case | Method | Relative path | Authentication | Baseline expected status | Notes |
|---|---|---|---|---|---|
| API-TC-001 | GET | `/api/v2/directory/employees` | Valid OAuth token + permission | 200 | Directory list |
| API-TC-002 | GET | `/api/v2/pim/employees/{{employeeNumber}}` | Valid OAuth token + permission | 200 | Existing ID required |
| API-TC-003 | GET | `/api/v2/admin/users/{{userId}}` | Valid OAuth token + admin permission | 200 | Existing ID required |
| API-TC-004 | GET | `/api/v2/directory/employees` | None | 401 *provisional* | Validate actual API contract |
| API-TC-005 | GET | `/api/v2/pim/employees/{{missingEmployeeNumber}}` | Valid OAuth token + permission | 404 | Docs specify missing-record 404 |
| API-TC-006 | GET | `/api/v2/admin/users/{{missingUserId}}` | Valid OAuth token + admin permission | 404 | Docs specify missing-record 404 |
| API-TC-007 | GET | `/api/v2/directory/employees` | Invalid bearer token | 401 *provisional* | Verify contract |

If a prerequisite is missing, mark **Blocked** rather than assuming a response status or changing assertions to accept unrelated failures.

## 6. Assertions and evidence

Each automated test checks expected HTTP status and an exploratory `<5000 ms` response-time threshold. Positive JSON responses are checked for JSON Content-Type and valid JSON. The threshold is a portfolio benchmark, **not an OrangeHRM SLA**. Add field-level contract assertions once actual schemas are verified. The collection does not automatically discover OAuth tokens.

For each run, record date, browser/Postman version, test environment/build, request ID, expected status, actual status, actual response category, pass/fail/blocked status, evidence and bug ID.

## 7. Traceability

API authentication tests provide supplementary coverage of **ORG-FR-LOGIN-014**, **ORG-FR-LOGIN-017**, **ORG-NFR-LOGIN-001**, and **ORG-NFR-LOGIN-003** if the session or OAuth behavior is confirmed to implement those requirements. Employee/directory data endpoints are *additional API portfolio coverage*, not direct evidence that UI login works. Do not claim a one-to-one mapping between `ORG-TC-LOGIN-002` (UI login) and a directory GET request.

## 8. Defect reporting

Confirmed defects belong in `06-bug-reports`. Every report must include: **Bug Description**, **Steps to Reproduce**, **Actual Result**, **Expected Result**, **Severity**; also add Priority, environment, evidence and linked API case. Report only against confirmed contracts/requirements.

## 9. References

- https://api-starter-orangehrm.readme.io/reference/get-started
- https://api-starter-orangehrm.readme.io/reference/authorization
- https://api-starter-orangehrm.readme.io/reference/get-the-employee-directory
- https://api-starter-orangehrm.readme.io/reference/get-an-employee
- https://api-starter-orangehrm.readme.io/reference/get-a-user

## 10. GitHub commit

```powershell
git add 08-postman-api-tests/
git commit -m "Add OrangeHRM Postman API tests and documentation"
git push origin main
```
