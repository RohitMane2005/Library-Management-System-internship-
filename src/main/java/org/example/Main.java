package org.example;

import org.example.model.Book;
import org.example.service.BookService;

public class Main {

    public static void main(String[] args) {

        BookService bookService = new BookService();

        // Creating Book Objects
        Book book1 = new Book(101, "Java Programming", "James Gosling", "Programming", 5);
        Book book2 = new Book(102, "Python Basics", "Guido van Rossum", "Programming", 3);
        Book book3 = new Book(103, "Data Structures", "Mark Allen Weiss", "Computer Science", 4);

        // 1. Add Books
        System.out.println("\n========== ADD BOOKS ==========");

        bookService.addBook(book1);
        bookService.addBook(book2);
        bookService.addBook(book3);

        // 2. Test Duplicate Book ID
        System.out.println("\n========== DUPLICATE TEST ==========");

        Book duplicateBook = new Book(101, "Advanced Java", "John Smith", "Programming", 2);
        bookService.addBook(duplicateBook);

        // 3. View All Books
        System.out.println("\n========== ALL BOOKS ==========");

        bookService.viewAllBooks();

        // 4. Search Existing Book
        System.out.println("\n========== SEARCH EXISTING BOOK ==========");

        Book searchedBook = bookService.searchBookById(102);

        if (searchedBook != null) {
            System.out.println("Book Found!");
            System.out.println(searchedBook);
        } else {
            System.out.println("Book not found!");
        }

        // 5. Search Non-Existing Book
        System.out.println("\n========== SEARCH INVALID BOOK ==========");

        Book invalidBook = bookService.searchBookById(999);

        if (invalidBook != null) {
            System.out.println(invalidBook);
        } else {
            System.out.println("Book not found!");
        }
    }
}