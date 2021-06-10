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
import sample.models.Checkout;
import sample.models.User;
import sample.utilities.Categories;
import sample.views.ViewsSwitcher;

import java.util.ArrayList;
import java.util.Date;

public class LastMonthReportController {
    @FXML TableView monthlyTable;
    @FXML
    Button backButton;

    public void initialize(){
        initializeTable();
        setTableData(getLastMonthCheckouts());
        backButton.setStyle("-fx-background-color: #FFCA33; ");
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ViewsSwitcher.getInstance().switchTo((Stage) backButton.getScene().getWindow(),"admin");
            }
        });
    }
    void initializeTable(){
        TableColumn<Checkout,String> id = new TableColumn<Checkout, String>("Checkout ID");
        TableColumn<Checkout, String> book = new TableColumn<Checkout, String>("Book");
        TableColumn<Checkout, String> user = new TableColumn<Checkout, String>("User");
        TableColumn<Checkout, String> date = new TableColumn<Checkout, String>("Date");
        TableColumn<Checkout, String> noOfCopies = new TableColumn<Checkout, String>("Number Of Copies");
        book.setMinWidth(150);
        id.setMinWidth(150);
        user.setMinWidth(150);
        date.setMinWidth(150);
        noOfCopies.setMinWidth(150);
        book.setCellValueFactory(tf->tf.getValue().getBook().getIsbnProperty());
        id.setCellValueFactory(new PropertyValueFactory<Checkout,String>("ID"));
        user.setCellValueFactory(tf->tf.getValue().getUser().getUserProperty());
        date.setCellValueFactory(new PropertyValueFactory<Checkout,String>("date"));
        noOfCopies.setCellValueFactory(new PropertyValueFactory<Checkout,String>("noOfCopies"));
        monthlyTable.getColumns().addAll(id,book,user,noOfCopies,date);
    }
    void setTableData(ArrayList<Checkout> checkouts){
        ObservableList<Checkout> data = FXCollections.observableArrayList(checkouts);
        monthlyTable.setItems(data);
    }
    ArrayList<Checkout> getLastMonthCheckouts(){
        ArrayList<Checkout> list = new ArrayList<>();
        list.add(new Checkout(1235654,
                new Book("12345678912","title",10,5, Categories.ART,"Publisher", new Date(),20),
                new User("name","12345","12354","5 Famous st","Ali","Ahmed","mail@mail",false),
                new Date(),
                20
                )
        );
        return list;
    }
}
