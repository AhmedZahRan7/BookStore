package sample.models;

public class Order {
    private String ISBN;
    private int noOfCopies;
    private int orderID;

    public Order(int orderID,String ISBN, int noOfCopies) {
        this.ISBN = ISBN;
        this.noOfCopies = noOfCopies;
        this.orderID = orderID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getNoOfCopies() {
        return noOfCopies;
    }

    public void setNoOfCopies(int noOfCopies) {
        this.noOfCopies = noOfCopies;
    }
}
