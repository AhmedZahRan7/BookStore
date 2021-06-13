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
import sample.viewmodels.UserViewModel;
import sample.views.ViewsSwitcher;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.*;

public class AdminPanelController {
    @FXML TextField ISBNAddField;
    @FXML TextField titleAddField;
    @FXML TextField noCopiesField;
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
    @FXML TextField thresholdField;
    @FXML TextField authorsNamesField;
    @FXML TextField removeBookField;
    @FXML Button removeBookButton;
    @FXML Button addBookButton;
    @FXML Button editBookButton;
    @FXML Button orderButton;
    @FXML Button confirmButton;
    @FXML Button promoteButton;
    @FXML Button totalSalesButton;
    @FXML Button top5CustomersButton;
    @FXML Button top10BooksButton;
    @FXML Button backButton;
    @FXML Button viewPublisherButton;
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
                String copies = noCopiesField.getText().trim();
                String publisher = publisherAddField.getText().trim();
                String publicationTear = publicationAddField.getText().trim();
                String price = priceAddField.getText().trim();
                String cat = categoryAddField.getText().trim();
                String threshold = thresholdField.getText().trim();
                List<String> authors = Arrays.asList(authorsNamesField.getText().split(","));

                if(
                        isbn.length()!=13 ||
                                title.isEmpty() ||
                                copies.isEmpty() ||
                                publisher.isEmpty() ||
                                publicationTear.isEmpty() ||
                                price.isEmpty() ||
                                cat.isEmpty() ||
                                threshold.isEmpty() ||
                                authors.isEmpty()
                ) {
                    ViewsSwitcher.showAlert("No field can be empty");
                    return;
                }
                try {
                    HashMap<String,Object> map = new HashMap<>();
                    map.put(SearchContract.ISBN,isbn);
                    map.put(SearchContract.TITLE,title);
                    map.put(SearchContract.PUBLISHER_NAME,publisher);
                    map.put(SearchContract.PUBLICATION_YEAR,publicationTear);
                    map.put(SearchContract.PRICE,Float.parseFloat(price));
                    map.put(SearchContract.CATEGORY,cat);
                    map.put(SearchContract.NOCOPIES, Integer.parseInt(copies));
                    map.put(SearchContract.THRESHOLD, Integer.parseInt(threshold));
                    map.put(SearchContract.LIST_OF_AUTHORS,authors);
                    ManagerViewModel.get_instance().addBook(map,
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
                            ViewsSwitcher.showSuccess("Added");
                            ISBNAddField.setText("");
                            titleAddField.setText("");
                            noCopiesField.setText("");
                            publisherAddField.setText("");
                            publicationAddField.setText("");
                            priceAddField.setText("");
                            categoryAddField.setText("");
                            thresholdField.setText("");
                            authorsNamesField.setText("");
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
        removeBookButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                HashMap<String,Object> map = new HashMap<>();
                map.put(SearchContract.ISBN,removeBookField.getText().trim());
                try {
                    ManagerViewModel.get_instance().removeBook(map, new IUserCallBack() {
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
                            removeBookField.setText("");
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
                try {
                    HashMap<String,Object> mapEdit = new HashMap<>();
                    HashMap<String,Object> mapWhere = new HashMap<>();
                    mapEdit.put(fieldToEditField.getValue(),newValueField.getText());
                    mapWhere.put(SearchContract.ISBN,ISBNEditField.getText());
                    ManagerViewModel.get_instance().modifyBook(mapEdit, mapWhere, new IUserCallBack() {
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
                            ViewsSwitcher.showSuccess("Modified");
                            ISBNEditField.setText("");
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
                            ViewsSwitcher.showSuccess("Promoted");
                            userNameToPromoteField.setText("");
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
                            ViewsSwitcher.showSuccess("Order Added");
                            noCopiesOrderField.setText("");
                            ISBNOrderField.setText("");
                            setDataInTable();
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
                    ManagerViewModel.get_instance().confirmOrder(Integer.parseInt(id), new IUserCallBack() {
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
                            ViewsSwitcher.showSuccess("Order Confirmed");
                            orderIdConfirmField.setText("");
                            setDataInTable();
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
        viewPublisherButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ViewsSwitcher.getInstance().switchTo((Stage) viewPublisherButton.getScene().getWindow(),"publishers");
            }
        });
    }
    void setDataInTable(){
        data.clear();
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
        noCopies.setCellValueFactory(new PropertyValueFactory<Order,String>(SearchContract.NOCOPIES));
        orderId.setCellValueFactory(new PropertyValueFactory<Order,String>("orders_id"));
        ordersTable.getColumns().addAll(orderId,isbn,noCopies);
    }
    void initializeFieldToEditFieldChoice(){
        fieldToEditField.getItems().addAll(
                SearchContract.ISBN,
                SearchContract.PRICE,
                SearchContract.TITLE,
                SearchContract.NOCOPIES,
                SearchContract.THRESHOLD,
                SearchContract.CATEGORY,
                SearchContract.PUBLISHER_NAME,
                SearchContract.PUBLICATION_YEAR
                );
    }
}
