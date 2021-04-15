DROP TABLE IF EXISTS transactions;
DROP TABLE IF EXISTS accounts;

CREATE TABLE accounts (
  acc_no INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  balance DECIMAL(10,2) NOT NULL DEFAULT 200.00,
  emailaddress VARCHAR(250) NOT NULL
);

CREATE TABLE transactions (
  trans_id INT AUTO_INCREMENT  PRIMARY KEY,
  from_account INT(10) NOT NULL,
  to_account INT(10) NOT NULL,
  amount DECIMAL(10,2) NOT NULL,
  remarks VARCHAR(250) NULL,
  status VARCHAR(10) NOT NULL,
  transaction_time TIMESTAMP NOT NULL
);