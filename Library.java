package LibraryManagementSystem;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBookbyID(int bookID) {
        books.removeIf(book -> book.getBookID() == bookID);
    }

    public void removeBookbyName(String bookName) {
        books.removeIf(book -> book.getBookName().equals(bookName));
    }

    public Book finBookbyID(int bookID) {
        for (Book book : books) {
            if (book.getBookID() == bookID) {
                return book;
            }
        }
        return null;
    }

    public Book finBookbyName(String bookName) {
        for (Book book : books) {
            if (book.getBookName().equals(bookName)) {
                return book;
            }
        }
        return null;
    }

    public List<Book> findBookByAuthorName(String authorName) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthorName().equals(authorName)) {
                result.add(book);;
            }
        }
        return result;
    }

    public List<Book> findBookbyPublisherName(String publisherName) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getPublisherName().equals(publisherName)) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> getAllBooks() {
        return books;
    }
}
