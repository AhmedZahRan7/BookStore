package sample.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Repository.SearchContract;
import sample.callBacks.IUserCallBack;
import sample.models.Book;
import sample.models.User;
import sample.viewmodels.UserViewModel;
import sample.views.ViewsSwitcher;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

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
                } catch (Exception e) {
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
                try {
                    HashMap<String,Object> userInformation = new HashMap<>();
                    userInformation.put(SearchContract.USER_NAME,user);
                    userInformation.put(SearchContract.FIRST_NAME,first);
                    userInformation.put(SearchContract.LAST_NAME,last);
                    userInformation.put(SearchContract.EMAIL,email);
                    userInformation.put(SearchContract.SHIPPING_ADDRESS,address);
                    userInformation.put(SearchContract.PASSWORD,pass);
                    UserViewModel.get_instance().addUser(userInformation, new IUserCallBack() {
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
                            try {
                                UserViewModel.get_instance().getUser(user, pass, new IUserCallBack() {
                                    @Override
                                    public void onSuccess(User user) {
                                        if(user == null) {
                                            ViewsSwitcher.showAlert("User not found");
                                        }
                                        else {
                                            CurrentUser.setUser(user);
                                            ViewsSwitcher.getInstance().switchTo((Stage) signupButton.getScene().getWindow(),"user_interface");
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
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure() {

                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
