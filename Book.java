package LibraryManagementSystem;

public class Book {
    int bookID;
    String bookName;
    String authorName;
    String publisherName;
    String ISBN;
    boolean available;

    public Book(int bookID, String bookName, String authorName, String publisherName, String ISBN, boolean available) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.authorName = authorName;
        this.publisherName = publisherName;
        this.ISBN = ISBN;
        this.available = available;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
