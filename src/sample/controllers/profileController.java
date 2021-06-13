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
import sample.callBacks.IUserCallBack;
import sample.models.Book;
import sample.models.Checkout;
import sample.models.User;
import sample.viewmodels.UserViewModel;
import sample.views.ViewsSwitcher;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    ObservableList<Checkout> data = FXCollections.observableArrayList(new ArrayList<>());
    public void initialize(){
        initializeTable();
        checkOutsHistoryTable.setItems(data);
        setTableData();
        initializeButtonsFunctions();
    }
    private void initializeTable(){
        TableColumn<Checkout,String> id = new TableColumn<Checkout, String>("Checkout ID");
        TableColumn<Checkout, String> book = new TableColumn<Checkout, String>("Book");
        TableColumn<Checkout, String> date = new TableColumn<Checkout, String>("Date");
        TableColumn<Checkout, String> noOfCopies = new TableColumn<Checkout, String>("No. Of Copies");
        book.setMinWidth(100);
        id.setMinWidth(100);
        date.setMinWidth(100);
        noOfCopies.setMinWidth(100);
        book.setCellValueFactory(new PropertyValueFactory<Checkout,String>("ISBN"));
        id.setCellValueFactory(new PropertyValueFactory<Checkout,String>("Checkout_id"));
        date.setCellValueFactory(new PropertyValueFactory<Checkout,String>("date"));
        noOfCopies.setCellValueFactory(new PropertyValueFactory<Checkout,String>("nocopies"));
        checkOutsHistoryTable.getColumns().addAll(id,book,noOfCopies,date);
    }
    void setTableData(){
        data.clear();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    UserViewModel.get_instance().getCheckouts(CurrentUser.getUser().getUser_name(), new IUserCallBack() {
                        @Override
                        public void onSuccess(User user) {

                        }

                        @Override
                        public void onSuccess(Book book) {

                        }

                        @Override
                        public void onSuccess(List<Object> checkouts) {
                            for(Object o : checkouts) data.add((Checkout) o);
                        }

                        @Override
                        public void onSuccess() throws SQLException, ClassNotFoundException {

                        }

                        @Override
                        public void onFailure() {

                        }
                    });
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
        }).start();
    }
    void setUserDataInFields(){
        firstNameField.setText(CurrentUser.getUser().getFirst_name());
        lastNameField.setText(CurrentUser.getUser().getLast_name());
        userNameField.setText(CurrentUser.getUser().getUser_name());
        userNameField.setEditable(false);
        mailField.setText(CurrentUser.getUser().getEmail());
        addressField.setText(CurrentUser.getUser().getShipping_address());
        passwordField.setText(CurrentUser.getUser().getPassword());
    }
    void initializeButtonsFunctions(){
        setUserDataInFields();
        confirmButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                User user = new User(
                        userNameField.getText().trim(),
                        passwordField.getText(),
                        addressField.getText().trim(),
                        lastNameField.getText().trim(),
                        firstNameField.getText().trim(),
                        mailField.getText().trim(),
                        CurrentUser.getUser().isManager());
                try {
                    UserViewModel.get_instance().updateUser(CurrentUser.getUser().getUser_name(), user, new IUserCallBack() {
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
                            try{
                                UserViewModel.get_instance().getUser(userNameField.getText().trim(), passwordField.getText(), new IUserCallBack() {
                                    @Override
                                    public void onSuccess(User user) {
                                        CurrentUser.setUser(user);
                                        ViewsSwitcher.showSuccess("Edits Confirmed");
                                        setTableData();
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
                            }
                            catch (Exception e){

                            }
                        }

                        @Override
                        public void onFailure() {

                        }
                    });
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
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
