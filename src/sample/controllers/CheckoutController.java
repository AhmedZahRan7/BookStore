package sample.controllers;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
import sample.models.User;
import sample.viewmodels.UserViewModel;
import sample.views.ViewsSwitcher;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

class CartEntry{
    StringProperty isbn = new SimpleStringProperty();
    StringProperty copies = new SimpleStringProperty();
    public CartEntry(String isbn, Integer copies){
        this.isbn.setValue(isbn);
        this.copies.setValue(copies.toString());
    }

}
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
    final ObservableList<CartEntry> data = FXCollections.observableArrayList(new ArrayList<>());
    public void initialize(){
        initializeButtonsFunctions();
        tableView.setItems(data);
        initializeCartTable();
        setDataInTable();
    }
    void initializeCartTable(){
        TableColumn<CartEntry,String> isbn = new TableColumn<CartEntry, String>("ISBN");
        TableColumn<CartEntry, String> noCopies = new TableColumn<CartEntry, String>("#Copies");
        isbn.setMinWidth(150);
        noCopies.setMinWidth(150);
        isbn.setCellValueFactory(tf->tf.getValue().isbn);
        noCopies.setCellValueFactory(tf->tf.getValue().copies);
        tableView.getColumns().addAll(isbn,noCopies);
    }
    void setDataInTable(){
        data.clear();
        try{
            costLabel.setText(UserViewModel.get_instance().getCart().getTotalPrice().toString());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            for ( Map.Entry<Book,Integer> entry : UserViewModel.get_instance().getCart().getSelectedBooks().entrySet()){
                data.add(new CartEntry(entry.getKey().getISBN(),entry.getValue()));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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
                try {
                    UserViewModel.get_instance().addToCart(addField.getText().trim(),1);
                    removeField.setText("");
                    setDataInTable();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        removeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent){
                try {
                    UserViewModel.get_instance().removeFromCart(addField.getText().trim());
                    removeField.setText("");
                    setDataInTable();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                setDataInTable();
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
