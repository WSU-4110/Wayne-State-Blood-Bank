CREATE TABLE messages (
    id INT NOT NULL AUTO_INCREMENT,
    message VARCHAR(50) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE accounts(
    id INT NOT NULL AUTO_INCREMENT,
    email VARCHAR(320) NOT NULL,
    password CHAR(60) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (email)
);

CREATE TABLE roles(
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


CREATE VIEW account_details AS SELECT email, password, GROUP_CONCAT(role_name) AS roles FROM accounts AS A INNER JOIN account_roles AS AR ON AR.account_id = A.id
INNER JOIN roles AS R ON R.id= AR.role_id GROUP BY A.email, A.password;

INSERT INTO roles(role_name) VALUES('USER'),('ADMIN'), ('UNVERIFIED');