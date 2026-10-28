-- ORANGEHRM PORTFOLIO PRACTICE DATABASE (SQLite)
-- Synthetic teaching schema; NOT the OrangeHRM production schema.
-- Safe to run only in a disposable local SQLite database.
PRAGMA foreign_keys = ON;
DROP TABLE IF EXISTS password_reset_requests;
DROP TABLE IF EXISTS login_attempts;
DROP TABLE IF EXISTS user_sessions;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS roles;

CREATE TABLE roles (role_id INTEGER PRIMARY KEY, role_name TEXT NOT NULL UNIQUE);
CREATE TABLE employees (employee_id INTEGER PRIMARY KEY, full_name TEXT NOT NULL, employment_status TEXT NOT NULL CHECK(employment_status IN ('ACTIVE','INACTIVE')));
CREATE TABLE users (
 user_id INTEGER PRIMARY KEY,
 username TEXT NOT NULL UNIQUE,
 employee_id INTEGER REFERENCES employees(employee_id),
 role_id INTEGER NOT NULL REFERENCES roles(role_id),
 account_status TEXT NOT NULL CHECK(account_status IN ('ACTIVE','DISABLED')),
 deleted INTEGER NOT NULL DEFAULT 0 CHECK(deleted IN (0,1)),
 credential_marker TEXT NOT NULL -- synthetic placeholder, not a password/hash
);
CREATE TABLE user_sessions (
 session_id TEXT PRIMARY KEY,
 user_id INTEGER NOT NULL REFERENCES users(user_id),
 created_at TEXT NOT NULL,
 revoked_at TEXT
);
CREATE TABLE login_attempts (
 attempt_id INTEGER PRIMARY KEY,
 attempted_username TEXT NOT NULL,
 success INTEGER NOT NULL CHECK(success IN (0,1)),
 attempted_at TEXT NOT NULL
);
CREATE TABLE password_reset_requests (
 reset_id INTEGER PRIMARY KEY,
 user_id INTEGER NOT NULL REFERENCES users(user_id),
 state TEXT NOT NULL CHECK(state IN ('REQUESTED','USED','EXPIRED')),
 created_at TEXT NOT NULL
);

INSERT INTO roles VALUES (1,'Admin'),(2,'ESS');
INSERT INTO employees VALUES (101,'Alex Morgan','ACTIVE'),(102,'Taylor Lee','ACTIVE'),(103,'Jordan Casey','INACTIVE');
INSERT INTO users VALUES
 (1,'Admin',101,1,'ACTIVE',0,'fixture-A'),
 (2,'ess_user',102,2,'ACTIVE',0,'fixture-B'),
 (3,'disabled_user',103,2,'DISABLED',0,'fixture-C'),
 (4,'deleted_user',NULL,2,'ACTIVE',1,'fixture-D');
INSERT INTO user_sessions VALUES
 ('session-A',1,'2026-10-28T08:00:00Z',NULL),
 ('session-B',2,'2026-10-28T08:10:00Z','2026-10-28T08:20:00Z');
INSERT INTO login_attempts VALUES
 (1,'Admin',1,'2026-10-28T08:00:00Z'),
 (2,'InvalidUser123',0,'2026-10-28T08:01:00Z'),
 (3,'Admin',0,'2026-10-28T08:02:00Z');
INSERT INTO password_reset_requests VALUES (1,2,'REQUESTED','2026-10-28T08:15:00Z');
