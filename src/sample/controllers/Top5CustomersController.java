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
import sample.models.User;
import sample.viewmodels.ManagerViewModel;
import sample.views.ViewsSwitcher;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Top5CustomersController {
    @FXML TableView customersTable;
    @FXML
    Button backButton;

    public void initialize(){
        initializeTable();
//        setTableData(getTopCustomers());
        backButton.setStyle("-fx-background-color: #FFCA33; ");
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ViewsSwitcher.getInstance().switchTo((Stage) backButton.getScene().getWindow(),"admin");
            }
        });
    }
    void initializeTable(){
        TableColumn<User, String> user = new TableColumn<User, String>("User");
        TableColumn<User, String> amount = new TableColumn<User, String>("Amount");
        user.setMinWidth(300);
        amount.setMinWidth(200);
        user.setCellValueFactory(new PropertyValueFactory<User,String>("user_name"));
        amount.setCellValueFactory(new PropertyValueFactory<User,String>("total_purchases"));
        customersTable.getColumns().addAll(user,amount);
    }

    void setTableData(List<User> purchases){
        ObservableList<User> data = FXCollections.observableArrayList(purchases);
        customersTable.setItems(data);
    }
    void getTopCustomers(){
        try{
            ManagerViewModel.get_instance().getTop5CustomersLastThreeMonths(new IUserCallBack() {
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
        }
        catch (Exception e){
        }
    }
}
