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
import sample.models.User;
import sample.models.UserPurchase;
import sample.views.ViewsSwitcher;

import java.util.ArrayList;

public class Top5CustomersController {
    @FXML TableView customersTable;
    @FXML
    Button backButton;

    public void initialize(){
        initializeTable();
        setTableData(getTopCustomers());
        backButton.setStyle("-fx-background-color: #FFCA33; ");
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ViewsSwitcher.getInstance().switchTo((Stage) backButton.getScene().getWindow(),"admin");
            }
        });
    }
    void initializeTable(){
        TableColumn<UserPurchase, String> user = new TableColumn<UserPurchase, String>("User");
        TableColumn<UserPurchase, String> amount = new TableColumn<UserPurchase, String>("Amount");
        user.setMinWidth(300);
        amount.setMinWidth(200);
        user.setCellValueFactory(tf->tf.getValue().getUser().getUserProperty());
        amount.setCellValueFactory(new PropertyValueFactory<UserPurchase,String>("amount"));
        customersTable.getColumns().addAll(user,amount);
    }

    void setTableData(ArrayList<UserPurchase> purchases){
        ObservableList<UserPurchase> data = FXCollections.observableArrayList(purchases);
        customersTable.setItems(data);
    }
    ArrayList<UserPurchase> getTopCustomers(){
        ArrayList<UserPurchase> list = new ArrayList<>();
        list.add(new UserPurchase(
                        new User("name","12345","12354","5 Famous st","Ali","Ahmed","mail@mail",false),
                        20.0
                )
        );
        return list;
    }
}
