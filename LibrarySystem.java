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
                currentUser = user; // Başarıyla giriş yaptıysa, currentUser'ı ayarla
                System.out.println("Login successful! Welcome, " + currentUser.getFirstName() + " " + currentUser.getLastName() + ".");
                loginSuccessful = true; // Giriş başarılı, döngüyü bitir
                if (currentUser.isLibrarian()) {
                    showLibrarianMenu();
                } else if (currentUser.isStudent()) {
                    showStudentMenu();
                }
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
        return null; // Kullanıcı doğrulanamadı
    }
    public void logout(){
        if (currentUser == null) {
            System.out.println("No user is currently logged in.");
            return;
        }
        System.out.println("Logging out user: " + currentUser.getUsername());
        currentUser = null; // dolaylı yoldan oturumu kapatmış olur

        System.out.println("Logout successful. Returning to login screen...");
        login(); //tekrar giriş yapma ekraına gönderir
    }
    public void showStudentMenu(){
        List<Book> books = library.getAllBooks();
        int bookCount = books.size();
        System.out.println("---------------------------------------------");
        System.out.println("1. Show All Books");
        System.out.println("2. Show Available Books");
        System.out.println("3. Borrow Book");
        System.out.println("4. Return Book");
        System.out.println("5. Search Book");
        System.out.println("6. Logout");
        System.out.println("---------------------------------------------");
        System.out.println("Total Book Number: "+books.size());
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
            case 5: {System.out.println("1. Search Book By ID");
                System.out.println("2. Search Book by Name");
                System.out.println("3. Search Book by Author");
                System.out.println("4. Search Book by Publisher");
                System.out.println("5. Search Book by ISBN");

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
                    case 5:
                        findBookbyISBN();
                    default:
                        System.out.println("Invalid choice, try again");}
                }
                break;
            case 6:
                logout();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                showStudentMenu();
                break;
        }
    }
    public void showLibrarianMenu(){
        List<Book> books = library.getAllBooks();
        int bookCount = books.size();
        System.out.println("---------------------------------------------");
        System.out.println("1. Show All Books");
        System.out.println("2. Add Book");
        System.out.println("3. Delete Book");
        System.out.println("4. Update Book");
        System.out.println("5. Search Book");
        System.out.println("6. Logout");
        System.out.println("---------------------------------------------");
        System.out.println("Total Book Number: "+books.size());
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
                updateBook();
                break;
            case 5: {
                System.out.println("1. Search Book By ID");
                System.out.println("2. Search Book by Name");
                System.out.println("3. Search Book by Author");
                System.out.println("4. Search Book by Publisher");
                System.out.println("5. Search Book by ISBN");

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
                    case 5:
                        findBookbyISBN();
                    default:
                        System.out.println("Invalid choice, try again");
                }
            }break;
            case 6:
                logout();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                showLibrarianMenu();
                break;
        }
    }
    public void start() {
        System.out.println("Library Management System");
        if (currentUser.isLibrarian()) {
            showLibrarianMenu();
        } else if (currentUser.isStudent()) {
            showStudentMenu();
        }
        boolean run = true;

        /*while (run) {
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
                {System.out.println("1. Search Book By ID");
                    System.out.println("2. Search Book by Name");
                    System.out.println("3. Search Book by Author");
                    System.out.println("4. Search Book by Publisher");
                    System.out.println("5. Search Book by ISBN");

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
                        case 5:
                            findBookbyISBN();
                        default:
                            System.out.println("Invalid choice, try again");
                    }
                    break;}
                case 5:
                    run = false;
                    System.out.println("Library Management System Exits");
                    break;
                default:
                    System.out.println("Invalid choice, try again");
            }
        }*/
    }

    private void listBook(){
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
    private void listAvailableBooks(){
        List<Book> books = library.getAllBooks();

        boolean hasAvailableBooks = false;
        System.out.println("Available Books:");
        System.out.println("---------------------------------------------");

        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println("Book ID        : " + book.getBookID());
                System.out.println("Book Name      : " + book.getBookName());
                System.out.println("Author Name    : " + book.getAuthorName());
                System.out.println("Publisher Name : " + book.getPublisherName());
                System.out.println("ISBN           : " + book.getISBN());
                System.out.println("---------------------------------------------");
                hasAvailableBooks = true;
            }
        }
        if (!hasAvailableBooks) {
            System.out.println("No available books found");
            }
        }
    private void borrowBook(){
        System.out.println("Would you like to search by (1) Book ID or (2) Book Name?");
        int choice = scanner.nextInt();
        scanner.nextLine();

        Book bookToBorrow = null;

        if (choice == 1) {

            System.out.print("Enter the Book ID you want to borrow: ");
            int bookID = scanner.nextInt();
            scanner.nextLine();

            bookToBorrow = library.finBookbyID(bookID);
        } else if (choice == 2) {

            System.out.print("Enter the Book Name you want to borrow: ");
            String bookName = scanner.nextLine();
            bookToBorrow = library.finBookbyName(bookName);
        } else {
            System.out.println("Invalid choice. Please enter 1 or 2.");
            return;
        }

        if (bookToBorrow != null) {
            if (bookToBorrow.isAvailable()) {
                bookToBorrow.setAvailable(false);
                System.out.println("You have successfully borrowed the book: " + bookToBorrow.getBookName());
            } else {
                System.out.println("Sorry, this book is currently unavailable.");
            }
        } else {
            System.out.println("Book not found.");
        }
    }
    public void returnBook() {
        System.out.print("Enter the Book (1)ID or (2)Name you want to return: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // nextInt() sonrası buffer'ı temizlemek için kullanılır
        Book bookToReturn = null;

        if (choice == 1) {
            System.out.print("Enter Book ID to return: ");
            int bookID = scanner.nextInt();
            bookToReturn = library.finBookbyID(bookID);
        } else if (choice == 2) {
            System.out.print("Enter Book Name to return: ");
            String bookName = scanner.nextLine();
            bookToReturn = library.finBookbyName(bookName);
        } else {
            System.out.println("Invalid choice. Please enter 1 or 2.");
            return;
        }

        if (bookToReturn == null) {
            System.out.println("No book found with the given ID or Name.");
            return;
        }

        if (bookToReturn.isAvailable()) {
            System.out.println("This book was not borrowed.");
        } else {
            bookToReturn.setAvailable(true);
            System.out.println("Book returned successfully: " + bookToReturn.getBookName());
        }
    }
    public void updateBook() {
        System.out.print("Enter Book ID to update: ");
        int bookID = scanner.nextInt();
        scanner.nextLine(); // nextInt sonrası buffer'ı temizlemek için kullanılır

        Book bookToUpdate = library.finBookbyID(bookID);

        if (bookToUpdate == null) {
            System.out.println("No book found with the given ID.");
            return;
        }

        System.out.print("Enter new Book Name (leave empty to keep current): ");
        String newBookName = scanner.nextLine();
        if (!newBookName.isEmpty()) {
            bookToUpdate.setBookName(newBookName);
        }

        System.out.print("Enter new Author Name (leave empty to keep current): ");
        String newAuthorName = scanner.nextLine();
        if (!newAuthorName.isEmpty()) {
            bookToUpdate.setAuthorName(newAuthorName);
        }

        System.out.print("Enter new Publisher Name (leave empty to keep current): ");
        String newPublisherName = scanner.nextLine();
        if (!newPublisherName.isEmpty()) {
            bookToUpdate.setPublisherName(newPublisherName);
        }

        System.out.print("Enter new ISBN (leave empty to keep current): ");
        String newISBN = scanner.nextLine();
        if (!newISBN.isEmpty()) {
            bookToUpdate.setISBN(newISBN);
        }

        System.out.println("Book updated successfully!");
    }



}
