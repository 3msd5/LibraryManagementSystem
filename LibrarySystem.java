package LibraryManagementSystem;

import java.util.List;
import java.util.Scanner;

public class LibrarySystem {
    private Library library;
    private Scanner scanner;

    public LibrarySystem() {
        library = new Library();
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        LibrarySystem librarySystem = new LibrarySystem();
        librarySystem.start();
    }

    public void start() {
        System.out.println("Library Management System");
        boolean run = true;

        while (run) {
            List<Book> books = library.getAllBooks();
            int bookCount = books.size();
            System.out.println("---------------------------------------------");
            System.out.println("1. Show All Books");
            System.out.println("2. Add Book");
            System.out.println("3. Delete Book");
            System.out.println("4. Search Book");
            System.out.println("5. Exit");
            System.out.println("---------------------------------------------");
            System.out.println("Total Book Number: "+books.size());
            System.out.println("---------------------------------------------");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    listBook();
                    break;
                case 2:
                    addBook();
                    break;
                case 3:
                    System.out.println("1. Delete Book By ID");
                    System.out.println("2. Delete Book by Name");
                    int choice2 = scanner.nextInt();
                    scanner.nextLine();
                    switch (choice2) {
                        case 1:
                            removeBookbyID();
                            break;
                        case 2:
                            removeBookbyName();
                            break;
                        default:
                            System.out.println("Invalid choice, try again");
                    }
                    break;
                case 4:
                    System.out.println("1. Search Book By ID");
                    System.out.println("2. Search Book by Name");
                    System.out.println("3. Search Book by Author");
                    System.out.println("4. Search Book by Publisher");
                    int choice3 = scanner.nextInt();
                    scanner.nextLine();
                    switch (choice3) {
                        case 1:
                            findBookbyID();
                            break;
                        case 2:
                            findBookbyName();
                            break;
                        case 3:
                            findBookByAuthorName();
                            break;
                        case 4:
                            findBookbyPublisherName();
                            break;
                        default:
                            System.out.println("Invalid choice, try again");
                    }
                    break;
                case 5:
                    run = false;
                    System.out.println("Library Management System Exits");
                    break;
                default:
                    System.out.println("Invalid choice, try again");
            }
        }
    }

    private void listBook() {
        List<Book> books = library.getAllBooks();

        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
            System.out.println("---------------------------------------------");
        } else {
            System.out.println("Book List");
            System.out.println("---------------------------------------------");
            for (Book book : books) {
                System.out.println("Book ID        : " + book.getBookID());
                System.out.println("Book Name      : " + book.getBookName());
                System.out.println("Author Name    : " + book.getAuthorName());
                System.out.println("Publisher Name : " + book.getPublisherName());
                System.out.println("ISBN           : " + book.getISBN());
                System.out.println("Available      : " + (book.isAvailable() ? "Yes" : "No"));
                System.out.println("---------------------------------------------");
            }
        }
    }

    private void addBook() {
        System.out.print("Book Name: ");
        String bookName = scanner.nextLine();
        System.out.print("Author Name: ");
        String authorName = scanner.nextLine();
        System.out.print("Publisher Name: ");
        String publisherName = scanner.nextLine();
        System.out.print("ISBN: ");
        String ISBN = scanner.nextLine();

        int bookID = library.getAllBooks().size() + 1;
        boolean available = true;

        Book newBook = new Book(bookID, bookName, authorName, publisherName, ISBN, available);
        library.addBook(newBook);
        System.out.println("Book Added  -->  " + bookName);
        System.out.println("---------------------------------------------");
    }

    private void removeBookbyID() {
        System.out.print("ID of the book you want to delete: ");
        int bookID = scanner.nextInt();
        scanner.nextLine(); // consume newline

        library.removeBookbyID(bookID);
        System.out.println("Book deleted --> " + bookID);
        System.out.println("---------------------------------------------");
    }

    private void removeBookbyName() {
        System.out.print("Name of the book you want to delete: ");
        String bookName = scanner.nextLine();

        library.removeBookbyName(bookName);
        System.out.println("Book deleted --> " + bookName);
        System.out.println("---------------------------------------------");
    }

    private void findBookbyID() {
        System.out.print("ID of the book you want to find: ");
        int bookID = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Book book = library.finBookbyID(bookID);
        if (book != null) {
            System.out.println("Book Found");
            System.out.println("---------------------------------------------");
            System.out.println("Book ID        : " + book.getBookID());
            System.out.println("Book Name      : " + book.getBookName());
            System.out.println("Author Name    : " + book.getAuthorName());
            System.out.println("Publisher Name : " + book.getPublisherName());
            System.out.println("ISBN           : " + book.getISBN());
            System.out.println("Available      : " + (book.isAvailable() ? "Yes" : "No"));
            System.out.println("---------------------------------------------");
        } else {
            System.out.println("Book not found");
            System.out.println("---------------------------------------------");
        }
    }

    private void findBookbyName() {
        System.out.print("Name of the book you want to find: ");
        String bookName = scanner.nextLine();

        Book book = library.finBookbyName(bookName);
        if (book != null) {
            System.out.println("Book Found");
            System.out.println("---------------------------------------------");
            System.out.println("Book ID        : " + book.getBookID());
            System.out.println("Book Name      : " + book.getBookName());
            System.out.println("Author Name    : " + book.getAuthorName());
            System.out.println("Publisher Name : " + book.getPublisherName());
            System.out.println("ISBN           : " + book.getISBN());
            System.out.println("Available      : " + (book.isAvailable() ? "Yes" : "No"));
            System.out.println("---------------------------------------------");
        } else {
            System.out.println("Book not found");
            System.out.println("---------------------------------------------");
        }
    }

    private void findBookByAuthorName() {
        System.out.print("Author of the book you want to find: ");
        String authorName = scanner.nextLine();

        List<Book> books = library.findBookByAuthorName(authorName);
        int bookCount = books.size();

        if (!books.isEmpty()) {
            System.out.println("Books Found");
            System.out.println("---------------------------------------------");
            for (Book book : books) {
                System.out.println("Book ID        : " + book.getBookID());
                System.out.println("Book Name      : " + book.getBookName());
                System.out.println("Author Name    : " + book.getAuthorName());
                System.out.println("Publisher Name : " + book.getPublisherName());
                System.out.println("ISBN           : " + book.getISBN());
                System.out.println("Available      : " + (book.isAvailable() ? "Yes" : "No"));
                System.out.println("---------------------------------------------");
            }
            System.out.println("Total book number for '"+authorName+"': " + bookCount);
        } else {
            System.out.println("No books found");
            System.out.println("---------------------------------------------");
        }
    }

    private void findBookbyPublisherName() {
        System.out.print("Publisher of the book you want to find: ");
        String publisherName = scanner.nextLine();

        List<Book> books = library.findBookbyPublisherName(publisherName);
        int bookCount = books.size();

        if (!books.isEmpty()) {
            System.out.println("Books Found");
            System.out.println("---------------------------------------------");
            for (Book book : books) {
                System.out.println("Book ID        : " + book.getBookID());
                System.out.println("Book Name      : " + book.getBookName());
                System.out.println("Author Name    : " + book.getAuthorName());
                System.out.println("Publisher Name : " + book.getPublisherName());
                System.out.println("ISBN           : " + book.getISBN());
                System.out.println("Available      : " + (book.isAvailable() ? "Yes" : "No"));
                System.out.println("---------------------------------------------");

            }
            System.out.println("Total book number for '"+publisherName+"': " + bookCount);
        } else {
            System.out.println("No books found");
            System.out.println("---------------------------------------------");
        }
    }

}
