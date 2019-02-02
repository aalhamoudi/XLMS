package Application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Modules.Pharmacokinetics.Pharmacokinetics;
import Navigation.*;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
        loader.setController(this);
        Parent root = loader.load();

        //Tray tray = new Tray(stage);
        /*stage.setMaximized(true);*/

        stage.setTitle("XLMS");
        stage.setScene(new Scene(root));
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
