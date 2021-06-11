package sample.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

public class Publisher {
    private int publisher_id;
    private String Address;
    private String publisher_name;
    private List<String> phoneNumber;
    private final StringProperty publisherNameProb  = new SimpleStringProperty();
    public Publisher(int id, String address, String name) {
        this.publisher_id = id;
        this.Address = address;
        this.publisher_name = name;
        this.phoneNumber = new ArrayList<>();
    }

    public StringProperty getPublisherNameProb() {
        setPublisherNameProb(this.publisher_name);
        return publisherNameProb;
    }

    public void setPublisherNameProb(String name) {
        this.publisherNameProb.setValue(name);
    }

    public Publisher(int id, String address, String name, List<String> phoneNumber) {
        this(id, address, name);
        this.phoneNumber = phoneNumber;
    }

    public Publisher() {

    }

    public int getPublisher_id() {
        return publisher_id;
    }

    public void setPublisher_id(int publisher_id) {
        this.publisher_id = publisher_id;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPublisher_name() {
        return publisher_name;
    }

    public void setPublisher_name(String publisher_name) {
        this.publisher_name = publisher_name;
    }

    public List<String> getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(List<String> phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
