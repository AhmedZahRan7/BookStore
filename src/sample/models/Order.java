package sample.models;

public class Order {
    private String ISBN;
    private int noCopies;
    private String orders_id;
    public Order(String ISBN, int noOfCopies,String id) {
        this.ISBN = ISBN;
        this.noCopies = noOfCopies;
        this.orders_id = id;
    }

    public Order() {

    }

    public int getNoCopies() {
        return noCopies;
    }

    public void setNoCopies(int noCopies) {
        this.noCopies = noCopies;
    }

    public String getOrders_id() {
        return orders_id;
    }

    public void setOrders_id(String orders_id) {
        this.orders_id = orders_id;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

}
