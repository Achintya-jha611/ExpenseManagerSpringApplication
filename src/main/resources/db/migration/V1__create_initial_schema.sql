CREATE TABLE users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(255),
                       email VARCHAR(255) NOT NULL UNIQUE,
                       phone_number VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       created_at DATETIME,
                       updated_at DATETIME,
                       date_of_birth DATE
);

CREATE TABLE expense (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         amount FLOAT NOT NULL,
                         category VARCHAR(255),
                         description VARCHAR(255),
                         date DATE,
                         user_id INT,
                         CONSTRAINT fk_expense_user
                             FOREIGN KEY (user_id)
                                 REFERENCES users(id)
);
CREATE TABLE audit_log (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           created_at DATETIME,
                           action VARCHAR(255)
);