package sample.models;

import java.util.Date;

public class Checkout {

    private long ID;
    private Book book;
    private User user;
    private Date date;
    private int noOfCopies;

    public Checkout(long ID, Book book, User user, Date date, int noOfCopies) {
        this.ID = ID;
        this.book = book;
        this.user = user;
        this.date = date;
        this.noOfCopies = noOfCopies;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNoOfCopies() {
        return noOfCopies;
    }

    public void setNoOfCopies(int noOfCopies) {
        this.noOfCopies = noOfCopies;
    }
}
