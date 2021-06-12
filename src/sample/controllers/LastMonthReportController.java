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
import sample.callBacks.IUserCallBack;
import sample.models.Book;
import sample.models.User;
import sample.viewmodels.ManagerViewModel;
import sample.views.ViewsSwitcher;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LastMonthReportController {
    @FXML TableView monthlyTable;
    @FXML
    Button backButton;
    ObservableList<Book> data = FXCollections.observableArrayList(new ArrayList<>());
    public void initialize(){
        initializeTable();
        monthlyTable.setItems(data);
        getLastMonthCheckouts();
        backButton.setStyle("-fx-background-color: #FFCA33; ");
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ViewsSwitcher.getInstance().switchTo((Stage) backButton.getScene().getWindow(),"admin");
            }
        });
    }
    void initializeTable(){
        TableColumn<Book, String> book = new TableColumn<Book, String>("Book");
        TableColumn<Book, String> noOfCopies = new TableColumn<Book, String>("Number Of Copies");
        book.setMinWidth(150);
        noOfCopies.setMinWidth(150);
        book.setCellValueFactory(new PropertyValueFactory<Book,String>("ISBN"));
        noOfCopies.setCellValueFactory(new PropertyValueFactory<Book,String>("noCopies"));
        monthlyTable.getColumns().addAll(book,noOfCopies);
    }

    void getLastMonthCheckouts(){
       new Thread(new Runnable() {
           @Override
           public void run() {
               try{
                   ManagerViewModel.get_instance().getSalesLastMonth(new IUserCallBack() {
                       @Override
                       public void onSuccess(User user) {

                       }

                       @Override
                       public void onSuccess(Book book) {

                       }

                       @Override
                       public void onSuccess(List<Object> books) {
                           for (Object o : books) data.add((Book) o);
                       }

                       @Override
                       public void onSuccess() throws SQLException {

                       }

                       @Override
                       public void onFailure() {

                       }
                   });
               }
               catch (Exception e){
               }
           }
       }).start();
    }
}
