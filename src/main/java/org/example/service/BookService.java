package org.example.service;
import org.example.model.Book;
import java.util.ArrayList;
import java.util.List;

public class BookService {


    private List<Book> books = new ArrayList<>();


    private boolean isBookExists(int bookId) {

        for (Book book : books) {
            if (book.getBookId() == bookId) {
                return true;
            }
        }

        return false;
    }


    public void addBook(Book book) {

        if (isBookExists(book.getBookId())) {
            System.out.println("Book already exists!");
            return;
        }

        books.add(book);
        System.out.println("Book added successfully!");
    }



    public void viewAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available!");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }
    public Book searchBookById(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                return book;
            }
        }
        return null;
    }
}
