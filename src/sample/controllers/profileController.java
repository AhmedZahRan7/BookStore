package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.models.Book;
import sample.models.Checkout;
import sample.models.User;
import sample.views.ViewsSwitcher;

import java.util.ArrayList;
import java.util.Date;

public class profileController {
    @FXML TextField firstNameField;
    @FXML TextField userNameField;
    @FXML TextField mailField;
    @FXML TextField addressField;
    @FXML TextField lastNameField;
    @FXML TextField passwordField;
    @FXML Button confirmButton;
    @FXML Button backButton;
    @FXML TableView checkOutsHistoryTable;

    public void initialize(){
        initializeTable();
        setTableData(getMyCheckouts());
        initializeButtonsFunctions();
    }
    private void initializeTable(){
        TableColumn<Checkout,String> id = new TableColumn<Checkout, String>("Checkout ID");
        TableColumn<Checkout, String> book = new TableColumn<Checkout, String>("Book");
        TableColumn<Checkout, String> date = new TableColumn<Checkout, String>("Date");
        TableColumn<Checkout, String> noOfCopies = new TableColumn<Checkout, String>("Number Of Copies");
        book.setMinWidth(150);
        id.setMinWidth(150);
        date.setMinWidth(150);
        noOfCopies.setMinWidth(150);
        book.setCellValueFactory(tf->tf.getValue().getBook().getIsbnProperty());
        id.setCellValueFactory(new PropertyValueFactory<Checkout,String>("ID"));
        date.setCellValueFactory(new PropertyValueFactory<Checkout,String>("date"));
        noOfCopies.setCellValueFactory(new PropertyValueFactory<Checkout,String>("noOfCopies"));
        checkOutsHistoryTable.getColumns().addAll(id,book,noOfCopies,date);
    }
    void setTableData(ArrayList<Checkout> checkouts){
        ObservableList<Checkout> data = FXCollections.observableArrayList(checkouts);
        checkOutsHistoryTable.setItems(data);
    }
    ArrayList<Checkout> getMyCheckouts(){
        ArrayList<Checkout> list = new ArrayList<>();
        return list;
    }
    void initializeButtonsFunctions(){
        confirmButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("confirm Edits");

            }
        });
        backButton.setStyle("-fx-background-color: #FFCA33;");
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ViewsSwitcher.getInstance().switchTo((Stage)
                        backButton.getScene().getWindow(),"user_interface");

            }
        });
    }
}
