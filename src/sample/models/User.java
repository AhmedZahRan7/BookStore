package sample.models;

public class User {

    private String user_name;
    private String password;
    private String last_name;
    private String first_name;
    private String email;
    private String shipping_address;
    private Integer manager;
    private Integer total_purchases;


// list of his credit cards

    public User(String userName, String password, String shippingAddress, String lastName, String firstName, String email, Integer manager) {
        this.user_name = userName;
        this.password = password;
       this.shipping_address = shippingAddress;
        this.last_name = lastName;
        this.first_name = firstName;
        this.email = email;
        this.manager = manager;
        total_purchases = 0;
    }

    public User() {

    }

    public Integer getTotal_purchases() {
        return total_purchases;
    }

    public void setTotal_purchases(Integer total_purchases) {
        this.total_purchases = total_purchases;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getShipping_address() {
        return shipping_address;
    }

    public void setShipping_address(String shipping_address) {
        this.shipping_address = shipping_address;
    }

    public String getLast_name() {
        return this.last_name;
    }

    public void setLast_name(String astName) {
        this.last_name = astName;
    }

    public String getFirst_name() {
        return this.first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer isManager() {
        return manager;
    }

    public void setManager(Integer manager) {
        this.manager = manager;
    }
}
