package sample.controllers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Repository.SearchContract;
import sample.callBacks.IUserCallBack;
import sample.models.Book;
import sample.models.User;
import sample.viewmodels.UserViewModel;
import sample.views.ViewsSwitcher;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.*;

public class UserInterfaceController {
    @FXML Button profileButton;
    @FXML Button cartButton;
    @FXML Button logoutButton;
    @FXML Button adminButton;
    @FXML Button searchButton;
    @FXML TextField searchTextField;
    @FXML TableView tableView;
    final ObservableList<Book> books = FXCollections.observableArrayList(new ArrayList<>());
    public void initialize(){
        tableView.setItems(books);
        initializeButtonsFunctions();
        initializeBooksTable();
        try {
            setDataInTableFromDataBase();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    void initializeBooksTable(){
        TableColumn<Book, String> isbn = new TableColumn<Book, String>("ISBN");
        TableColumn<Book, String> title = new TableColumn<>("Title");
        TableColumn<Book, Integer> noCopies = new TableColumn<Book, Integer>("No. Copies");
        TableColumn<Book, Float> price = new TableColumn<Book, Float>("Price");
        TableColumn<Book, String> publisher = new TableColumn<Book, String>("Publisher");
        TableColumn<Book, String> category = new TableColumn<Book, String>("Category");
        TableColumn<Book, String> date = new TableColumn<Book, String>("publicationYear");
        isbn.setMinWidth(150);
        title.setMinWidth(100);
        noCopies.setMinWidth(40);
        price.setMinWidth(40);
        publisher.setMinWidth(40);
        category.setMinWidth(40);
        date.setMinWidth(100);
        isbn.setCellValueFactory(new PropertyValueFactory<Book,String>("ISBN"));
        title.setCellValueFactory(new PropertyValueFactory<Book,String>(SearchContract.TITLE));
        noCopies.setCellValueFactory(new PropertyValueFactory<Book,Integer>("noCopies"));
        price.setCellValueFactory(new PropertyValueFactory<Book, Float>(SearchContract.PRICE));
        publisher.setCellValueFactory(tf->tf.getValue().getPublisher().getPublisherNameProb());
        category.setCellValueFactory(tf->tf.getValue().getCatagory().getGategoryProp());
        date.setCellValueFactory(new PropertyValueFactory<Book,String>(SearchContract.PUBLICATION_YEAR));
        tableView.getColumns().addAll(isbn,title,noCopies,price,publisher,category,date);
    }
    void setDataInTableFromDataBase() throws SQLException, ClassNotFoundException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    UserViewModel.get_instance().getBooks(null, new IUserCallBack() {
                        @Override
                        public void onSuccess(User user) {

                        }

                        @Override
                        public void onSuccess(Book book) {

                        }

                        @Override
                        public void onSuccess(List<Object> data) {
                            for(Object o : data){
                                books.add((Book) o);
                            }
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
        }).start();
    }
    void initializeButtonsFunctions(){
//        if(CurrentUser.getUser().isManager() == 0) adminButton.setVisible(false);
        adminButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Admin");
                ViewsSwitcher.getInstance().switchTo((Stage) adminButton.getScene().getWindow(),"admin");
            }
        });
        profileButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("profile");
                ViewsSwitcher.getInstance().switchTo((Stage) cartButton.getScene().getWindow(),"profile");
            }
        });
        cartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("cart");
                ViewsSwitcher.getInstance().switchTo((Stage) cartButton.getScene().getWindow(),"cart");
            }
        });
        logoutButton.setStyle("-fx-background-color: #FF3361;");
        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("logout");
                ViewsSwitcher.getInstance().switchTo((Stage) logoutButton.getScene().getWindow(),"login");
            }
        });
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String searchFor = searchTextField.getText().trim();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Map<String,Object> map = new HashMap<>();
                        map.put(SearchContract.ISBN,searchFor);
                        map.put(SearchContract.TITLE,searchFor);
                        map.put(SearchContract.PUBLISHER_NAME,searchFor);
                        if (searchFor.isEmpty()) map = null;
                        try {
                            UserViewModel.get_instance().getBooks(map, new IUserCallBack() {
                                @Override
                                public void onSuccess(User user) {

                                }

                                @Override
                                public void onSuccess(Book book) {

                                }

                                @Override
                                public void onSuccess(List<Object> data) {
                                    books.clear();
                                    for(Object o : data){
                                        books.add((Book) o);
                                    }
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
                }).start();
            }
        });
    }
}
