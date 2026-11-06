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


    public void updateBook(int bookId, String title,
                           String author, String category,
                           int newTotalQuantity){
        Book book = searchBookById(bookId);

        if (book == null){
            System.out.println("Error: Book with ID " + bookId + " not found.");
            return;
        }

        if (title == null || title.isBlank() ||
                author == null || author.isBlank() ||
                category == null || category.isBlank()) {

            System.out.println("Error: Book details cannot be empty!");
            return;
        }
        int issuedCopies = book.getTotalQuantity() - book.getAvailableQuantity();

        if (newTotalQuantity < issuedCopies){
            System.out.println("Error: New total quantity cannot be less than currently issued copies (" + issuedCopies + ").");
            return;
        }

        if (newTotalQuantity < 0){
            System.out.println("Error: New total quantity cannot be negative");
            return;
        }

        int newAvailableQuantity = newTotalQuantity - issuedCopies;

        book.setTitle(title);
        book.setAuthor(author);
        book.setCategory(category);
        book.setTotalQuantity(newTotalQuantity);
        book.setAvailableQuantity(newAvailableQuantity);

        System.out.println("Success: Book details updated successfully for ID " + bookId + ".");

    }

    public void deleteBook(int bookId){
        Book book = searchBookById(bookId);

        if (book == null){
            System.out.println("Error : Book is not available");
            return;
        }

        int issuedCopies = book.getTotalQuantity() - book.getAvailableQuantity();
        if (issuedCopies > 0) {
            System.out.println("Error: Cannot delete book. There are still " + issuedCopies + " copies currently issued to users.");
            return;
        }

        books.remove(book);
        System.out.println("Book with ID " + bookId +
                " deleted successfully!");
    }


    public void checkAvailability(int bookId) {

        Book book = searchBookById(bookId);

        if (book == null) {
            System.out.println("Error: Book not found!");
            return;
        }

        System.out.println("\n========== BOOK AVAILABILITY ==========");
        System.out.println("Book ID: " + book.getBookId());
        System.out.println("Book Name: " + book.getTitle());
        System.out.println("Available Copies: " + book.getAvailableQuantity());

        if (book.getAvailableQuantity() > 0) {
            System.out.println("Status: AVAILABLE");
        } else {
            System.out.println("Status: NOT AVAILABLE");
        }
    }


}
