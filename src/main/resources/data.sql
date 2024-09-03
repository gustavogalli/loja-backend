INSERT INTO users (user_id, password, username)
SELECT 1, '$2a$10$NWM0kcUU1csGRPXmz/LpM.z6XCRL2fANh1OQhonCsZC2fskKk5T/q', 'admin'
WHERE NOT EXISTS (SELECT 1 FROM users WHERE user_id = 1);

INSERT INTO roles (role_id, rolename)
SELECT 1, 'ROLE_ADMIN'
WHERE NOT EXISTS (SELECT 1 FROM roles WHERE role_id = 1);

INSERT INTO roles (role_id, rolename)
SELECT 2, 'ROLE_USER'
WHERE NOT EXISTS (SELECT 1 FROM roles WHERE role_id = 2);

INSERT INTO users_roles (user_id, role_id)
SELECT 1, 1
WHERE NOT EXISTS (SELECT 1 FROM users_roles WHERE user_id = 1 AND role_id = 1);
