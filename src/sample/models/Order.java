package sample.models;

public class Order {
    private Integer Orders_id;
    private String ISBN;
    private Integer nocopies;

    public Integer getOrders_id() {
        return Orders_id;
    }

    public void setOrders_id(Integer orders_id) {
        Orders_id = orders_id;
    }

    public Integer getNocopies() {
        return nocopies;
    }

    public void setNocopies(Integer nocopies) {
        this.nocopies = nocopies;
    }

    public Order(String ISBN, int noOfCopies) {
        this.ISBN = ISBN;
        this.nocopies = noOfCopies;
    }

    public Order() {

    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getNoOfCopies() {
        return nocopies;
    }

    public void setNoOfCopies(int noOfCopies) {
        this.nocopies = noOfCopies;
    }
}
