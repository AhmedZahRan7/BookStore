package sample.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import sample.utilities.Categories;

import java.util.Date;

public class Book {

    private String ISBN;
    private float price;
    private String title;
    private Integer noCopies;
    private Integer threshold;
    private Categories category;
    private String publisherName;
    private Date publicationYear;
    //value to be shown in table instead of pointer
    private final StringProperty isbnProperty = new SimpleStringProperty();
    public Book(String ISBN, String title, Integer noCopies, Integer threshold, Categories category, String publisherName, Date publicationYear, float price) {
        this.ISBN = ISBN;
        this.title = title;
        this.noCopies = noCopies;
        this.threshold = threshold;
        this.category = category;
        this.publisherName = publisherName;
        this.publicationYear = publicationYear;
        this.price = price;
        this.isbnProperty.setValue(ISBN);
    }

    public StringProperty getIsbnProperty() {
        return isbnProperty;
    }

    public StringProperty isbnPropertyProperty() {
        return isbnProperty;
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

    public Categories getCategory() {
        return category;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public Date getPublicationYear() {
        return publicationYear;
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

    public void setCategory(Categories category) {
        this.category = category;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public void setPublicationYear(Date publicationYear) {
        this.publicationYear = publicationYear;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
