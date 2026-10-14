# OrangeHRM – Login Module Requirements Specification

**Project:** OrangeHRM Complete QA Portfolio  
**Module:** Authentication – Login  
**Document ID:** ORG-FRS-AUTH-001  
**Version:** 1.0  
**Document Type:** Functional Requirements Specification  
**Prepared By:** QA Engineer  
**Date:** 14 October 2026  
**Status:** Draft – Under Verification

---

## 1. Introduction

### 1.1 Purpose

This document defines functional and non-functional requirements for the OrangeHRM Login Module. The objective is to establish a baseline for manual testing, test case design, regression testing, and automation.

### 1.2 Application Details

| Field | Details |
|---|---|
| Application | OrangeHRM |
| Application Type | Web-based HR Management System |
| Test Environment | Public Demo |
| URL | https://opensource-demo.orangehrmlive.com/ |
| Module | Login / Authentication |
| Testing Approach | Manual and Automated |
| Browser | Google Chrome |
| Requirement Source | Public demo observation and QA-proposed requirements |

### 1.3 Scope

**In Scope**

- Login page elements
- Valid and invalid login
- Mandatory field validation
- Password masking
- Error message handling
- Forgot Password navigation
- Post-login navigation
- Session and logout behavior
- Basic authentication security checks

**Out of Scope**

- Employee management
- Leave management
- Payroll functionality
- Recruitment
- Role administration
- Third-party authentication integrations

## 2. Requirement Classification

The document uses two classifications:

- **Observed baseline:** Behavior or UI elements identified from the publicly available OrangeHRM demo.
- **Proposed requirement:** Expected behavior based on standard QA practices that must be verified against the application's actual behavior or official specifications.

A failed proposed requirement must not automatically be reported as a product defect.

## 3. Functional Requirements

| Requirement ID | Requirement Description | Priority | Classification |
|---|---|---|---|
| ORG-FR-LOGIN-001 | The application shall display a login page containing Username, Password and Login controls. | High | Observed baseline |
| ORG-FR-LOGIN-002 | The application shall authenticate users with valid credentials. | Critical | Proposed |
| ORG-FR-LOGIN-003 | The application shall reject an invalid username and valid password combination. | High | Proposed |
| ORG-FR-LOGIN-004 | The application shall reject a valid username and invalid password combination. | High | Proposed |
| ORG-FR-LOGIN-005 | The application shall reject invalid username and password combinations. | High | Proposed |
| ORG-FR-LOGIN-006 | The application shall validate the username field when left empty. | High | Proposed |
| ORG-FR-LOGIN-007 | The application shall validate the password field when left empty. | High | Proposed |
| ORG-FR-LOGIN-008 | The application shall validate when both login fields are empty. | High | Proposed |
| ORG-FR-LOGIN-009 | The application shall mask password characters during entry. | High | Proposed |
| ORG-FR-LOGIN-010 | The application shall provide a suitable authentication error message for unsuccessful login attempts. | High | Proposed |
| ORG-FR-LOGIN-011 | The application shall navigate an authenticated user to the appropriate landing page. | Critical | Proposed |
| ORG-FR-LOGIN-012 | The application shall display a Forgot Password link. | Medium | Observed baseline |
| ORG-FR-LOGIN-013 | The Forgot Password link shall navigate to the password recovery screen. | High | Proposed |
| ORG-FR-LOGIN-014 | The application shall prevent unauthenticated access to protected pages. | Critical | Proposed |
| ORG-FR-LOGIN-015 | The application shall terminate the authenticated session on logout. | Critical | Proposed |
| ORG-FR-LOGIN-016 | The application shall prevent access to protected content after logout using browser navigation. | High | Proposed |
| ORG-FR-LOGIN-017 | The application shall prevent submission of an invalid authentication request from establishing a session. | Critical | Proposed |

## 4. Detailed Requirements and Acceptance Criteria

### ORG-FR-LOGIN-001 – Login Page Display

**Description:** The application shall display a login page with the necessary authentication controls.

**Acceptance Criteria:**

- Given the user is unauthenticated,
- When the user opens the OrangeHRM login URL,
- Then the application displays the Username field, Password field, and Login button.

### ORG-FR-LOGIN-002 – Valid Login

**Description:** The application shall authenticate a user with valid credentials.

**Acceptance Criteria:**

- Given the user is on the login page,
- When the user enters a valid username and password and clicks Login,
- Then the application authenticates the user and navigates to the authorized landing page.

### ORG-FR-LOGIN-003 – Invalid Username

**Description:** The application shall reject incorrect usernames.

**Acceptance Criteria:**

- Given the user is on the login page,
- When the user enters an invalid username and a valid password,
- Then the application rejects authentication and displays an appropriate error message.

### ORG-FR-LOGIN-004 – Invalid Password

**Description:** The application shall reject incorrect passwords.

**Acceptance Criteria:**

- Given the user is on the login page,
- When the user enters a valid username and an invalid password,
- Then access is denied and an appropriate error message is displayed.

### ORG-FR-LOGIN-005 – Invalid Credentials

**Acceptance Criteria:**

- Given the user is on the login page,
- When both the username and password are invalid,
- Then the application shall not establish an authenticated session.

### ORG-FR-LOGIN-006 to 008 – Mandatory Field Validation

