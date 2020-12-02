INSERT INTO accounts(email, password) VALUES('user1@wayne.edu', 'Password1234');
INSERT INTO user_details
VALUES(SELECT id FROM accounts WHERE email = 'user1@wayne.edu', 'John', 'Doe', '1900-01-01', 'Y', '1111111111', 'A+');


INSERT INTO accounts(email, password) VALUES('user2@wayne.edu', 'Password1234');
INSERT INTO user_details
VALUES((SELECT id FROM accounts WHERE email = 'user2@wayne.edu'), 'Johny', 'Doeui', '1901-01-01', 'Y', '1111111110', 'A+');