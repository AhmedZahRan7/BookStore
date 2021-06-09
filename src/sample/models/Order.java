package sample.models;

public class Order {
    private String ISBN;
    private int noOfCopies;

    public Order(String ISBN, int noOfCopies) {
        this.ISBN = ISBN;
        this.noOfCopies = noOfCopies;
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
