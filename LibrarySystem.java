package LibraryManagementSystem;

import java.util.List;
import java.util.Scanner;

public class LibrarySystem {
    private Library library;
    private Scanner scanner;
    private User currentUser;

    public LibrarySystem() {
        library = new Library();
        scanner = new Scanner(System.in);
        library.loadBooksFromFile("C:\\Users\\muham\\OneDrive\\Masaüstü\\LibraryManagementSystem\\out\\production\\LibraryManagementSystem\\books.txt\\");
    }

    public static void main(String[] args) {
        LibrarySystem librarySystem = new LibrarySystem();
        librarySystem.login();
    }

    public void login() {
        boolean loginSuccessful = false;

        while (!loginSuccessful) {
            System.out.print("Enter your username: ");
            String username = scanner.nextLine();
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();

            User user = authenticateUser(username, password);

            if (user != null) {
                currentUser = user;
                System.out.println("Login successful! Welcome, " + currentUser.getFirstName() + " " + currentUser.getLastName() + ".");
                loginSuccessful = true;
                start(); // Giriş başarılı olduktan sonra ana menüyü başlat
            } else {
                System.out.println("Invalid username or password. Please try again.");
            }
        }
    }

    private User authenticateUser(String username, String password) {
        Librarian librarian = new Librarian(9999,"AdminAd","AdminSoyad","admin@mail.com","admin","123","Librarian");
        Student student = new Student(1,"studentAd","studentSoyad","student@mail.com","studentname","123","Student");

        if (username.equals(librarian.getUsername()) && password.equals(librarian.getPassword())) {
            return librarian;
        } else if (username.equals(student.getUsername()) && password.equals(student.getPassword())) {
            return student;
        }
        return null;
    }

    public void logout() {
        if (currentUser == null) {
            System.out.println("No user is currently logged in.");
            return;
        }
        System.out.println("Logging out user: " + currentUser.getUsername());
        currentUser = null;

        System.out.println("Logout successful. Returning to login screen...");
        login();
    }

    public void start() {
        boolean run = true;
        while (run) {
            if (currentUser.isLibrarian()) {
                showLibrarianMenu();
            } else if (currentUser.isStudent()) {
                showStudentMenu();
            }
        }
    }

    public void showStudentMenu() {
        List<Book> books = library.getAllBooks();
        System.out.println("---------------------------------------------");
        System.out.println("1. Show All Books");
        System.out.println("2. Show Available Books");
        System.out.println("3. Borrow Book");
        System.out.println("4. Return Book");
        System.out.println("5. Search Book");
        System.out.println("6. Logout");
        System.out.println("---------------------------------------------");
        System.out.println("Total Book Number: " + books.size());
        System.out.println("---------------------------------------------");

        int choice = scanner.nextInt();
        scanner.nextLine(); // for scan.nextInt() error

        switch (choice) {
            case 1:
                listBook();
                break;
            case 2:
                listAvailableBooks();
                break;
            case 3:
                borrowBook();
                break;
            case 4:
                returnBook();
                break;
            case 5:
                searchBook();
                break;
            case 6:
                logout();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                break;
        }
    }

    public void showLibrarianMenu() {
        List<Book> books = library.getAllBooks();
        System.out.println("---------------------------------------------");
        System.out.println("1. Show All Books");
        System.out.println("2. Add Book");
        System.out.println("3. Delete Book");
        System.out.println("4. Update Book");
        System.out.println("5. Search Book");
        System.out.println("6. Logout");
        System.out.println("---------------------------------------------");
        System.out.println("Total Book Number: " + books.size());
        System.out.println("---------------------------------------------");

        int choice = scanner.nextInt();
        scanner.nextLine(); // for scan.nextInt() error

        switch (choice) {
            case 1:
                listBook();
                break;
            case 2:
                addBook();
                break;
            case 3:
                deleteBook();
                break;
            case 4:
                updateBook();
                break;
            case 5:
                searchBook();
                break;
            case 6:
                logout();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                break;
        }
    }

