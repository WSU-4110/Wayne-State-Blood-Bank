CREATE TABLE accounts(
    id INT NOT NULL AUTO_INCREMENT,
    email VARCHAR(320) NOT NULL,
    password CHAR(60) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (email)
);

CREATE TABLE roles (
    id INT NOT NULL AUTO_INCREMENT,
    role_name VARCHAR(10) NOT NULL,
    PRIMARY KEY(id),
    UNIQUE(role_name)
);

CREATE TABLE account_roles(
    role_id INT NOT NULL,
    account_id INT NOT NULL,
    PRIMARY KEY(role_id, account_id)
);

ALTER TABLE account_roles ADD CONSTRAINT fk_account_roles_account_id
    FOREIGN KEY (account_id) REFERENCES accounts(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

ALTER TABLE account_roles ADD CONSTRAINT fk_account_roles_role_id
    FOREIGN KEY(role_id) REFERENCES roles(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE;


CREATE VIEW account_details AS SELECT A.id, email, password, GROUP_CONCAT(role_name) AS roles FROM accounts AS A LEFT JOIN account_roles AS AR ON AR.account_id = A.id
                               LEFT JOIN roles AS R ON R.id= AR.role_id GROUP BY A.email, A.password, A.id;

INSERT INTO roles(role_name) VALUES('USER'),('ADMIN'), ('UNVERIFIED');

CREATE TABLE verification_tokens(
    account_id INT NOT NULL,
    token CHAR(36) NOT NULL,
    PRIMARY KEY (token),
    UNIQUE(account_id)
);

ALTER TABLE verification_tokens ADD CONSTRAINT fk_verification_account_id
    FOREIGN KEY (account_id) REFERENCES accounts(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE;

CREATE TABLE user_details
(
    id INT NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    birth_day DATE NOT NULL,
    blood_donor_status CHAR(1),
    phone_number VARCHAR(11),
    blood_type CHAR(3),
    PRIMARY KEY (id)
);

ALTER TABLE user_details ADD CONSTRAINT fk_user_details_accounts
   FOREIGN KEY (id) REFERENCES accounts(id)
   ON DELETE CASCADE
   ON UPDATE CASCADE;

CREATE TABLE feedback(
    id INT NOT NULL AUTO_INCREMENT,
    message TEXT NOT NULL,
    account_id INT NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE feedback ADD CONSTRAINT fk_feedback_account_id
    FOREIGN KEY (account_id) REFERENCES accounts(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

CREATE TABLE blood_drives(
    id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(350) NOT NULL,
    location VARCHAR(120) NOT NULL,
    blood_drive_time TIME NOT NULL,
    blood_drive_date DATE NOT NULL,
    description TEXT NOT NULL,
    link VARCHAR(2048),
    PRIMARY KEY (id)
);

CREATE TABLE events(
    id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(350) NOT NULL,
    description TEXT NOT NULL,
    event_date DATE NOT NULL,
    PRIMARY KEY (id)
);

CREATE VIEW vw_feedback AS SELECT F.id, F.message, A.email, CONCAT(U.first_name, ' ',  U.last_name) AS name FROM feedback AS F
INNER JOIN accounts AS A ON F.account_id = A.id INNER JOIN user_details AS U ON F.account_id = U.id;

CREATE VIEW vw_user_profile AS SELECT U.first_name, U.last_name, U.phone_number
, U.blood_donor_status, U.blood_type, U.birth_day, A.email as email
    FROM user_details AS U INNER JOIN accounts AS A ON A.id = U.id;

CREATE VIEW vw_donor_list AS SELECT U.id, A.email, U.birth_day, U.blood_type, U.phone_number as phone_number
                         FROM user_details U, accounts A
                         WHERE U.id = A.id AND U.blood_donor_status ='Y';
