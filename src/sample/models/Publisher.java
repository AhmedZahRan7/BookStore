package sample.models;

import java.util.ArrayList;
import java.util.List;

public class Publisher {
    private String id;
    private String Address;
    private String Name;
    private List<String> phoneNumber;

    public Publisher(String id, String address, String name) {
        this.id = id;
        this.Address = address;
        this.Name = name;
        this.phoneNumber = new ArrayList<>();
    }

    public Publisher(String id, String address, String name, List<String> phoneNumber) {
        this(id, address, name);
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<String> getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(List<String> phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
