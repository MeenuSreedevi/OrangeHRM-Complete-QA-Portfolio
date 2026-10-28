# OrangeHRM Login — Selenium Java + TestNG

> **Portfolio status:** Runnable scaffold. It has **not** been executed against live OrangeHRM from this delivery environment. UI locators and assertions should be re-checked when the demo updates. Do not present unexecuted tests as passed.

## Stack
- Java 17+, Maven 3.9+, Google Chrome
- Selenium Java 4.51.0, TestNG 7.12.0, Maven Surefire
- Page Object Model (LoginPage / DashboardPage)
- Selenium Manager handles ChromeDriver installation (internet may be required on first run)

## Quick start (Windows PowerShell)

```powershell
cd "D:\Meenu\QA portfolio\OrangeHRM-Complete-QA-Portfolio\10-selenium-java"
java -version
mvn -version
mvn test
```

Set demo credentials for authenticated cases in the **current terminal session** only (values must come from the currently displayed official demo login page):

```powershell
$env:ORANGEHRM_USERNAME="YOUR_CURRENT_DEMO_USERNAME"
$env:ORANGEHRM_PASSWORD="YOUR_CURRENT_DEMO_PASSWORD"
mvn test
```

Headless: `mvn test -Pheadless`.

Missing credentials cause authenticated tests to be **SKIPPED**, not falsely passed. Public demo access and UI can change.

## Test mappings

| Java test | Existing manual case |
|---|---|
| loginControlsVisible | ORG-TC-LOGIN-001 |
| validLoginNavigatesToDashboard | ORG-TC-LOGIN-002, 011 |
| invalidUsernameRejected | ORG-TC-LOGIN-003 |
| invalidPasswordRejected | ORG-TC-LOGIN-004, 010 |
| invalidUsernameAndPasswordRejected | ORG-TC-LOGIN-005 |
| emptyUsernameIsRequired | ORG-TC-LOGIN-006 |
| emptyPasswordIsRequired | ORG-TC-LOGIN-007 |
| bothFieldsRequired | ORG-TC-LOGIN-008 |
| passwordIsMasked | ORG-TC-LOGIN-009 |
| forgotPasswordLinkVisible | ORG-TC-LOGIN-012 |
| forgotPasswordNavigates | ORG-TC-LOGIN-013 |
| logoutReturnsToLogin | ORG-TC-LOGIN-015 |

Coverage: 12 automated methods covering 14 existing case IDs, not all 32. Remaining security, exploratory, cross-browser and boundary tests are manual or future automation.

## Report locations
- `target/surefire-reports/` — TestNG/Surefire XML and HTML (depending on provider)
- `target/failure-screenshots/` — PNG screenshots captured on failed tests (where browser session still exists)
- `target/` is excluded from git; use sanitized evidence in `12-test-reports/` when publishing summaries.

## CI / GitHub Actions
The workflow file in this package belongs at repository root: `.github/workflows/selenium-login.yml`, **not** inside `10-selenium-java/.github`. It is manually triggered (`workflow_dispatch`) to avoid uncontrolled tests against the public demo. Repository secrets `ORANGEHRM_USERNAME` and `ORANGEHRM_PASSWORD` are optional; without them credential-dependent tests skip. Workflows need repository Actions permissions.

## Project structure

```
10-selenium-java/
  pom.xml
  testng.xml
  .gitignore
  .env.example
  README.md
  src/test/java/com/orangehrm/pages/LoginPage.java
  src/test/java/com/orangehrm/pages/DashboardPage.java
  src/test/java/com/orangehrm/tests/BaseTest.java
  src/test/java/com/orangehrm/tests/LoginTests.java
```

## Troubleshooting
- `mvn` missing: install Maven, add its `bin` to `PATH`, restart VS Code.
- Java wrong version: install JDK 17+, update `JAVA_HOME`.
- ChromeDriver error: ensure Chrome is installed and Selenium Manager can download/cache compatible driver.
- `NoSuchElementException` / timeout: use Chrome DevTools to verify locators for the current demo.
- Authentication tests skipped: set both environment variables.
- Network/CAPTCHA/403: do not bypass protections; record case as Blocked and test only in an authorized stable environment.
- Avoid frequent repeated failed-logins on the shared demo.

## Reporting bugs
Only report confirmed deviations from verified requirements with **Bug Description, Steps to Reproduce, Actual Result, Expected Result, Severity**, plus priority, linked `ORG-TC-LOGIN-###`, environment and evidence. See `06-bug-reports/`.

