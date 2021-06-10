package sample.controllers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.models.Book;
import sample.utilities.Categories;
import sample.views.ViewsSwitcher;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class UserInterfaceController {
    @FXML Button profileButton;
    @FXML Button cartButton;
    @FXML Button logoutButton;
    @FXML Button adminButton;
    @FXML Button searchButton;
    @FXML TextField searchTextField;
    @FXML TableView tableView;
    public void initialize(){
        initializeButtonsFunctions();
        initializeBooksTable();
        setDataInTable(getDataFromDatabase());
    }
    void initializeBooksTable(){
        TableColumn<Book, String> isbn = new TableColumn<Book, String>("ISBN");
        TableColumn<Book, String> title = new TableColumn<>("Title");
        TableColumn<Book, String> noCopies = new TableColumn<Book, String>("#Copies");
        TableColumn<Book, String> price = new TableColumn<Book, String>("Price");
        TableColumn<Book, String> publisher = new TableColumn<Book, String>("Publisher");
        TableColumn<Book, String> category = new TableColumn<Book, String>("Category");
        TableColumn<Book, String> date = new TableColumn<Book, String>("publicationYear");
        isbn.setMinWidth(30);
        title.setMinWidth(30);
        noCopies.setMinWidth(30);
        price.setMinWidth(30);
        publisher.setMinWidth(30);
        category.setMinWidth(30);
        date.setMinWidth(30);
        isbn.setCellValueFactory(new PropertyValueFactory<Book,String>("ISBN"));
        title.setCellValueFactory(new PropertyValueFactory<Book,String>("Title"));
        noCopies.setCellValueFactory(new PropertyValueFactory<Book,String>("noCopies"));
        price.setCellValueFactory(new PropertyValueFactory<Book,String>("price"));
        publisher.setCellValueFactory(new PropertyValueFactory<Book,String>("publisherName"));
        category.setCellValueFactory(new PropertyValueFactory<Book,String>("Category"));
        date.setCellValueFactory(new PropertyValueFactory<Book,String>("publicationYear"));
        tableView.getColumns().addAll(isbn,title,noCopies,price,publisher,category,date);
    }
    void setDataInTable(ArrayList<Book> books){
        final ObservableList<Book> data = FXCollections.observableArrayList(books);
        tableView.setItems(data);
    }
    ArrayList<Book> getDataFromDatabase(){
        /*todo: get data from backend*/
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("12345678912","title",10,5, Categories.ART,"Publisher", new Date(),20));
        books.add(new Book("12345678912","title",10,5, Categories.ART,"Publisher", new Date(),20));
        books.add(new Book("12345678912","title",10,5, Categories.ART,"Publisher", new Date(),20));
        books.add(new Book("12345678912","title",10,5, Categories.ART,"Publisher", new Date(),20));
        books.add(new Book("12345678912","title",10,5, Categories.ART,"Publisher", new Date(),20));
        books.add(new Book("12345678912","title",10,5, Categories.ART,"Publisher", new Date(),20));
        books.add(new Book("12345678912","title",10,5, Categories.ART,"Publisher", new Date(),20));
        books.add(new Book("12345678912","title",10,5, Categories.ART,"Publisher", new Date(),20));
        books.add(new Book("12345678912","title",10,5, Categories.ART,"Publisher", new Date(),20));
        books.add(new Book("12345678912","title",10,5, Categories.ART,"Publisher", new Date(),20));
        books.add(new Book("12345678912","title",10,5, Categories.ART,"Publisher", new Date(),20));
        books.add(new Book("12345678912","title",10,5, Categories.ART,"Publisher", new Date(),20));
        books.add(new Book("12345678912","title",10,5, Categories.ART,"Publisher", new Date(),20));
        books.add(new Book("12345678912","title",10,5, Categories.ART,"Publisher", new Date(),20));
        books.add(new Book("12345678912","title",10,5, Categories.ART,"Publisher", new Date(),20));
        books.add(new Book("12345678912","title",10,5, Categories.ART,"Publisher", new Date(),20));
        return books;
    }
    void initializeButtonsFunctions(){
        /*todo: get type of user if user set false*/
        adminButton.setVisible(true);
        adminButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Admin");
                ViewsSwitcher.getInstance().switchTo((Stage) adminButton.getScene().getWindow(),"admin");
            }
        });
        profileButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("profile");

            }
        });
        cartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("cart");
                ViewsSwitcher.getInstance().switchTo((Stage) cartButton.getScene().getWindow(),"cart");
            }
        });
        logoutButton.setStyle("-fx-background-color: #FF3361;");
        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("logout");
                ViewsSwitcher.getInstance().switchTo((Stage) logoutButton.getScene().getWindow(),"login");
            }
        });
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("search for " + searchTextField.getText().trim());
            }
        });
    }
}
