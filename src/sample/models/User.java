package sample.models;

public class User {
    private String userName;
    private String password;
    private String userID;
    private String shippingAddress;
    private String lastName;
    private String firstName;
    private String email;
    private boolean manager;
    // list of his credit cards

    public User(String userName, String password, String userID, String shippingAddress, String lastName, String firstName, String email, boolean manager) {
        this.userName = userName;
        this.password = password;
        this.userID = userID;
        this.shippingAddress = shippingAddress;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.manager = manager;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String astName) {
        this.lastName = astName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isManager() {
        return manager;
    }

    public void setManager(boolean manager) {
        this.manager = manager;
    }
}
