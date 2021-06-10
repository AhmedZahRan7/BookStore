package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.models.Order;
import sample.views.ViewsSwitcher;

import java.util.ArrayList;

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
    public void initialize(){
        initializeButtonsFunctions();
        initializeFieldToEditFieldChoice();
        initializeOrdersTable();
        setDataInTable(getCurrentOrders());
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
                String author = authorAddField.getText().trim();
                String publisher = publisherAddField.getText().trim();
                String publicationTear = publicationAddField.getText().trim();
                String price = priceAddField.getText().trim();
                String cat = categoryAddField.getText().trim();

                System.out.println("add :"+isbn+title+author+publisher+publicationTear+price+cat);
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
                System.out.println("Promote :"+userName);
            }
        });
        orderButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String isbn = ISBNOrderField.getText().trim();
                int copies = Integer.parseInt(noCopiesOrderField.getText().trim());
                System.out.println("order :"+isbn+copies);
            }
        });
        confirmButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String id = orderIdConfirmField.getText().trim();
                System.out.println("confirm :"+id);
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
    void setDataInTable(ArrayList<Order> orders){
        final ObservableList<Order> data = FXCollections.observableArrayList(orders);
        ordersTable.setItems(data);
    }
    ArrayList<Order> getCurrentOrders(){
        //get data of books in the cart
        ArrayList<Order> books = new ArrayList<>();
        books.add(new Order(4,"13321455414", 20));
        books.add(new Order(5,"12365475521",40));
        return books;
    }
    void initializeOrdersTable(){
        TableColumn<Order,String> isbn = new TableColumn<Order, String>("ISBN");
        TableColumn<Order, String> noCopies = new TableColumn<Order, String>("#Copies");
        TableColumn<Order, String> orderId = new TableColumn<Order, String>("Order ID");
        isbn.setMinWidth(150);
        noCopies.setMinWidth(30);
        orderId.setMinWidth(30);
        isbn.setCellValueFactory(new PropertyValueFactory<Order,String>("ISBN"));
        noCopies.setCellValueFactory(new PropertyValueFactory<Order,String>("noOfCopies"));
        orderId.setCellValueFactory(new PropertyValueFactory<Order,String>("orderID"));
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
