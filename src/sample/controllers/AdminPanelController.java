package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Repository.SearchContract;
import sample.callBacks.IUserCallBack;
import sample.models.*;
import sample.viewmodels.ManagerViewModel;
import sample.views.ViewsSwitcher;

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.*;

public class AdminPanelController {
    @FXML TextField ISBNAddField;
    @FXML TextField titleAddField;
    @FXML TextField authorAddField;
    @FXML TextField publisherAddField;
    @FXML TextField publicationAddField;
    @FXML TextField priceAddField;
    @FXML TextField categoryAddField;
    @FXML TextField ISBNEditField;
    @FXML TextField newValueField;
    @FXML TextField ISBNOrderField;
    @FXML TextField noCopiesOrderField;
    @FXML TextField orderIdConfirmField;
    @FXML TextField userNameToPromoteField;
    @FXML Button addBookButton;
    @FXML Button editBookButton;
    @FXML Button orderButton;
    @FXML Button confirmButton;
    @FXML Button promoteButton;
    @FXML Button totalSalesButton;
    @FXML Button top5CustomersButton;
    @FXML Button top10BooksButton;
    @FXML Button backButton;
    @FXML ChoiceBox<String> fieldToEditField;
    @FXML TableView ordersTable;
    final ObservableList<Order> data = FXCollections.observableArrayList(new ArrayList<>());
    public void initialize(){
        initializeButtonsFunctions();
        initializeFieldToEditFieldChoice();
        initializeOrdersTable();
        ordersTable.setItems(data);
        setDataInTable();
    }
    void initializeButtonsFunctions(){
        backButton.setStyle("-fx-background-color: #FFCA33; ");
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ViewsSwitcher.getInstance().switchTo((Stage) backButton.getScene().getWindow(),"user_interface");
            }
        });
        addBookButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String isbn = ISBNAddField.getText().trim();
                String title = titleAddField.getText().trim();
                String copies = authorAddField.getText().trim();
                String publisher = publisherAddField.getText().trim();
                String publicationTear = publicationAddField.getText().trim();
                String price = priceAddField.getText().trim();
                String cat = categoryAddField.getText().trim();
                String threshold = "10";
                try {
                    HashMap<String,Object> map = new HashMap<>();
                    map.put(SearchContract.ISBN,isbn);
                    map.put(SearchContract.TITLE,title);
                    map.put(SearchContract.PUBLISHER_ID,Integer.parseInt(publisher));
                    map.put(SearchContract.PUBLICATION_YEAR,publicationTear);
                    map.put(SearchContract.PRICE,Float.parseFloat(price));
                    map.put(SearchContract.CATEGORY_ID,Integer.parseInt(cat));
                    map.put(SearchContract.NOCOPIES, Integer.parseInt(copies));
                    map.put(SearchContract.THRESHOLD, Integer.parseInt(threshold));
                    ManagerViewModel.get_instance().addBooks(map,
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
                        public void onSuccess() throws SQLException {

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
        editBookButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String isbn = ISBNEditField.getText().trim();
                String field = fieldToEditField.getValue();
                String newVal = newValueField.getText().trim();
                System.out.println("edit :"+isbn+field+newVal);
            }
        });
        promoteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String userName = userNameToPromoteField.getText().trim();
                try {
                    ManagerViewModel.get_instance().promoteUser(userName, new IUserCallBack() {
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
                        public void onSuccess() throws SQLException {

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
        orderButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String isbn = ISBNOrderField.getText().trim();
                int copies = Integer.parseInt(noCopiesOrderField.getText().trim());
                try {
                    ManagerViewModel.get_instance().orderBook(isbn, copies, new IUserCallBack() {
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
                        public void onSuccess() throws SQLException {

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
        confirmButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String id = orderIdConfirmField.getText().trim();
                try {
                    ManagerViewModel.get_instance().confirmOrder(id, new IUserCallBack() {
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
                        public void onSuccess() throws SQLException {

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
        totalSalesButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Total");
                ViewsSwitcher.getInstance().switchTo((Stage) totalSalesButton.getScene().getWindow(),"last_month_report");
            }
        });
        top5CustomersButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Top 5");
                ViewsSwitcher.getInstance().switchTo((Stage) top5CustomersButton.getScene().getWindow(),"top_5_customers");
            }
        });
        top10BooksButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Top 10");
                ViewsSwitcher.getInstance().switchTo((Stage) top10BooksButton.getScene().getWindow(),"top_10_books");
            }
        });
    }
    void setDataInTable(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ManagerViewModel.get_instance().getOrders(new IUserCallBack() {
                        @Override
                        public void onSuccess(User user) {

                        }

                        @Override
                        public void onSuccess(Book book) {

                        }

                        @Override
                        public void onSuccess(List<Object> orders) {
                            for (Object o : orders) data.add((Order) o);
                        }

                        @Override
                        public void onSuccess() throws SQLException {

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
    void initializeOrdersTable(){
        TableColumn<Order,String> isbn = new TableColumn<Order, String>("ISBN");
        TableColumn<Order, String> noCopies = new TableColumn<Order, String>("#Copies");
        TableColumn<Order, String> orderId = new TableColumn<Order, String>("Order ID");
        isbn.setMinWidth(120);
        noCopies.setMinWidth(30);
        orderId.setMinWidth(30);
        isbn.setCellValueFactory(new PropertyValueFactory<Order,String>("ISBN"));
        noCopies.setCellValueFactory(new PropertyValueFactory<Order,String>("noCopies"));
        orderId.setCellValueFactory(new PropertyValueFactory<Order,String>("orders_id"));
        ordersTable.getColumns().addAll(orderId,isbn,noCopies);
    }
    void initializeFieldToEditFieldChoice(){
        fieldToEditField.getItems().addAll(
                "ISBN",
                "price",
                "title",
                "noCopies",
                "threshold",
                "category",
                "publisherID",
                "publicationYear"
                );
    }
}
