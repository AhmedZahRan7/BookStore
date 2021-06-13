package sample.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.util.Objects;

public class ViewsSwitcher {
    private static final ViewsSwitcher singleton = new ViewsSwitcher();
    public static ViewsSwitcher getInstance(){
        return singleton;
    }
    public void switchTo(Stage currentStage, String newView){
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(Objects.requireNonNull(getClass().getResource(newView+".fxml"))));
            currentStage.setScene(new Scene(root,950,500));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void showAlert(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(message);
        alert.showAndWait();
    }
    public static void showSuccess(String message){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Done");
        alert.setHeaderText(message);
        alert.showAndWait();
    }
}
