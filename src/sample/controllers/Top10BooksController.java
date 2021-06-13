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
import sample.Repository.ManagerRepo;
import sample.callBacks.IUserCallBack;
import sample.models.Book;
import sample.models.Cart;
import sample.models.User;
import sample.viewmodels.ManagerViewModel;
import sample.views.ViewsSwitcher;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Top10BooksController {
    @FXML TableView topBooks;
    @FXML
    Button backButton;
    ObservableList<Book> data = FXCollections.observableArrayList(new ArrayList<>());
    public void initialize(){
        initializeTable();
        topBooks.setItems(data);
        setTableData();
        backButton.setStyle("-fx-background-color: #FFCA33; ");
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ViewsSwitcher.getInstance().switchTo((Stage) backButton.getScene().getWindow(),"admin");
            }
        });
    }
    void initializeTable(){
        TableColumn<Book, String> user = new TableColumn<Book, String>("ISBN");
        TableColumn<Book, String> amount = new TableColumn<Book, String>("noCopies");
        user.setMinWidth(300);
        amount.setMinWidth(200);
        user.setCellValueFactory(new PropertyValueFactory<Book,String>("ISBN"));
        amount.setCellValueFactory(new PropertyValueFactory<Book,String>("noCopies"));
        topBooks.getColumns().addAll(user,amount);
    }

    void setTableData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    ManagerViewModel.get_instance().getTopTenBooks(new IUserCallBack() {
                        @Override
                        public void onSuccess(User user) {

                        }

                        @Override
                        public void onSuccess(Book book) {

                        }

                        @Override
                        public void onSuccess(List<Object> books) {
                            for( Object b: books) data.add((Book)b);
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
