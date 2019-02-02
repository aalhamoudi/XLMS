package PaneControls;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class PaneControls extends HBox {


    public PaneControls()
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PanelControls.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        }
        catch(IOException e)
        {
            System.out.println("File Could not be found!");

        }


    }
}
