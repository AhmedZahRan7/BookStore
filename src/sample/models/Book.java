package sample.models;

import java.util.Date;

public class Book {
    private String ISBN;
    private String title;
    private Integer noCopies;
    private Integer threshold;
    private String category;
    private String publisherName;
    private Date publicationYear;
    private float price;

    public Book(String ISBN, String title, Integer noCopies, Integer threshold, String category, String publisherName, Date publicationYear) {
        this.ISBN = ISBN;
        this.title = title;
        this.noCopies = noCopies;
        this.threshold = threshold;
        this.category = category;
        this.publisherName = publisherName;
        this.publicationYear = publicationYear;
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

    public String getCategory() {
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

    public void setCategory(String category) {
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
