package LibraryManagementSystem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;
    public void saveBooksToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Book book : books) {
                writer.write(book.getBookID() + "," + book.getBookName() + "," + book.getAuthorName() + "," + book.getPublisherName() + "," + book.isAvailable());
                writer.newLine(); // Yeni kitap bilgisi için yeni satıra geç
            }
            System.out.println("Books saved to file successfully.");
        } catch (IOException e) {
            System.out.println("Error while writing to file: " + e.getMessage());
        }
    }
    public void loadBooksFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] bookData = line.split(","); // Kitap bilgilerini virgülden ayır
                int bookID = Integer.parseInt(bookData[0]);
                String bookName = bookData[1];
                String authorName = bookData[2];
                String publisherName = bookData[3];
                String ISBN=bookData[4];
                boolean available = Boolean.parseBoolean(bookData[5]);

                Book book = new Book(bookID, bookName, authorName, publisherName, ISBN, available);
                books.add(book); // Kitap listesine ekle
            }
            System.out.println("Books loaded from file successfully.");
        } catch (IOException e) {
            System.out.println("Error while reading from file: " + e.getMessage());
        }
    }
    public Library() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        saveBooksToFile("books.txt");
    }

    public void removeBookbyID(int bookID) {
        books.removeIf(book -> book.getBookID() == bookID);
        saveBooksToFile("books.txt");
    }

    public void removeBookbyName(String bookName) {
        books.removeIf(book -> book.getBookName().equals(bookName));
        saveBooksToFile("books.txt");
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
            if (book.getBookName().equalsIgnoreCase(bookName)) {
                return book;
            }
        }
        return null;
    }

    public List<Book> findBookByAuthorName(String authorName) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthorName().equalsIgnoreCase(authorName)) {
                result.add(book);;
            }
        }
        return result;
    }

    public List<Book> findBookbyPublisherName(String publisherName) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getPublisherName().equalsIgnoreCase(publisherName)) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public Book finBookbyISBN(String ISBN) {
        for (Book book : books) {
            if (book.getISBN().equalsIgnoreCase(ISBN)) {
                return book;
            }
        }
        return null;
    }

    public boolean returnBook(int bookID) {
        Book book = finBookbyID(bookID);
        if (book != null && !book.isAvailable()) {
            book.setAvailable(true);
            return true;
        }
        return false;
    }

    public boolean borrowBook(int bookID) {
        Book book = finBookbyID(bookID);
        if (book != null && book.isAvailable()) {
            book.setAvailable(false);
            return true;
        }
        return false;
    }

    public List<Book> listAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.isAvailable()) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }
}
