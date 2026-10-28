"""Execute standalone validation checks against the synthetic SQLite fixture."""
import sqlite3
from pathlib import Path
ROOT = Path(__file__).resolve().parent
con = sqlite3.connect(':memory:')
con.executescript((ROOT / '01_schema_and_sample_data.sql').read_text(encoding='utf-8'))
checks = [
 ('SQL-TC-001', "SELECT COUNT(*) FROM users WHERE username='Admin' AND account_status='ACTIVE' AND deleted=0", 1),
 ('SQL-TC-002', "SELECT COUNT(*) FROM users WHERE username='InvalidUser123'", 0),
 ('SQL-TC-003', "SELECT COUNT(*) FROM users WHERE account_status='DISABLED' AND deleted=0", 1),
 ('SQL-TC-004', "SELECT COUNT(*) FROM users WHERE deleted=1", 1),
 ('SQL-TC-005', "SELECT COUNT(*) FROM (SELECT username FROM users GROUP BY username HAVING COUNT(*)>1)", 0),
 ('SQL-TC-006', "SELECT COUNT(*) FROM users WHERE username IS NULL OR TRIM(username)=''", 0),
 ('SQL-TC-007', "SELECT role_name FROM roles WHERE role_id=(SELECT role_id FROM users WHERE username='Admin')", 'Admin'),
 ('SQL-TC-008', "SELECT full_name FROM employees WHERE employee_id=(SELECT employee_id FROM users WHERE username='ess_user')", 'Taylor Lee'),
 ('SQL-TC-009', "SELECT COUNT(*) FROM login_attempts WHERE attempted_username='Admin' AND success=0", 1),
 ('SQL-TC-010', "SELECT COUNT(*) FROM user_sessions WHERE user_id=1 AND revoked_at IS NULL", 1),
 ('SQL-TC-011', "SELECT COUNT(*) FROM user_sessions WHERE user_id=2 AND revoked_at IS NOT NULL", 1),
 ('SQL-TC-012', "SELECT state FROM password_reset_requests WHERE user_id=2", 'REQUESTED'),
 ('SQL-TC-013', "SELECT COUNT(*) FROM users u LEFT JOIN roles r ON r.role_id=u.role_id WHERE r.role_id IS NULL", 0),
 ('SQL-TC-014', "SELECT COUNT(*) FROM users WHERE role_id=2", 3),
]
for case_id, sql, expected in checks:
    actual = con.execute(sql).fetchone()[0]
    assert actual == expected, f'{case_id}: expected={expected!r}, actual={actual!r}'
    print(f'{case_id}: PASS (expected={expected!r}, actual={actual!r})')
print(f'Fixture validation: {len(checks)}/{len(checks)} PASS. This does not represent OrangeHRM application testing.')
