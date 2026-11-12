package org.example.dao;

import org.example.db.DatabaseConnection;
import org.example.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    public void addBook(Book book) {
        // Check for duplicate bookId first
        if (getBookById(book.getBookId()) != null) {
            System.out.println("Error: Book with ID " + book.getBookId() + " already exists!");
            return;
        }

        String sql = "INSERT INTO books (book_id, title, author, category, total_quantity, available_quantity) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, book.getBookId());
            stmt.setString(2, book.getTitle());
            stmt.setString(3, book.getAuthor());
            stmt.setString(4, book.getCategory());
            stmt.setInt(5, book.getTotalQuantity());
            stmt.setInt(6, book.getAvailableQuantity());

            stmt.executeUpdate();
            System.out.println("Book added successfully!");
        } catch (SQLException e) {
            System.out.println("Error: Failed to add book - " + e.getMessage());
        }
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Book book = mapResultSetToBook(rs);
                books.add(book);
            }
        } catch (SQLException e) {
            System.out.println("Error: Failed to retrieve books - " + e.getMessage());
        }

        return books;
    }

    public Book getBookById(int bookId) {
        String sql = "SELECT * FROM books WHERE book_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bookId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToBook(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: Failed to retrieve book - " + e.getMessage());
        }

        return null;
    }

    public void updateBook(Book book) {
        String sql = "UPDATE books SET title = ?, author = ?, category = ?, total_quantity = ?, available_quantity = ? WHERE book_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getCategory());
            stmt.setInt(4, book.getTotalQuantity());
            stmt.setInt(5, book.getAvailableQuantity());
            stmt.setInt(6, book.getBookId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Success: Book details updated successfully for ID " + book.getBookId() + ".");
            } else {
                System.out.println("Error: Book with ID " + book.getBookId() + " not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error: Failed to update book - " + e.getMessage());
        }
    }

    public void deleteBook(int bookId) {
        String sql = "DELETE FROM books WHERE book_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bookId);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book with ID " + bookId + " deleted successfully!");
            } else {
                System.out.println("Error: Book with ID " + bookId + " not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error: Failed to delete book - " + e.getMessage());
        }
    }

    public void updateAvailability(int bookId, int change) {
        String sql = "UPDATE books SET available_quantity = available_quantity + ? WHERE book_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, change);
            stmt.setInt(2, bookId);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book availability updated successfully for ID " + bookId + ".");
            } else {
                System.out.println("Error: Book with ID " + bookId + " not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error: Failed to update book availability - " + e.getMessage());
        }
    }

    private Book mapResultSetToBook(ResultSet rs) throws SQLException {
        Book book = new Book(
                rs.getInt("book_id"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getString("category"),
                rs.getInt("total_quantity")
        );
        book.setAvailableQuantity(rs.getInt("available_quantity"));
        return book;
    }
}
