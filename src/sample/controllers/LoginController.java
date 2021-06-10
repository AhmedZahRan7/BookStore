package sample.controllers;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.views.ViewsSwitcher;

import java.util.Objects;

public class LoginController {
    @FXML TextField userNameLogInField;
    @FXML TextField passwordLogInField;
    @FXML TextField firstNameField;
    @FXML TextField lastNameField;
    @FXML TextField userNameField;
    @FXML TextField mailField;
    @FXML TextField addressField;
    @FXML TextField passwordField;
    @FXML TextField repasswordField;
    @FXML Button signupButton;
    @FXML Button logInButton;
    public void initialize(){
        initializeButtonsFunctions();
    }
    void initializeButtonsFunctions(){
        logInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String user = userNameLogInField.getText().trim();
                String pass = passwordLogInField.getText().trim();
                System.out.println("LogIN :");
                System.out.println(user);
                System.out.println(pass);
                ViewsSwitcher.getInstance().switchTo((Stage) logInButton.getScene().getWindow(),"user_interface");
            }
        });
        signupButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String first = firstNameField.getText().trim();
                String last = lastNameField.getText().trim();
                String email = mailField.getText().trim();
                String address = addressField.getText().trim();
                String user = userNameField.getText().trim();
                String pass = passwordField.getText().trim();
                String repass = repasswordField.getText().trim();
                ViewsSwitcher.getInstance().switchTo((Stage) signupButton.getScene().getWindow(),"user_interface");
                System.out.println("Signup :");
                System.out.println(first);
                System.out.println(last);
                System.out.println(email);
                System.out.println(address);
                System.out.println(user);
                System.out.println(pass);
                System.out.println(repass);
            }
        });
    }
}
