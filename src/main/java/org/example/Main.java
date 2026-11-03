package org.example;

import org.example.model.Book;
import org.example.model.Student;
import org.example.model.IssueRecord;
public class Main {

    public static void main(String[] args) {

        Book book = new Book(
                101,
                "Java Programming",
                "James Gosling",
                "Programming",
                5
        );

        Student student = new Student(
                1,
                "Rohit",
                "rohit@gmail.com",
                "9876543210"
        );

        IssueRecord record = new IssueRecord(
                1,
                book.getBookId(),
                student.getStudentId()
        );

        System.out.println("Book: " + book.getTitle());
        System.out.println("Student: " + student.getName());

        System.out.println("Issue Status: " + record.getStatus());

        record.returnBook();

        System.out.println("Updated Status: " + record.getStatus());
        System.out.println("Return Date: " + record.getReturnDate());
    }
}