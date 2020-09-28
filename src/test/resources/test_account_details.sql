INSERT INTO accounts(email, password) VALUES('user@example.com', 'password');
INSERT INTO account_roles VALUES(1, SELECT id FROM accounts WHERE email = 'user@example.com');
INSERT INTO account_roles VALUES(2, SELECT id FROM accounts WHERE email = 'user@example.com');