package sample.models;

import java.util.Date;

public class Checkout {

    private String ID;
    private String book;
    private String user;
    private String date;
    private int noOfCopies;

    public Checkout(String ID, String book, String user, String date, int noOfCopies) {
        this.ID = ID;
        this.book = book;
        this.user = user;
        this.date = date;
        this.noOfCopies = noOfCopies;
    }

    public Checkout() {

    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNoOfCopies() {
        return noOfCopies;
    }

    public void setNoOfCopies(int noOfCopies) {
        this.noOfCopies = noOfCopies;
    }
}