    private void listBook() {
        List<Book> books = library.getAllBooks();
        System.out.println("Debug: Number of books in the list = " + books.size()); // Debugging line
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
        } else {
            System.out.println("Book List");
            System.out.println("---------------------------------------------");
            for (int i = 0; i < books.size(); i++) {
                Book book = books.get(i);
                System.out.println("Debug: Reading book at index " + i); // Debugging line
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

    private void deleteBook() {
        System.out.println("1. Delete Book By ID");
        System.out.println("2. Delete Book by Name");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1:
                removeBookbyID();
                break;
            case 2:
                removeBookbyName();
                break;
            default:
                System.out.println("Invalid choice, try again");
                break;
        }
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

    private void searchBook() {
        System.out.println("1. Search Book By ID");
        System.out.println("2. Search Book by Name");
        System.out.println("3. Search Book by Author");
        System.out.println("4. Search Book by Publisher");
        System.out.println("5. Search Book by ISBN");

        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
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
            case 5:
                findBookbyISBN();
                break;
            default:
                System.out.println("Invalid choice, try again");
                break;
        }
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
        System.out.print("Author name of the book you want to find: ");
        String authorName = scanner.nextLine();

        List<Book> books = library.findBookByAuthorName(authorName);
        if (books != null && !books.isEmpty()) {
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
        } else {
            System.out.println("No books found");
            System.out.println("---------------------------------------------");
        }
    }

    private void findBookbyPublisherName() {
        System.out.print("Publisher name of the book you want to find: ");
        String publisherName = scanner.nextLine();

        List<Book> books = library.findBookbyPublisherName(publisherName);
        if (books != null && !books.isEmpty()) {
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
        } else {
            System.out.println("No books found");
            System.out.println("---------------------------------------------");
        }
    }

    private void findBookbyISBN() {
        System.out.print("ISBN of the book you want to find: ");
        String ISBN = scanner.nextLine();

        Book book = library.finBookbyISBN(ISBN);
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

    private void borrowBook() {
        System.out.print("Enter Book ID to borrow: ");
        int bookID = scanner.nextInt();
        scanner.nextLine(); // consume newline

        boolean success = library.borrowBook(bookID);
        if (success) {
            System.out.println("Book borrowed successfully.");
        } else {
            System.out.println("Book borrowing failed. The book might not be available.");
        }
        System.out.println("---------------------------------------------");
    }

    private void returnBook() {
        System.out.print("Enter Book ID to return: ");
        int bookID = scanner.nextInt();
        scanner.nextLine(); // consume newline

        boolean success = library.returnBook(bookID);
        if (success) {
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Book returning failed. The book might not have been borrowed.");
        }
        System.out.println("---------------------------------------------");
    }

    private void updateBook() {
        System.out.print("Enter Book ID to update: ");
        int bookID = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Book book = library.finBookbyID(bookID);
        if (book != null) {
            System.out.println("Updating details for Book ID: " + bookID);
            System.out.print("New Book Name (leave blank to keep current): ");
            String bookName = scanner.nextLine();
            System.out.print("New Author Name (leave blank to keep current): ");
            String authorName = scanner.nextLine();
            System.out.print("New Publisher Name (leave blank to keep current): ");
            String publisherName = scanner.nextLine();
            System.out.print("New ISBN (leave blank to keep current): ");
            String ISBN = scanner.nextLine();

            if (!bookName.isEmpty()) book.setBookName(bookName);
            if (!authorName.isEmpty()) book.setAuthorName(authorName);
            if (!publisherName.isEmpty()) book.setPublisherName(publisherName);
            if (!ISBN.isEmpty()) book.setISBN(ISBN);

            System.out.println("Book details updated successfully.");
        } else {
            System.out.println("Book not found.");
        }
        System.out.println("---------------------------------------------");
    }
    public void listAvailableBooks() {
        List<Book> availableBooks = library.listAvailableBooks();
        if (availableBooks.isEmpty()) {
            System.out.println("No available books.");
        } else {
            System.out.println("Available books: " + availableBooks);
        }
    }
}
