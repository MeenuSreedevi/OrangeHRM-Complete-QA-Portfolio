# 09 – OrangeHRM SQL Validation

Practice SQL validation for the OrangeHRM QA Portfolio, based on a **synthetic SQLite teaching database**, not the OrangeHRM public demo database.

## Files

- `OrangeHRM-SQL-Validation-Test-Plan.md` – scenarios, expected results, traceability, and execution process
- `01_schema_and_sample_data.sql` – recreate a disposable database with fake accounts and sessions
- `02_validation_queries.sql` – 14 documented read-only checks
- `03_execute_checks.py` – verify 14 expected outcomes using Python's built-in SQLite library

## Execute (Windows VS Code PowerShell)

From your repository root:

```powershell
py 09-sql-validation/03_execute_checks.py
```

Or run `python 09-sql-validation/03_execute_checks.py` if `py` is unavailable.

**Expected:** 14/14 `PASS` for the synthetic fixture only.

## Data safety

Never execute fixture `DROP TABLE` commands against an OrangeHRM installation or production database. This package is designed solely for a new local SQLite database. All user names and markers are fictitious; no real password hashes, session tokens, or private HR data are included.
