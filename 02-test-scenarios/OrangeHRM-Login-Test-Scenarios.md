# OrangeHRM – Login Module Test Scenarios

**Project:** OrangeHRM Complete QA Portfolio  
**Module:** Authentication – Login  
**Document ID:** ORG-TS-AUTH-001  
**Version:** 1.0  
**Test Type:** Functional, Negative, UI, Security, Regression  
**Status:** Draft – Pending Execution  
**Reference Document:** ORG-FRS-AUTH-001

---

## 1. Objective

To identify comprehensive test scenarios for verifying the OrangeHRM Login Module, including successful authentication, invalid credentials, mandatory field validation, navigation, security, and session handling.

## 2. Test Environment

| Parameter | Details |
|---|---|
| Application | OrangeHRM |
| Environment | Public Demo |
| URL | https://opensource-demo.orangehrmlive.com/ |
| Browser | Google Chrome |
| Test Level | System Testing |
| Test Design | Positive and Negative Testing |
| Automation Candidates | Selenium Java, Playwright |

## 3. Functional Test Scenarios

| Scenario ID | Requirement ID | Test Scenario | Priority | Test Type |
|---|---|---|---|---|
| ORG-TS-LOGIN-001 | ORG-FR-LOGIN-001 | Verify login page displays username, password, and login controls | High | UI |
| ORG-TS-LOGIN-002 | ORG-FR-LOGIN-002 | Verify successful login using valid credentials | Critical | Positive |
| ORG-TS-LOGIN-003 | ORG-FR-LOGIN-003 | Verify login using invalid username and valid password | High | Negative |
| ORG-TS-LOGIN-004 | ORG-FR-LOGIN-004 | Verify login using valid username and invalid password | High | Negative |
| ORG-TS-LOGIN-005 | ORG-FR-LOGIN-005 | Verify login using invalid username and invalid password | High | Negative |
| ORG-TS-LOGIN-006 | ORG-FR-LOGIN-006 | Verify validation when username is empty | High | Validation |
| ORG-TS-LOGIN-007 | ORG-FR-LOGIN-007 | Verify validation when password is empty | High | Validation |
| ORG-TS-LOGIN-008 | ORG-FR-LOGIN-008 | Verify validation when both fields are empty | High | Validation |
| ORG-TS-LOGIN-009 | ORG-FR-LOGIN-009 | Verify entered password characters are masked | High | UI |
| ORG-TS-LOGIN-010 | ORG-FR-LOGIN-010 | Verify appropriate error message for incorrect credentials | High | Negative |
| ORG-TS-LOGIN-011 | ORG-FR-LOGIN-011 | Verify redirection to the authorized landing page after login | Critical | Navigation |
| ORG-TS-LOGIN-012 | ORG-FR-LOGIN-012 | Verify Forgot Password link is displayed | Medium | UI |
| ORG-TS-LOGIN-013 | ORG-FR-LOGIN-013 | Verify Forgot Password navigation | High | Navigation |
| ORG-TS-LOGIN-014 | ORG-FR-LOGIN-014 | Verify protected pages cannot be accessed without authentication | Critical | Security |
| ORG-TS-LOGIN-015 | ORG-FR-LOGIN-015 | Verify successful logout terminates the session | Critical | Functional |
| ORG-TS-LOGIN-016 | ORG-FR-LOGIN-016 | Verify browser Back button does not restore protected access after logout | High | Security |
| ORG-TS-LOGIN-017 | ORG-FR-LOGIN-017 | Verify invalid login does not establish an authenticated session | Critical | Security |

## 4. Additional Exploratory Test Scenarios

These scenarios supplement the functional requirements. Their expected behavior should be confirmed before raising defects.

| Scenario ID | Test Scenario | Priority | Test Type |
|---|---|---|---|
| ORG-TS-LOGIN-018 | Verify login with leading and trailing spaces in username | Medium | Negative / Input Handling |
| ORG-TS-LOGIN-019 | Verify login with leading and trailing spaces in password | Medium | Negative / Input Handling |
| ORG-TS-LOGIN-020 | Verify username case-sensitivity behavior | Medium | Functional |
| ORG-TS-LOGIN-021 | Verify password case sensitivity | High | Security |
| ORG-TS-LOGIN-022 | Verify form behavior when Enter key is pressed | Medium | Usability |
| ORG-TS-LOGIN-023 | Verify login page appearance at different screen sizes | Medium | UI |
| ORG-TS-LOGIN-024 | Verify login page behavior after browser refresh | Medium | Functional |
| ORG-TS-LOGIN-025 | Verify login using unusually long username input | Medium | Boundary |
| ORG-TS-LOGIN-026 | Verify login using unusually long password input | Medium | Boundary |

## 5. Non-Functional Test Scenarios

| Scenario ID | Requirement ID | Test Scenario | Priority |
|---|---|---|---|
| ORG-TS-LOGIN-027 | ORG-NFR-LOGIN-001 | Verify authentication requests use HTTPS | Critical |
| ORG-TS-LOGIN-028 | ORG-NFR-LOGIN-002 | Verify authentication errors do not disclose username existence | High |
| ORG-TS-LOGIN-029 | ORG-NFR-LOGIN-003 | Verify invalidated sessions cannot be reused | Critical |
| ORG-TS-LOGIN-030 | ORG-NFR-LOGIN-004 | Verify keyboard accessibility of login controls | Medium |
| ORG-TS-LOGIN-031 | ORG-NFR-LOGIN-005 | Verify login compatibility across supported browsers | Medium |
| ORG-TS-LOGIN-032 | ORG-NFR-LOGIN-006 | Verify account protection behavior against repeated failed authentication according to approved policy | High |

**Security testing note:** Use authorized environments and non-disruptive checks. Avoid aggressive login attempts against the shared public demo.

## 6. Test Data Categories

| Category | Sample Data |
|---|---|
| Valid username | Admin |
| Valid password | admin123 |
| Invalid username | InvalidUser123 |
| Invalid password | WrongPassword123 |
| Empty input | Blank field |
| Space input | Leading/trailing spaces |
| Case variation | admin / ADMIN |
| Special characters | !@#$%^ |
| Long input | Generated test string of agreed length |

## 7. Test Scenario Summary

| Test Category | Count |
|---|---:|
| Functional / UI / Validation / Session | 17 |
| Additional Exploratory | 9 |
| Non-Functional | 6 |
| **Total Test Scenarios** | **32** |

## 8. Execution Guidelines

1. Confirm the actual login page behavior.
2. Check proposed requirements against observed functionality.
3. Prepare detailed test cases for applicable scenarios.
4. Execute the scenarios using authorized test credentials.
5. Record actual results with supporting evidence.
6. Report confirmed defects using the established bug-report format.
7. Identify stable scenarios suitable for regression automation.

## 9. Next Deliverable

The next artifact is:

`03-test-cases/OrangeHRM-Login-Test-Cases.md`

Each test case will include:

- Test Case ID
- Test Scenario ID
- Requirement ID
- Description
- Preconditions
- Test Data
- Steps to Reproduce
- Expected Result
- Actual Result
- Status
- Priority

---

**Document Status:** Draft – Ready for test case design.