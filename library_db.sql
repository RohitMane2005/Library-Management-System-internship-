-- ============================================
-- Library Management System - Database Setup
-- ============================================

CREATE DATABASE IF NOT EXISTS library_db;
USE library_db;

-- -------------------------------------------
-- Table: books
-- -------------------------------------------
CREATE TABLE books (
    book_id INT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    category VARCHAR(100) NOT NULL,
    total_quantity INT NOT NULL DEFAULT 0,
    available_quantity INT NOT NULL DEFAULT 0
);

-- -------------------------------------------
-- Table: students
-- -------------------------------------------
CREATE TABLE students (
    student_id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(15) NOT NULL
);

-- -------------------------------------------
-- Table: issue_records
-- -------------------------------------------
CREATE TABLE issue_records (
    issue_id INT PRIMARY KEY AUTO_INCREMENT,
    book_id INT NOT NULL,
    student_id INT NOT NULL,
    issue_date DATE NOT NULL,
    return_date DATE DEFAULT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'ISSUED',
    FOREIGN KEY (book_id) REFERENCES books(book_id),
    FOREIGN KEY (student_id) REFERENCES students(student_id)
);

-- -------------------------------------------
-- Sample Data: Books
-- -------------------------------------------
INSERT INTO books (book_id, title, author, category, total_quantity, available_quantity) VALUES
(101, 'Data Structures and Algorithms', 'Thomas H. Cormen', 'Computer Science', 5, 5),
(102, 'Clean Code', 'Robert C. Martin', 'Software Engineering', 3, 3),
(103, 'The Great Gatsby', 'F. Scott Fitzgerald', 'Fiction', 4, 4);

-- -------------------------------------------
-- Sample Data: Students
-- -------------------------------------------
INSERT INTO students (student_id, name, email, phone) VALUES
(1001, 'Alice Johnson', 'alice.johnson@university.edu', '9876543210'),
(1002, 'Bob Smith', 'bob.smith@university.edu', '9876543211'),
(1003, 'Charlie Brown', 'charlie.brown@university.edu', '9876543212');
