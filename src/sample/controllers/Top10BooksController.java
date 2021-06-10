package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.models.Book;
import sample.models.BookSales;
import sample.utilities.Categories;
import sample.views.ViewsSwitcher;

import java.util.ArrayList;
import java.util.Date;

public class Top10BooksController {
    @FXML TableView topBooks;
    @FXML
    Button backButton;

    public void initialize(){
        initializeTable();
        setTableData(getTopBooks());
        backButton.setStyle("-fx-background-color: #FFCA33; ");
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ViewsSwitcher.getInstance().switchTo((Stage) backButton.getScene().getWindow(),"admin");
            }
        });
    }
    void initializeTable(){
        TableColumn<BookSales, String> user = new TableColumn<BookSales, String>("Book");
        TableColumn<BookSales, String> amount = new TableColumn<BookSales, String>("Sales");
        user.setMinWidth(300);
        amount.setMinWidth(200);
        user.setCellValueFactory(tf->tf.getValue().getBook().getIsbnProperty());
        amount.setCellValueFactory(new PropertyValueFactory<BookSales,String>("amount"));
        topBooks.getColumns().addAll(user,amount);
    }

    void setTableData(ArrayList<BookSales> sales){
        ObservableList<BookSales> data = FXCollections.observableArrayList(sales);
        topBooks.setItems(data);
    }
    ArrayList<BookSales> getTopBooks(){
        ArrayList<BookSales> list = new ArrayList<>();
        list.add(new BookSales(
                new Book("12345678912","title",10,5, Categories.ART,"Publisher", new Date(),20),
                        20
                )
        );
        return list;
    }
}
