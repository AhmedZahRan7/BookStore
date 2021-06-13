package sample.models;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
public class Author {
    public Integer author_id;
    public String author_Name;
    public Author(){
    }
    public Author(Integer id, String authorName) {
        this.author_id = id;
        this.author_Name = authorName;
    }
    public Integer getId() {
        return author_id;
    }
    public void setId(Integer id) {
        this.author_id = id;
    }
    public String getAuthorName() {
        return author_Name;
    }
    public void setAuthorName(String authorName) {
        this.author_Name = authorName;
    }
    StringProperty getAuthorProperty(){
        return new SimpleStringProperty(this.author_Name);
    }
}