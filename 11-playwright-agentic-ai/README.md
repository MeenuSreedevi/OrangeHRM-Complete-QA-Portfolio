# OrangeHRM Login Automation — Playwright + TypeScript

This is an **executable, deterministic Playwright starter**, not an AI-driven test agent. AI coding assistants can help discover locators and review failures, but must not invent passing results or bypass review. Tested cases map to `../03-test-cases/OrangeHRM-Login-Test-Cases.md`.

## Prerequisites
Node.js 20+ and npm; VS Code recommended. From this directory:

```powershell
npm install
npx playwright install chromium
```

## Credentials (current PowerShell terminal only)

```powershell
$env:ORANGEHRM_USERNAME="Admin"
$env:ORANGEHRM_PASSWORD="admin123"
```

Check the currently published demo credentials before running. Never commit private secrets. If no credentials are supplied, login/logout tests are **skipped**; remaining tests may still run.

## Run

```powershell
npm test
npm run test:smoke
npm run test:headed
npm run test:ui
npm run test:report
npm run typecheck
```

Reports are in `playwright-report/`; failure screenshots/traces/videos in `test-results/`. These are gitignored. The CI workflow uploads them as artifacts.

## Design
- `src/pages/` — Page Object Model
- `src/fixtures/pages.ts` — typed page fixtures
- `tests/login.spec.ts` — 12 automated test cases (14 manual IDs covered)
- `playwright.config.ts` — Chromium, HTML/JUnit reporters, failure capture, single worker

### Traceability
| Automation test | Manual IDs |
|---|---|
| Login controls | 001 |
| Valid login | 002, 011 |
| Invalid username | 003 |
| Invalid password | 004, 010 |
| Both credentials invalid | 005 |
| Empty username | 006 |
| Empty password | 007 |
| Both empty | 008 |
| Masked password | 009 |
| Forgot Password link | 012 |
| Forgot Password navigation | 013 |
| Logout | 015 |

Full manual IDs have prefix `ORG-TC-LOGIN-`.

## AI-assisted workflow (reviewed by a human)
1. Observe failing step in a Playwright trace and the expected requirement.
2. Ask an AI coding assistant to propose a specific locator or test adjustment.
3. Verify the locator against the live demo and source; avoid `waitForTimeout` workarounds.
4. Re-run the affected test locally; document observed results only.
5. Review and commit changes with test case ID in the commit or PR.

Example prompt: "Review `LoginPage.ts` against the OrangeHRM login UI; propose robust accessible selectors. Explain why each selector is stable. Do not change requirements or claim tests passed without execution."

## Public demo limitations
The public demo is shared and can change at any time. Keep runs small; do not run brute-force or stress tests. UI selectors and navigation paths must be verified locally. The project has been assembled but no browser run has been performed in this environment.

## GitHub Actions
Copy `.github/workflows/playwright-login.yml` to the **portfolio repository root** at `.github/workflows/playwright-login.yml` (not inside `11-playwright-agentic-ai`). The workflow is manual-only and uses optional repository secrets `ORANGEHRM_USERNAME`, `ORANGEHRM_PASSWORD`.
