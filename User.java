package LibraryManagementSystem;

public abstract class User {
    private int userID;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String role;


    public User(int userID, String firstName, String lastName, String email, String username, String password, String role) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public boolean isLibrarian() {
        return role.equalsIgnoreCase("Librarian");
    }

    public boolean isStudent() {
        return role.equalsIgnoreCase("Student");
    }

    public void showUserInfo() {
        System.out.println("User ID: " + userID
        + "\nFirst Name: " + firstName
        + "\nLast Name: " + lastName
        + "\nEmail: " + email
        + "\nUsername: " + username
        + "\nPassword: " + password);
    }
}
