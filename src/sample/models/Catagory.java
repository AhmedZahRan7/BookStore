package sample.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Catagory {
    private String catagory_name;
    private String catagory_id;
    private final StringProperty gategoryProp  = new SimpleStringProperty();

    public StringProperty getGategoryProp() {
        setGategoryProp(catagory_name);
        return gategoryProp;
    }

    public void setGategoryProp(String gategoryProp) {
        this.gategoryProp.set(gategoryProp);
    }

    public Catagory(String catagory_name, String catagory_id) {
        this.catagory_name = catagory_name;
        this.catagory_id = catagory_id;
    }

    public Catagory() {

    }

    public String getCatagory_name() {
        return catagory_name;
    }

    public void setCatagory_name(String catagory_name) {
        this.catagory_name = catagory_name;
    }

    public String getCatagory_id() {
        return catagory_id;
    }

    public void setCatagory_id(String catagory_id) {
        this.catagory_id = catagory_id;
    }
}
