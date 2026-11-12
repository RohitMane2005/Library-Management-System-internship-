package org.example.service;

import org.example.model.Book;
import org.example.model.IssueRecord;
import org.example.model.Student;

import java.util.ArrayList;
import java.util.List;

public class IssueService {

    private List<IssueRecord> issueRecords = new ArrayList<>();
    private int issueIdCounter = 1;

    private BookService bookService;
    private StudentService studentService;

    public IssueService(BookService bookService, StudentService studentService) {
        this.bookService = bookService;
        this.studentService = studentService;
    }

    public void issueBook(int bookId, int studentId) {
        // Validate book exists
        Book book = bookService.searchBookById(bookId);
        if (book == null) {
            System.out.println("Error: Book with ID " + bookId + " not found.");
            return;
        }

        // Validate student exists
        Student student = studentService.searchStudentById(studentId);
        if (student == null) {
            System.out.println("Error: Student with ID " + studentId + " not found.");
            return;
        }

        // Check book availability
        if (book.getAvailableQuantity() <= 0) {
            System.out.println("Error: Book \"" + book.getTitle() + "\" is not available. All copies are issued.");
            return;
        }

        // Check if student already has same book issued
        for (IssueRecord record : issueRecords) {
            if (record.getBookId() == bookId && record.getStudentId() == studentId
                    && record.getStatus().equals("ISSUED")) {
                System.out.println("Error: Student already has this book issued.");
                return;
            }
        }

        // Create issue record
        IssueRecord record = new IssueRecord(issueIdCounter++, bookId, studentId);
        issueRecords.add(record);

        // Decrement available quantity
        book.setAvailableQuantity(book.getAvailableQuantity() - 1);

        System.out.println("Success: Book \"" + book.getTitle() + "\" issued to student \"" + student.getName() + "\".");
    }

    public void returnBook(int issueId) {
        // Find the issue record
        IssueRecord record = null;
        for (IssueRecord r : issueRecords) {
            if (r.getIssueId() == issueId) {
                record = r;
                break;
            }
        }

        if (record == null) {
            System.out.println("Error: Issue record with ID " + issueId + " not found.");
            return;
        }

        // Validate status is ISSUED
        if (!record.getStatus().equals("ISSUED")) {
            System.out.println("Error: Book has already been returned for issue ID " + issueId + ".");
            return;
        }

        // Return the book
        record.returnBook();

        // Increment available quantity
        Book book = bookService.searchBookById(record.getBookId());
        if (book != null) {
            book.setAvailableQuantity(book.getAvailableQuantity() + 1);
        }

        System.out.println("Success: Book returned successfully for issue ID " + issueId + ".");
    }

    public void viewIssuedBooks() {
        boolean found = false;
        for (IssueRecord record : issueRecords) {
            if (record.getStatus().equals("ISSUED")) {
                System.out.println(record);
                System.out.println("-----------------------------");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books are currently issued.");
        }
    }

    public void viewAllIssueRecords() {
        if (issueRecords.isEmpty()) {
            System.out.println("No issue records found.");
            return;
        }
        for (IssueRecord record : issueRecords) {
            System.out.println(record);
            System.out.println("-----------------------------");
        }
    }

    public boolean hasActiveIssues(int studentId) {
        for (IssueRecord record : issueRecords) {
            if (record.getStudentId() == studentId && record.getStatus().equals("ISSUED")) {
                return true;
            }
        }
        return false;
    }

    public List<IssueRecord> getIssueRecordsByStudentId(int studentId) {
        List<IssueRecord> result = new ArrayList<>();
        for (IssueRecord record : issueRecords) {
            if (record.getStudentId() == studentId) {
                result.add(record);
            }
        }
        return result;
    }
}
