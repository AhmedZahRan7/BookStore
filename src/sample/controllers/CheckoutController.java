package sample.controllers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.models.Book;
import sample.views.ViewsSwitcher;

import java.util.ArrayList;
import java.util.Date;

public class CheckoutController {
    @FXML TextField cardIdField;
    @FXML TextField cvvField;
    @FXML TextField addField;
    @FXML TextField removeField;
    @FXML Button checkButton;
    @FXML Button addButton;
    @FXML Button removeButton;
    @FXML Button backButton;
    @FXML DatePicker dateField;
    @FXML TableView tableView;
    @FXML Text costLabel;
    public void initialize(){
        initializeButtonsFunctions();
        initializeCartTable();
        setDataInTable(getCurrentCartBooks());
    }
    void initializeCartTable(){
        TableColumn<Book,String> isbn = new TableColumn<Book, String>("ISBN");
        TableColumn<Book, String> title = new TableColumn<>("Title");
        TableColumn<Book, String> noCopies = new TableColumn<Book, String>("#Copies");
        TableColumn<Book, String> price = new TableColumn<Book, String>("Price");
        isbn.setMinWidth(100);
        title.setMinWidth(30);
        noCopies.setMinWidth(30);
        price.setMinWidth(30);
        isbn.setCellValueFactory(new PropertyValueFactory<Book,String>("ISBN"));
        title.setCellValueFactory(new PropertyValueFactory<Book,String>("title"));
        noCopies.setCellValueFactory(new PropertyValueFactory<Book,String>("noCopies"));
        price.setCellValueFactory(new PropertyValueFactory<Book,String>("price"));
        tableView.getColumns().addAll(isbn,title,noCopies,price);
    }
    void setDataInTable(ArrayList<Book> books){
        /*todo:get data from cart*/
        final ObservableList<Book> data = FXCollections.observableArrayList(books);
        tableView.setItems(data);
    }
    ArrayList<Book> getCurrentCartBooks(){
        //get data of books in the cart
        ArrayList<Book> books = new ArrayList<>();
        return books;
    }
    void initializeButtonsFunctions(){
        backButton.setStyle("-fx-background-color: #FFCA33; ");
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ViewsSwitcher.getInstance().switchTo((Stage) backButton.getScene().getWindow(),"user_interface");
            }
        });
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent){
                System.out.println("Add " + addField.getText().trim());
            }
        });
        removeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent){
                System.out.println("Remove " + removeButton.getText().trim());
            }
        });
        checkButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent){
                System.out.println("pay: ");
                System.out.println(cardIdField.getText().trim());
                System.out.println(cvvField.getText().trim());
                System.out.println(dateField.getValue());
            }
        });
    }
}
