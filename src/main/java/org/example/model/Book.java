package org.example.model;


public class Book {

    private int bookId;
    private String title;
    private String author;
    private String category;
    private int totalQuantity;
    private int availableQuantity;

    public Book(int bookId,String title,String author,String category,int
                totalQuantity){
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.totalQuantity = totalQuantity;
        this.availableQuantity = totalQuantity;
    }


    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }
}