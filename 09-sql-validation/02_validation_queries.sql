-- SQLite examples against 01_schema_and_sample_data.sql ONLY.
-- These are testable practice queries, not OrangeHRM production SQL.
-- Q01: Valid active, non-deleted account. Expected: 1 | Admin | ACTIVE | 0
SELECT user_id, username, account_status, deleted FROM users WHERE username='Admin' AND account_status='ACTIVE' AND deleted=0;
-- Q02: Invalid username lookup. Expected: zero rows
SELECT user_id FROM users WHERE username='InvalidUser123';
-- Q03: Disabled accounts. Expected: 3 | disabled_user
SELECT user_id, username FROM users WHERE account_status='DISABLED' AND deleted=0;
-- Q04: Soft-deleted accounts. Expected: 4 | deleted_user
SELECT user_id, username FROM users WHERE deleted=1;
-- Q05: Duplicate usernames (data-quality). Expected: zero rows
SELECT username, COUNT(*) AS duplicates FROM users GROUP BY username HAVING COUNT(*)>1;
-- Q06: Missing required username. Expected: zero rows
SELECT user_id FROM users WHERE username IS NULL OR TRIM(username)='';
-- Q07: User-to-role mapping. Expected: Admin | Admin
SELECT u.username,r.role_name FROM users u JOIN roles r ON r.role_id=u.role_id WHERE u.username='Admin';
-- Q08: User-to-employee mapping. Expected: ess_user | Taylor Lee
SELECT u.username,e.full_name FROM users u JOIN employees e ON e.employee_id=u.employee_id WHERE u.username='ess_user';
-- Q09: Failed attempts for Admin. Expected: 1
SELECT COUNT(*) AS failed_attempts FROM login_attempts WHERE attempted_username='Admin' AND success=0;
-- Q10: Active session for Admin. Expected: session-A
SELECT s.session_id FROM user_sessions s JOIN users u ON u.user_id=s.user_id WHERE u.username='Admin' AND s.revoked_at IS NULL;
-- Q11: Revoked sessions for ESS. Expected: session-B
SELECT s.session_id FROM user_sessions s JOIN users u ON u.user_id=s.user_id WHERE u.username='ess_user' AND s.revoked_at IS NOT NULL;
-- Q12: Reset requests for ESS. Expected: 1 | REQUESTED
SELECT r.reset_id,r.state FROM password_reset_requests r JOIN users u ON u.user_id=r.user_id WHERE u.username='ess_user';
-- Q13: Orphaned user mappings. Expected: zero rows
SELECT u.user_id FROM users u LEFT JOIN roles r ON r.role_id=u.role_id WHERE r.role_id IS NULL;
-- Q14: Role distribution (including soft-deleted accounts). Expected: Admin=1, ESS=3
SELECT r.role_name,COUNT(*) AS user_count FROM roles r LEFT JOIN users u ON u.role_id=r.role_id GROUP BY r.role_id,r.role_name ORDER BY r.role_name;
