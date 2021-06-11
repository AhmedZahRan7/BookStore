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
import sample.callBacks.IUserCallBack;
import sample.models.Book;
import sample.models.User;
import sample.viewmodels.UserViewModel;
import sample.views.ViewsSwitcher;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
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
                try {
                    UserViewModel.get_instance().getUser(user, pass, new IUserCallBack() {
                        @Override
                        public void onSuccess(User user) {
                            if(user == null) {
                                ViewsSwitcher.showAlert("User not found");
                            }
                            else {
                                CurrentUser.setUser(user);
                                ViewsSwitcher.getInstance().switchTo((Stage) logInButton.getScene().getWindow(),"user_interface");
                            }
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
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

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

                if(
                        first.length() == 0 ||
                        last.length() == 0 ||
                        email.length() == 0 ||
                        address.length() == 0 ||
                        user.length() == 0 ||
                        pass.length() == 0 ||
                        repass.length() == 0 ||
                        !repass.equals(pass)
                ){
                    ViewsSwitcher.showAlert("Error in values");
                }
//                UserViewModel.get_instance().addUser(new User(user,pass,"",address,last,first,email));
                ViewsSwitcher.getInstance().switchTo((Stage) signupButton.getScene().getWindow(),"user_interface");

                System.out.println("Signup :");
            }
        });
    }
}
