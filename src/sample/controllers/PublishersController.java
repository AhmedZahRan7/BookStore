package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.callBacks.IUserCallBack;
import sample.models.Author;
import sample.models.Book;
import sample.models.Publisher;
import sample.models.User;
import sample.viewmodels.ManagerViewModel;
import sample.views.ViewsSwitcher;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PublishersController {
    @FXML TextField publisherNameField;
    @FXML TextField publisherAddressField;
    @FXML TextField publisherPhoneField;
    @FXML TextField publisherIdField;
    @FXML Button addButton;
    @FXML Button backButton;
    @FXML Button deleteButton;
    @FXML TextField deleteAuthorNameField;
    @FXML TextField addAuthorNameField;
    @FXML Button addAuthorButton;
    @FXML Button deleteAuthorButton;
    @FXML TableView<Publisher> publishersTable;
    @FXML TableView<Author> authorsTable;
    ObservableList<Publisher> publishersData = FXCollections.observableArrayList(new ArrayList<>());
    ObservableList<Author> authorsData = FXCollections.observableArrayList(new ArrayList<>());
    public void initialize(){
        initializePublishersTable();
        setPublishersTableData();
        publishersTable.setItems(publishersData);
        initializeAuthorTable();
        setAuthorsTableData();
        authorsTable.setItems(authorsData);
        initializeButtonsFunctions();
    }
    private void initializePublishersTable(){
        TableColumn<Publisher,String> id = new TableColumn<>("ID");
        TableColumn<Publisher,String> name = new TableColumn<>("Name");
        TableColumn<Publisher,String> address = new TableColumn<>("Address");
        TableColumn<Publisher,String> phone = new TableColumn<>("Phone");
        id.setMinWidth(20);
        name.setMinWidth(100);
        address.setMinWidth(100);
        phone.setMinWidth(100);
        id.setCellValueFactory(new PropertyValueFactory<>("publisher_id"));
        name.setCellValueFactory(new PropertyValueFactory<>("publisher_name"));
        address.setCellValueFactory(new PropertyValueFactory<>("Address"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        publishersTable.getColumns().addAll(id,name,address,phone);
    }
    private void setPublishersTableData(){
        publishersData.clear();
        new Thread(() -> {
            try {
                ManagerViewModel.get_instance().getAllPublishers(new IUserCallBack() {
                    @Override
                    public void onSuccess(User user) {

                    }

                    @Override
                    public void onSuccess(Book book) {

                    }

                    @Override
                    public void onSuccess(List<Object> publishers) {
                        for(Object o : publishers) publishersData.add((Publisher) o);
                    }

                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onFailure() {

                    }
                });
            } catch (SQLException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException | ClassNotFoundException throwable) {
                throwable.printStackTrace();
            }
        }).start();
    }
    private void initializeAuthorTable(){
        TableColumn<Author,String> id = new TableColumn<>("ID");
        TableColumn<Author,String> name = new TableColumn<>("Name");
        id.setMinWidth(20);
        name.setMinWidth(100);
        id.setCellValueFactory(new PropertyValueFactory<>("author_id"));
        name.setCellValueFactory(new PropertyValueFactory<>("author_Name"));
        authorsTable.getColumns().addAll(id,name);
    }
    private void setAuthorsTableData(){
        authorsData.clear();
        new Thread(() -> {
            try {
                ManagerViewModel.get_instance().getAllAuthors(new IUserCallBack() {
                    @Override
                    public void onSuccess(User user) {

                    }

                    @Override
                    public void onSuccess(Book book) {

                    }

                    @Override
                    public void onSuccess(List<Object> authors) {
                        for(Object o : authors) authorsData.add((Author) o);
                    }

                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onFailure() {

                    }
                });
            } catch (SQLException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException | ClassNotFoundException throwable) {
                throwable.printStackTrace();
            }
        }).start();
    }
    void initializeButtonsFunctions(){
        backButton.setStyle("-fx-background-color: #FFCA33;");
        backButton.setOnAction(actionEvent -> ViewsSwitcher.getInstance().switchTo((Stage)
                backButton.getScene().getWindow(),"admin"));
        addButton.setOnAction(actionEvent -> {
            try {
                ManagerViewModel.get_instance().addPublisher(
                        publisherAddressField.getText().trim(),
                        publisherNameField.getText().trim(),
                        publisherPhoneField.getText().trim(),
                        new IUserCallBack() {
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
                            public void onSuccess() {
                                publisherAddressField.setText("");
                                publisherNameField.setText("");
                                publisherPhoneField.setText("");
                                ViewsSwitcher.showSuccess("Added");
                                setPublishersTableData();
                            }

                            @Override
                            public void onFailure() {

                            }
                        }
                );
            } catch (SQLException | ClassNotFoundException throwable) {
                throwable.printStackTrace();
            }
        });
        deleteButton.setOnAction(actionEvent -> {
            try {
                ManagerViewModel.get_instance().removePublisher(publisherIdField.getText().trim(), new IUserCallBack() {
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
                    public void onSuccess() {
                        ViewsSwitcher.showSuccess("Deleted");
                        publisherIdField.setText("");
                        setPublishersTableData();
                    }

                    @Override
                    public void onFailure() {

                    }
                });
            } catch (SQLException | ClassNotFoundException throwable) {
                throwable.printStackTrace();
            }
        });
        addAuthorButton.setOnAction(actionEvent -> {
            try {
                ManagerViewModel.get_instance().addAuthor(addAuthorNameField.getText().trim());
                setAuthorsTableData();
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        });
        deleteAuthorButton.setOnAction(actionEvent -> {
            try {
                ManagerViewModel.get_instance().removeAuthor(Integer.parseInt(deleteAuthorNameField.getText().trim()), new IUserCallBack() {
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
                        setAuthorsTableData();
                    }
                    @Override
                    public void onFailure() {
                    }
                });
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        });

    }

}