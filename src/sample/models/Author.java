package sample.models;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
public class Author {
    public Integer author_id;
    public String author_Name;
    public Author(){
    }
    public Author(Integer author_id, String author_Name) {
        this.author_id = author_id;
        this.author_Name = author_Name;
    }
    public Integer getAuthor_id() {
        return author_id;
    }
    public void setAuthor_id(Integer author_id) {
        this.author_id = author_id;
    }
    public String getAuthor_Name() {
        return author_Name;
    }
    public void setAuthor_Name(String author_Name) {
        this.author_Name = author_Name;
    }
    StringProperty getAuthorProperty(){
        return new SimpleStringProperty(this.author_Name);
    }
}