package LibraryManagementSystem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;
    private String filePath;

    public Library(String filePath) {
        this.books = new ArrayList<>();
        this.filePath = filePath;
        checkAndCreateFile(filePath);
        loadBooksFromFile(filePath);
    }

    public void checkAndCreateFile(String fileName) {
        File file = new File(fileName);
        try {
            if (file.exists()) {
                System.out.println(fileName + " dosyası zaten mevcut.");
            } else {
                if (file.createNewFile()) {
                    System.out.println(fileName + " dosyası başarıyla oluşturuldu.");
                } else {
                    System.out.println(fileName + " dosyası oluşturulamadı.");
                }
            }
        } catch (IOException e) {
            System.out.println("Dosya oluşturulurken bir hata meydana geldi: " + e.getMessage());
        }
    }

    public void saveBooksToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Book book : books) {
                writer.write(book.getBookID() + "," + book.getBookName() + "," + book.getAuthorName() + "," + book.getPublisherName() + "," + book.getISBN() + "," + book.isAvailable());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    public void loadBooksFromFile(String filePath) {
        books.clear(); // Önce mevcut kitap listesini temizleyin
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                //System.out.println("Reading line: " + line); // Debugging line
                String[] parts = line.split(",");
                //System.out.println("Parts length: " + parts.length); // Debugging line
                //for (int i = 0; i < parts.length; i++) {
                    //System.out.println("Part " + i + ": " + parts[i]); // Debugging line
                //}
                if (parts.length == 6) {  // 6 alan olduğundan emin olun
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    String author = parts[2].trim();
                    String publisher = parts[3].trim();
                    String isbn = parts[4].trim();
                    boolean isAvailable = Boolean.parseBoolean(parts[5].trim());

                    Book book = new Book(id, name, author, publisher, isbn, isAvailable);
                    //System.out.println("Adding book with ID: " + id); // Debugging line
                    books.add(book); // Kitapları mevcut listeye ekleyin
                } else {
                    System.out.println("Invalid line format: " + line); // Debugging line
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }





    public Library() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        saveBooksToFile();
    }

    public void removeBookbyID(int bookID) {
        books.removeIf(book -> book.getBookID() == bookID);
        saveBooksToFile();
    }

    public void removeBookbyName(String bookName) {
        books.removeIf(book -> book.getBookName().equalsIgnoreCase(bookName));
        saveBooksToFile();
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
                result.add(book);
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
            saveBooksToFile();
            return true;
        }
        return false;
    }

    public boolean borrowBook(int bookID) {
        Book book = finBookbyID(bookID);
        if (book != null && book.isAvailable()) {
            book.setAvailable(false);
            saveBooksToFile();
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