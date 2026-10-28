# OrangeHRM QA Test Reports

**Status: Baseline only — no actual OrangeHRM execution outcomes recorded.**

## Files
- `OrangeHRM-QA-Execution-Dashboard.xlsx`: 77 planned execution records, dropdown statuses, dashboard formulas, defect register, run register and separate synthetic fixture sheet.
- `OrangeHRM-QA-Test-Execution-Summary.md`: GitHub-readable summary.
- `OrangeHRM-Defect-Summary.md`: defect summary and required reporting fields.
- `EVIDENCE-GUIDE.md`: evidence naming and sanitization.

## Workflow
1. Record run metadata (Run ID, date, build, tester and environment) in **Run Register**.
2. Execute cases by following `03-test-cases`, `10-selenium-java`, `11-playwright-agentic-ai`, `08-postman-api-tests`, and `09-sql-validation`.
3. Update **Execution Log** with observed **Actual Result**, **Status**, **Evidence**, **Defect ID** and **Run ID**. Never mark a test Pass without execution.
4. If a confirmed defect exists, complete **Defect Register** with description, steps, actual/expected results, severity, priority, evidence and linked case ID.
5. Review formula-driven Dashboard counts; copy verified numbers into the Markdown summary with date and run ID.
6. Preserve execution history by copying the workbook for each new run.

**Scope:** Manual 32; Selenium 12; Playwright 12; Postman 7; authorized/live SQL 14 = 77 execution entries. SQL fixture examples are tracked separately, never counted as verified OrangeHRM results.

**Pass rate** = Pass / (Pass + Fail), if denominator > 0; otherwise N/A. **Coverage** = (Pass + Fail + Blocked + N/A) / Planned, with blocked items reported separately.

Never commit credentials, tokens, session cookies or unredacted personal data.
