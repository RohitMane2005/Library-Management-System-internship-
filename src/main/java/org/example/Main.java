package org.example;

import org.example.model.Book;
import org.example.model.Student;
import org.example.service.BookService;
import org.example.service.IssueService;
import org.example.service.StudentService;

public class Main {

    public static void main(String[] args) {

        // Initialize services
        BookService bookService = new BookService();
        StudentService studentService = new StudentService();
        IssueService issueService = new IssueService(bookService, studentService);

        // Wire issue service into student service (for active-issue check on delete)
        studentService.setIssueService(issueService);

        // =================================
        // 1. ADD BOOKS
        // =================================
        System.out.println("\n========== ADD BOOKS ==========");

        Book b1 = new Book(1, "Java: The Complete Reference", "Herbert Schildt", "Programming", 5);
        Book b2 = new Book(2, "Clean Code", "Robert C. Martin", "Software Engineering", 3);
        Book b3 = new Book(3, "Data Structures and Algorithms", "Narasimha Karumanchi", "Computer Science", 2);

        bookService.addBook(b1);
        bookService.addBook(b2);
        bookService.addBook(b3);

        // =================================
        // 2. DUPLICATE BOOK TEST
        // =================================
        System.out.println("\n========== DUPLICATE BOOK TEST ==========");

        Book duplicateBook = new Book(1, "Duplicate Book", "Author", "Category", 1);
        bookService.addBook(duplicateBook);

        // =================================
        // 3. SEARCH BOOK
        // =================================
        System.out.println("\n========== SEARCH BOOK ==========");

        Book foundBook = bookService.searchBookById(2);
        if (foundBook != null) {
            System.out.println("Book Found!");
            System.out.println(foundBook);
        } else {
            System.out.println("Book not found!");
        }

        // =================================
        // 4. VIEW ALL BOOKS
        // =================================
        System.out.println("\n========== ALL BOOKS ==========");

        bookService.viewAllBooks();

        // =================================
        // 5. UPDATE BOOK
        // =================================
        System.out.println("\n========== UPDATE BOOK ==========");

        bookService.updateBook(2, "Clean Code (2nd Edition)", "Robert C. Martin", "Software Engineering", 5);

        // =================================
        // 6. DELETE BOOK
        // =================================
        System.out.println("\n========== DELETE BOOK ==========");

        bookService.deleteBook(3);

        // =================================
        // 7. ADD STUDENTS
        // =================================
        System.out.println("\n========== ADD STUDENTS ==========");

        Student s1 = new Student(101, "Rohit Mane", "rohit@gmail.com", "9876543210");
        Student s2 = new Student(102, "Amit Patil", "amit@gmail.com", "9876543211");
        Student s3 = new Student(103, "Sneha Joshi", "sneha@gmail.com", "9876543212");

        studentService.addStudent(s1);
        studentService.addStudent(s2);
        studentService.addStudent(s3);

        // =================================
        // 8. DUPLICATE STUDENT TEST
        // =================================
        System.out.println("\n========== DUPLICATE STUDENT TEST ==========");

        Student duplicateStudent = new Student(101, "Another Student", "another@gmail.com", "9876543213");
        studentService.addStudent(duplicateStudent);

        // =================================
        // 9. SEARCH STUDENT
        // =================================
        System.out.println("\n========== SEARCH STUDENT ==========");

        Student foundStudent = studentService.searchStudentById(102);
        if (foundStudent != null) {
            System.out.println("Student Found!");
            System.out.println(foundStudent);
        } else {
            System.out.println("Student not found!");
        }

        // =================================
        // 10. VIEW ALL STUDENTS
        // =================================
        System.out.println("\n========== ALL STUDENTS ==========");

        studentService.viewAllStudents();

        // =================================
        // 11. UPDATE STUDENT
        // =================================
        System.out.println("\n========== UPDATE STUDENT ==========");

        studentService.updateStudent(102, "Amit Kumar", "amitkumar@gmail.com", "9876543214");

        // =================================
        // 12. DELETE STUDENT
        // =================================
        System.out.println("\n========== DELETE STUDENT ==========");

        studentService.deleteStudent(103);

        // =================================
        // 13. ISSUE BOOKS
        // =================================
        System.out.println("\n========== ISSUE BOOKS ==========");

        issueService.issueBook(1, 101);  // Issue "Java: The Complete Reference" to Rohit
        issueService.issueBook(2, 101);  // Issue "Clean Code" to Rohit

        // =================================
        // 14. TRY ISSUING UNAVAILABLE BOOK
        // =================================
        System.out.println("\n========== ISSUE UNAVAILABLE BOOK TEST ==========");

        // Book 3 was deleted earlier, so it won't be found
        issueService.issueBook(3, 102);

        // =================================
        // 15. VIEW ISSUED BOOKS
        // =================================
        System.out.println("\n========== CURRENTLY ISSUED BOOKS ==========");

        issueService.viewIssuedBooks();

        // =================================
        // 16. RETURN A BOOK
        // =================================
        System.out.println("\n========== RETURN BOOK ==========");

        issueService.returnBook(1);  // Return issue ID 1

        // =================================
        // 17. TRY RETURNING ALREADY RETURNED BOOK
        // =================================
        System.out.println("\n========== RETURN ALREADY RETURNED BOOK TEST ==========");

        issueService.returnBook(1);  // Try returning issue ID 1 again

        // =================================
        // 18. VIEW ALL ISSUE RECORDS
        // =================================
        System.out.println("\n========== ALL ISSUE RECORDS ==========");

        issueService.viewAllIssueRecords();

        // =================================
        // 19. CHECK BOOK AVAILABILITY
        // =================================
        System.out.println("\n========== CHECK AVAILABILITY ==========");

        bookService.checkAvailability(1);
        bookService.checkAvailability(2);

        // =================================
        // 20. TRY DELETING STUDENT WITH ACTIVE ISSUE
        // =================================
        System.out.println("\n========== DELETE STUDENT WITH ACTIVE ISSUE ==========");

        studentService.deleteStudent(101);  // Should fail — Rohit still has book 2 issued

        // =================================
        // 21. FINAL BOOK LIST
        // =================================
        System.out.println("\n========== FINAL BOOK LIST ==========");

        bookService.viewAllBooks();

        // =================================
        // 22. FINAL STUDENT LIST
        // =================================
        System.out.println("\n========== FINAL STUDENT LIST ==========");

        studentService.viewAllStudents();
    }
}
