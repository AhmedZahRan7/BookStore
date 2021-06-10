package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("views/login.fxml")));
        stage.setTitle("BookStore");
        stage.setScene(new Scene(root, 950, 500));
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

}