**Acceptance Criteria:**

- Submitting an empty username shall trigger username validation.
- Submitting an empty password shall trigger password validation.
- Submitting both fields empty shall prevent successful authentication.
- Validation messages shall be readable and associated with the corresponding fields.

### ORG-FR-LOGIN-009 – Password Masking

**Acceptance Criteria:**

- Given the user is entering a password,
- When characters are typed,
- Then the password shall be visually masked rather than displayed as plain text by default.

### ORG-FR-LOGIN-010 – Authentication Error Handling

**Acceptance Criteria:**

- Invalid credentials shall produce a clear error message.
- The message shall not expose sensitive authentication details.
- The user shall remain unauthenticated.

### ORG-FR-LOGIN-011 – Successful Navigation

**Acceptance Criteria:**

- After successful authentication, the user shall reach an authorized application landing page.
- The login form shall no longer be displayed as the active page.

### ORG-FR-LOGIN-012 and 013 – Forgot Password

**Acceptance Criteria:**

- The login page shall display the Forgot Password link.
- Clicking the link shall open the available password recovery interface.
- Password recovery behavior shall be documented and tested separately.

### ORG-FR-LOGIN-014 to 017 – Session Protection

**Acceptance Criteria:**

- Unauthenticated requests shall not expose protected application data.
- Logout shall invalidate the authenticated session.
- Browser navigation after logout shall not restore authenticated access without reauthentication.
- Failed authentication attempts shall not establish valid application sessions.

## 5. Non-Functional Requirements

| Requirement ID | Description | Priority |
|---|---|---|
| ORG-NFR-LOGIN-001 | Authentication requests and credentials shall be transmitted using HTTPS. | Critical |
| ORG-NFR-LOGIN-002 | Authentication failures shall not reveal whether a username exists. | High |
| ORG-NFR-LOGIN-003 | Authentication sessions shall be protected against unauthorized reuse. | Critical |
| ORG-NFR-LOGIN-004 | Login controls and validation messages shall support keyboard navigation. | Medium |
| ORG-NFR-LOGIN-005 | Login functionality shall work across agreed supported browsers. | Medium |
| ORG-NFR-LOGIN-006 | Appropriate protection against automated credential guessing shall be implemented according to the approved security policy. | High |

These non-functional requirements are proposed security, accessibility, and compatibility expectations. Quantitative performance targets and lockout thresholds require an approved specification.

## 6. Test Data

| Test Data ID | Username | Password | Purpose |
|---|---|---|---|
| TD-LOGIN-001 | Admin | admin123 | Public demo valid credentials, subject to availability |
| TD-LOGIN-002 | InvalidUser123 | admin123 | Invalid username |
| TD-LOGIN-003 | Admin | WrongPassword123 | Invalid password |
| TD-LOGIN-004 | InvalidUser123 | WrongPassword123 | Both credentials invalid |
| TD-LOGIN-005 | Empty | admin123 | Mandatory username validation |
| TD-LOGIN-006 | Admin | Empty | Mandatory password validation |
| TD-LOGIN-007 | Empty | Empty | Both fields empty |

**Note:** Use the public demo credentials only for authorized testing. Do not commit private production credentials, session cookies, or authentication tokens.

## 7. Assumptions and Limitations

1. The public demo application may be updated without notice.
2. Test data may be shared and reset between users.
3. Some authentication controls may not be configurable in the demo environment.
4. Requirements not confirmed through observation or official documentation are treated as QA-proposed requirements.
5. Destructive or disruptive authentication testing is excluded from the public demo.

## 8. Requirements Traceability

Each requirement will be mapped to at least one test scenario and test case.

| Requirement ID | Planned Test Scenario ID | Planned Test Case ID |
|---|---|---|
| ORG-FR-LOGIN-001 | ORG-TS-LOGIN-001 | ORG-TC-LOGIN-001 |
| ORG-FR-LOGIN-002 | ORG-TS-LOGIN-002 | ORG-TC-LOGIN-002 |
| ORG-FR-LOGIN-003 | ORG-TS-LOGIN-003 | ORG-TC-LOGIN-003 |
| ORG-FR-LOGIN-004 | ORG-TS-LOGIN-004 | ORG-TC-LOGIN-004 |
| ORG-FR-LOGIN-006 | ORG-TS-LOGIN-006 | ORG-TC-LOGIN-006 |
| ORG-FR-LOGIN-009 | ORG-TS-LOGIN-009 | ORG-TC-LOGIN-009 |

**Note:** These are planned mappings. They will be updated after the corresponding test scenarios and cases are created.

## 9. Entry and Exit Criteria

### Entry Criteria

- Login URL is accessible.
- Required test credentials are available.
- Test environment and browser are identified.
- Functional scope is documented.

### Exit Criteria

- All applicable login requirements are evaluated.
- Planned test cases are executed.
- Actual results and evidence are recorded.
- Observed deviations are investigated.
- Confirmed defects are documented using the portfolio bug-report template.

## 10. References

- OrangeHRM demo: https://opensource-demo.orangehrmlive.com/
- OrangeHRM official source repository: https://github.com/orangehrm/orangehrm
- OrangeHRM Help Center: https://help.orangehrm.com/

---

**Document Status:** Draft – Ready for exploratory verification and test scenario preparation.