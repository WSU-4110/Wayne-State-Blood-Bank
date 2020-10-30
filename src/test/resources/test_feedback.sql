INSERT INTO accounts(email, password) VALUES('user@wayne.edu', 'Password1234');

INSERT INTO user_details
VALUES(SELECT id FROM accounts WHERE email = 'user@wayne.edu', 'John', 'Doe', '1900-01-01', 'Y', '1111111111', 'A+');

INSERT INTO feedback(message, account_id) VALUES('Hello World', SELECT id FROM accounts WHERE email = 'user@wayne.edu');