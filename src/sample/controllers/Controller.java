package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import javax.swing.text.html.ListView;

public class Controller {
    @FXML private VBox leftBox;
    @FXML private ListView listView;

    public void initialize(){
        leftBox.maxHeight(10000);
        leftBox.getChildren().add(new Button("test1"));
        leftBox.getChildren().add(new Button("test2"));

    }
}
