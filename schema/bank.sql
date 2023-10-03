DROP SCHEMA IF EXISTS bank;

CREATE SCHEMA bank;

use bank;

SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE users (
    id INT NOT NULL AUTO_INCREMENT,
    user_role VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL unique ,
    password VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    status BOOLEAN NOT NULL,
    created_at DATETIME NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

CREATE TABLE accounts (
    id INT NOT NULL AUTO_INCREMENT,
    card_number VARCHAR(255) NOT NULL unique,
    cvv VARCHAR(255) NOT NULL,
    balance DECIMAL(10,2) NOT NULL,
    status BOOLEAN NOT NULL,
    created_at DATETIME NOT NULL,
    user_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

CREATE TABLE transactions (
    id INT NOT NULL AUTO_INCREMENT,
    transaction_type VARCHAR(255) NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    note TEXT NOT NULL,
    created_at DATETIME NOT NULL,
    account_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (account_id) REFERENCES accounts (id)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;