package sample.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

public class Publisher {
    private Integer publisher_id;
    private String Address;
    private String publisher_name;
    private String phoneNumber;

    public Publisher(int id, String address, String name,String phoneNumber) {
        this.publisher_id = id;
        this.Address = address;
        this.publisher_name = name;
        this.phoneNumber = phoneNumber;
    }

    public Publisher() {

    }
    public StringProperty getPublisherNameProb(){
            return new SimpleStringProperty(this.publisher_name);
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
