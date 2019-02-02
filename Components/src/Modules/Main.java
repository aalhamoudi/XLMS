package Modules;

import Toolbar.Toolbar;
import Toolbar.ToolbarContent;
import Toolbar.ToolbarTab;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.HBox;

public class Main extends SplitPane {

    public Main()
    {
        ToolbarTab tab = new ToolbarTab("Home");
        ToolbarContent content = new ToolbarContent(tab);
        content.getChildren().addAll(new HBox(new Button("Test 1"), new Button("Test 2"), new Button(("Test 3"))));
        tab.setContent(content);
        getChildren().add(new Toolbar(tab));


    }
}
