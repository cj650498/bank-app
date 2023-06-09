-- User table
CREATE TABLE User (
  user_id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255),
  email VARCHAR(255),
  password VARCHAR(255),
  address VARCHAR(255)
);

-- Admin table
CREATE TABLE Admin (
  admin_id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255),
  email VARCHAR(255),
  password VARCHAR(255),
  address VARCHAR(255)
);

-- Account table
CREATE TABLE Account (
  account_id INT AUTO_INCREMENT PRIMARY KEY,
  account_number VARCHAR(255),
  balance DECIMAL(10, 2),
  user_id INT,
  FOREIGN KEY (user_id) REFERENCES User(user_id)
);

-- Transaction table
CREATE TABLE Transaction (
  transaction_id INT AUTO_INCREMENT PRIMARY KEY,
  amount DECIMAL(10, 2),
  date DATE,
  type VARCHAR(255),
  account_id INT,
  FOREIGN KEY (account_id) REFERENCES Account(account_id)
);

