package sample.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Date;

public class Book {

    private String ISBN;
    private float price;
    private String title;
    private Integer noCopies;
    private Integer threshold;
    private Catagory catagory;
    private Publisher publisher;
    private Date publication_year;
    private final StringProperty isbnProperty = new SimpleStringProperty();

    public Book(String ISBN, String title, Integer noCopies, Integer threshold, Catagory category, Publisher publisher, Date publicationYear, float price) {
        this.ISBN = ISBN;
        this.title = title;
        this.noCopies = noCopies;
        this.threshold = threshold;
        this.catagory = category;
        this.publisher = publisher;
        this.publication_year = publicationYear;
        this.price = price;
        this.isbnProperty.setValue(ISBN);
    }
    public StringProperty getIsbnProperty() {
        return isbnProperty;
    }

    public Book() {

    }

    public String getISBN() {
        return ISBN;
    }

    public String getTitle() {
        return title;
    }

    public Integer getNoCopies() {
        return noCopies;
    }

    public Integer getThreshold() {
        return threshold;
    }

    public Catagory getCatagory() {
        return catagory;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public Date getPublication_year() {
        return publication_year;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNoCopies(Integer noCopies) {
        this.noCopies = noCopies;
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }

    public void setCatagory(Catagory catagory) {
        this.catagory = catagory;
    }

    public void setPublisherName(Publisher publisher) {
        this.publisher = publisher;
    }

    public void setPublication_year(Date publication_year) {
        this.publication_year = publication_year;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
