package sample.controllers;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.callBacks.IUserCallBack;
import sample.models.Book;
import sample.models.User;
import sample.viewmodels.UserViewModel;
import sample.views.ViewsSwitcher;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


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
        TableColumn<CartEntry, String> noCopies = new TableColumn<CartEntry, String>("No. Copies");
        isbn.setMinWidth(150);
        noCopies.setMinWidth(150);
        isbn.setCellValueFactory(tf->tf.getValue().isbn);
        noCopies.setCellValueFactory(tf->tf.getValue().copies);
        tableView.getColumns().addAll(isbn,noCopies);
    }
    void setDataInTable(){
        data.clear();
        try{
            costLabel.setText(String.valueOf(UserViewModel.get_instance().getCart().getTotalPurchase()));
        } catch (SQLException throwables) {
            ViewsSwitcher.showAlert(throwables.getMessage());
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            for ( Map.Entry<Book,Integer> entry : UserViewModel.get_instance().getCart().getSelectedBooks().entrySet()){
                data.add(new CartEntry(entry.getKey().getISBN(), entry.getValue()));
            }
        } catch (SQLException throwables) {
            ViewsSwitcher.showAlert(throwables.getMessage());
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
                    setDataInTable();
                } catch (Exception throwables) {
                    ViewsSwitcher.showAlert(throwables.getMessage());
                    throwables.printStackTrace();
                }
            }
        });
        removeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent){
                try {
                    UserViewModel.get_instance().removeFromCart(removeField.getText().trim());
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
                String cardID = cardIdField.getText().trim();
                String date = dateField.getValue().toString();
                System.out.println("DATE : "+date);
                System.out.println(dateField.getValue());
                try {
                    UserViewModel.get_instance().addCreditCard(cardID,date,CurrentUser.getUser().getUser_name(), new IUserCallBack() {
                        @Override
                        public void onSuccess(User user) {
                        }
                        @Override
                        public void onSuccess(Book book) {
                        }
                        @Override
                        public void onSuccess(List<Object> data) {
                        }
                        @Override
                        public void onSuccess() throws SQLException, ClassNotFoundException {
                        }
                        @Override
                        public void onFailure() {
                        }
                    });
                } catch (SQLException throwables) {
                    ViewsSwitcher.showAlert(throwables.getMessage());
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {

                    e.printStackTrace();
                }
                try {
                    UserViewModel.get_instance().writeCart(CurrentUser.getUser().getUser_name(), new IUserCallBack() {
                        @Override
                        public void onSuccess(User user) throws SQLException, ClassNotFoundException {
                        }
                        @Override
                        public void onSuccess(Book book) {
                        }
                        @Override
                        public void onSuccess(List<Object> data) {
                        }
                        @Override
                        public void onSuccess() throws SQLException, ClassNotFoundException {
                            UserViewModel.get_instance().removeCart();
                            cvvField.setText("");
                            cardIdField.setText("");
                            dateField.setValue(null);
                            setDataInTable();
                        }
                        @Override
                        public void onFailure() {
                        }
                    });
                } catch (SQLException | ClassNotFoundException throwables) {
                    ViewsSwitcher.showAlert(throwables.getMessage());
                    throwables.printStackTrace();
                }
            }
        });
    }
    private static class CartEntry{
        StringProperty isbn = new SimpleStringProperty();
        StringProperty copies = new SimpleStringProperty();
        public CartEntry(String isbn, Integer copies){
            this.isbn.setValue(isbn);
            this.copies.setValue(copies.toString());
        }

    }
}
