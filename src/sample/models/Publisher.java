package sample.models;

public class Publisher {
    private String id;
    private String Address;
    private String Name;

    public Publisher(String id, String address, String name) {
        this.id = id;
        this.Address = address;
        this.Name = name;
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
}
