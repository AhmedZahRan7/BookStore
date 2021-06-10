package sample.models;

public class UserPurchase {
    private User user;
    private double amount;

    public UserPurchase(User user, double amount) {
        this.user = user;
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public double getAmount() {
        return amount;
    }
}
