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
    @FXML TableView publishersTable;
    ObservableList<Publisher> data = FXCollections.observableArrayList(new ArrayList<>());
    public void initialize(){
        initializeTable();
        setTableData();
        publishersTable.setItems(data);
        initializeButtonsFunctions();
    }
    private void initializeTable(){
        TableColumn<Publisher,String> id = new TableColumn<>();
        TableColumn<Publisher,String> name = new TableColumn<>();
        TableColumn<Publisher,String> address = new TableColumn<>();
        TableColumn<Publisher,String> phone = new TableColumn<>();
        id.setMinWidth(100);
        name.setMinWidth(100);
        address.setMinWidth(100);
        phone.setMinWidth(100);
        id.setCellValueFactory(new PropertyValueFactory<Publisher,String>("publisher_id"));
        name.setCellValueFactory(new PropertyValueFactory<Publisher,String>("publisher_name"));
        address.setCellValueFactory(new PropertyValueFactory<Publisher,String>("Address"));
        publishersTable.getColumns().addAll(id,name,address);
//        phone.setCellValueFactory(new PropertyValueFactory<Publisher,String>("publisher_id"));
    }
    void setTableData(){
        data.clear();
        new Thread(new Runnable() {
            @Override
            public void run() {
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
                            for(Object o : publishers) data.add((Publisher) o);
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
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    void initializeButtonsFunctions(){
        backButton.setStyle("-fx-background-color: #FFCA33;");
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ViewsSwitcher.getInstance().switchTo((Stage)
                        backButton.getScene().getWindow(),"admin");

            }
        });
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            //phone not handled
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    ManagerViewModel.get_instance().addPublisher(
                            publisherAddressField.getText().trim(),
                            publisherNameField.getText().trim(),
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
                                public void onSuccess() throws SQLException, ClassNotFoundException {
                                    publisherAddressField.setText("");
                                    publisherNameField.setText("");
                                    publisherPhoneField.setText("");
                                    ViewsSwitcher.showSuccess("Added");
                                    setTableData();
                                }

                                @Override
                                public void onFailure() {

                                }
                            }
                    );
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
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
                        public void onSuccess() throws SQLException, ClassNotFoundException {
                            ViewsSwitcher.showSuccess("Deleted");
                            publisherIdField.setText("");
                            setTableData();
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
    }

}
