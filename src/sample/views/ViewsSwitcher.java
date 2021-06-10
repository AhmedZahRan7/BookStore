package sample.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
}
